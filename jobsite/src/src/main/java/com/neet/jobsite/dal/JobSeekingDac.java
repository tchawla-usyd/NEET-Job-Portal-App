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

@Service(value="jobSeekingDac")
@Transactional
public class JobSeekingDac implements IJobSeekingDac{

private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public ArrayList<Company> getCompanys() {
		final List<Company> list = this.sessionFactory.getCurrentSession().createQuery("FROM Company").list();

		return new ArrayList<Company>(list);
	}
	
	@Override
	public ArrayList<Job> GetJobsByUserId(Integer id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<Job> jobs  = currentSession.createCriteria(Job.class)
				.add(Restrictions.eq("UserID", id))
				.list();
		return new ArrayList<Job>(jobs);
	}

	@Override
	public ArrayList<SkillsForJob> getSkillSet(Integer id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<SkillsForJob> skillSet  = currentSession.createCriteria(SkillsForJob.class)
				.add(Restrictions.eq("JobID", id))
				.list();
		return new ArrayList<SkillsForJob>(skillSet);
	}

	@Override
	public ArrayList<SkillSet> getSkillNameSet(long skillID) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		final List<SkillSet> skillName  = currentSession.createCriteria(SkillSet.class)
				.add(Restrictions.eq("Id", skillID)).list();
		return new ArrayList<SkillSet>(skillName);
	}
	
}
