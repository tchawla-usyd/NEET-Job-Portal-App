package com.neet.jobsite.bal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.ICompanyManager;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.response.CandateJobRow;
import com.neet.jobsite.response.CandidateJobListResponse;

@Service(value ="jobSeekingService")
public class JobSeekingService {
	
	
	@Resource(name="jobSeekingService")
	private ICompanyManager companyManager;
	
	
	public CandidateJobListResponse GetJobResponse(Integer userId) {
		
	//List<Company> companies =	companyManager.getCompanys();
		
		CandidateJobListResponse fakeresponse = new CandidateJobListResponse();
		List<CandateJobRow> jobRows = new ArrayList<CandateJobRow>();
		
		CandateJobRow  row1 = new CandateJobRow();
		row1.setJobTitle("Software engg");
		row1.setCompany("Google");
		row1.setLocation("Sydney");
		List<String> skills = new ArrayList<String>();
		skills.add("Java");
		skills.add("Spring");
		row1.setSkills(skills);
		row1.setDateAdded(new Date(2019,1,1));
		jobRows.add(row1);
		
		CandateJobRow  row2 = new CandateJobRow();
		row2.setJobTitle("Software engg");
		row2.setCompany("Google");
		row2.setLocation("Sydney");
		List<String> skills1 = new ArrayList<String>();
		skills1.add("Java");
		skills1.add("Spring");
		row2.setSkills(skills1);
		row2.setDateAdded(new Date(2019,1,1));
		jobRows.add(row2);
		
		
		fakeresponse.setJobRows(jobRows);
		return fakeresponse;
	}
}
