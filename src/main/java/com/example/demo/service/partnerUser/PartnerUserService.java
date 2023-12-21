package com.example.demo.service.partnerUser;

import java.util.List;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;

public interface PartnerUserService {
	
	User userDetail(int user_Id);
	
	List<User> userAll();
	
	List<Enterprise> partnerEnterpriseList();
	
	List<User> filteredUserList(int enterprise_Id);
	
	void userInsert(User user);
	
	void userUpdate(User user);
	
	void userDelete(int user_id);

}
