package com.example.demo.Exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -276828812353122745L;
	
	public BadRequestException(String message) {
		super(message);
	}

}
