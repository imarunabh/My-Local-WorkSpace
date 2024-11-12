package com.scms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
	
	

}
