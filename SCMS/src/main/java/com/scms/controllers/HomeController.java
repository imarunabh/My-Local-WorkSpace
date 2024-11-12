package com.scms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scms.dto.TeacherDto;
import com.scms.services.home.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers(){
		List<TeacherDto> allTeachers = homeService.getAllTeachers();
		return ResponseEntity.ok(allTeachers);
	}
}
