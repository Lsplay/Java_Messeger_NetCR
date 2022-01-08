package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/mainPage")
	public String mainPage(Model model) {
		
		return "mainPage";
	}
	
}
