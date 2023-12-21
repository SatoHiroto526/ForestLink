package com.example.demo.entity;

import lombok.Data;

@Data
public class Stocktreasurer {
	
	private int stocktreasurer_id;
	private int stock_id;
	private int repuser_id;
	private String treasure;
	private int treasurercount;
	private String treasureday;
	private User user;

}
