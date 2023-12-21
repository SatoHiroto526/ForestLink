package com.example.demo.form.masterData;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BreedForm {
	
	private int breed_id;
	
	@NotNull(message = "品種は必須項目です")
	@Size(min = 1, max = 50,message = "品種は50文字以内の文字列で入力してください")
	private String breed;
	
	private boolean newBreed;

}
