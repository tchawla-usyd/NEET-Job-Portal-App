package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.List;

import com.neet.jobsite.model.User;

public interface IUserService {

	ArrayList<User> GetAllUsers();

	User AddUser(String firstName, String lastName, String email, String password, Integer userType);

	User GetUserById(long userId);

	ArrayList<User> GetUserByType(Integer userId);

}