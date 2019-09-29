package com.neet.jobsite.model;

import java.time.LocalDateTime;

public class JobCategory {
	/*
	  `Name` VARCHAR(45) NULL DEFAULT NULL,
	  `Description` VARCHAR(45) NULL DEFAULT NULL,
	  `CreatedBy` INT(11) NULL DEFAULT NULL,
	  `CreateDate` DATETIME NULL DEFAULT NULL,
	  */
	
	private String Name;
	private String Description;
	private Integer CreatedBy;
	private LocalDateTime CreatedDate;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public Integer getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(Integer createdBy) {
		CreatedBy = createdBy;
	}
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
	
}
