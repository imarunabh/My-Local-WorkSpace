package com.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.test.entities.User;
import com.test.entities.repo.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void createUser(){
		User user = new User();
		user.setEmail("user@test.com");
		userRepository.save(user);
	}

}
