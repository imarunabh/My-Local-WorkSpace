package com.assignment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//This is the first controller which is responsible for displaying "Hello World" message
//as this controller is returning the message in the form of ResponseEntity so we are declaring this
// this class with @RestController
@RestController
public class HelloController2 {
	
	@GetMapping("/hello2")
	public ResponseEntity<?> getHello(){
		return  ResponseEntity.ok("Hello World");
	}

}
