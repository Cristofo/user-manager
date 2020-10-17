package com.example.usermanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.usermanager.dto.UserManagerDTO;


public interface UserManagerService {

	public ResponseEntity<Object> addUser(UserManagerDTO userManagerDto);
	
	public ResponseEntity<Object> updateUser(UserManagerDTO userManagerDto);
	
	public ResponseEntity<Object> deleteUser(Long idUser);
	
	public ResponseEntity<List<UserManagerDTO>> getUsers();
}
