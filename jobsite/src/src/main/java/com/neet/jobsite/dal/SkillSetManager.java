package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.ArrayList;

import com.neet.jobsite.model.SkillSet;

public interface SkillSetManager extends Serializable {
	
    public ArrayList<SkillSet> getSkillSets();
    
    public void addSkillSet(SkillSet SkillSet);
    
    public SkillSet getSkillSetById(long id);
    
    public void updateSkillSet(SkillSet SkillSet);
    
    public void deleteSkillSet(long id);

	public SkillSet getSkillSetByName(String name);
    
}
