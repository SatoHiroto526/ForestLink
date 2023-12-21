package com.example.demo.form.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductForm {
	
	private int product_id;
	
	@NotNull(message = "製品種別は必須項目です")
	private int producttype_id;
	
	@NotNull(message = "取扱参加企業は必須項目です")
	private int enterprise_id;
	
	@NotNull(message = "品種は必須項目です")
	private int breed_id;
	private String sawingmethod;
	private String materialtype;
	private int length;
	private int diameter_bottom;
	private int diameter_top;
	private int length_short;
	private int length_long;
	private String jas;
	
	@Size(min = 1, max = 1000,message = "製品詳細は1000文字以内の文字列で入力してください")
	private String productdetail;
	
	private boolean newProduct;

}
