package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.ArrayList;

import com.neet.jobsite.model.Company;

public interface ICompanyManager extends Serializable{

	public ArrayList<Company> getCompanys();
    
    public void addCompany(Company Company);
    
    public Company getCompanyById(long id);
    
    public void updateCompany(Company Company);
    
    public void deleteCompany(long id);

	Company GetCompanyByUserId(long id);

	ArrayList<com.neet.jobsite.model.Job> GetJobsByCompanys(long userId);
}
