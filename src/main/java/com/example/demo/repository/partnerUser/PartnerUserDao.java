package com.example.demo.repository.partnerUser;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.User;

public interface PartnerUserDao {
	
	Optional<User> userDetail(int user_Id); 
	
	List<User> userAll();
	
	List<Enterprise> partnerEnterpriseList();
	
	List<User> filteredUserList(int enterprise_Id);
	
	int userInsert(User user);
	
	int userUpdate(User user);
	
	int userDelete(int user_id);

}
