package com.example.usermanager.service.impl;

import java.util.List;

import javax.annotation.processing.Generated;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.example.usermanager.dto.UserManagerDTO;

@Generated(value = "org.junit-tools-1.1.0")
public class UserManagerServiceImplTest {

	private UserManagerServiceImpl createTestSubject() {
		return new UserManagerServiceImpl();
	}

	@Test
	public void testAddUser() throws Exception {
		UserManagerServiceImpl testSubject;
		UserManagerDTO userManagerDto = null;
		ResponseEntity<Object> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.addUser(userManagerDto);
	}

	@Test
	public void testUpdateUser() throws Exception {
		UserManagerServiceImpl testSubject;
		UserManagerDTO userManagerDto = null;
		ResponseEntity<Object> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.updateUser(userManagerDto);
	}

	@Test
	public void testDeleteUser() throws Exception {
		UserManagerServiceImpl testSubject;
		Long idUser = null;
		ResponseEntity<Object> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.deleteUser(idUser);
	}

	@Test
	public void testGetUsers() throws Exception {
		UserManagerServiceImpl testSubject;
		ResponseEntity<List<UserManagerDTO>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getUsers();
	}

	@Test
	public void testUpdateLoginData() throws Exception {
		UserManagerServiceImpl testSubject;
		String userName = "";
		String token = "";

		// default test
		testSubject = createTestSubject();
		testSubject.updateLoginData(userName, token);
	}
}