package com.neet.jobsite;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.IAuthenticateService;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.model.User;

@Controller
@RequestMapping(value = "/authenticate/**")
public class UserAuthenticationController extends BaseMVCController {

	@Resource(name = "authenticateBal")
	private IAuthenticateService authenticateBal;
	
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:authenticate/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		model.addAttribute("login",new User());
		return "authenticate/login";
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request) {
		String email = request.getParameter("Email");
		String password =	request.getParameter("Password");
		
		//encrypting password
		//password = bCryptPasswordEncoder.encode(password);
		boolean result= this.authenticateBal.Authenticate(email, password);
		if(result)
		{
			HttpSession session = context.getSession(false);
			session.setAttribute("loggedInUser","GAVIN");
			//jsonReturn = objectMapper.writeValueAsString();
			return "home";
		}
		else {
			//jsonReturn = objectMapper.writeValueAsString();
			return "redirect:authenticate/login";
		}
		//please check the username and password from the server
		//update appropriate error message from the action if there is any
		//if successfull move to appropriate dashboard
		//model.addAttribute("login",new User());
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Locale locale, Model model) {
		model.addAttribute("register", new User());
		return "authenticate/register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String registerProcess(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		
		//encryption
		password = bCryptPasswordEncoder.encode(password);
		String userTypeValue = request.getParameter("radioUser");
		
		//temporary skills list for testing
		List<String> tempUserSkills = new ArrayList<String>();
		tempUserSkills.add("python");
		tempUserSkills.add("Java");
		tempUserSkills.add("Scala");
		
//		List<String> skills = new ArrayList<String>();
//		if(request.getParameter("skills") != null) {
//			skills = Arrays.asList(request.getParameterValues("skills"));
//		}
//		String education= null;
//		if(request.getParameter("education") != null) {
//			education = request.getParameter(request.getParameter("education"));
//		}
//		String experience = null;
//		if(request.getParameter("experience") != null) {
//			experience = request.getParameter(request.getParameter("experience"));
//		}
		
		
		
		//checking for employer
//		String companyName = null;
//		if(request.getParameter("CompanyName") != null) {
//			companyName = request.getParameter(request.getParameter("CompanyName"));
//		}
		Integer userIntTypeValue = 0;
		
		//testing purpose
		userIntTypeValue = 4;
		String tempEducation = "Masters of IT";
		String tempExperience = "8 years";
		String temoCompanyName = "USYD";
		
		//job seeker
//		if(userTypeValue == "seeker") {
//			userIntTypeValue = 4;
//		} else {
//			//employer
//			userIntTypeValue = 3;
//		}
		
		//todo: User Type
		this.userService.AddUser(firstName, lastName, email, password, userIntTypeValue, tempUserSkills, tempEducation, tempExperience, temoCompanyName);
		return "home";
		
	}
}
