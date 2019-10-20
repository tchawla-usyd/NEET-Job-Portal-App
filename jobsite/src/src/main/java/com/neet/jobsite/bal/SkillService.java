package com.neet.jobsite.bal;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neet.jobsite.dal.SkillSetManager;
import com.neet.jobsite.model.SkillSet;

@Service(value = "skillService")
public class SkillService {
	
	@Autowired
	@Qualifier("skillSetManager")
	private SkillSetManager skillManager;
	
	public SkillSet getSkillByName(String name) {
		
		return skillManager.getSkillSetByName(name);
	}
	
public void addSkill(String name, Integer userId) {
		SkillSet skill = new SkillSet();
		skill.setName(name);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		skill.setCreatedDate(date);
		skill.setCreatedBy(userId);
		skillManager.addSkillSet(skill);
	}
	
}
