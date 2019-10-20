package com.neet.jobsite;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neet.jobsite.bal.UserService;
import com.neet.jobsite.response.UserDetailResponse;

@Controller
@RequestMapping(value="/user/**")
public class UserController extends BaseMVCController {
	@Resource(name = "userService")
	private UserService userService;
	
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
	public String editUser(HttpServletRequest httpServletRequest) {
		return null;
		
	}
	 
}
