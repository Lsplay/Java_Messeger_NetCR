package com.example.Utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	public String encoder(String password) {
		return passwordEncoder.encode(password);
	}
}
