package com.example.demo.service.entryTrading;

import java.util.List;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Trading;
import com.example.demo.entity.User;

public interface EntryTradingService {
	
	List<Trading> tradingList();
	
	List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id);
	
	Trading purchaseTradingDetail(int trading_id);
	
	Trading salesTradingDetail(int trading_id);
	
	void tradingInsert(Trading trading);
	
	void tradingUpdate(Trading trading);
	
	void tradingDelete(int trading_id);
	
	List<Enterprise> enterpriseList();
	
	List<User> userList();
	
	List<Product> productList();

}
