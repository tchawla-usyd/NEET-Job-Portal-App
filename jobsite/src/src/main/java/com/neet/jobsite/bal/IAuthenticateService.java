package com.neet.jobsite.bal;

import java.io.Serializable;

import com.neet.jobsite.model.User;

public interface IAuthenticateService extends Serializable {

	boolean Authenticate(String username, String password);
	
	User AuthenticateAdmin(String username, String password);
	
}
