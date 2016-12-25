/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.trinet.model.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.trinet.model.service.ClpSerializer;
import com.trinet.model.service.ProfileLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ronak.parekh
 */
public class ProfileClp extends BaseModelImpl<Profile> implements Profile {
	public ProfileClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Profile.class;
	}

	@Override
	public String getModelClassName() {
		return Profile.class.getName();
	}

	@Override
	public String getPrimaryKey() {
		return _email;
	}

	@Override
	public void setPrimaryKey(String primaryKey) {
		setEmail(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _email;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("email", getEmail());
		attributes.put("userId", getUserId());
		attributes.put("associateId", getAssociateId());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("summary", getSummary());
		attributes.put("yearOfExperience", getYearOfExperience());
		attributes.put("experience", getExperience());
		attributes.put("primarySkills", getPrimarySkills());
		attributes.put("secondarySkills", getSecondarySkills());
		attributes.put("clientName", getClientName());
		attributes.put("education", getEducation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long associateId = (Long)attributes.get("associateId");

		if (associateId != null) {
			setAssociateId(associateId);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		Integer yearOfExperience = (Integer)attributes.get("yearOfExperience");

		if (yearOfExperience != null) {
			setYearOfExperience(yearOfExperience);
		}

		String experience = (String)attributes.get("experience");

		if (experience != null) {
			setExperience(experience);
		}

		String primarySkills = (String)attributes.get("primarySkills");

		if (primarySkills != null) {
			setPrimarySkills(primarySkills);
		}

		String secondarySkills = (String)attributes.get("secondarySkills");

		if (secondarySkills != null) {
			setSecondarySkills(secondarySkills);
		}

		String clientName = (String)attributes.get("clientName");

		if (clientName != null) {
			setClientName(clientName);
		}

		String education = (String)attributes.get("education");

		if (education != null) {
			setEducation(education);
		}
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setId", long.class);

				method.invoke(_profileRemoteModel, id);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmail() {
		return _email;
	}

	@Override
	public void setEmail(String email) {
		_email = email;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setEmail", String.class);

				method.invoke(_profileRemoteModel, email);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_profileRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public long getAssociateId() {
		return _associateId;
	}

	@Override
	public void setAssociateId(long associateId) {
		_associateId = associateId;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setAssociateId", long.class);

				method.invoke(_profileRemoteModel, associateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFirstName() {
		return _firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		_firstName = firstName;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setFirstName", String.class);

				method.invoke(_profileRemoteModel, firstName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLastName() {
		return _lastName;
	}

	@Override
	public void setLastName(String lastName) {
		_lastName = lastName;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setLastName", String.class);

				method.invoke(_profileRemoteModel, lastName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public void setSummary(String summary) {
		_summary = summary;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setSummary", String.class);

				method.invoke(_profileRemoteModel, summary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getYearOfExperience() {
		return _yearOfExperience;
	}

	@Override
	public void setYearOfExperience(int yearOfExperience) {
		_yearOfExperience = yearOfExperience;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setYearOfExperience", int.class);

				method.invoke(_profileRemoteModel, yearOfExperience);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExperience() {
		return _experience;
	}

	@Override
	public void setExperience(String experience) {
		_experience = experience;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setExperience", String.class);

				method.invoke(_profileRemoteModel, experience);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPrimarySkills() {
		return _primarySkills;
	}

	@Override
	public void setPrimarySkills(String primarySkills) {
		_primarySkills = primarySkills;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setPrimarySkills", String.class);

				method.invoke(_profileRemoteModel, primarySkills);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSecondarySkills() {
		return _secondarySkills;
	}

	@Override
	public void setSecondarySkills(String secondarySkills) {
		_secondarySkills = secondarySkills;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setSecondarySkills",
						String.class);

				method.invoke(_profileRemoteModel, secondarySkills);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClientName() {
		return _clientName;
	}

	@Override
	public void setClientName(String clientName) {
		_clientName = clientName;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setClientName", String.class);

				method.invoke(_profileRemoteModel, clientName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEducation() {
		return _education;
	}

	@Override
	public void setEducation(String education) {
		_education = education;

		if (_profileRemoteModel != null) {
			try {
				Class<?> clazz = _profileRemoteModel.getClass();

				Method method = clazz.getMethod("setEducation", String.class);

				method.invoke(_profileRemoteModel, education);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getProfileRemoteModel() {
		return _profileRemoteModel;
	}

	public void setProfileRemoteModel(BaseModel<?> profileRemoteModel) {
		_profileRemoteModel = profileRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _profileRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_profileRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			ProfileLocalServiceUtil.addProfile(this);
		}
		else {
			ProfileLocalServiceUtil.updateProfile(this);
		}
	}

	@Override
	public Profile toEscapedModel() {
		return (Profile)ProxyUtil.newProxyInstance(Profile.class.getClassLoader(),
			new Class[] { Profile.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		ProfileClp clone = new ProfileClp();

		clone.setId(getId());
		clone.setEmail(getEmail());
		clone.setUserId(getUserId());
		clone.setAssociateId(getAssociateId());
		clone.setFirstName(getFirstName());
		clone.setLastName(getLastName());
		clone.setSummary(getSummary());
		clone.setYearOfExperience(getYearOfExperience());
		clone.setExperience(getExperience());
		clone.setPrimarySkills(getPrimarySkills());
		clone.setSecondarySkills(getSecondarySkills());
		clone.setClientName(getClientName());
		clone.setEducation(getEducation());

		return clone;
	}

	@Override
	public int compareTo(Profile profile) {
		String primaryKey = profile.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProfileClp)) {
			return false;
		}

		ProfileClp profile = (ProfileClp)obj;

		String primaryKey = profile.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", associateId=");
		sb.append(getAssociateId());
		sb.append(", firstName=");
		sb.append(getFirstName());
		sb.append(", lastName=");
		sb.append(getLastName());
		sb.append(", summary=");
		sb.append(getSummary());
		sb.append(", yearOfExperience=");
		sb.append(getYearOfExperience());
		sb.append(", experience=");
		sb.append(getExperience());
		sb.append(", primarySkills=");
		sb.append(getPrimarySkills());
		sb.append(", secondarySkills=");
		sb.append(getSecondarySkills());
		sb.append(", clientName=");
		sb.append(getClientName());
		sb.append(", education=");
		sb.append(getEducation());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.trinet.model.model.Profile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>associateId</column-name><column-value><![CDATA[");
		sb.append(getAssociateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>firstName</column-name><column-value><![CDATA[");
		sb.append(getFirstName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lastName</column-name><column-value><![CDATA[");
		sb.append(getLastName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>summary</column-name><column-value><![CDATA[");
		sb.append(getSummary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>yearOfExperience</column-name><column-value><![CDATA[");
		sb.append(getYearOfExperience());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>experience</column-name><column-value><![CDATA[");
		sb.append(getExperience());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primarySkills</column-name><column-value><![CDATA[");
		sb.append(getPrimarySkills());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>secondarySkills</column-name><column-value><![CDATA[");
		sb.append(getSecondarySkills());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clientName</column-name><column-value><![CDATA[");
		sb.append(getClientName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>education</column-name><column-value><![CDATA[");
		sb.append(getEducation());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private String _email;
	private long _userId;
	private String _userUuid;
	private long _associateId;
	private String _firstName;
	private String _lastName;
	private String _summary;
	private int _yearOfExperience;
	private String _experience;
	private String _primarySkills;
	private String _secondarySkills;
	private String _clientName;
	private String _education;
	private BaseModel<?> _profileRemoteModel;
	private Class<?> _clpSerializerClass = com.trinet.model.service.ClpSerializer.class;
}