package com.example.demo.form.stock;

import lombok.Data;

@Data
public class StockFilter {
	
	private int enterprise_id;
	private String stocktype;
	private int stockstatus_id;
	private int producttype_id;
	private int breed_id;
	

}
