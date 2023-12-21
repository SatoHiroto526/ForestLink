package com.example.demo.form.enterprise;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnterpriseForm {
	
	private int enterprise_id;
	
	@NotNull(message = "企業名は必須項目です")
	@Size(min = 1, max = 50,message = "企業名は50文字以内の文字列で入力してください")
	private String enterprisename;
	
	@NotNull(message = "郵便番号は必須項目です")
	@Size(min = 1, max = 30,message = "郵便番号は30文字以内の文字列で入力してください")
	private String postalcode;
	
	@NotNull(message = "所在地は必須項目です")
	@Size(min = 1, max = 100,message = "所在地は100文字以内の文字列で入力してください")
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String foundnationdate; 
	
	@Size(max = 50,message = "代表者氏名は50文字以内の文字列で入力してください")
	private String chiefname;
	
	@Size(max = 50,message = "資本金は50文字以内の文字列で入力してください")
	private String capital;
	
	@Size(max = 50,message = "法人番号は50文字以内の文字列で入力してください")
	private String corporatenumber;
	
	private int employees;
	
	private int partnerenterpriserank_id;
	
	@NotNull(message = "事業区分は必須項目です")
	private int businesstype_id;
	
	@Size(max = 200,message = "事業内容は200文字以内の文字列で入力してください")
	private String businessdetail;
	
	@Size(max = 30,message = "企業電話番号は30文字以内の文字列で入力してください")
	private String enterprisenumber;
	
	@Email(message = "入力されたメールアドレスは有効ではありません")
	@Size(max = 100,message = "企業メールアドレスは100文字以内の文字列で入力してください")
	private String enterpriseemail;
	
	@Size(max = 500,message = "企業ホームページURLは500文字以内の文字列で入力してください")
	private String homepage;
	
	private int mainenterprise_id;

	private boolean newEnterprise;

}
