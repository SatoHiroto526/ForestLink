package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProducttypeForm {
	
	private int producttype_id;
	
	@NotNull(message = "製品種別は必須項目です")
	@Size(min = 1, max = 50,message = "製品種別は50文字以内の文字列で入力してください")
	private String producttype;
	
	private boolean newProducttype;

}
