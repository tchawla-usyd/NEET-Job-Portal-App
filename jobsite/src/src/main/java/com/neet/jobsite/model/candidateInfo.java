package com.neet.jobsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidateinfo")
public class candidateInfo {
	
	@Id
	@Column(name="Id")
	private long Id;
	
	@Column(name="education")
	private String education;
	
	@Column(name="experience")
	private String experience;
	
	@Column(name="resume")
	private String resume;
	
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



	
	

}
