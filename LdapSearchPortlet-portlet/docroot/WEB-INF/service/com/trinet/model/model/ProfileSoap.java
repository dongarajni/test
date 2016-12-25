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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author ronak.parekh
 * @generated
 */
public class ProfileSoap implements Serializable {
	public static ProfileSoap toSoapModel(Profile model) {
		ProfileSoap soapModel = new ProfileSoap();

		soapModel.setId(model.getId());
		soapModel.setEmail(model.getEmail());
		soapModel.setUserId(model.getUserId());
		soapModel.setAssociateId(model.getAssociateId());
		soapModel.setFirstName(model.getFirstName());
		soapModel.setLastName(model.getLastName());
		soapModel.setSummary(model.getSummary());
		soapModel.setYearOfExperience(model.getYearOfExperience());
		soapModel.setExperience(model.getExperience());
		soapModel.setPrimarySkills(model.getPrimarySkills());
		soapModel.setSecondarySkills(model.getSecondarySkills());
		soapModel.setClientName(model.getClientName());
		soapModel.setEducation(model.getEducation());

		return soapModel;
	}

	public static ProfileSoap[] toSoapModels(Profile[] models) {
		ProfileSoap[] soapModels = new ProfileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProfileSoap[][] toSoapModels(Profile[][] models) {
		ProfileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProfileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProfileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProfileSoap[] toSoapModels(List<Profile> models) {
		List<ProfileSoap> soapModels = new ArrayList<ProfileSoap>(models.size());

		for (Profile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProfileSoap[soapModels.size()]);
	}

	public ProfileSoap() {
	}

	public String getPrimaryKey() {
		return _email;
	}

	public void setPrimaryKey(String pk) {
		setEmail(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getAssociateId() {
		return _associateId;
	}

	public void setAssociateId(long associateId) {
		_associateId = associateId;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public int getYearOfExperience() {
		return _yearOfExperience;
	}

	public void setYearOfExperience(int yearOfExperience) {
		_yearOfExperience = yearOfExperience;
	}

	public String getExperience() {
		return _experience;
	}

	public void setExperience(String experience) {
		_experience = experience;
	}

	public String getPrimarySkills() {
		return _primarySkills;
	}

	public void setPrimarySkills(String primarySkills) {
		_primarySkills = primarySkills;
	}

	public String getSecondarySkills() {
		return _secondarySkills;
	}

	public void setSecondarySkills(String secondarySkills) {
		_secondarySkills = secondarySkills;
	}

	public String getClientName() {
		return _clientName;
	}

	public void setClientName(String clientName) {
		_clientName = clientName;
	}

	public String getEducation() {
		return _education;
	}

	public void setEducation(String education) {
		_education = education;
	}

	private long _id;
	private String _email;
	private long _userId;
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
}