package com.example.demo.form.stock;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockForm {
	
	private int stock_id;
	
	@NotNull(message = "所有参加企業は必須項目です")
	private int enterprise_id;
	
	@NotNull(message = "製品は必須項目です")
	private int product_id;
	
	@NotNull(message = "在庫種別は必須項目です")
	private String stocktype;
	
	@NotNull(message = "在庫状態は必須項目です")
	private int stockstatus_id;
	
	@NotNull(message = "責任者ユーザーは必須項目です")
	private int liabilityuser_id;
	
	@NotNull(message = "アーカイブは必須項目です")
	private String archive;
	
	private boolean newStock;

}
