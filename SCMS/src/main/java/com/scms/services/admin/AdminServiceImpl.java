package com.scms.services.admin;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scms.dto.FeeDto;
import com.scms.dto.SingleStudentDto;
import com.scms.dto.SingleTeacherDto;
import com.scms.dto.StudentDto;
import com.scms.dto.StudentLeaveDto;
import com.scms.dto.TeacherDto;
import com.scms.entities.Fee;
import com.scms.entities.StudentLeave;
import com.scms.entities.Teacher;
import com.scms.entities.User;
import com.scms.enums.StudentLeaveStatus;
import com.scms.enums.UserRole;
import com.scms.repositories.FeeRepository;
import com.scms.repositories.StudentLeaveRepository;
import com.scms.repositories.TeacherRepository;
import com.scms.repositories.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final UserRepository userRepository;
	
	private final FeeRepository feeRepository;
	
	private final StudentLeaveRepository studentLeaveRepository;
	
	private final TeacherRepository teacherRepository;
	

	@PostConstruct
	public void createAminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if (adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(admin);
		}

	}

	@Override
	public StudentDto postStudent(StudentDto studentDto) {
		Optional<User> opt = userRepository.findFirstByEmail(studentDto.getEmail());
		
		if (opt.isEmpty()) {
			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepository.save(user);
			StudentDto createdDto = new StudentDto();
			createdDto.setId(createdUser.getId());
			createdDto.setEmail(createdUser.getEmail());
          
			return createdDto;
		}
		return null;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		
		return userRepository.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto).collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		userRepository.deleteById(studentId);
	}

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		Optional<User> optionalUser = userRepository.findById(studentId);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		optionalUser.ifPresent(user->singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;
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

	@Override
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		Optional<User> optionalStudent = userRepository.findById(studentId);
		if(optionalStudent.isPresent()) {
			Fee fee = new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreatedDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());
			Fee paidFee = feeRepository.save(fee);
			FeeDto paidfeeDto = new FeeDto();
			paidfeeDto.setId(paidFee.getId());
			return paidfeeDto;
			
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllAppliedLeaves() {
		
	return studentLeaveRepository.findAll().stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
		
	}

	@Override
	public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
		Optional<StudentLeave> optionalStudentLeave = studentLeaveRepository.findById(leaveId);
		if(optionalStudentLeave.isPresent()) {
			StudentLeave studentLeave = optionalStudentLeave.get();
			
			if(Objects.equals(status, "Approve")) {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);
			}
			else {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
			}
			StudentLeave updatedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto updatedStudentLeaveDto = new StudentLeaveDto();
			updatedStudentLeave.setId(updatedStudentLeave.getId());
			return updatedStudentLeaveDto;
		}
		
		return null;
	}

	@Override
	public TeacherDto postTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher = teacherRepository.save(teacher);
		TeacherDto createdTeacherDto = new TeacherDto();
		createdTeacherDto.setId(createdTeacher.getId());
		return createdTeacherDto;
	}

	@Override
	public List<TeacherDto> getAllTeachers() {
		
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}

	@Override
	public void deleteTeacher(Long teacherId) {
		teacherRepository.deleteById(teacherId);
		
	}

	@Override
	public SingleTeacherDto getTeacherById(Long teacherId) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if(optionalTeacher.isPresent()) {
			SingleTeacherDto singleTeacherDto = new SingleTeacherDto();
			singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
			return singleTeacherDto;
		}
		return null;
	}

	@Override
	public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
	    Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
	    if(optionalTeacher.isPresent()) {
	    	Teacher updateTeacher = optionalTeacher.get();
	    	updateTeacher.setName(teacherDto.getName());
	    	updateTeacher.setGender(teacherDto.getGender());
	    	updateTeacher.setAddress(teacherDto.getAddress());
	    	updateTeacher.setDob(teacherDto.getDob());
	    	updateTeacher.setDepartment(teacherDto.getDepartment());
	    	updateTeacher.setQualification(teacherDto.getQualification());
	    	
	    	Teacher updatedTeacher  = teacherRepository.save(updateTeacher);
	    	TeacherDto updatedTeacherDto =  new TeacherDto();
	    	updatedTeacherDto.setId(updatedTeacher.getId());
	    	return updatedTeacherDto;
	    }
		return null;
	}
	

}
