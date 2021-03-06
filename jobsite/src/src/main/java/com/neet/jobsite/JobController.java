package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.neet.jobsite.bal.JobService;
import com.neet.jobsite.response.ErrorResponse;
import com.neet.jobsite.response.JobResponse;

import io.jsonwebtoken.Claims;

@Controller
@RequestMapping(value="/job/**")
public class JobController  extends BaseMVCController {
	
	@Resource(name="jobService")
	private JobService jobService;
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addJob(HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
		
		String title = httpServletRequest.getParameter("title");
		String description = httpServletRequest.getParameter("description");
		String location = httpServletRequest.getParameter("location");
		String startDate = httpServletRequest.getParameter("start_date");
		String endDate = httpServletRequest.getParameter("end_date");
		Integer jobCategory = Integer.parseInt(httpServletRequest.getParameter("job_category"));
		List<String> skills = Arrays.asList(httpServletRequest.getParameterValues("skills"));
		
		logger.info(title);
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) claims.get("uid");
			jobService.addJob(title, description, location, startDate, endDate, jobCategory, userId, skills);
		}
		else {
			response.setStatus(403);
		}
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editJob(HttpServletRequest httpServletRequest, HttpServletResponse response,
			@RequestHeader("Authorization") String userToken) {
		
		long UID = Long.parseLong(httpServletRequest.getParameter("job_id"));
		String title = httpServletRequest.getParameter("title");
		String description = httpServletRequest.getParameter("description");
		String location = httpServletRequest.getParameter("location");
		String startDate = httpServletRequest.getParameter("start_date");
		String endDate = httpServletRequest.getParameter("end_date");
		String jobCategory_tmp = httpServletRequest.getParameter("job_category");
		Integer jobCategory = jobCategory_tmp == null ? null : Integer.parseInt(jobCategory_tmp);		
		
		List<String> skills = null;
		if (httpServletRequest.getParameterValues("skills") != null)
				skills = Arrays.asList(httpServletRequest.getParameterValues("skills"));
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) claims.get("uid");
			jobService.editJob(UID, title, description, location, startDate, endDate, jobCategory, userId, skills);
		}
		else {
			response.setStatus(403);
		}

	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteJob(@PathVariable("id") Integer id, HttpServletResponse response,
			@RequestHeader("Authorization") String userToken) {
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) claims.get("uid");
			jobService.deleteJob(id, userId);
		}
		else {
			response.setStatus(403);
		}
	}

	@RequestMapping(value="/getjob/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getJob(@PathVariable("id") Long id,  HttpServletResponse response,
			@RequestHeader("Authorization") String userToken) {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			JobResponse job = jobService.getJob(id);		
			jsonReturn = objectToJSON(objectMapper, job);
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}
			
		return jsonReturn;
	}
	
	@RequestMapping(value="/employer/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getJobByEmployer(@PathVariable("id") Long employer_id, 
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			List<JobResponse> jobs = jobService.getJobByEmployer(employer_id);
			jsonReturn = objectToJSON(objectMapper, jobs);			
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}

		return jsonReturn;
	}
	
	@RequestMapping(value="/all", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getJobs(HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			List<JobResponse> jobs = jobService.getJobs();
			jsonReturn = objectToJSON(objectMapper, jobs);			
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}

		return jsonReturn;
	}

	@RequestMapping(value="/getjob/{job_id}/add/skill/{skill_id}", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void addSkillToJob(@PathVariable("job_id") Integer jobId, @PathVariable("skill_id") Integer skillId, 
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
        Claims claims = authenticateByToken(userToken);
		
		if(claims != null) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) claims.get("uid");
			jobService.addSkillToJob(jobId, skillId, userId);
		}
		else {
			response.setStatus(403);
		}
	}
	
	@RequestMapping(value="/getjob/{job_id}/delete/skill/{skill_id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteSkillFromJob(@PathVariable("job_id") Integer jobId, @PathVariable("skill_id") Integer skillId,
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
        Claims claims = authenticateByToken(userToken);	
		if(claims != null) {
			HttpSession session = context.getSession(false);
			Integer userId = (Integer) claims.get("uid");
			jobService.deleteSkillFromJob(jobId, skillId, userId);		
		}
		else {
			response.setStatus(403);
		}
		
	}
	
	@RequestMapping(value="/candidate/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)	
	@ResponseBody
	public String getJobByCandidate(@PathVariable("id") Long candidateId, 
			HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
	
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
        Claims claims = authenticateByToken(userToken);

		if(claims != null) {
			List<JobResponse> apps = jobService.getJobByCandidate(candidateId);		
			jsonReturn = objectToJSON(objectMapper, apps);
		}
		else {
			response.setStatus(403);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}
		
		return jsonReturn;
	}
	
	
	
}
