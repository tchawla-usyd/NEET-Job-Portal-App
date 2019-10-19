package com.neet.jobsite.response;

import java.sql.Date;
import java.util.List;

public class CandateJobRow {
	private String JobTitle;
	private String Company;
	private String Location;
	private List<String> Skills;
	private Date DateAdded;
	public String getJobTitle() {
		return JobTitle;
	}
	public void setJobTitle(String jobTitle) {
		JobTitle = jobTitle;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public List<String> getSkills() {
		return Skills;
	}
	public void setSkills(List<String> skills) {
		Skills = skills;
	}
	public Date getDateAdded() {
		return DateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		DateAdded = dateAdded;
	}
}
