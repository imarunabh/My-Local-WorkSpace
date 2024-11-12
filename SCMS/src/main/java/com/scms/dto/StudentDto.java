package com.scms.dto;

import java.util.Date;

public class StudentDto {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String fatherName;
	private String motherName;
	private String studentClass;
	private Date birthDate;
	private String address;
	private String gender;
	
	
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	public StudentDto(Long id, String name, String email, String password, String fatherName, String motherName,
			String studentClass, Date birthDate, String address, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.studentClass = studentClass;
		this.birthDate = birthDate;
		this.address = address;
		this.gender = gender;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", fatherName=" + fatherName + ", motherName=" + motherName + ", studentClass=" + studentClass
				+ ", birthDate=" + birthDate + ", address=" + address + ", gender=" + gender + "]";
	}
	
	
	

}
