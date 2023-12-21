package com.example.demo.form.trading;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TradingForm {
	
	private int trading_id;
	
	@NotNull(message = "取引製品は必須項目です")
	private int product_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String orderday;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String purchaseday;
	
	@NotNull(message = "購買企業は必須項目です")
	private int purchaseenterprise_id;
	
	@NotNull(message = "販売企業は必須項目です")
	private int salesenterprise_id;
	
	@NotNull(message = "購買ユーザーは必須項目です")
	private int purchaseuser_id;
	
	@NotNull(message = "販売ユーザーは必須項目です")
	private int salesuser_id;
	
	private String salesworktype;
	
	@Min(value = 1, message = "取引数は1以上の整数で入力してください")
	private int count;
	
	@Min(value = 0, message = "取引総額は0以上の整数で入力してください")
	private int price;
	
	private boolean newTrading;

}
