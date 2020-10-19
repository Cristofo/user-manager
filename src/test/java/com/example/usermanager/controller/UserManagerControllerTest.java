package com.example.usermanager.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.usermanager.config.JwtTokenUtil;
import com.example.usermanager.dto.UserManagerDTO;
import com.example.usermanager.filter.JwtRequestFilter;
import com.example.usermanager.repository.UserManagerRepository;
import com.example.usermanager.service.MyUserDetailsService;
import com.example.usermanager.service.UserManagerService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserManagerController.class)
public class UserManagerControllerTest {


	@Autowired
	private MockMvc mvcMock;
	
	@MockBean
	private UserManagerService userManagerService;
	
	@MockBean
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private UserManagerRepository userRepo;
	
	@MockBean
	private AuthenticationManager authenticationManager;
	
	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	
	private Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMzA4NzUxOCwiaWF0IjoxNjAzMDY5NTE4fQ.JXeHk5bWRCt21TyoaVjEOPK-BdLIWZlh2Mylq9tGDhUHx70vB9sz9SIlO8QBERfZJ_PQoAwGj7Nrz38p5dI3FA";
	
	  @Before
	    public void setUp() {
		 
			String userName = "admin";
			Mockito.when(jwtTokenUtil.getUsernameFromToken(token.substring(7))).thenReturn(userName);
			UserDetails userDetails = new User(userName, "Hunter22", new ArrayList<>());
			Mockito.when(myUserDetailsService.loadUserByUsername(userName)).thenReturn(userDetails);
			Mockito.when(jwtTokenUtil.validateToken(token.substring(7), userDetails)).thenReturn(true);
	    }
	
	@Test
	public void addUserTest() {
		
		UserManagerDTO userDto = new UserManagerDTO();
		String url = "/api/addUser";
		Mockito.when(userManagerService.addUser(Mockito.any())).thenReturn(new ResponseEntity<Object>(userDto,HttpStatus.OK));
		try {
			mvcMock.perform(MockMvcRequestBuilders.post(url).param("").header("Authorization", token)).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}  
	
	@Test
	public void updateUserTest() {
		
		UserManagerDTO userDto = new UserManagerDTO();
		String url = "/api/updateUser";
		Mockito.when(userManagerService.updateUser(Mockito.any())).thenReturn(new ResponseEntity<Object>(userDto,HttpStatus.OK));
		try {
			mvcMock.perform(MockMvcRequestBuilders.put(url).param("").header("Authorization", token)).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	} 

	@Test
	public void deleteUserTest() {
		String url = "/api/getUsers";
		Mockito.when(userManagerService.deleteUser(Mockito.any())).thenReturn(new ResponseEntity<Object>("",HttpStatus.OK));
		try {
			mvcMock.perform(MockMvcRequestBuilders.delete(url).param("").header("Authorization", token)).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}  
	
	@Test
	public void getUsersTest() {
		List<UserManagerDTO> userManagerDtoList = new ArrayList<UserManagerDTO>();
		Mockito.when(userManagerService.getUsers()).thenReturn(new ResponseEntity<List<UserManagerDTO>>(userManagerDtoList,HttpStatus.OK));
		String url = "/api/getUsers";
		
		try {
			mvcMock.perform(MockMvcRequestBuilders.get(url).header("Authorization", token)).andExpect(status().isOk());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}  
	
}
