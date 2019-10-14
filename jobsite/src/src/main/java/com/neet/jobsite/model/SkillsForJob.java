package com.neet.jobsite.model;

import java.io.Serializable;
import java.sql.Date;
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
	private long Id;

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

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
		
		
	public long getId() {
		return this.Id;
	}
	public void setId(long id) {
		this.Id = id;
	}
}
