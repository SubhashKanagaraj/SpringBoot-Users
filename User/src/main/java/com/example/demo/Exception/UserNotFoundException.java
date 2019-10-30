package com.example.demo.Exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4030662195288689598L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
