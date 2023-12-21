package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PartnerenterpriserankForm {
	
	private int partnerenterpriserank_id;
	
	@NotNull(message = "取引先ランク詳細は必須項目です")
	@Size(min = 1, max = 50,message = "取引先ランク詳細は50文字以下の文字列で入力してください")
	private String partnerenterpriserankdetail;
	
	private boolean newPartnerenterpriserank;

}
