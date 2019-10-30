package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
	
	UserEntity findUserByEmail(String email);
	UserEntity findUserById(int id);
	
}
