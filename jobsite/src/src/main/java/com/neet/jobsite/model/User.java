package com.neet.jobsite.model;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable { 
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
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long Id;
	
	@Column(name="FirstName")
	private String FirstName;
	
	@Column(name="LastName")
	private String LastName;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="Password")
	private String Password;
	
	@Column(name="IsActive")
	private boolean IsActive;
	
	@Column(name="IsLocked")
	private boolean IsLocked;
	
	@Column(name="CreateDate")
	private Date CreateDate;
	
	@Column(name="ModifiedDate")
	private Date ModifiedDate;
	
	@Column(name="UserTypeID")
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
	
	public Date getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}
	
	public Date getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	
	public Integer getUserTypeID() {
		return UserTypeID;
	}
	public void setUserTypeID(Integer userTypeID) {
		UserTypeID = userTypeID;
	}
}
