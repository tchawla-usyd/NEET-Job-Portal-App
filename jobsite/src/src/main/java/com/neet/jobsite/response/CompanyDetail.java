package com.neet.jobsite.response;

import java.io.Serializable;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.User;

public class CompanyDetail implements Serializable {
	
	private Company company;
	private User user;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
