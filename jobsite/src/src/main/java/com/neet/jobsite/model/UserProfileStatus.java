package com.neet.jobsite.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserProfileStatus implements Serializable{
	private boolean IsEmailSent;
	private boolean IsSmsSent;
	private String PasswordResetGuid;
	private String PasswordResetExpiryGuid;
	private LocalDateTime CreatedDate;
	private LocalDateTime ModifiedDate;
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
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
	
	public LocalDateTime getModifiedDate() {
		return ModifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		ModifiedDate = modifiedDate;
	}
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
}
