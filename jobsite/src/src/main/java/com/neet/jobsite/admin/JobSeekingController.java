package com.neet.jobsite.admin;


import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.dal.SkillSetManager;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;

@Controller
@RequestMapping(value="/jobSeekingPage/**")
public class JobSeekingController {

	
	@Resource(name="skillSetManager")
	private SkillSetManager skillSetManager;
	
	@RequestMapping(value = "/Homepage", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		model.addAttribute("login",new User());
		return "jobSeekingPage/Homepage";
	}
	
	//Methods to pull data from database for images, positions, locations, job listings, user profile link
	
	
}
