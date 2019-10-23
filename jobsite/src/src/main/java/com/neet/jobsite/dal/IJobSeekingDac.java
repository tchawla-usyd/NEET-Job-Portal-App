package com.neet.jobsite.dal;

import java.util.ArrayList;
import java.util.List;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;

public interface IJobSeekingDac {

	List<Company> getCompanys();

	List<Job> GetJobsByUserId(Integer id);

	List<SkillsForJob> getSkillSet(Integer id);

	List<SkillSet> getSkillNameSet(long skillID);
}
