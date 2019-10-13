package com.neet.jobsite.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="jobskill")
public class SkillsForJob extends BaseObject {
	
	@Id
	@GeneratedValue
	@Column(name="UID")
	private long UID;
	
	@Column(name="SkillID")
	private Integer SkillID;
	
	@Column(name="JobID")
	private Integer JobID;
	
	@Column(name="CreatedDate")
	private Date CreatedDate;
	
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
	
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date date) {
		CreatedDate = date;
	}
	public long getUID() {
		return UID;
	}
	public void setUID(long uID) {
		UID = uID;
	}
}
