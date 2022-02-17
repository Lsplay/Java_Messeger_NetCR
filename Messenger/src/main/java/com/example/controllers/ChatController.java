package com.example.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.json.simple.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.CryptedMessage;
import com.example.model.DefaultMessage;
import com.example.model.Group;
import com.example.model.Message;
import com.example.model.User;
import com.example.repository.GroupRepository;
import com.example.service.CryptedMessageService;
import com.example.service.DefaultMessageService;
import com.example.service.GroupService;
import com.example.service.UserService;
import com.jayway.jsonpath.InvalidJsonException;

@Controller
public class ChatController {

	@Autowired
	UserService userService;

	@Autowired
	GroupService groupService;

	@Autowired
	GroupRepository groupRepository;

	@Autowired
	CryptedMessageService cryptedMessageService;

	@Autowired
	DefaultMessageService defaultMessageService;

	@GetMapping("/chat")
	public String chatsPage(Principal principal, Model model) {
		// Передает в модель последние чаты пользователя и выводит их
		User user = userService.loadUserByUsername(principal.getName());
		Iterable<Group> gr = groupService.findByUser(user);
		System.out.println(gr.toString());
		model.addAttribute("groups", gr);
		model.addAttribute("user", model);// model=user

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
		model.addAttribute("user", model);// model=user
		return "chatCreate";
	}

	@GetMapping("/addUser{id}")
	public String addUser(Principal principal, Model model, @PathVariable(value = "id") long id) {
		model.addAttribute("user", model);
		model.addAttribute("id", id);
		return "addUser";
	}

	@PostMapping("/addUser{id}")
	public String addUserPost(Principal principal, @RequestParam("userName") String userName, Model model,
			@PathVariable(value = "id") long id) {
		Group gr = groupRepository.getById(id);
		if (userService.loadUserByUsername(userName) != null) {
			gr.addUser(userService.loadUserByUsername(userName));
			groupRepository.save(gr);
		}

		model.addAttribute("user", model);// model=user
		return "redirect:/chat";
	}

	@GetMapping("/{id}")
	public String chat(Principal principal, Model model, @PathVariable(value = "id") long id) {

		model.addAttribute("user", id);// model=user
		model.addAttribute("id", id);
		return "chat";
	}

	@PostMapping("/{id}")
	public String findUsers(Principal principal, @RequestParam(value = "Crypt", required = false) String check,
			@RequestParam("Text") String message, Model model, @PathVariable(value = "id") long id)
			throws InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException,
			NoSuchAlgorithmException {

		User user = userService.loadUserByUsername(principal.getName());

		if (groupRepository.getById(id) != null) {
			Group gr = groupRepository.getById(id);
			if (check != null) {
				CryptedMessage ms = new CryptedMessage(message, groupRepository.getById(id), user.getId());
				cryptedMessageService.create(ms);
			} else {
				DefaultMessage ms = new DefaultMessage(message, groupRepository.getById(id), user.getId());
				System.out.println(ms.toString());
				defaultMessageService.create(ms);
			}

			return "redirect:/" + id;
		}
		return "redirect:/" + id;
	}

	@PostMapping("/chatCreate")
	public String findUsers(Principal principal, Model model, @RequestParam("chatName") String chatName) {
		Set<User> users = Set.of(userService.loadUserByUsername(principal.getName()));
		Group group = new Group(chatName);
		group.addUser(userService.loadUserByUsername(principal.getName()));
		System.out.println(group.toString());
		System.out.println(chatName);

		groupService.create(group);

		model.addAttribute("user", model);// model=user
		return "redirect:/chat";
	}

	@GetMapping("/gets/{id}")
	public @ResponseBody List<Message> take(@PathVariable(value = "id") long id) {
		List<Message> ls = new ArrayList<>();
		ls.addAll(groupRepository.getById(id).getCrMesseges());
		ls.addAll(groupRepository.getById(id).getDfMessages());
		ls.sort(new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				return o1.getTimeOfSend().compareTo(o2.getTimeOfSend());
			}
		});
		List<Message> ls2 = new ArrayList<>();
		for (int i = ls.size(); 0 < i; i--) {
			ls2.add(ls.get(i - 1));
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", ls2);
		return ls2;
	}

	@PostMapping("/get/{id}")
	@ResponseBody
	public JSONObject addMessage(@RequestBody JSONObject message, @PathVariable(value = "id") long id,
			Principal principal) throws InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException,
			BadPaddingException, NoSuchAlgorithmException {
		
		System.out.println(message.toString());
		String Name = (String) message.get("text");
		String s=(String)message.get("crypt");
		System.out.println(s);
		boolean check=s.equals("true");
		System.out.println(check);
		
		User user = userService.loadUserByUsername(principal.getName());

		if (groupRepository.getById(id) != null) {

			if (check) {
				Group gr = groupRepository.getById(id);

				CryptedMessage ms = new CryptedMessage((String) message.get("text"), groupRepository.getById(id),
						user.getId());
				System.out.println(ms.toString());
				System.out.println("Crypt");
				cryptedMessageService.create(ms);
			} else {
				Group gr = groupRepository.getById(id);

				DefaultMessage ms = new DefaultMessage((String) message.get("text"), groupRepository.getById(id),
						user.getId());
				System.out.println(ms.toString());
				System.out.println("default");
				defaultMessageService.create(ms);
			}
			return message;

		}

		return null;
	}

}
