package com.medcloud.api.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	@RequestMapping(value="/new", method={RequestMethod.POST}, consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
	public ImageResponseDTO save(
			@RequestPart("properties") ImageRequestDTO properties, 
			@RequestPart("file") MultipartFile file,
			MultipartHttpServletRequest request, HttpServletResponse response){
		
		ImageResponseDTO dto = new ImageResponseDTO();
		
		Image image = new Image();
		
		image.setCid(properties.getCid());
		image.setCreation_dateString(properties.getCreation_date());
		image.setIid(properties.getIid());
		image.setModality(ImageType.valueOf(properties.getModality()));
		image.setPid(properties.getPid());
		image.setSid(properties.getSid());
		image.setSno(properties.getSno());
		image.setSize(properties.getSize());
		
		try {
			imageService.save(image, file.getInputStream(), properties.getUser_token());
			dto.setSuccess(true);
		} catch (AuthenticationException e) {
			dto.setSuccess(false);
			dto.setError(e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} catch (IOException e) {
			dto.setSuccess(false);
			dto.setError(e.getMessage());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		
		return dto;
	}
	
	
	
}
