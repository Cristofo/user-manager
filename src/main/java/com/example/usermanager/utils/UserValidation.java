package com.example.usermanager.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.usermanager.dto.UserManagerDTO;

public class UserValidation {

	@Value("${error.add.user}")
	private String errorAddUser;
	
	public ResponseEntity<Object> validResponse(UserManagerDTO userManagerDTO){
		
		if(Optional.of(userManagerDTO).isPresent()) return  new ResponseEntity<Object>(userManagerDTO, HttpStatus.OK);
		
		else return new ResponseEntity<Object>(this.errorAddUser, HttpStatus.OK);
		
	}
}
