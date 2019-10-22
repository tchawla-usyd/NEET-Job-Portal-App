package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.JobService;
import com.neet.jobsite.response.JobResponse;

@Controller
@RequestMapping(value="/job/**")
public class JobController  extends BaseMVCController {
	
	@Resource(name="jobService")
	private JobService jobService;
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addJob(HttpServletRequest httpServletRequest) {
		
		String title = httpServletRequest.getParameter("title");
		String description = httpServletRequest.getParameter("description");
		String location = httpServletRequest.getParameter("location");
		String startDate = httpServletRequest.getParameter("start_date");
		String endDate = httpServletRequest.getParameter("end_date");
		Integer jobCategory = Integer.parseInt(httpServletRequest.getParameter("job_category"));
		System.out.println(httpServletRequest.getParameterValues("skills"));
		List<String> skills = Arrays.asList(httpServletRequest.getParameterValues("skills"));
		System.out.println(skills);

		// authorized token to get user id
		String userToken = httpServletRequest.getParameter("token");
		
		logger.info(title);
			
		jobService.addJob(title, description, location, startDate, endDate, jobCategory, userToken, skills);

	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editJob(HttpServletRequest httpServletRequest) {
		
		long UID = Long.parseLong(httpServletRequest.getParameter("job_id"));
		String title = httpServletRequest.getParameter("title");
		String description = httpServletRequest.getParameter("description");
		String location = httpServletRequest.getParameter("location");
		String startDate = httpServletRequest.getParameter("start_date");
		String endDate = httpServletRequest.getParameter("end_date");
		String jobCategory_tmp = httpServletRequest.getParameter("job_category");
		Integer jobCategory = jobCategory_tmp == null ? null : Integer.parseInt(jobCategory_tmp);
		
		// authorized token to get user id
		String userToken = httpServletRequest.getParameter("token");
		
		
		logger.info(title);
			
		jobService.editJob(UID, title, description, location, startDate, endDate, jobCategory, userToken);

	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteJob(@PathVariable("id") Integer id) {
		
		// authorized token
		String authToken = "abcd";
		
		jobService.deleteJob(id, authToken);
	}

	@RequestMapping(value="/getjob/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getJob(@PathVariable("id") Long id) {
		
		JobResponse job = jobService.getJob(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(job);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
	}
	
	@RequestMapping(value="/employer/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getJobByEmployer(@PathVariable("id") Long employer_id, Model uiModel) {
		List<JobResponse> jobs = jobService.getJobByEmployer(employer_id);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(jobs);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
	}

	@RequestMapping(value="/getjob/{job_id}/add/skill/{skill_id}", method=RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void addSkillToJob(@PathVariable("job_id") Integer jobId, @PathVariable("skill_id") Integer skillId) {
		
		String authToken = "abcd";
		
		jobService.addSkillToJob(jobId, skillId, authToken);
	}
	
	@RequestMapping(value="/getjob/{job_id}/delete/skill/{skill_id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteSkillFromJob(@PathVariable("job_id") Integer jobId, @PathVariable("skill_id") Integer skillId) {
		
		String authToken = "abcd";
		
		jobService.deleteSkillFromJob(jobId, skillId, authToken);		
	}
	
	
	
}
