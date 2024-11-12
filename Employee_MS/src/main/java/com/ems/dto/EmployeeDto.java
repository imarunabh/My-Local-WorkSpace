package com.ems.dto;

import com.ems.enums.UserRole;

public class EmployeeDto {
	
	 private Long Id;
		
		private String name;
		
		private String Address;
		
		private String email;
		
		private String password;
		
		private UserRole role;

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return Address;
		}

		public void setAddress(String address) {
			Address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public EmployeeDto(Long id, String name, String address, String email, String password, UserRole role) {
			super();
			Id = id;
			this.name = name;
			Address = address;
			this.email = email;
			this.password = password;
			this.role = role;
		}

		public EmployeeDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "EmployeeDto [Id=" + Id + ", name=" + name + ", Address=" + Address + ", email=" + email
					+ ", password=" + password + ", role=" + role + "]";
		}
		
		

}
