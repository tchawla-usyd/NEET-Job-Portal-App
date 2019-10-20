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
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="IsActive")
	private boolean isActive;
	
	@Column(name="IsLocked")
	private boolean isLocked;
	
	@Column(name="CreateDate")
	private Date createDate;
	
	@Column(name="ModifiedDate")
	private Date modifiedDate;
	
	@Column(name="UserTypeID")
	private Integer userTypeID;
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isIsActive() {
		return isActive;
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isIsLocked() {
		return this.isLocked;
	}
	
	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getModifiedDate() {
		return this.modifiedDate;
	}
	
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Integer getUserTypeID() {
		return this.userTypeID;
	}
	
	public void setUserTypeID(Integer userTypeID) {
		this.userTypeID = userTypeID;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
}
