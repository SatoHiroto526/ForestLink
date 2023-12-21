package com.example.demo.entity;

import lombok.Data;

@Data
public class Trading {
	
	private int trading_id;
	private int product_id;
	private String orderday;
	private String purchaseday;
	private int purchaseenterprise_id;
	private int salesenterprise_id;
	private int purchaseuser_id;
	private int salesuser_id;
	private int count;
	private int price;
	private String salesworktype;
	private Enterprise enterprise;
	private User user;
	

}
