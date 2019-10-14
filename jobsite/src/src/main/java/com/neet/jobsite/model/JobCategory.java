package com.neet.jobsite.model;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private long Id;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="Description")
	private String Description;
	
	@Column(name="CreatedBy")
	private Integer CreatedBy;
	
	@Column(name="CreatedDate")
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
	
	public long getId() {
		return this.Id;
	}
	
	public void setUID(long id) {
		this.Id = id;
	}
	
}
