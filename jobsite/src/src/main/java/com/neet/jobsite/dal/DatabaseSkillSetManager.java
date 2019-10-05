package com.neet.jobsite.dal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.SkillSet;

@Service(value="skillSetManager")
@Transactional
public class DatabaseSkillSetManager implements SkillSetManager {

	/**
	 * Default serialization version
	 */
	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addSkillSet(SkillSet skillSet) {
		this.sessionFactory.getCurrentSession().save(skillSet);
	}
	
	@Override
	public List<SkillSet> getSkillSets() {
		List<SkillSet> list = this.sessionFactory.getCurrentSession().createQuery("FROM SkillSet").list();
		return list;
	}

	@Override
	public SkillSet getSkillSetById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		SkillSet skillSet = (SkillSet) currentSession.get(SkillSet.class, id);
		return skillSet;
	}

	@Override
	public void updateSkillSet(SkillSet skillSet) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(skillSet);
	}

	@Override
	public void deleteSkillSet(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		SkillSet product = (SkillSet) currentSession.get(SkillSet.class, id);
		currentSession.delete(product);
	}

}
