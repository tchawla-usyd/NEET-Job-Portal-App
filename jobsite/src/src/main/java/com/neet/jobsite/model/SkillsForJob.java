package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class SkillsForJob extends BaseObject {
	private Integer SkillID;
	private Integer JobID;
	private LocalDateTime CreatedDate;
	
	public Integer getSkillID() {
		return SkillID;
	}
	public void setSkillID(Integer skillID) {
		SkillID = skillID;
	}
	
	public Integer getJobID() {
		return JobID;
	}
	public void setJobID(Integer jobID) {
		JobID = jobID;
	}
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
}
