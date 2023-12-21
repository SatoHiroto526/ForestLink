package com.example.demo.repository.home;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Stock;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;


public interface HomeDao {
	
	Optional<User> userDetail(String email);
	
	List<Trading> entryTradingPurchase(int enterprise_id);
	
	List<Trading> purchaseTrading(int enterprise_id);
	
	List<Trading> entryTradingSales(int enterprise_id);
	
	List<Trading> salesTrading(int enterprise_id);
	
	List<Stock> stockType(int enterprise_id);
	
	List<Stock> breed(int enterprise_id);

}
