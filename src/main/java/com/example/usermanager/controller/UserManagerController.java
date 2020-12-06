package com.example.usermanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanager.config.JwtTokenUtil;
import com.example.usermanager.dto.AuthenticationReqDTO;
import com.example.usermanager.dto.AuthenticationRespDTO;
import com.example.usermanager.dto.UserManagerDTO;
import com.example.usermanager.service.MyUserDetailsService;
import com.example.usermanager.service.UserManagerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value="/api")
public class UserManagerController {

	@Autowired
	private UserManagerService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
//	@Autowired
//	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> addUser(@RequestBody UserManagerDTO userDto){
		return userService.addUser(userDto);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody UserManagerDTO userDto){
		return userService.updateUser(userDto);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long idUser){
		return userService.deleteUser(idUser);
	}
	
	@HystrixCommand(fallbackMethod = "hystrixGetUsers", commandKey = "getUsers", groupKey = "getUsers")
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserManagerDTO>> getUsers(){
		int val = 1_234;
//		kafkaTemplate.send("Kafka_Example", "test message");
		if(RandomUtils.nextBoolean()) {
			throw new RuntimeException("Hystrix custom exception");
		}
		return userService.getUsers();	
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationReqDTO authRequest) throws Exception {
		try {	
		authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Bad username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		
		final String jwt =  jwtTokenUtil.generateToken(userDetails);
		
		userService.updateLoginData(authRequest.getUsername(), jwt);
		
		return ResponseEntity.ok(new AuthenticationRespDTO(jwt));
	}
	
	public ResponseEntity<List<UserManagerDTO>> hystrixGetUsers() {
		UserManagerDTO userDto = new UserManagerDTO();
		List<UserManagerDTO> list = new ArrayList<>();
		list.add(userDto);
		return new ResponseEntity<List<UserManagerDTO>>(list,HttpStatus.OK);
	}
}
