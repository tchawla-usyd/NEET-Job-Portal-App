package com.neet.jobsite.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="Job")
public class Job implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long Id;
	
	
	@Column(name="Title")
	private String Title;
	
	@Column(name="JobDescription")
	private String JobDescription;
	
	@Column(name="Location")
	private String Location;
	
	@Column(name="JobCategoryID")
	private Integer JobCategoryID;
	
	@Column(name="UserID")
	private Integer UserID;
	
	@Column(name="StartDate")
	private java.sql.Date StartDate;
	
	@Column(name="EndDate")
	private java.sql.Date EndDate;
	
	@Column(name="IsActive")
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
	
	public java.sql.Date getStartDate() {
		return StartDate;
	}
	
	public void setStartDate(java.sql.Date startDate) {
		StartDate = startDate;
	}
	
	public java.sql.Date getEndDate() {
		return EndDate;
	}
	
	public void setEndDate(java.sql.Date endDate) {
		EndDate = endDate;
	}
	
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public long getId() {
		return this.Id;
	}
	
	public void setUID(long id) {
		this.Id = id;
	}
}
