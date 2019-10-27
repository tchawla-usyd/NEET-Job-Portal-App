package com.neet.jobsite.response;

import io.jsonwebtoken.Claims;

public class ClaimsResponse {
	private Integer userId;
	private Integer userType;
	
	public ClaimsResponse(Claims claims) {
		this.userId = (Integer) claims.get("uid");
		this.setUserType((Integer) claims.get("ut"));
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
