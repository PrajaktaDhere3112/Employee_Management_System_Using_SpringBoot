package com.qsp.employee_springboot.exception;

public class NameNotFoundException extends RuntimeException{
	private String message;
	public NameNotFoundException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
}
