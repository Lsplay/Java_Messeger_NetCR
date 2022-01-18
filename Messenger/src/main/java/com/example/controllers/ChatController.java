package com.example.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Group;
import com.example.model.User;
import com.example.service.GroupService;
import com.example.service.UserService;

@Controller
public class ChatController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@GetMapping("/chat")
	public String chatsPage(Principal principal, Model model) {
		//Передает в модель последние чаты пользователя и выводит их
		model.addAttribute("user", model);//model=user
	
		return "chatsPage";
	}
	
//	@GetMapping("/chat/user/?{id}")
//	public String chatsUserId(@PathVariable Long id,Principal principal, Model model) {
//		Set<User> user=Set.of(userService.loadUserByUsername(principal.getName())
//				,userService.loadUserById(id));
//		Group group=new Group(user,null);
//		if (!groupService.saveGroup(group)) {
//			
//			return "redirect:/chat";
//		}
//		model.addAttribute("user", model);//model=user
//		return "redirect:/chat";
//	}
	
	@GetMapping("/chatСreate")
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
	public String findUsers(Principal principal, Model model,@RequestParam("chatName") String chatName) {
		Set<User> users=Set.of(userService.loadUserByUsername(principal.getName()));
		Group group=new Group(chatName);
		System.out.println(group.toString());
		System.out.println(chatName);
		groupService.create(group);
		
		
		model.addAttribute("user", model);//model=user
		return "redirect:/chat";
	}
	
	
}
