package com.scms.services.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.scms.dto.SingleStudentDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;
import com.scms.entities.StudentLeave;
import com.scms.entities.User;
import com.scms.enums.StudentLeaveStatus;
import com.scms.repositories.StudentLeaveRepository;
import com.scms.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	
	private final UserRepository userRepository;
	private final StudentLeaveRepository studentLeaveRepository;

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		Optional<User> optionalUser = userRepository.findById(studentId);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		optionalUser.ifPresent(user->singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		System.out.println(studentLeaveDto.getUserid());
		Optional<User> optionalUser = userRepository.findById(studentLeaveDto.getUserid());
		if(optionalUser.isPresent()) {
			StudentLeave studentLeave = new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setDate(new Date());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave SubmittedstudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto studentLeaveDto1 = new StudentLeaveDto();
			studentLeaveDto1.setId(SubmittedstudentLeave.getId());
			return studentLeaveDto1;
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeavesByStudentId(Long studentId) {
		
		return studentLeaveRepository.findAllByUserId(studentId).stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
	}

	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		Optional<User> optionalUser = userRepository.findById(studentId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(studentDto.getName());
			user.setGender(studentDto.getGender());
			user.setAddress(studentDto.getAddress());
			user.setBirthDate(studentDto.getBirthDate());
			user.setStudentClass(studentDto.getStudentClass());
			user.setFatherName(studentDto.getFatherName());
			user.setMotherName(studentDto.getMotherName());
			user.setEmail(studentDto.getEmail());
			User updatedStudent = userRepository.save(user);
			StudentDto updatedStudentDto = new StudentDto();
			updatedStudentDto.setId(updatedStudent.getId());
			return updatedStudentDto;
		}
		return null;
	}
	
	

}
