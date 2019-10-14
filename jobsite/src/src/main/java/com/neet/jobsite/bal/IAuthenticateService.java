package com.neet.jobsite.bal;

import java.io.Serializable;

public interface IAuthenticateService extends Serializable {

	boolean Authenticate(String username, String password);
	
}
