package com.neet.jobsite.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.neet.jobsite.model.CandidateSkills;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;
import com.neet.jobsite.model.candidateInfo;

public interface IUserManager {
	
    public ArrayList<User> getUsers();
    
    public void addUser(User User);
    
    public User getUserById(long email);
    
    public void updateUser(User User);
    
    public void deleteUser(long id);

    ArrayList<User> getUserByType(Integer id);

	public void addSkills(SkillSet userSkills);

	public void addUserInfo(candidateInfo userSkills);

	public void addCompanyInfo(Company userCompany);

	public User getUserByEmail(String email);

	public void deleteCandidateInfor(long id);

	public void deleteSkills(long id);

	public void addCandidateSkills(CandidateSkills userCandidateSkills);

	public void updateEducation(long userId, String education);

	public void updateExperience(long userId, String experience);

	public void deleteCandidateSkills(long userId);
    
}
