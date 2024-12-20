package com.job.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.job.dto.AuthenticationRequest;
import com.job.entities.User;
import com.job.repository.UserRepository;
import com.job.services.jwt.UserrDetailsServiceImpl;
import com.job.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserrDetailsServiceImpl userDetailsServie;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization ";
	
	@PostMapping("/authenticate")
	public void createAuthentication(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or Password");
		}
		catch(DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User does not exist");
		}
		
		final UserDetails userDetails=  userDetailsServie.loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		if((optionalUser.isPresent())) {
			response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId()).put("role", optionalUser.get().getRole()).toString());
		}
		
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-with,Content-Type,Accept,X-Custom-header");
		response.setHeader(HEADER_STRING,TOKEN_PREFIX+jwt);
		System.out.println(jwt);
		return ;
		
	}

}
