package com.assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//This is the first controller which is responsible for displaying hello.html file of thymeleaf
//as this controller is returning the html file to we are annotating this class with @Controller
@Controller
public class HelloController1 {
	
	@GetMapping("/hello1")
	public String getHello() {
		return "hello";//it is returning hello.html file
	}

}
