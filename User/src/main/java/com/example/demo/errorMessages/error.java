package com.example.demo.errorMessages;

public class error {

	private String code;
	private String description;
	
	public error(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String error) {
		this.code = error;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
