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
//import com.neet.jobsite.configuration;
import com.neet.jobsite.response.TokenResponse;
import com.neet.jobsite.response.UserDetailResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Controller
@RequestMapping(value = "/authenticate/**")
public class UserAuthenticationController extends BaseMVCController {
	
	public static String tokenHolder = "";

	@Resource(name = "authenticateBal")
	private IAuthenticateService authenticateBal;
	
	
	@Resource(name = "userService")
	private IUserService userService;
	
//	@Inject
//	private JwtFilter jwtFilter;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Adding the bean Registration Filter for token
		
		return "redirect:authenticate/login";
	}
	
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	public String testMethod(HttpServletRequest request,HttpServletResponse response,@RequestHeader("Authorization") String userToken) {
		//jwtFilter.doFilter(request, response, chain);
		if(userToken.equals(tokenHolder)) {
			System.out.println("Test Successful");
			return "home";
		}else {
			
			return "redirect:authenticate/login";
		}
		
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		model.addAttribute("login",new User());
		return "authenticate/login";
		
	}
	
	@RequestMapping(value = "/loginProcess", 
			method = RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String loginProcess(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("Email");
		String password =	request.getParameter("Password");
		
        System.out.println(email);
		boolean result= this.authenticateBal.Authenticate(email, password);
		if(result)
		{
//			HttpSession session = context.getSession(false);
//			session.setAttribute("loggedInUser","GAVIN");
			
			String token = Jwts.builder().setSubject(email)
	                .claim("roles","cr@gmail.com").setIssuedAt(new Date())
	                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			
			System.out.println(token);
			tokenHolder = token;
			System.out.println("Value of Token Holder : " + tokenHolder);
			
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonReturn = null;
			Integer userId = null;
			
			userId = (int) userService.GetUserByEmail(email).getId();
			
			
			TokenResponse tokenResponse = new TokenResponse();
			tokenResponse.setToken(token);
			tokenResponse.setId(userId);
			
			System.out.println("Token:" + tokenResponse.getToken());
			System.out.println("User Id:" + tokenResponse.getId());
			
			try {
				jsonReturn = objectMapper.writeValueAsString(tokenResponse);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			System.out.println(jsonReturn);
			response.setStatus(200);
			return jsonReturn;	
			
		}
		else {
			
			System.out.println("Failed");
			response.setStatus(403);
			return null;
		}
	
		
	}
    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

	


	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegister(Locale locale, Model model) {
		model.addAttribute("register", new User());
		return "authenticate/register";
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
	
}

