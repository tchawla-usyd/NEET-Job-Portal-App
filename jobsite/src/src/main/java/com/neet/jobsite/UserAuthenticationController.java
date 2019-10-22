package com.neet.jobsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
			return "home";
		}
		else {
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
		String userTypeValue = request.getParameter("radioUser");
		
		List<String> skills = new ArrayList<String>();
		if(request.getParameter("skills") != null) {
			skills = Arrays.asList(request.getParameterValues("skills"));
		}
		String education= null;
		if(request.getParameter("education") != null) {
			education = request.getParameter(request.getParameter("education"));
		}
		String experience = null;
		if(request.getParameter("experience") != null) {
			experience = request.getParameter(request.getParameter("experience"));
		}
		
		//checking for employer
		String companyName = null;
		if(request.getParameter("CompanyName") != null) {
			companyName = request.getParameter(request.getParameter("CompanyName"));
		}
		Integer userIntTypeValue = 0;
		//job seeker
		if(userTypeValue == "seeker") {
			userIntTypeValue = 4;
		} else {
			//employer
			userIntTypeValue = 3;
		}
		
		//todo: User Type
		try {
			this.userService.AddUser(firstName, lastName, email, password, userIntTypeValue, skills, education, experience, companyName);
			if(userIntTypeValue == 4) {
				//route to user dashboard
				return "home";
			} else {
				// route to employer dashboard
				return "home";
			}
		} catch (Exception e) {
			return "authenticate/register";
		}
		
	}
}
