package com.neet.jobsite.dal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			Query query = this.sessionFactory.getCurrentSession().createQuery("From User where Email = :Email AND password = :Password");
			query.setParameter("Email", username);
			query.setParameter("Password", password);
			List userList  = query.list();
			if(userList != null && userList.isEmpty()==false)
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
}
