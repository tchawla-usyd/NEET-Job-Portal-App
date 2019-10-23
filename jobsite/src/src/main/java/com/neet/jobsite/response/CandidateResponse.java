package com.neet.jobsite.response;

import com.neet.jobsite.model.Candidate;

public class CandidateResponse {
	private UserDetailResponse BasicInfo;
	private Candidate CandidateInfo;
	
	
	public UserDetailResponse getBasicInfo() {
		return BasicInfo;
	}
	public void setBasicInfo(UserDetailResponse basicInfo) {
		BasicInfo = basicInfo;
	}
	public Candidate getCandidateInfo() {
		return CandidateInfo;
	}
	public void setCandidateInfo(Candidate candidateInfo) {
		CandidateInfo = candidateInfo;
	}
}
