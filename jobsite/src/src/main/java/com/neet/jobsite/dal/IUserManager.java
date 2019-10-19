package com.neet.jobsite.dal;

import java.util.ArrayList;

import com.neet.jobsite.model.User;

public interface IUserManager {
	
    public ArrayList<User> getUsers();
    
    public void addUser(User User);
    
    public User getUserById(long id);
    
    public void updateUser(User User);
    
    public void deleteUser(long id);
    
}
