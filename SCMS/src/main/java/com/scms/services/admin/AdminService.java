package com.scms.services.admin;

import java.util.List;

import com.scms.dto.FeeDto;
import com.scms.dto.SingleStudentDto;
import com.scms.dto.SingleTeacherDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;
import com.scms.dto.TeacherDto;

public interface AdminService {
	
	public StudentDto postStudent(StudentDto studentDto);
	
	List<StudentDto> getAllStudents();
	
	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);

	List<StudentLeaveDto> getAllAppliedLeaves();

	StudentLeaveDto changeLeaveStatus(Long leaveId, String status);

	TeacherDto postTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);

	SingleTeacherDto getTeacherById(Long teacherId);

	TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);

}
