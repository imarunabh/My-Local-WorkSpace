package com.ems.services.admin;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDto;
import com.ems.entities.User;
import com.ems.enums.UserRole;
import com.ems.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostConstruct
	public void createUserAccount() {
		User user = new User();
		user.setEmail("employee@test.com");
		user.setName("employee");
		user.setRole(UserRole.EMPLOYEE);
		user.setPassword(new BCryptPasswordEncoder().encode("employee"));
		userRepository.save(user);
	}


	@Override
	public EmployeeDto postStudent(EmployeeDto employeeDto) {
		Optional<User> opt = userRepository.findFirstByEmail(employeeDto.getEmail());
		
		if(opt.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(employeeDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(employeeDto.getPassword()));
			user.setRole(UserRole.EMPLOYEE);
			User createdUser = userRepository.save(user);
			EmployeeDto createdDto = new EmployeeDto();
			createdDto.setId(createdUser.getId());
			createdDto.setEmail(createdUser.getEmail());
			return createdDto;
		}
		return null;
		
	}

}
