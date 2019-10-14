package com.neet.jobsite.model;

import java.sql.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="skill")
public class SkillSet implements Serializable {
	
	@Id
	@Column(name="UID")
	@GeneratedValue
	private long UID;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Column(name="CreatedBy")
	private Integer CreatedBy;
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Integer getCreatedBy() {
		return CreatedBy;
	}
	
	public void setCreatedBy(Integer createdBy) {
		CreatedBy = createdBy;
	}
	
	public String toString() {
	    StringBuffer buffer = new StringBuffer();
        buffer.append("Name: " + this.name + ";");
        return buffer.toString();
	}

	public long getUID() {
		return UID;
	}

	public void setUID(long uID) {
		UID = uID;
	}
}
