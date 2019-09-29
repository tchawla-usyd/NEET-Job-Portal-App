package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class CandidateSkills extends BaseObject {
	private Integer UserID;
	private Integer SkillID;
	private LocalDateTime CreatedDate;
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	
	public Integer getSkillID() {
		return SkillID;
	}
	public void setSkillID(Integer skillID) {
		SkillID = skillID;
	}
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
}
