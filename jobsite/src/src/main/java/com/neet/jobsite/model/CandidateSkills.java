package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CandidateSkills")
public class CandidateSkills implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
	private Integer UserID;
	private Integer SkillID;
	private Date CreatedDate;
	
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
	
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
}
