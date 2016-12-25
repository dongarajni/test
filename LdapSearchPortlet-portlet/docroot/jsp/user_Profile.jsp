<%@include file="/jsp/init.jsp"%>

<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>


<%@taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%
ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
String redirect = ParamUtil.getString(request, "redirect");
String themeImagePath = themeDisplay.getPathThemeImages();
%>


<portlet:renderURL var="viewURL">
  <portlet:param name="jspPage" value="/jsp/view.jsp"/>
</portlet:renderURL>
<div class="row-fluid">
<div class="span12">
<aui:button  name="back" cssClass="btn-primary pull-right" value="BACK TO SEARCH RESULT"  onClick="${viewURL}"/>
</div>
</div>
<%-- <liferay-ui:header backURL="${viewURL}" title="back" /> --%>

<%-- <c:out value="${profileObject}"/> --%>
<div class="user-detail">
<div class="row-fluid">
	<div class="span4">
	<c:choose>
  					<c:when test="${image == null}">
  						<img class="user-pic" src="<%=themeImagePath%>/trinet/user-icon.png"/>
					</c:when>
					<c:otherwise>
						<img class="user-pic" src="data:image/png;base64,${image}"/>
					</c:otherwise>
				</c:choose>
	</div>
	<div class="span8">
		<div class="row-fluid">
			<div class="span8">
				<aui:input name="firstName"  value="${profileObject.getFirstName()}" type="text" label="First Name"/>
			</div>
			<div class="span4">
				<aui:input name="lastName" value="${profileObject.getLastName()}" type="text" label="Last Name"/>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span3">
				 <aui:input name="associateId" value="${profileObject.getAssociateId()}" type="text" label="Trianz Associate Id"/>
			</div>
			<div class="span6">
				<aui:input name="email" value="${profileObject.getEmail()}" type="text" label="Trianz Email Id"/>
			</div>
			<div class="span3">
				<aui:input name="yearOfExperience" value="${profileObject.getYearOfExperience()}" type="text" label="Years of Experience"/>
			</div>
		</div>
	</div>
</div>
<div class="row-fluid boldlable">
	<div class="span12">
		<aui:input name="summary" value="${profileObject.getSummary()}"  type="textarea" label="Background/Summary"/>
	</div>
</div>
<div class="row-fluid boldlable">
	<div class="span12">
		<aui:input name="experience" value="${profileObject.getExperience()}" type="textarea" label="Experience"/>
	</div>
</div>
<div class="row-fluid boldlable">
	<div class="span6">
		<aui:input name="primarySkills"  value="${profileObject.getPrimarySkills()}" type="textarea" label="Primary Skills"/>
	</div>
	<div class="span6">
		<aui:input name="secondarySkills" value="${profileObject.getSecondarySkills()}" type="textarea" label="Seconadry Skills"/>
	</div>
</div>
<div class="row-fluid boldlable">
	<div class="span6">
		<aui:input name="clientName" value="${profileObject.getClientName()}" type="textarea" label="Client Seved at Trianz"/>
	</div>
	<div class="span6">
		<aui:input name="education" value="${profileObject.getEducation()}" type="textarea" label="Education & Certifications"/>
	</div>
</div>  
</div>
<%-- <c:out value="${renderRequestScope.profileObject}"/> --%>
