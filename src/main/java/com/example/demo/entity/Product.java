package com.example.demo.entity;

import lombok.Data;

@Data
public class Product {
	
	private int product_id;
	private int producttype_id;
	private int enterprise_id;
	private int breed_id;
	private String sawingmethod;
	private String materialtype;
	private int length;
	private int diameter_bottom;
	private int diameter_top;
	private int length_short;
	private int length_long;
	private String jas;
	private String productdetail;
	private Enterprise entryenterprise;
	private Producttype producttype;
	private Breed breed;

}
