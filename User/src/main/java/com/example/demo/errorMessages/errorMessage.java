package com.example.demo.errorMessages;

public enum errorMessage {
	
	MISSING_REQUIRED_FIELDS("Missing the required field. Please check the documentation for required fields"),
	RECORD_ALREADY_EXISTS("Provide the unique email id"),
	NO_DATA_FOUND("No record found in the profile"),
	NO_RECORD_FOUND("No record found for the Id passed");
	
	private String errorMessages;

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	private errorMessage(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	
}
