package com.job.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.job.entities.User;
import com.job.repository.UserRepository;


@Service
public class UserrDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findFirstByEmail(email);
		if(optUser.isEmpty())throw new UsernameNotFoundException("User not found");
		return new org.springframework.security.core.userdetails.User(optUser.get().getEmail(),optUser.get().getPassword(),new ArrayList<>());
	}

}
