package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorktypeForm {
	
	private int worktype_id;
	
	@NotNull(message = "活動詳細は必須項目です")
	@Size(min = 1, max = 50,message = "活動種別は50文字以下の文字列で入力してください")
	private String worktype;
	
	private boolean newWorktype;

}
