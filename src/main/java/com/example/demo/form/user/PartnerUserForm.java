package com.example.demo.form.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PartnerUserForm {
	
private int user_id;
	
	@NotNull(message = "所属企業は必須項目です")
	private int enterprise_id;
	
	@NotNull(message = "外部取引先人材名は必須項目です")
	@Size(min = 1, max = 50,message = "外部取引先人材名は50文字以内の文字列で入力してください")
	private String username;
	
	@Email(message = "入力されたメールアドレスは有効ではありません")
	@Size(max = 100,message = "外部取引先人材メールアドレスは100文字以内の文字列で入力してください")
	private String useremail;
	
	@Size(max = 30,message = "外部取引先人材電話番号は30文字以内の文字列で入力してください")
	private String usernumber;
	
	private boolean newUser;

}
