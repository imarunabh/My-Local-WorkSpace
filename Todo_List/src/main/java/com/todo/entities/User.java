package com.todo.entities;

import com.todo.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String first_Name;
	
	private String last_Name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private UserRole role = UserRole.USER;

}
