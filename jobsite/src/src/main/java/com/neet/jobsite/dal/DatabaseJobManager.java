package com.neet.jobsite.dal;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.JobController;
import com.neet.jobsite.exception.NoSkillsException;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.JobCategory;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.SkillsForJob;
import com.neet.jobsite.model.User;


@Repository(value = "jobDAO")
@Transactional
public class DatabaseJobManager implements JobManager {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6086269742818995787L;
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Job> getJobs() {
		List<Job> list = this.sessionFactory.getCurrentSession().createQuery("FROM Job").list();
		
		if(list.isEmpty()) {
			return new ArrayList<Job>();
		}
		
		return list;
	}

	@Override
	public void addJob(Job job) {
		this.sessionFactory.getCurrentSession().save(job);
	}

	@Override
	public Job getJobById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Job job = (Job) currentSession.get(Job.class, id);
		return job;
	}
	
	@Override
	public List<Job> getJobByEmployer(long employer_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Job WHERE UserID = :id");
		query.setParameter("id", (int) employer_id);
	
		List<Job> list = query.list();
		return list;
	}

	@Override
	public List<Job> getJobByCandidate(long candidate_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT JobID FROM CandidateJobApplied WHERE UserID = :id");
		query.setParameter("id", (int) candidate_id);
	
		List<Long> ids = new ArrayList<Long>();		
		
		if(query.list().isEmpty())
			return new ArrayList<Job>();
		
		for(Object id: query.list()) {
			Integer intID = (Integer) id;
			ids.add(new Long(intID));
		}
				
		Query jobQuery = session.createQuery("FROM Job WHERE Id in (:ids)");
		jobQuery.setParameterList("ids", ids);
		
		List<Job> jobs = jobQuery.list();
		
		return jobs;
	}

	@Override
	public void updateJob(Job job) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		System.out.println(job.getStartDate());
		System.out.println(job.getId());
		currentSession.merge(job);

	}

	@Override
	public void deleteJob(Job job) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.delete(job);

	}

	@Override
	public JobCategory getJobCategoryById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		JobCategory jobCategory = (JobCategory) currentSession.get(JobCategory.class, id);
		return jobCategory;
		
	}

	@Override
	public void addSkillToJob(SkillsForJob jobSkill){
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM SkillsForJob WHERE JobID = :jid AND SkillID = :sid");
		query.setParameter("jid", jobSkill.getJobID());
		query.setParameter("sid", jobSkill.getSkillID());
		
		if(query.list().isEmpty()) {
			currentSession.save(jobSkill);
		}
		
		
	}
	
	@Override
	public void deleteSkillFromJob(SkillsForJob jobSkill) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.delete(jobSkill);
		
	}
	
	@Override
	public List<SkillSet> getSkillsByJob(Integer jobId) throws NoSkillsException {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("SELECT sj.SkillID FROM SkillsForJob sj WHERE sj.JobID = :id");
		query.setParameter("id", jobId);
				
		if (query.list().isEmpty()) {
			throw new NoSkillsException("This job does not require any skills");
		}
		
		List<Long> ids = new ArrayList<Long>();
		
		
		for(Object id: query.list()) {
			Integer intID = (Integer) id;
			ids.add(new Long(intID));
		}
		
		
		Query skillQuery = currentSession.createQuery("FROM SkillSet WHERE Id in (:ids)");
		skillQuery.setParameterList("ids", ids);
		
		List<SkillSet> skills = skillQuery.list();
		
		System.out.println(ids);
		System.out.println(skills);
		
		return skills;
	}


	@Override
	public SkillsForJob getSkillsForJob(Integer jobId, Integer skillId) throws NoSkillsException {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM SkillsForJob js WHERE JobID = :jid AND SkillID = :sid");
		query.setParameter("jid", jobId);
		query.setParameter("sid", skillId);
		
		List<SkillsForJob> skills = query.list();
		
		if (query.list().isEmpty()) {
			throw new NoSkillsException("No such skill exists");
		}
		
		return skills.get(0);
	}

	@Override
	public Company getCompanyByCreator(Integer userID) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM Company WHERE UserID = :id");
		query.setParameter("id", new Long(userID));

		return (Company) query.list().get(0);
	}
	
	
	

}
