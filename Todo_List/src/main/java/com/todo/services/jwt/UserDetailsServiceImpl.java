package com.todo.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.entities.User;
import com.todo.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findFirstByEmail(username);
		if(optUser.isEmpty())throw new UsernameNotFoundException("User not found");
		return new org.springframework.security.core.userdetails.User(optUser.get().getEmail(),optUser.get().getPassword(),new ArrayList<>());
	}

}
