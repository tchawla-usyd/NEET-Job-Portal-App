package com.neet.jobsite.bal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.response.CandidateJobListResponse;

public interface IJobSeekingService extends Serializable{
	
	public List<Company> getCompanys();
	
	public List<Job> GetJobsByUserId(Integer id);

	public CandidateJobListResponse createJobRow(List<Company> result, List<Job> finalJobList);
}
