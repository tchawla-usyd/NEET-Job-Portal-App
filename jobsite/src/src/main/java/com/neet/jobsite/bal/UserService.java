package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.IUserManager;
import com.neet.jobsite.model.User;

@Service(value = "userService")
public class UserService implements IUserService {
	@Resource(name = "userManager")
	private IUserManager userManager;
	
	@Override
	public ArrayList<User> GetAllUsers(){
		return this.userManager.getUsers();
	}
	
	@Override
	public User AddUser(String firstName, String lastName, String email, String password, Integer userType) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setUserTypeID(userType);
		newUser.setIsActive(true);
		newUser.setIsLocked(false);
		newUser.setCreateDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newUser.setModifiedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		this.userManager.addUser(newUser);
		return newUser;
	}
}
