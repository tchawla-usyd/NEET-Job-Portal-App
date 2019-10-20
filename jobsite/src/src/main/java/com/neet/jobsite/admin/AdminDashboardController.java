package com.neet.jobsite.admin;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.HomeController;
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
	
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "admin/home";
	}
	
	@RequestMapping(value = "/skillsets", method = RequestMethod.GET)
	public String skillsets(Locale locale, Model model) {
		ArrayList<SkillSet> skillSets  = this.skillSetManager.getSkillSets();
		model.addAttribute("skillSets", skillSets );
		return "admin/skillsets/skillsetlists";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String users(Locale locale, Model model) {
		List<User> users = this.userService.GetUserByType(4);
		model.addAttribute("users", users );
		return "admin/users/userslists";
	}
	
	@RequestMapping(value = "/employers", method = RequestMethod.GET)
	public String employers(Locale locale, Model model) {
		List<User> users = this.userService.GetUserByType(3);
		model.addAttribute("users", users );
		return "admin/employers/employerslists";
	}
	
	@RequestMapping(value = "/administrators", method = RequestMethod.GET)
	public String adminusers(Locale locale, Model model) {
		List<User> users = this.userService.GetUserByType(2);
		model.addAttribute("users", users );
		return "admin/administrators/administratorslists";
	}
}
