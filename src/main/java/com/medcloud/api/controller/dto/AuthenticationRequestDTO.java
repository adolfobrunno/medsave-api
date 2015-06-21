package com.medcloud.api.controller.dto;

public class AuthenticationRequestDTO {

	String username;
	String password;
	
	public AuthenticationRequestDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
