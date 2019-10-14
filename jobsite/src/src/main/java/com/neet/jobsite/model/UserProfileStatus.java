package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserProfileStatus")
public class UserProfileStatus implements Serializable{
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
	private boolean IsEmailSent;
	private boolean IsSmsSent;
	private String PasswordResetGuid;
	private String PasswordResetExpiryGuid;
	private Date CreatedDate;
	private Date ModifiedDate;
	private Integer UserID;
	
	public boolean isIsEmailSent() {
		return IsEmailSent;
	}
	public void setIsEmailSent(boolean isEmailSent) {
		IsEmailSent = isEmailSent;
	}
	
	public boolean isIsSmsSent() {
		return IsSmsSent;
	}
	public void setIsSmsSent(boolean isSmsSent) {
		IsSmsSent = isSmsSent;
	}
	
	public String getPasswordResetGuid() {
		return PasswordResetGuid;
	}
	public void setPasswordResetGuid(String passwordResetGuid) {
		PasswordResetGuid = passwordResetGuid;
	}
	
	public String getPasswordResetExpiryGuid() {
		return PasswordResetExpiryGuid;
	}
	public void setPasswordResetExpiryGuid(String passwordResetExpiryGuid) {
		PasswordResetExpiryGuid = passwordResetExpiryGuid;
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
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
}
