package com.neet.jobsite.response;

import java.sql.Date;
import java.util.List;

public class CandidateJobRow {
	private String jobTitle;
	private String company;
	private String location;
	private List<String> skills;
	private Date dateAdded;
	private long jobId;
	private String jobDescription;
	
	public long getJobId() {
		return this.jobId;
	}
	
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	public String getJobDescription() {
		return this.jobDescription;
	}
	
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	public String getJobTitle() {
		return this.jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return this.company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getSkills() {
		return this.skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public Date getDateAdded() {
		return this.dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
}
