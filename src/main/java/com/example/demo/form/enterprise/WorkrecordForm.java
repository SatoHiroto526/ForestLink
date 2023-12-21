package com.example.demo.form.enterprise;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorkrecordForm {
	
	private int workrecord_id;
	
	@NotNull(message = "活動種別は必須項目です")
	private int worktype_id;
	
	@NotNull(message = "インバウンドorアウトバウンドは必須項目です")
	private String salesworktype;
	
	@NotNull(message = "参加企業名は必須項目です")
	private int enterprise_id;
	private int partnerenterprise_id;
	
	@NotNull(message = "ユーザーは必須項目です")
	private int user_id;
	
	@NotNull(message = "外部取引先人材は必須項目です")
	private int partneruser_id;
	
	@NotNull(message = "連絡手段は必須項目です")
	private int contactmethod_id;
	
	@Size(min = 1, max = 1000,message = "活動詳細は1000文字以内の文字列で入力してください")
	private String workrecorddetail;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "活動日は必須項目です")
	private String workrecordday;
	
	private boolean newWorkrecord;

}
