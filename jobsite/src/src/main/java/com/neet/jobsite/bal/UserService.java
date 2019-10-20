package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.IUserManager;
import com.neet.jobsite.model.User;
import com.neet.jobsite.response.UserDetailResponse;

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
		newUser.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		this.userManager.addUser(newUser);
		return newUser;
	}
	
	public UserDetailResponse getUser(Integer userId) {
		User user = userManager.getUserById(userId);
		
		UserDetailResponse res = new UserDetailResponse();
		res.setId(user.getId());
		res.setFirstName(user.getFirstName());
		res.setLastName(user.getLastName());
		res.setEmail(user.getEmail());
		res.setUserTypeID(user.getUserTypeID());
		res.setCreatedDate(user.getCreateDate());
		res.setModifiedDate(user.getModifiedDate());
		res.setIsActive(user.isIsActive());
		res.setIsLocked(user.isIsLocked());
		
		return res;
	}
}
