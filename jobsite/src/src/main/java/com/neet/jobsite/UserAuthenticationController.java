package com.neet.jobsite;
 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.IAuthenticateService;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.configuration.JwtFilter;
import com.neet.jobsite.model.User;
import com.neet.jobsite.response.ClaimsResponse;
import com.neet.jobsite.response.ErrorResponse;
//import com.neet.jobsite.configuration;
import com.neet.jobsite.response.TokenResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Controller
@RequestMapping(value = "/authenticate/**")
public class UserAuthenticationController extends BaseMVCController {
	


	@Resource(name = "authenticateBal")
	private IAuthenticateService authenticateBal;
	
	
	@Resource(name = "userService")
	private IUserService userService;
	

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Adding the bean Registration Filter for token
		
		return "home";
	}
	

	
	
	
	@RequestMapping(value = "/loginProcess", 
			method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String loginProcess(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("Email");
		String password =	request.getParameter("Password");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonReturn = null;
		
        System.out.println(email);
		User user = this.authenticateBal.Authenticate(email, password);
		if(user != null)
		{	
			
	        String token = createJWT(
	                "neet.net", // claim = iss
	                user.getId(), // claim = uid
	                user.getUserTypeID() // claim = ut
	        );
			
			System.out.println(token);
			
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setToken(token);

			jsonReturn = objectToJSON(objectMapper, tokenResponse);
	
		}
		else {
			
			response.setStatus(401);
			jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
		}
		
		return jsonReturn;	

	
		
	}
	
    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

	


	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void registerProcess(HttpServletRequest request,HttpServletResponse response) {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userTypeValue = request.getParameter("user_type");
		
		//encryption
			password = bCryptPasswordEncoder.encode(password);
			//String userTypeValue = request.getParameter("radioUser");
		
		
		//Checking for details for Job Seeker
		List<String> skills = null;
		String education= null;
		String experience = null;
		try {
		if(Arrays.asList(request.getParameterValues("skills")) != null) {
			skills = Arrays.asList(request.getParameterValues("skills"));
		}
		
		if(!request.getParameter("education").isEmpty()) {
			education = request.getParameter("education");
		}
		
		if(!request.getParameter("experience").isEmpty()) {
			experience = request.getParameter("experience");
		}
		}catch(Exception e) {}
		
		
		
		//checking for employer
		String companyName = null;
		try {
		if(!request.getParameter("company").isEmpty()) {
			companyName = request.getParameter("company");
		}
		}catch(Exception e) {}
		
		Integer userIntTypeValue = 0;
	
		//job seeker
		if(userTypeValue.equals("seeker")) {
			userIntTypeValue = 4;
		} else {
			//employer
			userIntTypeValue = 3;
		}
		
		int i =1;
		for(String item : skills) {
			System.out.println(i+": "+item);
			i++;
		}
		
		
		//todo: User Type
		this.userService.AddUser(firstName, lastName, email, password, userIntTypeValue, skills, education, experience, companyName);
		System.out.println( firstName + lastName + email + password + userIntTypeValue + skills + education + experience + companyName);
		response.setStatus(200);
		System.out.print("Success OK");
		}
	
		@RequestMapping(value="/token", 
				method=RequestMethod.GET, 
				produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public String decodeToken(HttpServletResponse response, @RequestHeader("Authorization") String userToken) {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonReturn = null;
			
	        Claims claims = authenticateByToken(userToken);
			if(claims != null) {
				ClaimsResponse claimsResponse = new ClaimsResponse(claims);
				jsonReturn = objectToJSON(objectMapper, claimsResponse);
	
			}
			else {
				response.setStatus(403);
				jsonReturn = objectToJSON(objectMapper, new ErrorResponse("Authentication Failed"));
			}
			return jsonReturn;
		}
	
}
