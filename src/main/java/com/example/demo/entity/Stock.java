package com.example.demo.entity;

import lombok.Data;

@Data
public class Stock {
	
	private int stock_id;
	private int enterprise_id;
	private int product_id;
	private String stocktype;
	private int stockstatus_id;
	private int liabilityuser_id;
	private int stockcount;
	private String archive;
	private Enterprise enterprise;
	private Stockstatus stockstatus;
	private User user;
	private Producttype producttype;
	private Breed breed; 
	private Product product;

}
