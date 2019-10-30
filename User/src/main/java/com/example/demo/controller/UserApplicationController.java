package com.example.demo.controller; 

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.UserDto.UserDto;
import com.example.demo.errorMessages.errorMessage;
import com.example.demo.operation.OperationResponse;
import com.example.demo.service.UserService;
import com.example.demo.userRequest.UserRequest;
import com.example.demo.userResponse.UserResponse;

@RestController
@RequestMapping("/user")
public class UserApplicationController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public UserResponse addUser(@RequestBody UserRequest request)
	{
		UserResponse returnValue = new UserResponse();
		UserDto requestBody = new UserDto();
		if(request.getFirstName().isEmpty() || request.getLastName().isEmpty() || request.getEmail().isEmpty())
			throw new BadRequestException(errorMessage.MISSING_REQUIRED_FIELDS.getErrorMessages());
		BeanUtils.copyProperties(request, requestBody);
		UserDto responseBody = userService.addUser(requestBody);
		BeanUtils.copyProperties(responseBody, returnValue);
		return returnValue;
	}
	
	@GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserResponse getUser(@PathVariable("id") int id)
	{
		UserResponse returnValue = new UserResponse();
		UserDto userData = userService.getUser(id);
		BeanUtils.copyProperties(userData, returnValue);
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public OperationResponse deleteUser(@PathVariable("id") int id)
	{
		OperationResponse returnValue = new OperationResponse("Delete", "Record has been successfully deleted");
		userService.deleteUser(id);
		return returnValue;
	}
	
	@PutMapping(path="/{id}", produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserResponse updateUser(@RequestBody UserRequest requestBody, @PathVariable("id") int id)
	{
		UserResponse returnValue = new UserResponse();
		UserDto request = new UserDto();
		BeanUtils.copyProperties(requestBody, request);
		UserDto response = userService.updateUser(request, id);
		BeanUtils.copyProperties(response, returnValue);
		return returnValue;
		
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse> getAllUser(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit)
	{
		List<UserResponse> returnValue = new ArrayList<>();
		if(page > 0) page -= 1;
		List<UserDto> userDto = userService.getAllUser(page,limit);
		for(UserDto obj : userDto)
		{
			UserResponse response = new UserResponse();
			BeanUtils.copyProperties(obj, response);
			returnValue.add(response);
		}
		return returnValue;
	}

}
