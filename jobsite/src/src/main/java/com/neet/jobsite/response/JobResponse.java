package com.neet.jobsite.response;

import java.util.List;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.JobCategory;
import com.neet.jobsite.model.SkillSet;

public class JobResponse {
	
	private long UID;
	private String title;
	private String description;
	private String location;
	private JobCategory jobCategory;
	private Integer created_by;
	private String StartDate;
	private String EndDate;
	private boolean IsActive;
	private Company companyInfo;
	
	private List<SkillSet> skills;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public long getUID() {
		return UID;
	}
	public void setUID(long l) {
		UID = l;
	}
	public JobCategory getJobCategory() {
		return jobCategory;
	}
	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}
	public List<SkillSet> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}
	public Company getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(Company companyInfo) {
		this.companyInfo = companyInfo;
	}
	
}