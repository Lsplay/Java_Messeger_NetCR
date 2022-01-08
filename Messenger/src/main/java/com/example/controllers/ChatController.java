package com.example.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {
	
	
	@GetMapping("/chat")
	public String chatsPage(Principal principal, Model model) {
		//Передает в модель последние чаты пользователя и выводит их
		model.addAttribute("user", model);//model=user
	
		return "chatsPage";
	}
	
	@GetMapping("/qwe")
	public String qwe(Principal principal, Model model) {
		//Передает в модель последние чаты пользователя и выводит их
		model.addAttribute("user", model);//model=user
	
		return "qwe";
	}
	
	@GetMapping("/chat/create")
	public String chatsСreate(Principal principal, Model model) {
		model.addAttribute("user", model);//model=user
		return "chatCreate";
	}
	
	@GetMapping("/chat/{id}")
	public String chat(Principal principal, Model model) {
		//Передает в модель лист сообщений и выводи их
		model.addAttribute("user", model);//model=user
	
		return "chat";
	}
	
	@PostMapping("/chat/{id}")
	public String findUsers(Principal principal, Model model, @PathVariable(value = "id") long id, String string/*Тут вместо стринга будет принятие ajax запроса*/) {
		// извлечение сообщения из ajax запроса , добавление его в список сообщений чата.
		model.addAttribute("user", model);//model=user
		
		return "chat";
	}
	
	@PostMapping("/chat/create")
	public String findUsers(Principal principal, Model model,List<String> string, String name) {
		//Добавление пользователей что переданны листом в новый созданный чат с именем name
		model.addAttribute("user", model);//model=user
		
		return "redirect:/chat/{id}";
	}
	
	
}
