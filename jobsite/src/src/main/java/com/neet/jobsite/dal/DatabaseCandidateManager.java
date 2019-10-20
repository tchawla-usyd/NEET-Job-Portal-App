package com.neet.jobsite.dal;

import org.hibernate.SessionFactory;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.Job;

@Repository(value="candidateManager")
@Transactional
public class DatabaseCandidateManager implements CandidateManager{
	
	private static final long serialVersionUID = 6086269742818995788L;
	private SessionFactory sessionFactory;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void applyJob(CandidateJobApplied application) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.save(application);
		
	}
	
	@Override
	public void addCandidateInfo(Candidate candidate) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.save(candidate);
	}

	@Override
	public void editCandidateInfo(Candidate candidate) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(candidate);
		
	}

	@Override
	public Candidate getCandidateById(Integer userId) {
		System.out.println(userId);
		System.out.println(this.sessionFactory);
		Session currentSession = this.sessionFactory.getCurrentSession();
		Candidate candidate = (Candidate) currentSession.get(Candidate.class, new Long(userId));
		return candidate;
	}
}
