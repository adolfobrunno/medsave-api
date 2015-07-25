package com.medcloud.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medcloud.api.controller.dto.UserRequestDTO;
import com.medcloud.api.controller.dto.UserResponseDTO;
import com.medcloud.core.entities.Company;
import com.medcloud.core.entities.User;
import com.medcloud.core.persistence.services.CompanyService;
import com.medcloud.core.persistence.services.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	
	@Resource
	private UserService userService;
	@Resource
	private CompanyService companyService;
	
	@ResponseBody
	@RequestMapping(value="/new", method={RequestMethod.POST})
	public UserResponseDTO create(@RequestBody  UserRequestDTO request, HttpServletResponse response){
	
		Company company = new Company();
		company.setCid(request.getCid());
		company.setName("Empresa teste");
		companyService.save(company);
		
		UserResponseDTO dto = new UserResponseDTO();
		User user = new User();
		user.setUsername(request.getUsername());
		user.setRole(request.getRole());
		user.setPassword(request.getPassword());
		user.setCompany(company);
		userService.save(user);
		
		dto.setToken(user.getToken());
		
		return dto;
		
	}
}
