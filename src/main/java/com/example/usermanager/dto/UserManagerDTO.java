package com.example.usermanager.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserManagerDTO {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String creationDate;
	private String modificationDate;
	private String lastLoginDate;
	private String token;
	private boolean isActive;
	private List<PhoneDTO> phones;
}
