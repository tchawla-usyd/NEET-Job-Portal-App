package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.IJobDescriptionDac;
import com.neet.jobsite.dal.IJobSeekingDac;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;
import com.neet.jobsite.response.CandidateJobRow;

@Service(value = "jobDescriptionService")
public class JobDescriptionService implements IJobDescriptionService {
	
	@Resource(name = "jobDescriptionDac")
	private IJobDescriptionDac jobDescriptionDac;

	@Override
	public CandidateJobRow getJobDescription(long id2) {
		List <Job> specificJob = new ArrayList<Job>();
		List<SkillsForJob> skillSet;
		List<SkillSet> skillNameSet;
		List<String> skills;
		Company companyName;
		
		CandidateJobRow jobDescriptionEntry = new CandidateJobRow();
		
		specificJob = this.jobDescriptionDac.getJobDescription(id2);
		
		//fetching skill Ids
		skillSet = jobDescriptionDac.getSkillIds(id2);
		
		//now fetching skill names
		
		//now fetching skill name list
		skillNameSet = new ArrayList<SkillSet>();
		skills = new ArrayList<String>();
		
		for(SkillsForJob skillId : skillSet) {
			skillNameSet = this.jobDescriptionDac.getSkillNameSet((long)skillId.getSkillID());
			for(SkillSet itemName:skillNameSet) {
				skills.add(itemName.getName());
			}
			skillNameSet = new ArrayList<SkillSet>();
		}
		
		jobDescriptionEntry.setSkills(skills);	
		
		for(Job item : specificJob) {
			jobDescriptionEntry.setJobTitle(item.getTitle());
			jobDescriptionEntry.setLocation(item.getLocation());
			jobDescriptionEntry.setJobDescription(item.getJobDescription());
			jobDescriptionEntry.setDateAdded(item.getStartDate());
			jobDescriptionEntry.setJobId(item.getId());
			//finally getting the company name
			companyName = jobDescriptionDac.getCompany((long)item.getUserID());
			
			jobDescriptionEntry.setCompany(companyName.getCompanyName());
		}
		
		return jobDescriptionEntry;
	}
	
}
