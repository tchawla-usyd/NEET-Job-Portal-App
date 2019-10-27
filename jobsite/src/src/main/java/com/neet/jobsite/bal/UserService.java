package com.neet.jobsite.bal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.IUserManager;
import com.neet.jobsite.model.CandidateSkills;
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
				
				//adding skills to candidateskills
				CandidateSkills userCandidateSkills = new CandidateSkills();
				userCandidateSkills.setUserID((int)newUser.getId());
				userCandidateSkills.setSkillID((int)userSkills.getId());
				userCandidateSkills.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				
				this.userManager.addCandidateSkills(userCandidateSkills);
			}

			// Adding education and experience in the DB

			candidateInfo userInfo = new candidateInfo();
			userInfo.setId((int) newUser.getId());
			userInfo.setEducation(education);
			userInfo.setExperience(experience);
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
	public boolean updateUser(long userId, String education, String experience, List<String> skills) {

		try {
			// getting user details based on the email id
			//getUser = this.userManager.getUserByEmail(email);

			// deleting candidate info and adding updated info if education and experience are not empty
			if(education!=null && experience!=null) {
				candidateInfo userInfo = new candidateInfo();
				userInfo.setId(userId);
				userInfo.setEducation(education);
				userInfo.setExperience(experience);
				
				this.userManager.deleteCandidateInfor(userId);
				
				this.userManager.addUserInfo(userInfo);
			} else if(education!=null) {
				this.userManager.updateEducation(userId, education);
			} else if(experience!=null) {
				this.userManager.updateExperience(userId, experience);
			}
			if(skills!= null) {
				// deleting skills
				this.userManager.deleteSkills(userId);	
				
				//Deleting from candidate skills table
				this.userManager.deleteCandidateSkills(userId);

				// Adding updated skills
				SkillSet userSkills;
				for (String item : skills) {
					userSkills = new SkillSet();

					userSkills.setCreatedBy((int) userId);
					userSkills.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					userSkills.setName(item);

					this.userManager.addSkills(userSkills);
					
					//Adding into Candidate skills table
					CandidateSkills userCandidateSkills = new CandidateSkills();
					userCandidateSkills.setUserID((int)userId);
					userCandidateSkills.setSkillID((int)userSkills.getId());
					userCandidateSkills.setCreatedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
					
					this.userManager.addCandidateSkills(userCandidateSkills);
				}
				
			}
			return true;
		} catch (Exception e) {
			return false;
		}
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
}
