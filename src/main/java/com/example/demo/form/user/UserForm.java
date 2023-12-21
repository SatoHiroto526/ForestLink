package com.example.demo.form.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
	
	private int user_id;
	
	@NotNull(message = "権限は必須項目です")
	private String authority;
	
	@NotNull(message = "所属企業は必須項目です")
	private int enterprise_id;
	
	@NotNull(message = "パスワードは必須項目です")
	@Size(min = 8, max = 16,message = "パスワードは8文字以上16文字以下の文字列で入力してください")
	private String password;
	
	@NotNull(message = "ユーザー名は必須項目です")
	@Size(min = 1, max = 50,message = "ユーザー名は50文字以内の文字列で入力してください")
	private String username;
	
	@NotNull(message = "ユーザーメールアドレスは必須項目です")
	@Email(message = "入力されたメールアドレスは有効ではありません")
	@Size(max = 100,message = "ユーザーメールアドレスは100文字以内の文字列で入力してください")
	private String useremail;
	
	@Size(max = 30,message = "ユーザー電話番号は30文字以内の文字列で入力してください")
	private String usernumber;
	
	private boolean newUser;

}
