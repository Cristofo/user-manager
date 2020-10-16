package com.example.usermanager.dto;

import lombok.Data;

@Data
public class UserManagerDto {
	
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
}
