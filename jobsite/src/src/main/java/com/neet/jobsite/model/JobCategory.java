package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JobCategory")
public class JobCategory implements Serializable{
	/*
	  `Name` VARCHAR(45) NULL DEFAULT NULL,
	  `Description` VARCHAR(45) NULL DEFAULT NULL,
	  `CreatedBy` INT(11) NULL DEFAULT NULL,
	  `CreateDate` DATETIME NULL DEFAULT NULL,
	  */
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	private String Name;
	private String Description;
	private Integer CreatedBy;
	private Date CreatedDate;
	
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
	
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
	
}
