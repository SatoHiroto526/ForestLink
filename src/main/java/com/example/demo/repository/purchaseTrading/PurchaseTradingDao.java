package com.example.demo.repository.purchaseTrading;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Trading;

public interface PurchaseTradingDao {
	
	List<Trading> tradingList();
	
	List<Trading> filteredTradingList(int purchaseenterprise_id, int salesenterprise_id);
	
	Optional<Trading> purchaseTradingDetail(int trading_id);
	
	Optional<Trading> salesTradingDetail(int trading_id);
	
	int tradingInsert(Trading trading);
	
	int tradingUpdate(Trading trading);
	
	int tradingDelete(int trading_id);

}
