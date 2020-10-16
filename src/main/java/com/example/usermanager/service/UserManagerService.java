package com.example.usermanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.usermanager.dto.UserManagerDto;


public interface UserManagerService {

	public ResponseEntity<Object> addUser(UserManagerDto userManagerDto);
	
	public ResponseEntity<Object> updateUser(UserManagerDto userManagerDto);
	
	public ResponseEntity<Object> deleteUser(Long idUser);
	
	public ResponseEntity<List<UserManagerDto>> getUsers();
}
