package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class SkillSet extends BaseObject {
	
	private String Name;
	private LocalDateTime CreatedDate;
	private Integer CreatedBy;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
	
	public Integer getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(Integer createdBy) {
		CreatedBy = createdBy;
	}
	
}
