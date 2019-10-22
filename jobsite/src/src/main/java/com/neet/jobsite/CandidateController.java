package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.CandidateService;
import com.neet.jobsite.bal.JobService;
import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.response.ApplicantsResponse;
import com.neet.jobsite.response.CandidateResponse;

@Controller
@RequestMapping(value="/candidate/**")
public class CandidateController {
	
	@Resource(name="candidateService")
	private CandidateService candidateService;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addApplication(HttpServletRequest httpServletRequest) {
		
		Integer jobId = Integer.parseInt(httpServletRequest.getParameter("job_id"));

		// authorized token to get user id
		String userToken = httpServletRequest.getParameter("token");
		
		candidateService.applyJob(jobId, userToken);
	}
	
	@RequestMapping(value="/getapplicants/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public String getApplicants(@PathVariable("id") Long jobId) {
		String userToken = "abcd";
		
		List<ApplicantsResponse> applicants = candidateService.getApplicants(jobId, userToken);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(applicants);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
	}
	
	@RequestMapping(value="/skills/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCandidateSkills(@PathVariable("id") Long candidateId) {
		String userToken = "abcd";

		List<SkillSet> skills = candidateService.getCandidateSkills(candidateId, userToken);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(skills);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
	}
	
	@RequestMapping(value="/view/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCandidate(@PathVariable("id") Long candidateId) {
		String userToken = "abcd";
		
		CandidateResponse candidate = candidateService.getCandidate(candidateId, userToken);

		
		System.out.println(candidate);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		
		try {
			jsonReturn = objectMapper.writeValueAsString(candidate);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
	}
		
}
