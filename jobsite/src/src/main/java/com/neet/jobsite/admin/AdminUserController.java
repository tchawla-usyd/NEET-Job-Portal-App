package com.neet.jobsite.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neet.jobsite.bal.IUserService;
import com.neet.jobsite.model.User;

@Controller
@RequestMapping(value="/users/**")
public class AdminUserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public String viewUser(@PathVariable("id") Long id, Model uiModel) {
		User user = this.userService.GetUserById(id);
		uiModel.addAttribute("user", user);
		return "admin/users/viewuser";
	}
}
