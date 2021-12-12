package com.example.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendListController {

	@GetMapping("/friends")
	public String friendsPage(Principal principal, Model model) {
		
	///////user_information user= userService.loadUserByUsername(principal.getName()); и потом берем друзей юзера записывая в List. 
		//После передаем в модель , а она в html-ке выводит этот список
			//model=user
		model.addAttribute("user", model);
		
		return "friendsPage";
	}
	
	@PostMapping("/friend/{id}/delete")
	public String friendDelete(Principal principal, Model model , @PathVariable(value = "id") long id) {
			//Удаляем id пользователя с ссылки из списка друзей пользовтеля
			model.addAttribute("user", model);
			
			return "friendsPage";
	}
	
	@PostMapping("/friend/{id}/chat")
	public String friendChat(Principal principal, Model model , @PathVariable(value = "id") long id) {
			//Проверяем наличие чата с id из ссылки. При отсутствии добавляем новый , при наличии переходим в него
		
			
			model.addAttribute("user", model);
			
			return "redirect:/chat/{id}";
	}
}
