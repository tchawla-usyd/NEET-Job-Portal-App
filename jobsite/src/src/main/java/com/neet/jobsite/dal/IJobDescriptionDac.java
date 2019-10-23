package com.neet.jobsite.dal;

import java.util.List;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;

public interface IJobDescriptionDac {

	List<Job> getJobDescription(long jobId);

	List<SkillsForJob> getSkillIds(long id2);

	List<SkillSet> getSkillNameSet(long skillID);

	Company getCompany(long userID);

}
