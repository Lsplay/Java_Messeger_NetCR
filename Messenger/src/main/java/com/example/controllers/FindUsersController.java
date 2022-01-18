package com.example.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class FindUsersController {

	@Autowired
	UserService userService;
	
	@GetMapping("/findUsers")
	public String findUsersPage(Principal principal, Model model) {
		
		model.addAttribute("user", model);//model=user
		
		return "findUsers";
	}
	
	@PostMapping("/findUsers")
	public RedirectView findUsers(Principal principal, Model model,@RequestParam("userName") String userName) {
		User user;
		if(userService.loadUserByUsername(userName)!=null) {
			user=userService.loadUserByUsername(userName);
			String s="/profile/"+user.getId();
			return new RedirectView(s);
		}
		model.addAttribute("user", model);//model=user
		return new RedirectView("/findUsers");
		
		
	}
}
