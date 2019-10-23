package com.neet.jobsite.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;

@Service(value="jobDescriptionDac")
@Transactional
public class JobDescriptionDac implements IJobDescriptionDac{
	
private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public ArrayList<Job> getJobDescription(long jobId) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<Job> jobs  = currentSession.createCriteria(Job.class)
				.add(Restrictions.eq("Id", jobId)).list();
		return new ArrayList<Job>(jobs);
	}

	@Override
	public ArrayList<SkillsForJob> getSkillIds(long id2) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<SkillsForJob> skillSet  = currentSession.createCriteria(SkillsForJob.class)
				.add(Restrictions.eq("JobID", (int)id2))
				.list();
		return new ArrayList<SkillsForJob>(skillSet);
	}

	@Override
	public List<SkillSet> getSkillNameSet(long skillID) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<SkillSet> skillName  = currentSession.createCriteria(SkillSet.class)
				.add(Restrictions.eq("Id", skillID)).list();
		return new ArrayList<SkillSet>(skillName);
	}

	@Override
	public Company getCompany(long userID) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Company> companies  = currentSession.createCriteria(Company.class)
				.add(Restrictions.eq("UserID", userID))
				.list();
		return companies.get(0);
	}
	
}
