package com.example.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class Profile {

	@Autowired
	UserService userService;
	
	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {
		User user= userService.loadUserByUsername(principal.getName());
		model.addAttribute("user", user);
		
		return "profile";
	}
	
	@GetMapping("/profile/{id}")
	public String profileId(Principal principal,@PathVariable Long id, Model model) {
		User user1=userService.loadUserByUsername(principal.getName());
		User user= userService.loadUserById(id);
		if(user1!=user) {
			model.addAttribute("user", user);
			return "profileUser";
		}
		return "redirect:/findUsers";
	}
}
