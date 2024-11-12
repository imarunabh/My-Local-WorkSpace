package com.job.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.enums.Role;
import com.job.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
User findByRole(Role userRole);
	
	Optional<User> findFirstByEmail(String email);
	
	List<User> findAllByRole(Role userRole);
}
