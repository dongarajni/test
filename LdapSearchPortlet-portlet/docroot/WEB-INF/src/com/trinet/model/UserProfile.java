package com.trinet.model;

public class UserProfile {
	
	  private long userId;
	  private long associateId;
	  private String email;
	  private String firstName;
	  private String lastName;
	  private String summary;
	  private int yearOfExperience;
	  private int experience;
	  private String primarySkills;
	  private String secondarySkills;
	  private long profileId;
	  private String clientName;
	  
	  
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAssociateId() {
		return associateId;
	}
	public void setAssociateId(long associateId) {
		this.associateId = associateId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getYearOfExperience() {
		return yearOfExperience;
	}
	public void setYearOfExperience(int yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getPrimarySkills() {
		return primarySkills;
	}
	public void setPrimarySkills(String primarySkills) {
		this.primarySkills = primarySkills;
	}
	public String getSecondarySkills() {
		return secondarySkills;
	}
	public void setSecondarySkills(String secondarySkills) {
		this.secondarySkills = secondarySkills;
	}
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	  

}
