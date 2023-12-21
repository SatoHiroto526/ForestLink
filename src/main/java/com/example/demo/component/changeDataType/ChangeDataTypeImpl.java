package com.example.demo.component.changeDataType;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class ChangeDataTypeImpl implements ChangeDataType {
	
	public String dateToString(Date data) {
		if(data != null) {
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = data.toLocalDate();
			return localDate.format(formatter1);
		}else {
			return "";
		}
		
		
	}
	
	
	public String changeDataFormat(String date) {
		
		DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(date, originalFormatter);
	    return localDate.format(targetFormatter);
		
	}
	

}
