package com.example.Controllers;

import java.lang.ProcessBuilder.Redirect;

import com.example.Service.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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


	@GetMapping(value="/registration")
	public String RegistrationPage(ModelMap model) {
		model.addAttribute("userFromDb",new User());

		return "registration.html";
	}

	@RequestMapping("/login")
	public String getLogin(@RequestParam(value = "error", required = false) String error,
						   @RequestParam(value = "login", required = false) String login,
						   Model model){
		model.addAttribute("error", error!=null);
		model.addAttribute("login", login!=null);
		return "login";
	}
	
	@PostMapping(value="/registration")
	public String Registration(@ModelAttribute("userFrom") @Validated User userFrom, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			return "registration";
		}
		if(!userFrom.getPassword().equals((userFrom.getPasswordConfirm()))){
			model.addAttribute("passwordError", "Password diferent from passwordComform");
			return "registration";
		}
		if(!userService.saveUser(userFrom)){
			model.addAttribute("usernameError","User with this nickname is already created");
			return "registration";
		}


		return "redirect:/";
	}
	
}
