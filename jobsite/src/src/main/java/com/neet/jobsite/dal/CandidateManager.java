package com.neet.jobsite.dal;

import java.io.Serializable;

import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;

public interface CandidateManager extends Serializable {

	void applyJob(CandidateJobApplied application);

	void addCandidateInfo(Candidate candidate);
	
	void editCandidateInfo(Candidate candidate);

	Candidate getCandidateById(Integer userId);

	
	

}
