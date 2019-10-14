package com.neet.jobsite.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserType implements Serializable {
	/*
	  `UID` INT(11) NOT NULL,
	  `Type` INT(11) NULL DEFAULT NULL,
	  `Description` VARCHAR(255) NULL DEFAULT NULL,
	  `CreatedDate` DATETIME NULL DEFAULT NULL,
	  */
	
	private Integer Type;
	private String Description;
	private LocalDateTime CreatedDate;
	
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
	
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
}
