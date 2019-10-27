package com.neet.jobsite.dal;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service(value = "dacFactory")
public class DacFactory implements IDacFactory {

	private HashMap<DacType, Object> dacitems = new HashMap<DacType, Object>();

	public DacFactory() {
		dacitems.put(DacType.UserManager, new DatabaseUserManager());
		dacitems.put(DacType.SkillSetManager, new DatabaseSkillSetManager());
		dacitems.put(DacType.CandidateManager, new DatabaseCandidateManager());
		dacitems.put(DacType.CompanyManager, new DatabaseCompanyManager());
		dacitems.put(DacType.JobManager, new DatabaseJobManager());
		dacitems.put(DacType.JobSeekManager, new JobSeekingDac());
		dacitems.put(DacType.JobDescriptionManager, new JobDescriptionDac());
		dacitems.put(DacType.UserAuthManager, new UserAuthenticationDac());
	}

	@Override
	public <T> T GetDac(DacType dacType) throws Exception {
		if (dacitems.containsKey(dacType)) {
			return (T) dacitems.get(dacType);
		} else {
			throw new Exception("The class you are looking for can not be created by this factory");
		}
	}
}
