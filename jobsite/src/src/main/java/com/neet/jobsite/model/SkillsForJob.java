package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="SkillsForJob")
public class SkillsForJob implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
	private Integer SkillID;
	private Integer JobID;
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
	public void Date(Date createdDate) {
		CreatedDate = createdDate;
	}
}
