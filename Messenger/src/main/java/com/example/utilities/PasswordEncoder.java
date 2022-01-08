package com.example.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends BCryptPasswordEncoder{
	
	public String encoder(String password) {
		
		return super.encode(password);
	}
}
