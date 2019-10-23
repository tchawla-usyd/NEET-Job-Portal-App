package com.neet.jobsite.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;
import com.neet.jobsite.model.candidateInfo;

public interface IUserManager {
	
    public ArrayList<User> getUsers();
    
    public void addUser(User User);
    
    public User getUserById(long id);
    
    public void updateUser(User User);
    
    public void deleteUser(long id);

    ArrayList<User> getUserByType(Integer id);

	public void addSkills(SkillSet userSkills);

	public void addUserInfo(candidateInfo userSkills);

	public void addCompanyInfo(Company userCompany);
    
}
