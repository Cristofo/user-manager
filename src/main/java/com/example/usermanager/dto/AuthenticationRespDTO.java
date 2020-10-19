package com.example.usermanager.dto;

import lombok.Data;

@Data
public class AuthenticationRespDTO {
	
	private String jwt;
	
	public AuthenticationRespDTO(String jwt) {
		this.jwt = jwt;
	}
}
