package com.neet.jobsite.bal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.dal.IUserAuthenticationDac;

@Service(value="authenticateBal")
@Transactional
public class AuthenticateService implements IAuthenticateService {
	
	@Resource(name = "userAuthenticationDac")
	private IUserAuthenticationDac userAuthenticationDac;
	
	@Override
	public boolean Authenticate(String username, String password) {
		System.out.println("Test run");
		return this.userAuthenticationDac.Authenticate(username, password);
	}
}
