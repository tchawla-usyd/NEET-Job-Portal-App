package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.List;

import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.CandidateSkills;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;

public interface CandidateManager extends Serializable {

	void applyJob(CandidateJobApplied application);

	void addCandidateInfo(Candidate candidate);
	
	void editCandidateInfo(Candidate candidate);

	Candidate getCandidateById(Integer userId);

	List<User> getApplicants(Long jobId);

	List<Candidate> getCandidateByIds(List<Long> ids);
	
	List<SkillSet> getCandidateSkillsById(Long ids);

	List<CandidateJobApplied> getApplicationInfo(Long jobId);

	
	

}
