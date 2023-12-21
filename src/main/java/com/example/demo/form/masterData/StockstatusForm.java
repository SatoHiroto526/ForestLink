package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StockstatusForm {
	
	private int stockstatus_id;
	
	@NotNull(message = "在庫状態は必須項目です")
	@Size(min = 1, max = 50,message = "在庫状態は50文字以内の文字列で入力してください")
	private String stockstatus;
	
	private boolean newStockstatus;

}
