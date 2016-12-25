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

package com.trinet.model.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.trinet.model.model.Profile;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Profile in entity cache.
 *
 * @author ronak.parekh
 * @see Profile
 * @generated
 */
public class ProfileCacheModel implements CacheModel<Profile>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{id=");
		sb.append(id);
		sb.append(", email=");
		sb.append(email);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", associateId=");
		sb.append(associateId);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", yearOfExperience=");
		sb.append(yearOfExperience);
		sb.append(", experience=");
		sb.append(experience);
		sb.append(", primarySkills=");
		sb.append(primarySkills);
		sb.append(", secondarySkills=");
		sb.append(secondarySkills);
		sb.append(", clientName=");
		sb.append(clientName);
		sb.append(", education=");
		sb.append(education);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Profile toEntityModel() {
		ProfileImpl profileImpl = new ProfileImpl();

		profileImpl.setId(id);

		if (email == null) {
			profileImpl.setEmail(StringPool.BLANK);
		}
		else {
			profileImpl.setEmail(email);
		}

		profileImpl.setUserId(userId);
		profileImpl.setAssociateId(associateId);

		if (firstName == null) {
			profileImpl.setFirstName(StringPool.BLANK);
		}
		else {
			profileImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			profileImpl.setLastName(StringPool.BLANK);
		}
		else {
			profileImpl.setLastName(lastName);
		}

		if (summary == null) {
			profileImpl.setSummary(StringPool.BLANK);
		}
		else {
			profileImpl.setSummary(summary);
		}

		profileImpl.setYearOfExperience(yearOfExperience);

		if (experience == null) {
			profileImpl.setExperience(StringPool.BLANK);
		}
		else {
			profileImpl.setExperience(experience);
		}

		if (primarySkills == null) {
			profileImpl.setPrimarySkills(StringPool.BLANK);
		}
		else {
			profileImpl.setPrimarySkills(primarySkills);
		}

		if (secondarySkills == null) {
			profileImpl.setSecondarySkills(StringPool.BLANK);
		}
		else {
			profileImpl.setSecondarySkills(secondarySkills);
		}

		if (clientName == null) {
			profileImpl.setClientName(StringPool.BLANK);
		}
		else {
			profileImpl.setClientName(clientName);
		}

		if (education == null) {
			profileImpl.setEducation(StringPool.BLANK);
		}
		else {
			profileImpl.setEducation(education);
		}

		profileImpl.resetOriginalValues();

		return profileImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		email = objectInput.readUTF();
		userId = objectInput.readLong();
		associateId = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		summary = objectInput.readUTF();
		yearOfExperience = objectInput.readInt();
		experience = objectInput.readUTF();
		primarySkills = objectInput.readUTF();
		secondarySkills = objectInput.readUTF();
		clientName = objectInput.readUTF();
		education = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeLong(userId);
		objectOutput.writeLong(associateId);

		if (firstName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (summary == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(summary);
		}

		objectOutput.writeInt(yearOfExperience);

		if (experience == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(experience);
		}

		if (primarySkills == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(primarySkills);
		}

		if (secondarySkills == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondarySkills);
		}

		if (clientName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(clientName);
		}

		if (education == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(education);
		}
	}

	public long id;
	public String email;
	public long userId;
	public long associateId;
	public String firstName;
	public String lastName;
	public String summary;
	public int yearOfExperience;
	public String experience;
	public String primarySkills;
	public String secondarySkills;
	public String clientName;
	public String education;
}