package com.medcloud.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medcloud.api.controller.dto.ImageRequestDTO;
import com.medcloud.api.controller.dto.ImageResponseDTO;
import com.medcloud.core.entities.Image;
import com.medcloud.core.enums.ImageType;
import com.medcloud.core.exceptions.AuthenticationException;
import com.medcloud.core.persistence.services.ImageService;

@Controller
@RequestMapping(value="/image")
public class ImageController {

	@Resource
	private ImageService imageService;
	
	@ResponseBody
	@RequestMapping(value="/new", method={RequestMethod.POST})
	public ImageResponseDTO save(@RequestBody  ImageRequestDTO request, HttpServletResponse response){
		
		ImageResponseDTO dto = new ImageResponseDTO();
		
		Image image = new Image();
		image.setCid(request.getCid());
		image.setCreation_date(request.getCreation_date());
		image.setIid(request.getIid());
		image.setType(ImageType.valueOf(request.getType()));
		
		try {
			imageService.save(image, request.getUser_token());
			dto.setSuccess(true);
			response.setStatus(HttpStatus.OK.value());
		} catch (AuthenticationException e) {
			dto.setSuccess(false);
			dto.setError(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
		return dto;
	}
	
	
	
}
