package com.neet.jobsite.dal;

import com.neet.jobsite.model.User;

public interface IUserAuthenticationDac {

	User Authenticate(String username);

}
