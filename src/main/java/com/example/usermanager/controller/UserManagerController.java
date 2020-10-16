package com.example.usermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanager.dto.UserManagerDto;
import com.example.usermanager.service.UserManagerService;

@RestController
@RequestMapping(value="/api")
public class UserManagerController {

	@Autowired
	private UserManagerService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserManagerDto userDto){
		return userService.addUser(userDto);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(UserManagerDto userDto){
		return userService.updateUser(userDto);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long idUser){
		return userService.deleteUser(idUser);
	}
	
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserManagerDto>> getUsers(){
		return userService.getUsers();
	}
}
