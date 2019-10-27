package com.neet.jobsite;

import java.security.Key;
import java.util.Date;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.IAuthenticateService;
import com.neet.jobsite.response.ErrorResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import com.neet.jobsite.configuration.JwtFilter;


public class BaseMVCController {

	@Autowired
	protected HttpServletRequest context;
	
	private final String SECRET_KEY = "t)878wb8h6u&c1g8ti7=94!a8o0(cu^1_@$ijz0k3^)t=&*rc8\r\n";
	
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
	
	private Claims decodeJWT(String jwt) throws Exception{
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
	protected String createJWT(String issuer, long subject, Integer userType) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
	            .setIssuedAt(now)
	            .setIssuer(issuer)
	            .claim("uid", subject)
	            .claim("ut", userType)
	            .signWith(signatureAlgorithm, signingKey);
	  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public Claims authenticateByToken(String token) {
		
		if(token == null)
			return null;
		
		try {
			Claims claims = decodeJWT(token);
			return claims;
		} catch (Exception e) {
			return null;
		}
						
	}
	
	public Claims authenticateByToken(String token, Integer userType) {
		
		if(token == null)
			return null;
		
		try {
			Claims claims = decodeJWT(token);
			if(userType == claims.get("ut"))
				return claims;
		} catch (Exception e) {
			return null;
		}
		
		return null;
		
	}
	
	protected String objectToJSON(ObjectMapper objectMapper, Object obj) {
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
		
	}
}
