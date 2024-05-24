package com.qsp.employee_springboot.exception;

public class NoDataAvailableException extends RuntimeException{
	private String message;
	public NoDataAvailableException(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
}
