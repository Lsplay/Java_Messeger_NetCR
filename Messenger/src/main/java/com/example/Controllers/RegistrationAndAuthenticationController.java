package com.example.Controllers;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationAndAuthenticationController {

	
	@GetMapping(value="/registration")
	public String RegistrationPage(ModelMap model) {
		
		return "RegistationPage";
	}
	
	@GetMapping(value="/authentication")
	public String AuthenticationPage() {
		
		return "AuthenticationPage";
	}
	
	@PostMapping(value="/authentication")
	public String Authentication(Model model) {
		if(1==1) {///////Если авторизация прошла успешно
			return "redirect:/authentication";
		}
		return "redirect:/mainPage";
	}
	
	@PostMapping(value="/registration")
	public String Registration(Model model) {
		if(1==1) {///////Если регистрация прошла успешно
			return "redirect:/authentication";
		}
		
		return "redirect:/registration";
	}
	
}
