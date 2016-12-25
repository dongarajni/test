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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Profile}.
 * </p>
 *
 * @author ronak.parekh
 * @see Profile
 * @generated
 */
public class ProfileWrapper implements Profile, ModelWrapper<Profile> {
	public ProfileWrapper(Profile profile) {
		_profile = profile;
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

	/**
	* Returns the primary key of this profile.
	*
	* @return the primary key of this profile
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _profile.getPrimaryKey();
	}

	/**
	* Sets the primary key of this profile.
	*
	* @param primaryKey the primary key of this profile
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_profile.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this profile.
	*
	* @return the ID of this profile
	*/
	@Override
	public long getId() {
		return _profile.getId();
	}

	/**
	* Sets the ID of this profile.
	*
	* @param id the ID of this profile
	*/
	@Override
	public void setId(long id) {
		_profile.setId(id);
	}

	/**
	* Returns the email of this profile.
	*
	* @return the email of this profile
	*/
	@Override
	public java.lang.String getEmail() {
		return _profile.getEmail();
	}

	/**
	* Sets the email of this profile.
	*
	* @param email the email of this profile
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_profile.setEmail(email);
	}

	/**
	* Returns the user ID of this profile.
	*
	* @return the user ID of this profile
	*/
	@Override
	public long getUserId() {
		return _profile.getUserId();
	}

	/**
	* Sets the user ID of this profile.
	*
	* @param userId the user ID of this profile
	*/
	@Override
	public void setUserId(long userId) {
		_profile.setUserId(userId);
	}

	/**
	* Returns the user uuid of this profile.
	*
	* @return the user uuid of this profile
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _profile.getUserUuid();
	}

	/**
	* Sets the user uuid of this profile.
	*
	* @param userUuid the user uuid of this profile
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_profile.setUserUuid(userUuid);
	}

	/**
	* Returns the associate ID of this profile.
	*
	* @return the associate ID of this profile
	*/
	@Override
	public long getAssociateId() {
		return _profile.getAssociateId();
	}

	/**
	* Sets the associate ID of this profile.
	*
	* @param associateId the associate ID of this profile
	*/
	@Override
	public void setAssociateId(long associateId) {
		_profile.setAssociateId(associateId);
	}

	/**
	* Returns the first name of this profile.
	*
	* @return the first name of this profile
	*/
	@Override
	public java.lang.String getFirstName() {
		return _profile.getFirstName();
	}

	/**
	* Sets the first name of this profile.
	*
	* @param firstName the first name of this profile
	*/
	@Override
	public void setFirstName(java.lang.String firstName) {
		_profile.setFirstName(firstName);
	}

	/**
	* Returns the last name of this profile.
	*
	* @return the last name of this profile
	*/
	@Override
	public java.lang.String getLastName() {
		return _profile.getLastName();
	}

	/**
	* Sets the last name of this profile.
	*
	* @param lastName the last name of this profile
	*/
	@Override
	public void setLastName(java.lang.String lastName) {
		_profile.setLastName(lastName);
	}

	/**
	* Returns the summary of this profile.
	*
	* @return the summary of this profile
	*/
	@Override
	public java.lang.String getSummary() {
		return _profile.getSummary();
	}

	/**
	* Sets the summary of this profile.
	*
	* @param summary the summary of this profile
	*/
	@Override
	public void setSummary(java.lang.String summary) {
		_profile.setSummary(summary);
	}

	/**
	* Returns the year of experience of this profile.
	*
	* @return the year of experience of this profile
	*/
	@Override
	public int getYearOfExperience() {
		return _profile.getYearOfExperience();
	}

	/**
	* Sets the year of experience of this profile.
	*
	* @param yearOfExperience the year of experience of this profile
	*/
	@Override
	public void setYearOfExperience(int yearOfExperience) {
		_profile.setYearOfExperience(yearOfExperience);
	}

	/**
	* Returns the experience of this profile.
	*
	* @return the experience of this profile
	*/
	@Override
	public java.lang.String getExperience() {
		return _profile.getExperience();
	}

	/**
	* Sets the experience of this profile.
	*
	* @param experience the experience of this profile
	*/
	@Override
	public void setExperience(java.lang.String experience) {
		_profile.setExperience(experience);
	}

	/**
	* Returns the primary skills of this profile.
	*
	* @return the primary skills of this profile
	*/
	@Override
	public java.lang.String getPrimarySkills() {
		return _profile.getPrimarySkills();
	}

	/**
	* Sets the primary skills of this profile.
	*
	* @param primarySkills the primary skills of this profile
	*/
	@Override
	public void setPrimarySkills(java.lang.String primarySkills) {
		_profile.setPrimarySkills(primarySkills);
	}

	/**
	* Returns the secondary skills of this profile.
	*
	* @return the secondary skills of this profile
	*/
	@Override
	public java.lang.String getSecondarySkills() {
		return _profile.getSecondarySkills();
	}

	/**
	* Sets the secondary skills of this profile.
	*
	* @param secondarySkills the secondary skills of this profile
	*/
	@Override
	public void setSecondarySkills(java.lang.String secondarySkills) {
		_profile.setSecondarySkills(secondarySkills);
	}

	/**
	* Returns the client name of this profile.
	*
	* @return the client name of this profile
	*/
	@Override
	public java.lang.String getClientName() {
		return _profile.getClientName();
	}

	/**
	* Sets the client name of this profile.
	*
	* @param clientName the client name of this profile
	*/
	@Override
	public void setClientName(java.lang.String clientName) {
		_profile.setClientName(clientName);
	}

	/**
	* Returns the education of this profile.
	*
	* @return the education of this profile
	*/
	@Override
	public java.lang.String getEducation() {
		return _profile.getEducation();
	}

	/**
	* Sets the education of this profile.
	*
	* @param education the education of this profile
	*/
	@Override
	public void setEducation(java.lang.String education) {
		_profile.setEducation(education);
	}

	@Override
	public boolean isNew() {
		return _profile.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_profile.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _profile.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_profile.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _profile.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _profile.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_profile.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _profile.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_profile.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_profile.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_profile.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ProfileWrapper((Profile)_profile.clone());
	}

	@Override
	public int compareTo(com.trinet.model.model.Profile profile) {
		return _profile.compareTo(profile);
	}

	@Override
	public int hashCode() {
		return _profile.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.trinet.model.model.Profile> toCacheModel() {
		return _profile.toCacheModel();
	}

	@Override
	public com.trinet.model.model.Profile toEscapedModel() {
		return new ProfileWrapper(_profile.toEscapedModel());
	}

	@Override
	public com.trinet.model.model.Profile toUnescapedModel() {
		return new ProfileWrapper(_profile.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _profile.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _profile.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_profile.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProfileWrapper)) {
			return false;
		}

		ProfileWrapper profileWrapper = (ProfileWrapper)obj;

		if (Validator.equals(_profile, profileWrapper._profile)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Profile getWrappedProfile() {
		return _profile;
	}

	@Override
	public Profile getWrappedModel() {
		return _profile;
	}

	@Override
	public void resetOriginalValues() {
		_profile.resetOriginalValues();
	}

	private Profile _profile;
}