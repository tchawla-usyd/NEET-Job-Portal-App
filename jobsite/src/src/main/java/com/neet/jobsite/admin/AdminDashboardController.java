package com.neet.jobsite.admin;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neet.jobsite.HomeController;
import com.neet.jobsite.bal.IAuthenticateService;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.dal.SkillSetManager;
import com.neet.jobsite.model.SkillSet;
import com.neet.jobsite.model.User;

@Controller
@RequestMapping(value="/admin/**")
public class AdminDashboardController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="skillSetManager")
	private SkillSetManager skillSetManager;
	
	@Resource(name = "authenticateBal")
	private IAuthenticateService authenticateBal;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "admin/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "admin/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String loginPost(HttpServletRequest request,HttpServletResponse response) {
		String email = request.getParameter("Email");
		String password =	request.getParameter("Password");
		User admin = this.authenticateBal.AuthenticateAdmin(email, password);
		if(admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser",admin.getEmail());
			response.setStatus(200);
			System.out.println("Success OK");
		}
		else {
//			response.setStatus(403);
//			System.out.println("Failed");
//			return "redirect:/admin/login";
		}
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
//		todo: clear the session data
		return "redirect:/admin/login";
	}
	
	@RequestMapping(value = "/skillsets", method = RequestMethod.GET)
	public String skillsets(Locale locale, Model model) {
		ArrayList<SkillSet> skillSets  = this.skillSetManager.getSkillSets();
		model.addAttribute("skillSets", skillSets );
		return "admin/skillsets/skillsetlists";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Locale locale, Model model) {
		ArrayList<User> users = this.userService.GetUserByType(4);
		model.addAttribute("users", users );
		return "admin/users/userslists";
	}
	
	@RequestMapping(value = "/employers", method = RequestMethod.GET)
	public String employers(Locale locale, Model model) {
		ArrayList<User> users = this.userService.GetUserByType(3);
		model.addAttribute("users", users );
		return "admin/employers/employerslists";
	}
	
	@RequestMapping(value = "/administrators", method = RequestMethod.GET)
	public String adminusers(Locale locale, Model model) {
		ArrayList<User> users = this.userService.GetUserByType(2);
		model.addAttribute("users", users );
		return "admin/administrators/administratorslists";
	}
	
	@RequestMapping(value="/admin/viewadmin/{id}", method=RequestMethod.GET)
	public String viewadmin(@PathVariable("id") Long id, Model uiModel) {
		User user = this.userService.GetUserById(id);
		uiModel.addAttribute("user", user );
		return "admin/administrators/viewadmin";
	}
	
	@RequestMapping(value="/addadmin", method=RequestMethod.GET)
	public String viewadmin(Locale locale, Model model) {
		model.addAttribute("user", new User() );
		return "admin/administrators/addadmin";
	}
}
