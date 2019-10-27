package com.neet.jobsite.admin;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.bal.ICompanyService;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.model.User;
import com.neet.jobsite.response.CompanyDetail;

@Controller
@RequestMapping(value = "/admin/company/**")
public class CompanyAdministrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyAdministrationController.class);
	
	@Resource(name = "companyService")
	private ICompanyService companyService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:admin/home";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String employers(Locale locale, Model model) {
		ArrayList<CompanyDetail> companyDetails = this.companyService.GetAllCompanies();
		model.addAttribute("companies", companyDetails);
		return "admin/employers/employerslists";
	}
}
