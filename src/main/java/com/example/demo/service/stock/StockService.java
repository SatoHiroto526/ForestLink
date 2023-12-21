package com.example.demo.service.stock;

import java.util.List;

import com.example.demo.entity.Archive;
import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.Stocktype;
import com.example.demo.entity.User;

public interface StockService {
	
	List<Enterprise> enterpriseList();
	
	List<Stockstatus> stockstatusList();
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	List<Stock> stockList(String archive);
	
	List<Stocktype> stocktypeList();
	
	List<Stock> filteredStockList(String archive, int enterprise_id, String stocktype, int stockstatus_id, int producttype_id, int breed_id);
	
	Stock stockDetail(int stock_id);
	
	List<Archive> archiveList(); 
	
	List<Product> productList();
	
	List<User> userList();
	
	void stockInsert(Stock stock);
	
	void stockUpdate(Stock stock);
	
	

}
