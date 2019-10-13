package com.neet.jobsite.model;

import java.time.LocalDateTime;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Table(name="jobcategory")
public class JobCategory {
	/*
	  `Name` VARCHAR(45) NULL DEFAULT NULL,
	  `Description` VARCHAR(45) NULL DEFAULT NULL,
	  `CreatedBy` INT(11) NULL DEFAULT NULL,
	  `CreateDate` DATETIME NULL DEFAULT NULL,
	  */
	
	@Id
	@GeneratedValue
	@Column(name="UID")
	private long UID;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Description")
	private String Description;
	
	@Column(name="CreatedBy")
	private Integer CreatedBy;
	
	@Column(name="CreatedDate")
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
	public long getUID() {
		return UID;
	}
	public void setUID(long uID) {
		UID = uID;
	}
	
}
