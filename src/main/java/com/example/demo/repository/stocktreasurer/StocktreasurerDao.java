package com.example.demo.repository.stocktreasurer;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.User;

public interface StocktreasurerDao {
	
	List<Stocktreasurer> stocktreasurerList(int stock_id);
	
	int stocktreasurerInsert(Stocktreasurer stocktreasurer);
	
	int executeStocktreasurer(int stock_id, int treasurercount);
	
	List<User> userList(int stock_id);
	
	Optional<User> userDetail(int stock_id);
	

}
