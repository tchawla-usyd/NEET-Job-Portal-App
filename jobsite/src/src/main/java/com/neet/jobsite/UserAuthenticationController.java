package com.neet.jobsite;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.bal.IAuthenticateBal;
import com.neet.jobsite.dal.SkillSetManager;
import com.neet.jobsite.model.User;

@Controller
@RequestMapping(value = "/authenticate/**")
public class UserAuthenticationController {

	@Resource(name = "authenticateBal")
	private IAuthenticateBal authenticateBal;
	
	
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
		boolean result =	this.authenticateBal.Authenticate(email, password);
		if(result)
		{
			return "redirect:admin/home";
		}
		//please check the username and password from the server
		//update appropriate error message from the action if there is any
		//if successfull move to appropriate dashboard
		//model.addAttribute("login",new User());
		return "redirect:authenticate/login";
	}
}
