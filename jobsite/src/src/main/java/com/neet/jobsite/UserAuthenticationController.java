package com.neet.jobsite;

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
	
	
	@Resource(name = "authenticateBal")
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
			return "redirect:/";
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
		//todo: User Type
		this.userService.AddUser(firstName, lastName, email, password, 4);
		return "home";
	}
}
