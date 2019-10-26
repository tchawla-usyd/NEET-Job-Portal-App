package com.neet.jobsite;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.bal.UserService;
import com.neet.jobsite.response.UserDetailResponse;

@Controller
@RequestMapping(value="/user/**")
public class UserController extends BaseMVCController {
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "userService")
	private IUserService Iuserservice;
	
	@RequestMapping(value="/getuser/{id}", 
			method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getUser(@PathVariable("id") Integer userId) {
		
		UserDetailResponse res = userService.getUser(userId);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonReturn = null;
		try {
			jsonReturn = objectMapper.writeValueAsString(res);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonReturn;
				
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void editUser(HttpServletRequest request, HttpServletResponse response) {
		long userId=  Long.parseLong(request.getParameter("userId"));
		String education = request.getParameter("education");
		String experience = request.getParameter("experience");
		String resume = request.getParameter("resume");
		List<String> skills = Arrays.asList(request.getParameterValues("skills"));
		boolean result = false;
		try {
			result = Iuserservice.updateUser(userId, education, experience, resume, skills);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result) {
			response.setStatus(200);
		} else {
			response.setStatus(403);
		}
	}
	 
}
