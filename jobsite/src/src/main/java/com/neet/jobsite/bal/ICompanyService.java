package com.neet.jobsite.bal;

import java.util.ArrayList;

import com.neet.jobsite.model.Company;
import com.neet.jobsite.model.Job;
import com.neet.jobsite.response.CompanyDetail;

public interface ICompanyService {

	ArrayList<CompanyDetail> GetAllCompanies();

	Company GetCompanyById(long id);

	ArrayList<Job> GetJobsByCompanys(long id);

}