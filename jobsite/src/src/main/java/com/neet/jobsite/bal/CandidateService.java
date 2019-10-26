package com.neet.jobsite.bal;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.CandidateManager;
import com.neet.jobsite.dal.JobManager;
import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;
import com.neet.jobsite.response.ApplicantsResponse;
import com.neet.jobsite.response.CandidateResponse;
import com.neet.jobsite.response.UserDetailResponse;

@Service(value = "candidateService")
public class CandidateService {
	
	@Autowired
	@Qualifier("candidateManager")
	private CandidateManager candidateManager;
	
	@Resource(name="userService")
	private UserService userService;
	
	
	public void applyJob(Integer jobId, Integer userId) {			
		CandidateJobApplied application = new CandidateJobApplied();
		application.setApplyDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		application.setJobID(jobId);
		application.setUserID(userId);
		
		candidateManager.applyJob(application);
	}
	
	public void addResume(Integer userId, String fileUrl) {
			
		Candidate candidate = candidateManager.getCandidateById(userId);
		candidate.setResume(fileUrl);
		candidateManager.editCandidateInfo(candidate);
	}
	
	public List<SkillSet> getCandidateSkills(Long candidateId, Integer userId) {
		List<SkillSet> skills = candidateManager.getCandidateSkillsById(candidateId);
		return skills;
	}
	
	public List<ApplicantsResponse> getApplicants(Long jobId, Integer userId) {			
		List<CandidateJobApplied> applications = candidateManager.getApplicationInfo(jobId);
		
		List<User> users = candidateManager.getApplicants(jobId);
		
		List<Long> ids = new ArrayList<Long>();
		
		for(User user: users) {
			ids.add(user.getId());
		}
					
		List<ApplicantsResponse> applicants = new ArrayList<ApplicantsResponse>();
		
		for(int i = 0; i < ids.size(); i++) {
			ApplicantsResponse app = new ApplicantsResponse();
			app.setUser_id(users.get(i).getId());
			app.setFirst_name(users.get(i).getFirstName());
			app.setLast_name(users.get(i).getLastName());
			app.setEmail(users.get(i).getEmail());
			app.setApplyDate(applications.get(i).getApplyDate().toString());
			
			List<SkillSet> skills = candidateManager.getCandidateSkillsById(users.get(i).getId());
			app.setSkills(skills);
			
			applicants.add(app);
		}
		
		return applicants;		
	}
	
	public CandidateResponse getCandidate(Long candidateId, Integer userId) {
		UserDetailResponse basicUser = userService.getUser((int) (long) candidateId);
		Candidate candidate = candidateManager.getCandidateById((int) (long) candidateId);
		
		CandidateResponse res = new CandidateResponse();
		res.setBasicInfo(basicUser);
		res.setCandidateInfo(candidate);
		System.out.println(res.getCandidateInfo());
		
		return res;
	}
	






	
	




}
