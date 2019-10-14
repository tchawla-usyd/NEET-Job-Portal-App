package com.neet.jobsite.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CandidateSkills implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
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
