package com.example.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FindUsersController {

	@GetMapping("/findUsers")
	public String findUsersPage(Principal principal, Model model) {
		
		model.addAttribute("user", model);//model=user
		
		return "findUsers";
	}
	
	@PostMapping("/findUsers")
	public String findUsers(Principal principal, Model model, String nickname) {
		// выводит профиль человека с таким Ником
		model.addAttribute("user", model);//model=user
		
		return "redirect:/profile/{id}";
	}
}
