package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.List;

import com.neet.jobsite.exception.NoSkillsException;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.JobCategory;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;

public interface JobManager extends Serializable {
	
	List<Job> getJobs();
    
    void addJob(Job job);
        
    void updateJob(Job job);
    
	void deleteJob(Job job);
    
	Job getJobById(long id);

	List<Job> getJobByEmployer(long employer_id);

	List<Job> getJobByCandidate(long candidate_id);

	JobCategory getJobCategoryById(long id);

	void addSkillToJob(SkillsForJob jobSkill);

	void deleteSkillFromJob(SkillsForJob jobSkill);

	List<SkillSet> getSkillsByJob(Integer jobId) throws NoSkillsException;

	SkillsForJob getSkillsForJob(Integer jobId, Integer skillId) throws NoSkillsException;

	
}
