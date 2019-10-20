package com.neet.jobsite.response;

import java.util.List;

import com.neet.jobsite.model.SkillSet;

public class ApplicantsResponse {
	private long user_id;
	private String first_name;
	private String last_name;
	private String email;
	private List<SkillSet> skills;
	private String applyDate;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long l) {
		this.user_id = l;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<SkillSet> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
}
