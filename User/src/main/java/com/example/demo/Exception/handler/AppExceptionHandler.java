package com.example.demo.Exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Exception.NoRecordFoundException;
import com.example.demo.Exception.UserAlreadyExistsException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.errorMessages.error;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = {BadRequestException.class})
	public ResponseEntity<Object> badRequestException(BadRequestException ex, WebRequest request)
	{
		error obj = new error("BAD_REQUEST", ex.getMessage());
		return new ResponseEntity<Object>(obj,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UserAlreadyExistsException.class})
	public ResponseEntity<Object> recordAlreadyExixts(UserAlreadyExistsException ex, WebRequest request)
	{
		error obj = new error("USER_ALREADY_EXISTS", ex.getMessage());
		return new ResponseEntity<Object>(obj,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value =  {UserNotFoundException.class})
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex, WebRequest request)
	{
		error obj = new error("NO_RECORD_FOUND", ex.getMessage());
		return new ResponseEntity<Object>(obj,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request)
	{
		error obj = new error("NO_DATA_FOUND", ex.getMessage());
		return new ResponseEntity<Object>(obj, HttpStatus.NOT_FOUND);
	}
	 
}
