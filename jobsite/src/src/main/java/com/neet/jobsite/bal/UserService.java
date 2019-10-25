package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.IUserManager;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;
import com.neet.jobsite.model.candidateInfo;
import com.neet.jobsite.response.UserDetailResponse;

@Service(value = "userService")
public class UserService implements IUserService {
	@Resource(name = "userManager")
	private IUserManager userManager;

	@Override
	public ArrayList<User> GetAllUsers() {
		return this.userManager.getUsers();
	}

	/*
	 * @Override public User AddUser(String firstName, String lastName, String
	 * email, String password, Integer userType) { User newUser = new User();
	 * newUser.setFirstName(firstName); newUser.setLastName(lastName);
	 * newUser.setEmail(email); newUser.setPassword(password);
	 * newUser.setUserTypeID(userType); newUser.setIsActive(true);
	 * newUser.setIsLocked(false); newUser.setCreateDate(new
	 * java.sql.Date(Calendar.getInstance().getTime().getTime()));
	 * newUser.setModifiedDate(new
	 * java.sql.Date(Calendar.getInstance().getTime().getTime()));
	 * newUser.setCreatedDate(new
	 * java.sql.Date(Calendar.getInstance().getTime().getTime()));
	 * this.userManager.addUser(newUser); return newUser; }
	 */

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

	@Override
	public User GetUserById(long userId) {
		User user = this.userManager.getUserById(userId);
		return user;
	}

	@Override
	public ArrayList<User> GetUserByType(Integer userType) {
		ArrayList<User> users = this.userManager.getUserByType(userType);
		return users;
	}

	@Override
	public void AddUser(String firstName, String lastName, String email, String password, Integer userIntTypeValue,
			List<String> skills, String education, String experience, String companyName) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setUserTypeID(userIntTypeValue);
		newUser.setIsActive(true);
		newUser.setIsLocked(false);
		newUser.setCreateDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newUser.setModifiedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newUser.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		this.userManager.addUser(newUser);

		// job seeker
		if (userIntTypeValue == 4) {
			// Adding skills in the database
			SkillSet userSkills;
			for (String item : skills) {
				userSkills = new SkillSet();

				userSkills.setCreatedBy((int) newUser.getId());
				userSkills.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				userSkills.setName(item);

				this.userManager.addSkills(userSkills);
			}

			// Adding education and experience in the DB

			candidateInfo userInfo = new candidateInfo();
			userInfo.setId((int) newUser.getId());
			userInfo.setEducation(education);
			userInfo.setExperience(experience);
			userInfo.setResume(null);

			this.userManager.addUserInfo(userInfo);
		} else if (userIntTypeValue == 3) {
			// employer
			Company userCompany = new Company();
			userCompany.setCompanyName(companyName);
			userCompany.setUserID((int) newUser.getId());

			this.userManager.addCompanyInfo(userCompany);
		}

	}

	@Override
	public boolean updateUser(String email, String education, String experience, String resume, List<String> skills) {

		User getUser = new User();

		try {
			// getting user details based on the email id
			getUser = this.userManager.getUserByEmail(email);

			// deleting candidate info and adding updated info
			this.userManager.deleteCandidateInfor(getUser.getId());

			// adding Candidate info
			candidateInfo userInfo = new candidateInfo();
			userInfo.setId(getUser.getId());
			userInfo.setEducation(education);
			userInfo.setExperience(experience);
			userInfo.setResume(resume);
			this.userManager.addUserInfo(userInfo);

			// deleting skills
			this.userManager.deleteSkills(getUser.getId());

			// Adding updated skills
			SkillSet userSkills;
			for (String item : skills) {
				userSkills = new SkillSet();

				userSkills.setCreatedBy((int) getUser.getId());
				userSkills.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				userSkills.setName(item);

				this.userManager.addSkills(userSkills);
			}

		} catch (Exception e) {
		}

		return false;
	}

	@Override
	public void AddAdmin(String firstName, String lastName, String email, String password, Integer userIntTypeValue) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setUserTypeID(userIntTypeValue);
		newUser.setIsActive(true);
		newUser.setIsLocked(false);
		newUser.setCreateDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newUser.setModifiedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		newUser.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		this.userManager.addUser(newUser);
	}
	
	
	@Override
	public void UpdateAdmin(User user) {
		this.userManager.updateUser(user);;
	}
}
