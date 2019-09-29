package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class Job extends BaseObject {
	private String Title;
	private String JobDescription;
	private String Location;
	private Integer JobCategoryID;
	private Integer UserID;
	private LocalDateTime StartDate;
	private LocalDateTime EndDate;
	private boolean IsActive;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	public String getJobDescription() {
		return JobDescription;
	}
	public void setJobDescription(String jobDescription) {
		JobDescription = jobDescription;
	}
	
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
	public Integer getJobCategoryID() {
		return JobCategoryID;
	}
	public void setJobCategoryID(Integer jobCategoryID) {
		JobCategoryID = jobCategoryID;
	}
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	
	public LocalDateTime getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		StartDate = startDate;
	}
	
	public LocalDateTime getEndDate() {
		return EndDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		EndDate = endDate;
	}
	
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
}
