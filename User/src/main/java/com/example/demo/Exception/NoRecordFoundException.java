package com.example.demo.Exception;

public class NoRecordFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5008669662013468284L;

	public NoRecordFoundException(String message) {
		super(message);
	}
	
}
