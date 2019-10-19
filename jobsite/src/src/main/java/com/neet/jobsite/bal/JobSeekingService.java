package com.neet.jobsite.bal;

import org.springframework.stereotype.Service;

import com.neet.jobsite.response.CandidateJobListResponse;

@Service(value ="jobSeekingService")
public class JobSeekingService {
	public CandidateJobListResponse GetJobResponse(Integer userId) {
		CandidateJobListResponse fakeresponse = new CandidateJobListResponse();
		return fakeresponse;
	}
}
