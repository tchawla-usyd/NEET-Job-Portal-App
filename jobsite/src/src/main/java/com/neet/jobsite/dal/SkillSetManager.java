package com.neet.jobsite.dal;

import java.io.Serializable;
import java.util.List;

import com.neet.jobsite.model.SkillSet;

public interface SkillSetManager extends Serializable {
	
    public List<SkillSet> getSkillSets();
    
    public void addSkillSet(SkillSet SkillSet);
    
    public SkillSet getSkillSetById(long id);
    
    public void updateSkillSet(SkillSet SkillSet);
    
    public void deleteSkillSet(long id);
    
}
