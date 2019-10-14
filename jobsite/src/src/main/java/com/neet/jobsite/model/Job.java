package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Job")
public class Job implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
	private String Title;
	private String JobDescription;
	private String Location;
	private Integer JobCategoryID;
	private Integer UserID;
	private Date StartDate;
	private Date EndDate;
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
	
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
}
