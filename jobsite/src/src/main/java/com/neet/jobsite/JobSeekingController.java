package com.neet.jobsite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.bal.IJobDescriptionService;
import com.neet.jobsite.bal.IJobSeekingService;
import com.neet.jobsite.dal.ICompanyManager;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.response.CandidateJobRow;
import com.neet.jobsite.response.CandidateJobListResponse;

@Controller
@RequestMapping(value="/jobSeekingPage/**")
public class JobSeekingController {
	
	@Resource(name="jobSeekingService")
	private IJobSeekingService jobSeekingService;
	
	@Resource(name="jobDescriptionService")
	private IJobDescriptionService jobDescriptionService;
	
	private CandidateJobListResponse jobListResponse = new CandidateJobListResponse();
	
	@RequestMapping(value="/jobDescription/{id1}&{id2}", method = RequestMethod.GET)
	public String jobDescriptionPage(@PathVariable("id1") String id1, @PathVariable("id2") String id2, Model model) {
		System.out.print(id1+ " " + id2);
		
		//Job specificJob = new Job();
		
		List<CandidateJobRow> candidatejobrows = jobListResponse.getJobRows();
		
		CandidateJobRow matchedJob = new CandidateJobRow();
		
//		if(candidatejobrows != null) {
//			for(CandidateJobRow item : candidatejobrows) {
//				if(item.getJobId() == Long.parseLong(id2)) {
//					matchedJob = item;
//					model.addAttribute("jobDescription", matchedJob);
//					return "jobSeekingPage/jobDescriptionPage";
//				} else {
//					return "jobSeekingPage/jobSeekingPage";
//				}
//			}
//		}
		
		matchedJob = jobDescriptionService.getJobDescription(Long.parseLong(id2));
		model.addAttribute("jobDescription", matchedJob);
		
		return "jobSeekingPage/jobDescriptionPage";
	}
	
	@RequestMapping(value = "/Homepage", method = RequestMethod.GET)
	public String jobSeekingPage(Locale locale, Model model) {
		List<Company> result = jobSeekingService.getCompanys();
		
		List<Integer> userIds = new ArrayList<Integer>();
		List<Job> jobs = new ArrayList<Job>();
		List<Job> finalJobList= new ArrayList<Job>();
		
		List <String> jobsPosition = new ArrayList<String>();
		List <String> jobsLocation = new ArrayList<String>();
		
		
		
		for(Company item : result) {
			userIds.add((int) item.getUserID());
		}
		
		for (Integer i : userIds) {
			jobs = jobSeekingService.GetJobsByUserId(i);
			
			for(Job item : jobs) {
				finalJobList.add(item);
			}
			jobs = new ArrayList<Job>();
		}
		
		for(Job it : finalJobList) {
			jobsPosition.add(it.getTitle());
			jobsLocation.add(it.getLocation());
		}
		
		jobListResponse = jobSeekingService.createJobRow(result, finalJobList);
		
		model.addAttribute("jobList", jobListResponse);
		model.addAttribute("positionDD", jobsPosition);
		model.addAttribute("locationDD", jobsLocation);
		
		return "jobSeekingPage/jobSeekingPage";
	}

}
