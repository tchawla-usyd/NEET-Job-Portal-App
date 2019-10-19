package com.neet.jobsite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseMVCController {

	@Autowired
	protected HttpServletRequest context;

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
}
