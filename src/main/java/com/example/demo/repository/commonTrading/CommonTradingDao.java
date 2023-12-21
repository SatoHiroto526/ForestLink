package com.example.demo.repository.commonTrading;

import java.util.List;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;

public interface CommonTradingDao {
	
	List<Enterprise> enterpriseList();
	
	List<User> userList();
	
	List<Product> productList();
	
	List<Enterprise> partnerenterpriseList();
	
	List<User> partneruserList();

}
