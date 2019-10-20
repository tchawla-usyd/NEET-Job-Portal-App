package com.neet.jobsite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="candidateinfo")
public class Candidate {
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	@Column(name="education")
	private String Education;
	
	@Column(name="experience")
	private String Experience;
	
	@Column(name="resume")
	private String Resume;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getExperience() {
		return Experience;
	}

	public void setExperience(String experience) {
		Experience = experience;
	}

	public String getResume() {
		return Resume;
	}

	public void setResume(String resume) {
		Resume = resume;
	}
	
}
