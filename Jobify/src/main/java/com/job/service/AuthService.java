package com.job.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.job.dto.AuthenticationRequest;
import com.job.dto.UserRegistrationDTO;
import com.job.enums.Role;
import com.job.models.User;
import com.job.repo.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserRegistrationDTO request) {
        User user = new User();
        user.setEmail(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    public String authenticate(AuthenticationRequest request) {
        User user = userRepository.findFirstByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return jwtService.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Incorrect username or password");
        }
    }
}
