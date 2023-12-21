package com.example.demo.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User{
	
	public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> string) {
		super(email, password, string);
	}

}
