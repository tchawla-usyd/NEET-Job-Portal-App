package com.neet.jobsite.model;

import java.time.LocalDateTime;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="SkillSet")
public class SkillSet implements Serializable {
	
	@Id
	@Column(name="UID")
	@GeneratedValue
	private long id;
	
	//@Column(name="Name")
	private String Name;
	
	//@Column(name="CreatedDate")
	private LocalDateTime CreatedDate;
	
	@Column(name="CreatedBy")
	@Type(type = "org.hibernate.type.IntegerType")
	private Integer CreatedBy;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
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
