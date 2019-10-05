package com.neet.jobsite.model;

import java.time.LocalDateTime;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="skillset")
public class SkillSet extends BaseObject {
	
	@Id
	@GeneratedValue
	@Column(name="UID")
	private long UID;
	
	@Column(name="Name")
	private String Name;
	
	@Column(name="CreatedDate")
	private LocalDateTime CreatedDate;
	
	@Column(name="CreatedBy")
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
	
	public String toString() {
		 StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + Name + ";");
        return buffer.toString();
	}
}
