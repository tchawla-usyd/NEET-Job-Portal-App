package com.neet.jobsite.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.Job;


@Service(value="jobManager")
@Transactional
public class DatabaseJobManager implements JobManager {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public List<Job> getJobs() {
		List<Job> list = this.sessionFactory.getCurrentSession().createQuery("FROM Job").list();
		return list;
	}

	@Override
	public void addJob(Job job) {
		this.sessionFactory.getCurrentSession().save(job);
	}

	@Override
	public Job getSkillSetById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Job job = (Job) currentSession.get(Job.class, id);
		return job;
	}

	@Override
	public List<Job> getSkillSetByEmployee(long employee_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Job WHERE UserID = :id");
		query.setParameter("id", employee_id);
	
		List<Job> list = query.list();
		return list;
	}

	@Override
	public List<Job> getSkillSetByCandidate(long candidate_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Job WHERE UserID = :id");
		query.setParameter("id", candidate_id);
	
		List<Job> list = query.list();
		return list;
	}

	@Override
	public void updateJob(Job job) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(job);

	}

	@Override
	public void deleteJob(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Job job = (Job) currentSession.get(Job.class, id);
		currentSession.delete(job);

	}

}
