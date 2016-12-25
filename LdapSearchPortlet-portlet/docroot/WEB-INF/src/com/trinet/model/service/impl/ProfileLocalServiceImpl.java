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

package com.trinet.model.service.impl;




import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.trinet.model.model.Profile;
import com.trinet.model.service.ProfileLocalServiceUtil;
import com.trinet.model.service.base.ProfileLocalServiceBaseImpl;

/**
 * The implementation of the profile local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.trinet.model.service.ProfileLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author ronak.parekh
 * @see com.trinet.model.service.base.ProfileLocalServiceBaseImpl
 * @see com.trinet.model.service.ProfileLocalServiceUtil
 */
public class ProfileLocalServiceImpl extends ProfileLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.trinet.model.service.ProfileLocalServiceUtil} to access the profile local service.
	 */
  public Profile addProfile(Profile newProfile) throws SystemException{
    Profile profile = null;
   
    long id=CounterLocalServiceUtil.increment(Profile.class.getName());
    profile = profilePersistence.create(newProfile.getEmail());
    
    profile.setId(id);
    profile.setUserId(newProfile.getUserId());
    profile.setFirstName(newProfile.getFirstName());
    profile.setLastName(newProfile.getLastName());
    profile.setAssociateId(newProfile.getAssociateId());
    profile.setEmail(newProfile.getEmail());
    profile.setSummary(newProfile.getSummary());
    profile.setYearOfExperience(newProfile.getYearOfExperience());
    profile.setExperience(newProfile.getExperience());
    
    profile.setPrimarySkills(newProfile.getPrimarySkills());
    profile.setSecondarySkills(newProfile.getSecondarySkills());
    profile.setClientName(newProfile.getClientName());
    profile.setEducation(newProfile.getEducation());
    return profilePersistence.update(profile);
    
    
  }
  
  public Profile userFinder(String email) throws PortalException,SystemException {
	   System.out.println("Profile====>>>> "+ProfileLocalServiceUtil.fetchProfile(email));
	  return ProfileLocalServiceUtil.fetchProfile(email);
	}
}