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
import com.neet.jobsite.dal.SkillSetManager;
import com.neet.jobsite.model.SkillSet;

@Controller
@RequestMapping(value="/admin/**")
public class AdminDashboardController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="skillSetManager")
	private SkillSetManager skillSetManager;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
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
		return "admin/skillsetlists";
		
	}
	
	
}
