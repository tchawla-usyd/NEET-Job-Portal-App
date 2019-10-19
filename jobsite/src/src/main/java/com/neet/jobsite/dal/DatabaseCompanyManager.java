package com.neet.jobsite.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;

@Service(value="companyManager")
@Transactional
public class DatabaseCompanyManager implements ICompanyManager{
	/**
	 * Default serialization version
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseCompanyManager.class);

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addCompany(Company Company) {
		this.sessionFactory.getCurrentSession().save(Company);
	}
	
	@Override
	public ArrayList<Company> getCompanys() {
		final List<Company> list = this.sessionFactory.getCurrentSession().createQuery("FROM Company").list();
		return new ArrayList<Company>(list);
	}

	@Override
	public Company getCompanyById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Company Company = (Company) currentSession.get(Company.class, id);
		return Company;
	}

	@Override
	public void updateCompany(Company Company) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(Company);
	}

	@Override
	public void deleteCompany(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		Company product = (Company) currentSession.get(Company.class, id);
		currentSession.delete(product);
	}
	
	@Override
	public Company GetCompnayByUserId(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		List<Company> companies  = currentSession.createCriteria(Company.class)
				.add(Restrictions.eq("UserID", id))
				.list();
		return companies.get(0);
	}
}
