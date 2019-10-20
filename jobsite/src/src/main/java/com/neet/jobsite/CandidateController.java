package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.bal.CandidateService;
import com.neet.jobsite.bal.JobService;

@Controller
@RequestMapping(value="/candidate/**")
public class CandidateController {
	
	@Resource(name="candidateService")
	private CandidateService candidateService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addApplication(HttpServletRequest httpServletRequest) {
		
		Integer jobId = Integer.parseInt(httpServletRequest.getParameter("job_id"));

		// authorized token to get user id
		String userToken = httpServletRequest.getParameter("token");
		
		candidateService.applyJob(jobId, userToken);
		return "redirect:/home.jsp";
	}
	
		
}
