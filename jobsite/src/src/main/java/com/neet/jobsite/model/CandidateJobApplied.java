package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CandidateJobApplied")
public class CandidateJobApplied implements Serializable {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	private Integer JobID;
	private Integer UserID;
	private Date ApplyDate;
	
	public Integer getJobID() {
		return JobID;
	}
	public void setJobID(Integer jobID) {
		JobID = jobID;
	}
	
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	
	public Date getApplyDate() {
		return ApplyDate;
	}
	public void setApplyDate(Date applyDate) {
		ApplyDate = applyDate;
	}
}
