package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class User extends BaseObject { 
	/*
	  `UID` INT(11) NOT NULL,
	  `FirstName` VARCHAR(45) NULL DEFAULT NULL,
	  `LastName` VARCHAR(45) NULL DEFAULT NULL,
	  `Email` VARCHAR(45) NULL DEFAULT NULL,
	  `Password` VARCHAR(45) NULL DEFAULT NULL,
	  `IsActive` TINYINT(4) NULL DEFAULT NULL,
	  `IsLocked` TINYINT(4) NULL DEFAULT NULL,
	  `CreatedDate` DATETIME NOT NULL,
	  `ModifiedDate` DATETIME NOT NULL,
	  `UserTypeID` INT(11) NULL DEFAULT NULL,
	  */
	
	private String FirstName;
	private String LastName;
	private String Email;
	private String Password;
	private boolean IsActive;
	private boolean IsLocked;
	private LocalDateTime CreateDate;
	private LocalDateTime ModifiedDate;
	private Integer UserTypeID;
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	
	public boolean isIsLocked() {
		return IsLocked;
	}
	public void setIsLocked(boolean isLocked) {
		IsLocked = isLocked;
	}
	
	public LocalDateTime getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		CreateDate = createDate;
	}
	
	public LocalDateTime getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	
	public Integer getUserTypeID() {
		return UserTypeID;
	}
	public void setUserTypeID(Integer userTypeID) {
		UserTypeID = userTypeID;
	}
}
