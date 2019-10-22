package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.neet.jobsite.response.ErrorResponse;

@Controller
@RequestMapping(value="/candidate/**")
public class CandidateController extends BaseMVCController{
	
	@Resource(name="candidateService")
	private CandidateService candidateService;
	
	@RequestMapping(value="/job/add", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addApplication(HttpServletRequest httpServletRequest, 
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
		
		Integer jobId = Integer.parseInt(httpServletRequest.getParameter("job_id"));
		
		if(authenticateByToken(userToken)) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			candidateService.applyJob(jobId, userId);
		}
		else {
			response.setStatus(403);
		}
	}
	
	@RequestMapping(value="/getapplicants/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody	
	public String getApplicants(@PathVariable("id") Long jobId, HttpServletResponse response,
			@RequestHeader("Authorization") String userToken) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		
		List<ApplicantsResponse> applicants = null;
		
		if(authenticateByToken(userToken)) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			applicants = candidateService.getApplicants(jobId, userId);
			
			try {
				jsonReturn = objectMapper.writeValueAsString(applicants);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setStatus(403);
			
			try {
				jsonReturn = objectMapper.writeValueAsString(new ErrorResponse("Authentication Failed"));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		}
		
		return jsonReturn;
	}
	
	
	@RequestMapping(value="/skills/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCandidateSkills(@PathVariable("id") Long candidateId, HttpServletResponse response,
			@RequestHeader("Authorization") String userToken) {
				
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		
		if(authenticateByToken(userToken)) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			List<SkillSet> skills = candidateService.getCandidateSkills(candidateId, userId);
			
			try {
				jsonReturn = objectMapper.writeValueAsString(skills);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setStatus(403);
			try {
				jsonReturn = objectMapper.writeValueAsString(new ErrorResponse("Authentication Failed"));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		}

		return jsonReturn;
	}
	
	@RequestMapping(value="/view/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getCandidate(@PathVariable("id") Long candidateId, 
			@RequestHeader("Authorization") String userToken,
			HttpServletResponse response) {		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
		
		if(authenticateByToken(userToken)) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) session.getAttribute("userId");
			CandidateResponse candidate = candidateService.getCandidate(candidateId, userToken);
			
			try {
				jsonReturn = objectMapper.writeValueAsString(candidate);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		else {
			response.setStatus(403);
			try {
				jsonReturn = objectMapper.writeValueAsString(new ErrorResponse("Authentication Failed"));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		}
		

		return jsonReturn;
	}
		
}
