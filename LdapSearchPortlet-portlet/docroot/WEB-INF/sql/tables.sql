create table userprofile_Profile (
	id_ LONG,
	email VARCHAR(75) not null primary key,
	userId LONG,
	associateId LONG,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	summary VARCHAR(2000) null,
	yearOfExperience INTEGER,
	experience VARCHAR(2000) null,
	primarySkills VARCHAR(2000) null,
	secondarySkills VARCHAR(2000) null,
	clientName VARCHAR(2000) null,
	education VARCHAR(2000) null
);