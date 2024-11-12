package com.scms.services.home;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.scms.dto.TeacherDto;
import com.scms.entities.Teacher;
import com.scms.repositories.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	
	private final TeacherRepository teacherRepository;
	
	public List<TeacherDto> getAllTeachers(){
		
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
		
	}

}
