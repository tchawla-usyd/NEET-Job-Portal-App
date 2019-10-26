package com.neet.jobsite.bal;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.ICompanyManager;
import com.neet.jobsite.dal.IUserManager;
import com.neet.jobsite.model.Company;
import com.neet.jobsite.response.CompanyDetail;

@Service(value = "companyService")
public class CompanyService implements ICompanyService {

	@Resource(name="companyManager")
	private ICompanyManager companyManager;
	
	@Resource(name = "userManager")
	private IUserManager userManager;
	
	@Override
	public ArrayList<CompanyDetail> GetAllCompanies(){
		ArrayList<CompanyDetail> companyDetails = new ArrayList<CompanyDetail>();
		ArrayList<Company> companies = this.companyManager.getCompanys();
		for(int i=0; i< companies.size(); i++) {
			CompanyDetail detail = new CompanyDetail();
			detail.setCompany(companies.get(i));
			detail.setUser(userManager.getUserById(detail.getCompany().getUserID())); 
			companyDetails.add(detail);
		}
		return companyDetails;
	}
}