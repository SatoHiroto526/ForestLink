package com.example.demo.service.stocktreasurer;

import java.util.List;

import com.example.demo.entity.Stocktreasurer;
import com.example.demo.entity.Treasure;
import com.example.demo.entity.User;

public interface StocktreasurerService {
	
	List<Stocktreasurer> stocktreasurerList(int stock_id);
	
	List<User> userList(int stock_id);
	
	List<Treasure> treasureList();
	
	void treasureInsert(Stocktreasurer stocktreasurer);

}
