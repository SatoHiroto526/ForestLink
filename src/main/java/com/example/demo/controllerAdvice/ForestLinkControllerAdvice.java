package com.example.demo.controllerAdvice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.exception.HandlingException;

@ControllerAdvice
public class ForestLinkControllerAdvice {
	
	@InitBinder
	public void intiBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ExceptionHandler(HandlingException.class)
	public String handleException(HandlingException e,Model model) {
		model.addAttribute("message",e);
		return "error/customError";
	}	

}
