package com.dbms.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbms.entities.Users;
import com.dbms.repository.UserRepository;

@RestController
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/student")
	public ResponseEntity<?> postStudent(@RequestBody Users user){
		user.setDate(new Date());
		Users save = this.userRepository.save(user);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("/student")
	public ResponseEntity<?> getStudent(@RequestBody String email){
		List<String> findByEmail = this.userRepository.findtByEmail("arunabhsharma@gmail.com");
		return ResponseEntity.ok(findByEmail);
	}

}
