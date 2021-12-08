package com.example.Classes;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private List<User> users=new ArrayList<>();
	private List<Message> messages=new ArrayList<>();
	private String name;
	
	Group(List<User> users,String name){
		this.users.addAll(users);
		this.name=name;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	public void delUser(int id) {
		users.remove(id);
	}
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	
	
	public List<User> getUsers() {
		return users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void save() {
		//сохраняет все в бд
	}
	
}
