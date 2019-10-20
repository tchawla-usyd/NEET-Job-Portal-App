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

import com.neet.jobsite.model.User;

@Service(value="userManager")
@Transactional
public class DatabaseUserManager implements IUserManager {

	/**
	 * Default serialization version
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseUserManager.class);

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addUser(User User) {
		this.sessionFactory.getCurrentSession().save(User);
	}
	
	@Override
	public ArrayList<User> getUsers() {
		final List<User> list = this.sessionFactory.getCurrentSession().createQuery("FROM User").list();
		return new ArrayList<User>(list);
	}

	@Override
	public User getUserById(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User User = (User) currentSession.get(User.class, id);
		return User;
	}
	
	@Override
	public List<User> getUserByType(Integer id) {
		
		final List<User> list = this.sessionFactory.getCurrentSession().createQuery("FROM User").list();
		return new ArrayList<User>(list);
		
//		Session currentSession = this.sessionFactory.getCurrentSession();
//		final List<User> userList  = currentSession.createCriteria(User.class)
//				.add(Restrictions.eq("UserTypeID", id))
//				.list();
//		return userList;
	}

	@Override
	public void updateUser(User User) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.merge(User);
	}

	@Override
	public void deleteUser(long id) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		User product = (User) currentSession.get(User.class, id);
		currentSession.delete(product);
	}
	
}
