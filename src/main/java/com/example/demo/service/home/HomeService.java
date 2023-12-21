package com.example.demo.service.home;

import com.example.demo.entity.Home;
import com.example.demo.entity.User;

public interface HomeService {
	
	User userDetail(String useremail);
	
	Home purchase(int enterprise_id);
	
	Home sales(int enterprise_id);
	
	Home stockType(int enterprise_id);
	
	Home breed(int enterprise_id);

}
