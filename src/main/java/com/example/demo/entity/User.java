package com.example.demo.entity;

import lombok.Data;

@Data
public class User {
	
	private int user_id;
	private String authority;
	private int enterprise_id;
	private String username;
	private String password;
	private String useremail;
	private String usernumber;
	private Enterprise enterprise;

}
