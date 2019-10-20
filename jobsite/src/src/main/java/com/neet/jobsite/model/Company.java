package com.neet.jobsite.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CompanyInfo")
public class Company implements Serializable {
	/*
	  `UID` INT(11) NOT NULL,
	  `CompanyName` VARCHAR(45) NULL DEFAULT NULL,
	  `BusinessPhone` VARCHAR(45) NULL DEFAULT NULL,
	  `WebSite` VARCHAR(45) NULL DEFAULT NULL,
	  `CompanyImage` VARCHAR(45) NULL DEFAULT NULL,
	  `UserID` INT(11) NULL DEFAULT NULL,
	 */
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private long id;
	
	@Column(name="CompanyName")
	private String CompanyName;
	
	@Column(name="BusinessPhone")
	private String BusinessPhone;
	
	@Column(name="WebSite")
	private String WebSite;
	
	@Column(name="CompanyImage")
	private String CompanyImage;
	
	@Column(name="UserID")
	private long UserID;
	
	public String getCompanyName() {
		return CompanyName;
	}
	
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	
	public String getBusinessPhone() {
		return BusinessPhone;
	}
	
	public void setBusinessPhone(String businessPhone) {
		BusinessPhone = businessPhone;
	}
	
	public String getWebSite() {
		return WebSite;
	}
	
	public void setWebSite(String webSite) {
		WebSite = webSite;
	}
	
	public String getCompanyImage() {
		return CompanyImage;
	}
	
	public void setCompanyImage(String companyImage) {
		CompanyImage = companyImage;
	}
	
	public long getUserID() {
		return UserID;
	}
	
	public void setUserID(long userID) {
		UserID = userID;
	}
}
