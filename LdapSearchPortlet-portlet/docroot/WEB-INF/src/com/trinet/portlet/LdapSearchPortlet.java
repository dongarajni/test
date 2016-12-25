package com.trinet.portlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.omg.IOP.ProfileIdHelper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.trinet.model.UserDetails;
import com.trinet.model.model.Profile;
import com.trinet.model.model.impl.ProfileImpl;
import com.trinet.model.service.ProfileLocalServiceUtil;

public class LdapSearchPortlet extends MVCPortlet {

	private static final Log LOGGER = LogFactoryUtil
			.getLog(LdapSearchPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		
		
		
		String email = ParamUtil.getString(renderRequest, "email");
		
		if (null != email && email != "") {
			try {
				System.out.println("Profile Object : "
						+ ProfileLocalServiceUtil.fetchProfile(email));
				Profile profileObject =  ProfileLocalServiceUtil.fetchProfile(email);
				renderRequest.setAttribute("profileObject", profileObject);
				String image = ParamUtil.getString(renderRequest, "image");
				renderRequest.setAttribute("image", image);
			} catch (SystemException e) {
				e.printStackTrace();
			}
			viewTemplate = "/jsp/user_Profile.jsp";
		}
		
		renderRequest.setAttribute("userDetailList",
				renderRequest.getAttribute("userDetailList"));
		renderRequest.setAttribute("firstName",
				renderRequest.getAttribute("firstName"));
		renderRequest.setAttribute("lastName",
				renderRequest.getAttribute("lastName"));
		renderRequest.setAttribute("location",
				renderRequest.getAttribute("location"));
		renderRequest.setAttribute("department",
				renderRequest.getAttribute("department"));
		renderRequest
				.setAttribute("email", renderRequest.getAttribute("email"));
		renderRequest.setAttribute("advanceChecked",
				renderRequest.getAttribute("advanceChecked"));
		renderRequest.setAttribute("simpleChecked",
				renderRequest.getAttribute("simpleChecked"));
		renderRequest.setAttribute("keywords",
				renderRequest.getAttribute("keywords"));
		super.doView(renderRequest, renderResponse);
	}

	private List<LdapContext> getLdapContexts(ThemeDisplay td) {

		List<LdapContext> ctx = new ArrayList<LdapContext>();
		long companyId = td.getCompanyId();
		try {
			long[] ldapServerIds = StringUtil.split(
					PrefsPropsUtil.getString(companyId, "ldap.server.ids"), 0L);
			for (long ldapServerId : ldapServerIds) {

				String postfix = StringPool.PERIOD + ldapServerId;

				String baseProviderURL = PrefsPropsUtil.getString(companyId,
						PropsKeys.LDAP_BASE_PROVIDER_URL + postfix);
				String pricipal = PrefsPropsUtil.getString(companyId,
						PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix);
				String credentials = PrefsPropsUtil.getString(companyId,
						PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix);

				LOGGER.info("Ldap Server Id :" + ldapServerId);
				LOGGER.info("postfix :" + postfix);
				LOGGER.info("Provider URL :" + baseProviderURL);
				LOGGER.info("Principal :" + pricipal);
				LOGGER.info("credentials :" + credentials);

				Properties environmentProperties = new Properties();

				environmentProperties.put(Context.INITIAL_CONTEXT_FACTORY,
						PrefsPropsUtil.getString(companyId,
								PropsKeys.LDAP_FACTORY_INITIAL));
				environmentProperties
						.put(Context.PROVIDER_URL, baseProviderURL);
				environmentProperties.put(Context.SECURITY_PRINCIPAL, pricipal);
				environmentProperties.put(Context.SECURITY_CREDENTIALS,
						credentials);
				environmentProperties.put(Context.REFERRAL, PrefsPropsUtil
						.getString(companyId, PropsKeys.LDAP_REFERRAL));

				Properties ldapConnectionProperties = PropsUtil.getProperties(
						PropsKeys.LDAP_CONNECTION_PROPERTY_PREFIX, true);

				PropertiesUtil.merge(environmentProperties,
						ldapConnectionProperties);

				LdapContext ldapContext = null;

				ldapContext = new InitialLdapContext(environmentProperties,
						null);
				LOGGER.info("Connection Successful.");

				ctx.add(ldapContext);
			}
			LOGGER.info("Ldap Context Size : " + ctx.size());
			/*
			 * Hashtable<String, String> env = new Hashtable<String, String>();
			 * env.put(Context.INITIAL_CONTEXT_FACTORY,
			 * "com.sun.jndi.ldap.LdapCtxFactory");
			 * env.put(Context.SECURITY_AUTHENTICATION, "Simple"); String unm =
			 * "trianz"+"\\"+"Samir.bhat"; System.out.println("username :"+unm);
			 * env.put(Context.SECURITY_PRINCIPAL, unm);
			 * env.put(Context.SECURITY_CREDENTIALS, "System@50");
			 * env.put(Context.PROVIDER_URL, "ldap://172.16.0.6:389"); ctx = new
			 * InitialLdapContext(env, null);
			 * System.out.println("connection Successful....");
			 */

		} catch (SystemException sex) {
			LOGGER.debug("System Exception :" + sex.getMessage());
		} catch (NamingException nex) {
			System.out.println("Ldap connection failed.....");
			LOGGER.debug("LDAP Connection Failed :" + nex.getMessage());
		}
		return ctx;
	}

	private List<UserDetails> getUserDetailsByFilter(String filter,
			List<LdapContext> ctx) {

		List<UserDetails> userDetails = new ArrayList<UserDetails>();
		for (LdapContext context : ctx) {

			try {

				SearchControls constraints = new SearchControls();
				constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
				// First input parameter is search bas, it can be
				// "CN=Users,DC=YourDomain,DC=com"
				// Second Attribute can be uid=username
				NamingEnumeration<SearchResult> answer = context.search(
						"DC=trianz,DC=int", filter, constraints);

				while (answer.hasMore()) {
					UserDetails user = new UserDetails();

					Attributes attrs = ((SearchResult) answer.next())
							.getAttributes();

					Attribute firstName = attrs.get("givenName");
					Attribute lastName = attrs.get("sn");
					Attribute email = attrs.get("mail");
					Attribute department = attrs.get("department");
					Attribute designation = attrs.get("title");
					Attribute location = attrs.get("l");
					Attribute photo = attrs.get("thumbnailPhoto");

					if (null != firstName) {
						user.setFirstName(firstName.get().toString());
					}
					if (null != lastName) {
						user.setLastName(lastName.get().toString());
					}
					if (null != email) {
						user.setUserEmail(email.get().toString());
					}
					if (null != department) {
						user.setUserDepartment(department.get().toString());
					}
					if (null != designation) {
						user.setUserDesignation(designation.get().toString());
					}
					if (null != location) {
						user.setUserLocation(location.get().toString());
					}
					if (null != photo && photo.get() != "") {
						System.out.println("Binary data of Image :"
								+ (byte[]) photo.get());
						System.out.println("Encoded bytes :"
								+ Base64.encode((byte[]) photo.get()));
						user.setUserImage(Base64.encode((byte[]) photo.get()));
					}

					userDetails.add(user);
				}
				context.close();

			} catch (NamingException nme) {
				LOGGER.debug("Naming Exception :" + nme.getMessage());
			}
		}

		return userDetails;
	}

	public void simpleSearch(ActionRequest request, ActionResponse response)
			throws NamingException {

		ThemeDisplay td = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		List<UserDetails> listUserDetails = new ArrayList<UserDetails>();
		String keyword = request.getParameter("keywords");
		request.setAttribute("keywords", keyword);
		request.setAttribute("simpleChecked", "checked");
		StringBuffer filter = new StringBuffer();
		if (null != keyword && keyword != "") {

			filter.append("(givenName=" + keyword + "*)");
			filter.append("(sn=" + keyword + "*)");
			filter.append("(l=" + keyword + "*)");
			filter.append("(department=" + keyword + "*)");
			filter.append("(mail=" + keyword + "*)");
		}
		if (filter.length() > 0) {
			String filter1 = filter.toString();
			filter1 = "(|" + filter1 + ")";
			System.out.println("Simple Filter query :" + filter1);
			List<LdapContext> context = this.getLdapContexts(td);
			listUserDetails = this.getUserDetailsByFilter(filter1, context);

		}
		request.setAttribute("userDetailList", listUserDetails);
	}

	public void advanceSearch(ActionRequest request, ActionResponse response)
			throws ReadOnlyException, ValidatorException, IOException {

		ThemeDisplay td = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		StringBuffer filter = new StringBuffer();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String location = request.getParameter("location");
		String department = request.getParameter("department");
		String email = request.getParameter("email");

		List<UserDetails> listUserDetails = new ArrayList<UserDetails>();

		request.setAttribute("firstName", firstName);

		request.setAttribute("lastName", lastName);
		request.setAttribute("location", location);
		request.setAttribute("department", department);
		request.setAttribute("email", email);
		request.setAttribute("advanceChecked", "checked");

		if (null != firstName && firstName != "") {
			filter.append("(givenName=" + firstName + "*)");
		}
		if (null != lastName && lastName != "") {
			filter.append("(sn=" + lastName + "*)");
		}
		if (null != location && location != "") {
			filter.append("(l=" + location + "*)");
		}
		if (null != department && department != "") {
			filter.append("(department=" + department + "*)");
		}
		if (null != email && email != "") {
			filter.append("(mail=" + email + "*)");
		}

		if (filter.length() > 0) {
			String filter1 = filter.toString();
			filter1 = "(&" + filter1 + ")";
			System.out.println("Filter query :" + filter1);
			List<LdapContext> context = this.getLdapContexts(td);
			if (null != context) {
				listUserDetails = this.getUserDetailsByFilter(filter1, context);
			}

		}
		request.setAttribute("userDetailList", listUserDetails);
	}

	public void addUserProfile(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException, InvalidFormatException,
			IOException, PortletModeException {

		LOGGER.info("IN add Profile");
		UploadPortletRequest uploadPortletRequest = PortalUtil
				.getUploadPortletRequest(request);
		String fileName = uploadPortletRequest.getFileName("profileId");
		LOGGER.info("FileName" + fileName);
		File userFile = uploadPortletRequest.getFile("profileId");
		LOGGER.info("uaserProfile" + userFile);
		InputStream inputStream = new FileInputStream(userFile);
		Workbook workbook = WorkbookFactory.create(inputStream);

		Sheet sheet = workbook.getSheetAt(0); // Getting the first Sheet

		Profile profile = new ProfileImpl();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			if (row.getRowNum() != 0) {
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:

						if (cell.getColumnIndex() == 1) {

							profile.setFirstName(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 2) {

							profile.setLastName(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 3) {

							profile.setSummary(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							profile.setEmail(cell.getStringCellValue());
							System.out.println("Email"
									+ cell.getStringCellValue());
						}
						
						if (cell.getColumnIndex() == 6) {
							profile.setExperience(cell.getStringCellValue());
						}

						if (cell.getColumnIndex() == 7) {
							profile.setPrimarySkills(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 8) {
							profile.setSecondarySkills(cell
									.getStringCellValue());
						}
						if (cell.getColumnIndex() == 9) {
							profile.setClientName(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 10) {
							profile.setEducation(cell.getStringCellValue());
						}

						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (cell.getColumnIndex() == 0) {
							Double d = cell.getNumericCellValue();
							Long associateId = d.longValue();
							profile.setUserId(associateId);
						}
						if (cell.getColumnIndex() == 5) {

							profile.setYearOfExperience((int) cell
									.getNumericCellValue());
						}
						
						break;
					}

				}
				ProfileLocalServiceUtil.addProfile(profile);
			}

		}
		response.setPortletMode(PortletMode.VIEW);
	}

}