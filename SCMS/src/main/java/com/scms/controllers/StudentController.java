package com.scms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scms.dto.SingleStudentDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;
import com.scms.services.student.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	
	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto= studentService.getStudentById(studentId);
		if(singleStudentDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(singleStudentDto);
	}
	
	
	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto){
		StudentLeaveDto submittedstudentLeaveDto = studentService.applyLeave(studentLeaveDto);
		if(submittedstudentLeaveDto ==  null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(submittedstudentLeaveDto);	
	}
	
	
	@GetMapping("/leave/{studentId}")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeavesByStudentId(@PathVariable Long studentId){
		List<StudentLeaveDto> studentLeaveDtos= studentService.getAllAppliedLeavesByStudentId(studentId);
		if(studentLeaveDtos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentLeaveDtos);
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId,@RequestBody StudentDto studentDto){
		StudentDto createStudentDto = studentService.updateStudent(studentId,studentDto);
		if(createStudentDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}
	
	
	
	

}
