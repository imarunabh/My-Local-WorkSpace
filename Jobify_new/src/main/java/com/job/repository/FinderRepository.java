package com.job.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job.entities.Finder;

public interface FinderRepository extends JpaRepository<Finder, Long>{
	Optional<Finder> findFirstByEmail(String email);

}
