package com.example.demo.form.stocktreasurer;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StocktreasurerForm {
	
	private int stocktreasurer_id;
	private int stock_id;
	
	@NotNull(message = "入出庫担当ユーザーは必須項目です")
	private int repuser_id;
	
	@NotNull(message = "入庫or出庫は必須項目です")
	private String treasure;
	
	@Digits(integer= 5, fraction = 0, message = "入出庫数は1以上99999以下の整数で入力してください")
	@Min(value = 1, message = "入出庫数は1以上99999以下の整数で入力してください")
	private int treasurercount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "入出庫日は必須項目です")
	private String treasureday;

}
