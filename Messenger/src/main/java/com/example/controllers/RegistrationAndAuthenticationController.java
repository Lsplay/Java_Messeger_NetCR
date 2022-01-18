package com.example.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Collections;
import java.util.Map;


import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.utilities.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationAndAuthenticationController {

	@Autowired
	private UserService userService;

	 @Autowired
	    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	    @PostMapping("/registration")
	    public String addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult,
				Model model) {
	    	System.out.println("qqq");
			if (bindingResult.hasErrors()) {
				return "registration";
			}
			if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
				model.addAttribute("Error", "Пароли не совпадают");
				return "registration";
			}
			if (!userService.saveUser(userForm)) {
				model.addAttribute("Error", "Пользователь с таким именем уже существует");
				return "registration";
			}

			return "redirect:/login";
		}
	    
	    @RequestMapping("/login")
		public String Login(Model model) {
	    	
	    	model.addAttribute("userForm", new User());
			return "login";
		}
	    
	    @PostMapping("/login")
	    public String auth(@ModelAttribute("userForm") @Validated User userForm){
			
	    	if(userService.loadUserByUsername(userForm.getUserName()).getPassword()==userForm.getPassword()) {
				return "mainPage";
			}
	    	return "login";
	    	
	    }
	    
	    
	
}
