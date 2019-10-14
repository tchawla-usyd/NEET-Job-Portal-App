package com.neet.jobsite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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
	
	private String CompanyName;
	private String BusinessPhone;
	private String WebSite;
	private String CompanyImage;
	private Integer UserID;
	
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
	
	public Integer getUserID() {
		return UserID;
	}
	
	public void setUserID(Integer userID) {
		UserID = userID;
	}
}
