package com.neet.jobsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	@ResponseStatus(value = HttpStatus.OK)
	public void registerProcess(HttpServletRequest request,HttpServletResponse response) {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userTypeValue = request.getParameter("user_type");
		
		
		//Checking for details for Job Seeker
		List<String> skills = null;
		String education= null;
		String experience = null;
		try {
		if(Arrays.asList(request.getParameterValues("skills")) != null) {
			skills = Arrays.asList(request.getParameterValues("skills"));
		}
		
		if(!request.getParameter("education").isEmpty()) {
			education = request.getParameter("education");
		}
		
		if(!request.getParameter("experience").isEmpty()) {
			experience = request.getParameter("experience");
		}
		}catch(Exception e) {}
		
		
		
		//checking for employer
		String companyName = null;
		try {
		if(!request.getParameter("company").isEmpty()) {
			companyName = request.getParameter("company");
		}
		}catch(Exception e) {}
		
		Integer userIntTypeValue = 0;
	
		//job seeker
		if(userTypeValue.equals("seeker")) {
			userIntTypeValue = 4;
		} else {
			//employer
			userIntTypeValue = 3;
		}
		
		int i =1;
		for(String item : skills) {
			System.out.println(i+": "+item);
			i++;
		}
		
		
		//todo: User Type
		this.userService.AddUser(firstName, lastName, email, password, userIntTypeValue, skills, education, experience, companyName);
		System.out.println( firstName + lastName + email + password + userIntTypeValue + skills + education + experience + companyName);
		response.setStatus(200);
		System.out.print("Success OK");
		
	}
}
