package com.scms.services.student;

import java.util.List;

import com.scms.dto.SingleStudentDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;

public interface StudentService {

	SingleStudentDto getStudentById(Long studentId);

	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

	List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long userId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

}
