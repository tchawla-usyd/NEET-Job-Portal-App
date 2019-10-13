package com.neet.jobsite.bal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="authenticateBal")
@Transactional
public class AuthenticateBal implements IAuthenticateBal {
	
	@Override
	public void Test() {
		System.out.println("Test run");
	}
}
