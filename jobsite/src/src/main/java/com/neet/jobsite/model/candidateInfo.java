package com.neet.jobsite.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class candidateInfo {
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long Id;
	
	@Column(name="education")
	private String education;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(name="experience")
	private String experience;
	
	@Column(name="resume")
	private String resume;
	
	

}
