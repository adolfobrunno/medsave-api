package com.medcloud.api.controller.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.medcloud.api.controller.utils.DTO;

@JsonSerialize(include=Inclusion.NON_NULL)
public class AuthenticationResponseDTO extends DTO{

	String token;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
