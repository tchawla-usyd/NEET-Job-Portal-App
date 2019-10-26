package com.neet.jobsite;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.CandidateService;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.response.ApplicantsResponse;
import com.neet.jobsite.response.CandidateResponse;
import com.neet.jobsite.response.ErrorResponse;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping(value="/candidate/**")
public class CandidateController extends BaseMVCController{
	
	@Resource(name="candidateService")
	private CandidateService candidateService;
	
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addApplication(HttpServletRequest httpServletRequest, 
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
		
		Integer jobId = Integer.parseInt(httpServletRequest.getParameter("job_id"));
        Claims claims = authenticateByToken(userToken);
		if(claims != null) {
			Integer userId = (Integer) claims.get("uid");
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
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			Integer userId = (Integer) claims.get("uid");
			applicants = candidateService.getApplicants(jobId, userId);
			jsonReturn = objectToJSON(objectMapper, applicants);
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));

			
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
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			Integer userId = (Integer) claims.get("uid");
			List<SkillSet> skills = candidateService.getCandidateSkills(candidateId, userId);
			jsonReturn = objectToJSON(objectMapper, skills);
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}

		return jsonReturn;
	}
	
	@RequestMapping(value="/view/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public String getCandidate(@PathVariable("id") Long candidateId, 
			@RequestHeader("Authorization") String userToken, HttpServletResponse response) {		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			Integer userId = (Integer) claims.get("uid");
			CandidateResponse candidate = candidateService.getCandidate(candidateId, userId);
			jsonReturn = objectToJSON(objectMapper, candidate);
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
			
		}
		
		return jsonReturn;
	}
		
}
