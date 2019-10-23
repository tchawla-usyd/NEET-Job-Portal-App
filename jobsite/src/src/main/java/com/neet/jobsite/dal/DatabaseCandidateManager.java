package com.neet.jobsite.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.exception.NoSkillsException;
import com.neet.jobsite.model.Candidate;
import com.neet.jobsite.model.CandidateJobApplied;
import com.neet.jobsite.model.CandidateSkills;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;

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
		Session currentSession = this.sessionFactory.getCurrentSession();
		Candidate candidate = (Candidate) currentSession.get(Candidate.class, new Long(userId));
		return candidate;
	}
	
	@Override
	public List<Candidate> getCandidateByIds(List<Long> ids) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM Candidate WHERE Id IN :ids");
		query.setParameterList("ids", ids);
		List<Candidate> candidates = query.list();

		
		return candidates;
	}

	@Override
	public List<CandidateJobApplied> getApplicationInfo(Long jobId) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("FROM CandidateJobApplied cj WHERE cj.JobID = :id");
		query.setParameter("id", (int) (long) jobId);
				
		if (query.list().isEmpty()) {
			return null;
		}
		
		return query.list();
	}
	
	@Override
	public List<User> getApplicants(Long jobId) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("SELECT cj.UserID FROM CandidateJobApplied cj WHERE cj.JobID = :id");
		query.setParameter("id", (int) (long) jobId);
				
		if (query.list().isEmpty()) {
			return null;
		}
		
		List<Long> ids = new ArrayList<Long>();
		
		
		for(Object id: query.list()) {
			Integer intID = (Integer) id;
			ids.add(new Long(intID));
		}
		
		
		Query userQuery = currentSession.createQuery("FROM User WHERE Id in (:ids)");
		userQuery.setParameterList("ids", ids);
		
		List<User> users = userQuery.list();
		
		System.out.println(ids);
		System.out.println(users);
		
		return users;
	}

	@Override
	public List<SkillSet> getCandidateSkillsById(Long candidateId) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("SELECT SkillID FROM CandidateSkills WHERE UserID = :id");
		query.setParameter("id", (int) (long) candidateId);
		
		List<Long> ids = new ArrayList<Long>();
		
		
		for(Object id: query.list()) {
			Integer intID = (Integer) id;
			ids.add(new Long(intID));
		}
		
		if(ids.isEmpty()) {
			return new ArrayList<SkillSet>();
		}
		Query skillQuery = currentSession.createQuery("FROM SkillSet WHERE Id in (:ids)");
		skillQuery.setParameterList("ids", ids);
		
		List<SkillSet> skillSets = skillQuery.list();
		
		return skillSets;
	}
}
