package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BusinesstypeForm {
	
	private int businesstype_id;
	
	@NotNull(message = "事業区分は必須項目です")
	@Size(min = 1, max = 50,message = "事業区分は50文字以下の文字列で入力してください")
	private String businesstype;
	
	private boolean newBusinesstype;

}
