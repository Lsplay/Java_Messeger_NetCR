package com.example.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/mainPage")
	public String mainPage(Principal principal,Model model) {
		User user=userService.loadUserByUsername(principal.getName());
		model.addAttribute("user", user);
		return "mainPage";
	}
	
}
