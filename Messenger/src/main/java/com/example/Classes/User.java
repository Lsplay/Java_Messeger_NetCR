package com.example.Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Utilities.PasswordEncoder;

enum Roles{USER,ADMIN};

public class User {
	private String userName;
	private int id;
	private File avatar;
	private String password;
	private String email;
	private Roles role;
	private List<Integer> friendIdList = new ArrayList<Integer>();
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public void addFriend(int id) {
		friendIdList.add(id);
	}
	
	public List<Integer> getFriendIdList() {
		return friendIdList;
	}

	public void setFriendIdList(List<Integer> friendIdList) {
		this.friendIdList = friendIdList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = passwordEncoder.encoder(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void save() {
		////Передаем параметры в бд 
	}
	
}
