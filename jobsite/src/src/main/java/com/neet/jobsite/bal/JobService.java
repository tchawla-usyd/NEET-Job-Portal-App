package com.neet.jobsite.bal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.DatabaseSkillSetManager;
import com.neet.jobsite.dal.JobManager;
import com.neet.jobsite.exception.NoSkillsException;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;
import com.neet.jobsite.response.JobResponse;

@Service(value = "jobService")
public class JobService {
	
	@Autowired
	@Qualifier("jobDAO")
	private JobManager jobManager;
	
	private static final Logger logger = LoggerFactory.getLogger(JobService.class);
	
	public void addJob(String title, String description, String location, String startDate,
						String endDate, Integer jobCategory, String userToken) {
		
		if (isValidUser(userToken)) {
			
			Integer userId = getUserId(userToken);
			java.sql.Date startDateSQL = getDate(startDate);
			java.sql.Date endDateSQL = getDate(endDate);
			boolean isActive = isActiveDate(startDateSQL, endDateSQL);
			
			Job job = new Job();
			
			job.setTitle(title);
			job.setJobDescription(description);
			job.setLocation(location);
			job.setStartDate(startDateSQL);
			job.setEndDate(endDateSQL);
			job.setJobCategoryID(jobCategory);	
			job.setIsActive(isActive);
			job.setUserID(userId);
			
			jobManager.addJob(job);
		}
		
	}
	
	public void editJob(long uID, String title, String description, String location, String startDate, String endDate,
						Integer jobCategory, String userToken) {
			
		if (isValidUser(userToken)) {
			
			Integer userId = getUserId(userToken);
			java.sql.Date startDateSQL = getDate(startDate);
			java.sql.Date endDateSQL = getDate(endDate);
			boolean isActive = isActiveDate(startDateSQL, endDateSQL);
			
			Job job = jobManager.getJobById(uID);
			
			job.setTitle(title);
			job.setJobDescription(description);
			job.setLocation(location);
			job.setStartDate(startDateSQL);
			job.setEndDate(endDateSQL);
			job.setJobCategoryID(jobCategory);	
			job.setIsActive(isActive);
			job.setUserID(userId);
			
			jobManager.updateJob(job);
		}
		
	}
	
	public JobResponse getJob(Long jobId) {		
		Job job = jobManager.getJobById(jobId);
		
		JobResponse jobResponse = new JobResponse();
		addJobAttribute(jobResponse, job);
		
		return jobResponse;
	}
	
	public List<JobResponse> getJobByEmployer(Long employer_id) {
		List<JobResponse> response = new ArrayList<JobResponse>();
		List<Job> jobs = jobManager.getJobByEmployer(employer_id);
		
		for(Job job : jobs) {
			JobResponse r = new JobResponse();
			addJobAttribute(r, job);
			response.add(r);
			
		}
		
		return response;
	}
	

	private void addJobAttribute(JobResponse jobResponse, Job job) {

		jobResponse.setUID(job.getUID());
		jobResponse.setTitle(job.getTitle());
		jobResponse.setCreated_by(job.getUserID());
		jobResponse.setDescription(job.getJobDescription());
		jobResponse.setEndDate(job.getEndDate().toString());
		jobResponse.setStartDate(job.getStartDate().toString());
		jobResponse.setLocation(job.getLocation());
		jobResponse.setJobCategory(jobManager.getJobCategoryById(job.getJobCategoryID()));
		jobResponse.setIsActive(job.isIsActive());
		
		try {
			jobResponse.setSkills(jobManager.getSkillsByJob((int) job.getUID()));
		} catch (NoSkillsException e) {
			
			jobResponse.setSkills(new ArrayList<SkillSet>());
		}
		
	}
	
	public void deleteJob(Integer id, String authToken) {
		if (isValidUser(authToken)) {
			Job job = jobManager.getJobById(id);
			if (hasPermissionJob(job, authToken)) {
				jobManager.deleteJob(job);
			}
		}
		
	}
	
	public void addSkillToJob(Integer jobId, Integer skillId, String userToken) {
		if (isValidUser(userToken)) {
			Integer userId = getUserId(userToken);
			
			SkillsForJob jobSkill = new SkillsForJob();
			jobSkill.setJobID(jobId);
			jobSkill.setSkillID(skillId);
			jobSkill.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			
			jobManager.addSkillToJob(jobSkill);
			
		}
	}
	
	public void deleteSkillFromJob(Integer jobId, Integer skillId, String userToken) {
		if (isValidUser(userToken)) {
			
			SkillsForJob sj = null;
			try {
				sj = jobManager.getSkillsForJob(jobId, skillId);
			} catch (NoSkillsException e) {
				logger.warn("No such skills+job combination");
			}
			
			if (hasPermissionSkillsForJob(sj, userToken)) {
				jobManager.deleteSkillFromJob(sj);
			}
		}
	}
	
	
	private boolean hasPermissionSkillsForJob(SkillsForJob sj, String userToken) {
		// TODO Auto-generated method stub
		return true;
	}

	
	private boolean hasPermissionJob(Job job, String authToken) {
		// TODO check if the user have a permission to delete the job
		return true;
	}

	private boolean isValidUser(String userToken) {
		// TODO validate userToken
		return true;
	}

	private Integer getUserId(String userToken) {
		// TODO get User ID from an authorized userToken
		return 1;
	}

	private java.sql.Date getDate(String startDate) {
		Date sDate = null;
		try {
			sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Date(sDate.getTime());
	}

	private boolean isActiveDate(java.sql.Date startDate, java.sql.Date endDate) {
		LocalDate today = LocalDate.now();
		return today.isAfter(startDate.toLocalDate()) && today.isBefore(endDate.toLocalDate());
	}

	




	
	
}
