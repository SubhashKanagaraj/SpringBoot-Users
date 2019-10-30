package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.NoRecordFoundException;
import com.example.demo.Exception.UserAlreadyExistsException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.UserDto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.errorMessages.errorMessage;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDto addUser(UserDto data) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(data, userEntity);
		UserEntity checkUser = repo.findUserByEmail(userEntity.getEmail());
		if(checkUser != null)
			throw new UserAlreadyExistsException(errorMessage.RECORD_ALREADY_EXISTS.getErrorMessages());
		UserEntity responseBody = repo.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(responseBody, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUser(int id) {
		UserEntity userEntity = repo.findUserById(id);
		if(userEntity == null) throw new UserNotFoundException(errorMessage.NO_RECORD_FOUND.getErrorMessages());
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(int id) {
		
		UserEntity userEntity = repo.findUserById(id);
		if(userEntity == null) throw new UserNotFoundException(errorMessage.NO_RECORD_FOUND.getErrorMessages());
		repo.delete(userEntity);
		
	}

	@Override
	public UserDto updateUser(UserDto data, int id) {
		UserEntity entity = repo.findUserById(id);
		if(entity == null) throw new UserNotFoundException(errorMessage.NO_RECORD_FOUND.getErrorMessages());
		entity.setFirstName(data.getFirstName());
		entity.setLastName(data.getLastName());
		entity.setEmail(data.getEmail());
		UserEntity response = repo.save(entity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(response, returnValue);
		return returnValue;
	}

	@Override
	public List<UserDto> getAllUser(int page, int limit) {
		List<UserDto> returnValue = new ArrayList<UserDto>();
		Pageable pageRequest = PageRequest.of(page, limit);
		Page<UserEntity> response = repo.findAll(pageRequest);
		List<UserEntity> users = response.getContent();
		if(users.size() == 0) throw new NoRecordFoundException(errorMessage.NO_DATA_FOUND.getErrorMessages());
		for(UserEntity obj : users)
		{
			UserDto data = new UserDto();
			BeanUtils.copyProperties(obj, data);
			returnValue.add(data);
		}
		return returnValue;
	}

}
