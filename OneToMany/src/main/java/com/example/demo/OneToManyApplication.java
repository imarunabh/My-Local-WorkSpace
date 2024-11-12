package com.example.demo;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Address;
import com.example.demo.entities.Student;
import com.example.demo.repo.StudentRepository;

@SpringBootApplication
public class OneToManyApplication implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepository;
	private Logger logger= LoggerFactory.getLogger(OneToManyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OneToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student student = new Student();
		student.setStudentName("Arunabh Sharma");
		student.setAbout("I am a software engineer");
		student.setStudentId(15);
		
		Address a1 = new Address();
		a1.setAddressId(131);
		a1.setStreet("235/235");
		a1.setCity("LKD");
		a1.setCountry("IND");
		a1.setStudent(student);
		
		Address a2 = new Address();
		a2.setAddressId(133);
		a2.setStreet("421/235");
		a2.setCity("DELHI");
		a2.setCountry("IND");
		a2.setStudent(student);
		
		ArrayList<Address> addressList = new ArrayList<>();
		addressList.add(a1);
		addressList.add(a2);
		student.setAddresses(addressList);
		Student save = studentRepository.save(student);
		logger.info("Student created with address {}",student.getAddresses());
		
	}

}
