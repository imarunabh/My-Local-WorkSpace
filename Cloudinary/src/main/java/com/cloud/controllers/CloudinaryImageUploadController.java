package com.cloud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.service.cloudinary.CloudinaryImageService;

@RestController
@RequestMapping("/cloudinary/upload")
@CrossOrigin
public class CloudinaryImageUploadController {
	
	@Autowired
	private CloudinaryImageService cloudinaryImageService;
	
	@PostMapping
	public ResponseEntity<Map> uploadImage(@RequestParam("image") MultipartFile file){
		Map data = this.cloudinaryImageService.upload(file);
//		System.out.println(data);
		if(data.containsKey("url")) {
			System.out.println(data.get("url"));
		}
		return new ResponseEntity<>(data,HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<Map<String,String>> getString(){
		String fetch = this.cloudinaryImageService.fetch();
		Map<String,String> response = new HashMap<>();
		response.put("url", fetch);
		return ResponseEntity.ok(response);
	}

}
