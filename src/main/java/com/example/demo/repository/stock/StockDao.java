package com.example.demo.repository.stock;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Breed;
import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Product;
import com.example.demo.entity.Producttype;
import com.example.demo.entity.Stock;
import com.example.demo.entity.Stockstatus;
import com.example.demo.entity.User;

public interface StockDao {
	
	List<Enterprise> enterpriseList();
	
	List<Stockstatus> stockstatusList();
	
	List<Producttype> producttypeList();
	
	List<Breed> breedList();
	
	List<Stock> stockList(String archive);
	
	List<Stock> filteredStockList(String archive, int enterprise_id, String stocktype, int stockstatus_id, int producttype_id, int breed_id);
	
	Optional<Stock> StockDetail(int stock_id); 
	
	List<Product> productList();
	
	List<User> userList();
	
	int stockInsert(Stock stock);
	
	int stockUpdate(Stock stock);
	
}
