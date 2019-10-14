package com.neet.jobsite.admin;


import java.util.Date;

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

@Controller
@RequestMapping(value="/adminSkillset/**")
public class SkillSetController {

	@Resource(name="skillSetManager")
	private SkillSetManager skillSetManager;
	
	@RequestMapping(value="/add")
	public String addSkillSet(Model uiModel) {
		return "admin/skillsets/addskill";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addSkillSet(HttpServletRequest httpServletRequest) {
		SkillSet skillSet = new SkillSet();
		skillSet.setName(httpServletRequest.getParameter("name"));
		skillSet.setCreatedDate(new Date());
		skillSet.setCreatedBy(1);
		this.skillSetManager.addSkillSet(skillSet);
		return "redirect:/admin/skillsets";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editSkillSet(@PathVariable("id") Long id, Model uiModel) {
		SkillSet SkillSet = this.skillSetManager.getSkillSetById(id);
		uiModel.addAttribute("SkillSet", SkillSet);
		return "admin/skillsets/editskill";
	}
	
	@RequestMapping(value="/edit/**", method=RequestMethod.POST)
	public String editSkillSet(@Valid SkillSet SkillSet) {
		this.skillSetManager.updateSkillSet(SkillSet);
		return "redirect:/admin/skillsets";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteSkillSet(@PathVariable("id") Long id) {
		this.skillSetManager.deleteSkillSet(id);
		return "redirect:/admin/skillsets";
	}
}
