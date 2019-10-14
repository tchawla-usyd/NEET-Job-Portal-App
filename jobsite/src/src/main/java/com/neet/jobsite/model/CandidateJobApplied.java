package com.neet.jobsite.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CandidateJobApplied implements Serializable {
	private Integer JobID;
	private Integer UserID;
	private LocalDateTime ApplyDate;
	
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
	
	public LocalDateTime getApplyDate() {
		return ApplyDate;
	}
	public void setApplyDate(LocalDateTime applyDate) {
		ApplyDate = applyDate;
	}
}
