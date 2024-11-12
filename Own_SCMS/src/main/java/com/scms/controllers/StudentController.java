package com.scms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.scms.entities.Student;
import com.scms.repository.StudentRepo;

@RestController
@CrossOrigin
public class StudentController {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/student")
	public ResponseEntity<?> createStudent(@RequestBody Student student){
		
		
		Student student2 = studentRepo.save(student);
		return new ResponseEntity<>(student2,HttpStatus.OK);
	}
	
	@GetMapping("/student")
	public ResponseEntity<?> getAllStudents(){
		return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
	}
	
	@PutMapping("/student/{Id}")
	public ResponseEntity<?> findById(@RequestBody Student student,@PathVariable Long Id){
		Student students2 = studentRepo.findById(Id).get();
		student.setId(Id);
		studentRepo.save(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@DeleteMapping("/student/{Id}")
	public ResponseEntity<?> deleteStudentByid(@PathVariable Long Id){
		studentRepo.deleteById(Id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
