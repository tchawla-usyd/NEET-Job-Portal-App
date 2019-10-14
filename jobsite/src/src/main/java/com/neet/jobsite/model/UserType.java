package com.neet.jobsite.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserType")
public class UserType implements Serializable {
	/*
	  `UID` INT(11) NOT NULL,
	  `Type` INT(11) NULL DEFAULT NULL,
	  `Description` VARCHAR(255) NULL DEFAULT NULL,
	  `CreatedDate` DATETIME NULL DEFAULT NULL,
	  */
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	
	private Integer Type;
	private String Description;
	private Date CreatedDate;
	
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public Date getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}
}
