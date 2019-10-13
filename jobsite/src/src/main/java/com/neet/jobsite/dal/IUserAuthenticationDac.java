package com.neet.jobsite.dal;

public interface IUserAuthenticationDac {

	boolean Authenticate(String username, String password);

}
