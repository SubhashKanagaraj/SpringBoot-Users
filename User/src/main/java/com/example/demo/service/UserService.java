package com.example.demo.service;

import java.util.List;

import com.example.demo.UserDto.UserDto;

public interface UserService {
	
	public UserDto addUser(UserDto data);
	public UserDto getUser(int id);
	public void deleteUser(int id);
	public UserDto updateUser(UserDto data, int id);
	public List<UserDto> getAllUser(int page, int limit);

}
