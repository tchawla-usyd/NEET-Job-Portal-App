package com.neet.jobsite;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.neet.jobsite.bal.IAuthenticateService;

public class BaseMVCController {

	@Autowired
	protected HttpServletRequest context;
	
	@Resource(name = "authenticateBal")
	private IAuthenticateService authenticateBal;

	public boolean IsAuthenticated() {
		HttpSession session = context.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			// user is not logged in, do something about it
			return false;
		} else {
			// user IS logged in, do something: set model or do whatever you need
			return true;
		}
	}
	
	public boolean authenticateByToken(String token) {
		if(token == null)
			return false;
		
		HttpSession session = context.getSession(true);
		if(token.equals("testBadToken")){
			return false;
		}
		
		session.setAttribute("userId", 1);
		return true;
		
	}
}
