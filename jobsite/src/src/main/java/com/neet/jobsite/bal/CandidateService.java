package com.neet.jobsite.bal;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.CandidateManager;
import com.neet.jobsite.dal.JobManager;
import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;

@Service(value = "candidateService")
public class CandidateService {
	
	@Autowired
	@Qualifier("candidateManager")
	private CandidateManager candidateManager;
	
	public void applyJob(Integer jobId, String userToken) {
		if (isValidUser(userToken)) {
			Integer userId = getUserId(userToken);
			
			CandidateJobApplied application = new CandidateJobApplied();
			application.setApplyDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			application.setJobID(jobId);
			application.setUserID(userId);
			
			candidateManager.applyJob(application);
		}
	}
	
	public void addResume(String userToken, String fileUrl) {
		if (isValidUser(userToken)) {
			Integer userId = getUserId(userToken);
			
			Candidate candidate = candidateManager.getCandidateById(userId);
			candidate.setResume(fileUrl);
			candidateManager.editCandidateInfo(candidate);
		}
		
	}
	
	
	private Integer getUserId(String userToken) {
		// TODO Auto-generated method stub
		return 1;
	}

	private boolean isValidUser(String userToken) {
		// TODO Auto-generated method stub
		return true;
	}




}
