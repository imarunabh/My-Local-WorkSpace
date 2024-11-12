package com.scms.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scms.dto.FeeDto;
import com.scms.dto.SingleStudentDto;
import com.scms.dto.SingleTeacherDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;
import com.scms.dto.TeacherDto;
import com.scms.services.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {
	
	
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}
	
	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
		StudentDto createStudentDto = adminService.postStudent(studentDto);
		if(createStudentDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
	       List<StudentDto> allStudents = adminService.getAllStudents();
	       return ResponseEntity.ok(allStudents);
	}
	
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
		adminService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId){
		SingleStudentDto singleStudentDto= adminService.getStudentById(studentId);
		if(singleStudentDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(singleStudentDto);
	}
	
	@PutMapping("/student/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId,@RequestBody StudentDto studentDto){
		StudentDto createStudentDto = adminService.updateStudent(studentId,studentDto);
		if(createStudentDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}
	
	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> payFee(@PathVariable Long studentId,@RequestBody FeeDto feeDto){
		FeeDto paidFeeDto = adminService.payFee(studentId,feeDto);
		if(paidFeeDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(paidFeeDto);
	}
	
	
	@GetMapping("/leaves")
	public ResponseEntity<List<StudentLeaveDto>> getAllAppliedLeaves(){
		List<StudentLeaveDto> studentLeaveDtos= adminService.getAllAppliedLeaves();
		if(studentLeaveDtos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentLeaveDtos);
	}
	
	@GetMapping("/leave/{leaveId}/{status}")
	public ResponseEntity<?> changeLeaveStatus(@PathVariable Long leaveId,@PathVariable String status){
		StudentLeaveDto studentLeaveDto = adminService.changeLeaveStatus(leaveId,status);
		if(studentLeaveDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(studentLeaveDto);
	}
	
	//Teachers Opearation
	
	@PostMapping("/teacher")
	public ResponseEntity<?> postTeacher(@RequestBody TeacherDto teacherDto){
		TeacherDto createTeacherDto = adminService.postTeacher(teacherDto);
		if(createTeacherDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createTeacherDto);
	}
	
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers(){
		List<TeacherDto> allTeachers = adminService.getAllTeachers();
		return ResponseEntity.ok(allTeachers);
	}
	
	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId){
		adminService.deleteTeacher(teacherId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<SingleTeacherDto> getTeacherById(@PathVariable Long teacherId){
		SingleTeacherDto singleTeacherDto = adminService.getTeacherById(teacherId);
		if(singleTeacherDto==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleTeacherDto);	
	}
	
	@PutMapping("/teacher/{teacherId}")
	public ResponseEntity<?> updateTeacher(@PathVariable Long teacherId,@RequestBody TeacherDto teacherDto){
		TeacherDto createTeacherDto = adminService.updateTeacher(teacherId,teacherDto);
		if(createTeacherDto == null) {
			return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createTeacherDto);
	}
	
	

}
