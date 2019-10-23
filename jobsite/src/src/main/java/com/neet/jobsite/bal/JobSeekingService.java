package com.neet.jobsite.bal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.ICompanyManager;
import com.neet.jobsite.dal.IJobSeekingDac;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;
import com.neet.jobsite.response.CandidateJobRow;
import com.neet.jobsite.response.CandidateJobListResponse;

@Service(value = "jobSeekingService")
public class JobSeekingService implements IJobSeekingService{

	@Resource(name = "jobSeekingDac")
	private IJobSeekingDac jobSeekingDac;
	
	@Override
	public List<Company> getCompanys(){
		return this.jobSeekingDac.getCompanys();
	}

	@Override
	public List<Job> GetJobsByUserId(Integer id) {
		return this.jobSeekingDac.GetJobsByUserId(id);
	}

	@Override
	public CandidateJobListResponse createJobRow(List<Company> companyList, List<Job> jobList) {
		
		CandidateJobListResponse fakeresponse = new CandidateJobListResponse();
		List<CandidateJobRow> jobRows = new ArrayList<CandidateJobRow>();
		List<SkillsForJob> skillSet;
		List<SkillSet> skillNameSet;
		List<String> skills;
		
		//get list of skills as well
		
		for(Job item : jobList) {
			CandidateJobRow row1 = new CandidateJobRow();
			
			row1.setJobTitle(item.getTitle());
			row1.setLocation(item.getLocation());
			row1.setDateAdded(item.getStartDate());
			row1.setJobId(item.getId());
			row1.setJobDescription(item.getJobDescription());
			
			//getting company name
			for(Company item1 : companyList) {
				if(item1.getUserID() == item.getUserID()) {
					row1.setCompany(item1.getCompanyName());
				}
			}
			
			
			//getting set of skills
			skillSet = new ArrayList<SkillsForJob>();
			skillSet = this.jobSeekingDac.getSkillSet((int)item.getId());
			
			//now fetching skill name list
			skillNameSet = new ArrayList<SkillSet>();
			skills = new ArrayList<String>();
			
			for(SkillsForJob skillId : skillSet) {
				skillNameSet = this.jobSeekingDac.getSkillNameSet((long)skillId.getSkillID());
				for(SkillSet itemName:skillNameSet) {
					skills.add(itemName.getName());
				}
				skillNameSet = new ArrayList<SkillSet>();
			}
			
			row1.setSkills(skills);
			
			jobRows.add(row1);
			fakeresponse.setJobRows(jobRows);

		}
		return fakeresponse;
		
	}
}
