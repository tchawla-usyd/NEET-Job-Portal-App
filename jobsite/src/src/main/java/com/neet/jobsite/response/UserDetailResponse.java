package com.neet.jobsite.response;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserDetailResponse {

	private long Id;
	private String FirstName;
	private String LastName;
	private String Email;
	private boolean IsActive;	
	private boolean IsLocked;
	private Date CreatedDate;
	private Date ModifiedDate;
	private Integer UserTypeID;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
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
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
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
