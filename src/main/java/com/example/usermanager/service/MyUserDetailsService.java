package com.example.usermanager.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.usermanager.entity.UserManagerEntity;
import com.example.usermanager.repository.UserManagerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserManagerRepository userManagerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserManagerEntity userManagerEntity = userManagerRepository.findByName(username);
		if(null == userManagerEntity) {
			throw new UsernameNotFoundException("User not found.");
		}
		return new User(userManagerEntity.getName(), userManagerEntity.getPassword(), new ArrayList<>());
	}
}