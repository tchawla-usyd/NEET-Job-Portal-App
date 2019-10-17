package com.neet.jobsite.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.model.User;

@Service(value="userAuthenticationDac")
@Transactional
public class UserAuthenticationDac implements IUserAuthenticationDac {
	
	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public boolean Authenticate(String username, String password) {
		try {
			//Query query = this.sessionFactory.getCurrentSession().createQuery("From User where Email = :Email AND password = :Password");
//			Query query = this.sessionFactory.getCurrentSession().createQuery("From User");
////			query.setParameter("Email", username);
////			query.setParameter("Password", password);
			Session currentSession = this.sessionFactory.getCurrentSession();
			List userList  = currentSession.createCriteria(User.class)
					.add(Restrictions.eq("Email", username))
					.add(Restrictions.eq("Password", password))
					.list();
			if(userList != null && userList.isEmpty()==false)
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
}
