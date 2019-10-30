package com.example.demo.operation;

public class OperationResponse {
	
	private String operation;
	private String description;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OperationResponse(String operation, String description) {
		super();
		this.operation = operation;
		this.description = description;
	}
	
	
}
