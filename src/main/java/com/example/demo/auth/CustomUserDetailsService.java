package com.example.demo.auth;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.login.LoginDao;

import lombok.RequiredArgsConstructor;

@Configuration
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final LoginDao loginDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = loginDao.loginUser(email);
		
		return new CustomUserDetails(user.getUseremail(), user.getPassword() , toGrantedAuthorityList(user.getAuthority()));
	}
	
	private List<GrantedAuthority> toGrantedAuthorityList(String authority){
		return Collections.singletonList(new SimpleGrantedAuthority(authority));
	}

}
