package com.neet.jobsite.dal;

import java.util.ArrayList;

import com.neet.jobsite.model.Company;

public interface ICompanyManager {
public ArrayList<Company> getCompanys();
    
    public void addCompany(Company Company);
    
    public Company getCompanyById(long id);
    
    public void updateCompany(Company Company);
    
    public void deleteCompany(long id);

	Company GetCompnayByUserId(long id);
}
