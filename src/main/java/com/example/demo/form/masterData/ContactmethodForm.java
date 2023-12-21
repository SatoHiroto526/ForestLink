package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactmethodForm {
	
	private int contactmethod_id;
	
	@NotNull(message = "連絡手段は必須項目です")
	@Size(min = 1, max = 50,message = "連絡手段は50文字以下の文字列で入力してください")
	private String contactmethod;
	
	private boolean newContactmethod;

}
