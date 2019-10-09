package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.List;

import com.neet.jobsite.model.Job;

public interface JobManager extends Serializable {
	
	public List<Job> getJobs();
    
    public void addJob(Job job);
    
    public Job getSkillSetById(long id);
    
    public List<Job> getSkillSetByEmployee(long employee_id);
    
    public List<Job> getSkillSetByCandidate(long candidate_id);
    
    public void updateJob(Job job);
    
    public void deleteJob(long id);
	
}
