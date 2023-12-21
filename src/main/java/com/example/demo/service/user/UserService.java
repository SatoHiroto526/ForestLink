package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;

public interface UserService {
	
	User userDetail(String useremail);
	
	User userDetail(int user_Id);
	
	List<User> userAll();
	
	List<Enterprise> entryEnterpriseList();
	
	List<User> filteredUserList(int enterprise_Id);
	
	void userInsert(User user);
	
	List<Authority> authortyList();
	
	void userUpdate(User user);
	
	void userDelete(int user_id);

}
