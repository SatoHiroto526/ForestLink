package com.example.demo.exception;

public class HandlingException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public HandlingException(String message) {
		super(message);
	}
	
	@Override
    public String getMessage() {
        return super.getMessage(); 
    }
	
	@Override
    public String toString() {
        return getMessage(); 
    }

}
