package com.neet.jobsite.bal;

import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neet.jobsite.dal.IUserAuthenticationDac;
import com.neet.jobsite.model.User;

@Service(value="authenticateBal")
@Transactional
public class AuthenticateService implements IAuthenticateService {
	
	@Resource(name = "userAuthenticationDac")
	private IUserAuthenticationDac userAuthenticationDac;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder() ;
	
	@Override
	public boolean Authenticate(String username, String password) {
		System.out.println("Test run");
		User tempUser = new User();
		tempUser = this.userAuthenticationDac.Authenticate(username);
		//System.out.println(tempUser.getPassword());
		Boolean passVerification =  bCryptPasswordEncoder.matches(password, tempUser.getPassword());
		return passVerification;
	}
	
//	@Override
//	public boolean Authenticate(String username, String password) {
//		System.out.println("Test run");
//		return this.userAuthenticationDac.Authenticate(username, password);
//	}
}
