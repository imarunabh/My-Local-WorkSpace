package com.job.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.dto.UserRegistrationDTO;
import com.job.models.User;
import com.job.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
	@Autowired
    private UserService userService;

    // Endpoint for user registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        // Register user
        User registeredUser = userService.registerUser(userRegistrationDTO);
        return ResponseEntity.ok("User registered successfully with ID: " + registeredUser.getId());
    }

}
