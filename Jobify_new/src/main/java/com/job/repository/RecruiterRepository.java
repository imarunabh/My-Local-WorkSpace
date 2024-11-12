package com.job.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{
	Optional<Recruiter> findFirstByEmail(String email);

}
