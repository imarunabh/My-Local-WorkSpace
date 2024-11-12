package com.scms.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.dto.StudentDto;
import com.scms.entities.User;
import com.scms.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByRole(UserRole userRole);
	
	Optional<User> findFirstByEmail(String email);
	
	List<User> findAllByRole(UserRole userRole);

}
