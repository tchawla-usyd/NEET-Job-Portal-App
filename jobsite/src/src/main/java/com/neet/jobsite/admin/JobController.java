package com.neet.jobsite.admin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.dal.JobManager;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;

@Controller
@RequestMapping(value="/job/**")
public class JobController {
	
	class JobResponse {
		
		private String title;
		private String description;
		private String location;
		private String job_category;
		private Integer created_by;
		private String StartDate;
		private String EndDate;
		private boolean IsActive;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getJob_category() {
			return job_category;
		}
		public void setJob_category(String job_category) {
			this.job_category = job_category;
		}
		public Integer getCreated_by() {
			return created_by;
		}
		public void setCreated_by(Integer created_by) {
			this.created_by = created_by;
		}
		public String getStartDate() {
			return StartDate;
		}
		public void setStartDate(String startDate) {
			StartDate = startDate;
		}
		public String getEndDate() {
			return EndDate;
		}
		public void setEndDate(String endDate) {
			EndDate = endDate;
		}
		public boolean isIsActive() {
			return IsActive;
		}
		public void setIsActive(boolean isActive) {
			IsActive = isActive;
		}
	}
	
	
	@Resource(name="jobManager")
	private JobManager jobManager;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addSkillSet(HttpServletRequest httpServletRequest) {
		Job job = new Job();
		job.setTitle(httpServletRequest.getParameter("title"));
		job.setJobDescription(httpServletRequest.getParameter("description"));
		job.setLocation(httpServletRequest.getParameter("location"));
		
		LocalDateTime startDate = LocalDateTime.parse(httpServletRequest.getParameter("start_date"));
		LocalDateTime endDate = LocalDateTime.parse(httpServletRequest.getParameter("end_date"));

		job.setStartDate(startDate);
		job.setEndDate(endDate);
		
		job.setIsActive(true);
		
		
		this.jobManager.addJob(job);
		return "redirect:/home.jsp";
	}

	@RequestMapping(value="/job/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public JobResponse getJob(@PathVariable("id") Long id, Model uiModel) {
		JobResponse job = new JobResponse();
		return job;
	}
	
	@RequestMapping(value="/employee/{id}/jobs}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public List<JobResponse> getJobByEmployer(@PathVariable("id") Long employer_id, Model uiModel) {
		List<JobResponse> jobs = new ArrayList<JobResponse>();
		return jobs;
	}
	
}
