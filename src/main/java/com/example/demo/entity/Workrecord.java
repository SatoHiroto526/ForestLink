package com.example.demo.entity;

import lombok.Data;

@Data
public class Workrecord {
	
	private int workrecord_id;
	private int worktype_id;
	private String salesworktype;
	private int enterprise_id;
	private int partnerenterprise_id;
	private int user_id;
	private int partneruser_id;
	private int contactmethod_id;
	private String workrecorddetail;
	private String workrecordday;
	
	private Worktype worktype;
	private Enterprise entryEnterprise;
	private Enterprise partnerEnterprise;
	private User user;
	private User partnerUser;
	private Contactmethod contactmethod;
	
	
}
