package com.neet.jobsite.bal;

import java.io.Serializable;

import com.neet.jobsite.model.Job;
import com.neet.jobsite.response.CandidateJobRow;

public interface IJobDescriptionService extends Serializable{

	CandidateJobRow getJobDescription(long jDId);

}
