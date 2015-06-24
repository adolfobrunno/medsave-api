package com.medcloud.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medcloud.api.controller.dto.AuthenticationRequestDTO;
import com.medcloud.api.controller.dto.AuthenticationResponseDTO;
import com.medcloud.core.exceptions.AuthenticationException;
import com.medcloud.core.persistence.services.UserService;

@Controller
@RequestMapping(value="/authenticate")
public class AuthenticationController {
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value="", method={RequestMethod.POST, RequestMethod.GET})
	public AuthenticationResponseDTO autenticate(@RequestBody  AuthenticationRequestDTO request, HttpServletResponse response){
		
		AuthenticationResponseDTO dto = new AuthenticationResponseDTO();
		
		try {
			dto.setToken(userService.authenticate(request.getUsername(), request.getPassword()));
			dto.setSuccess(true);
			response.setStatus(HttpStatus.OK.value());
		} catch (AuthenticationException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			dto.setSuccess(false);
			dto.setError(e.getMessage());
		}
		
		return dto;
	}

}
