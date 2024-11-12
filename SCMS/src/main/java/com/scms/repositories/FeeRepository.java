package com.scms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scms.entities.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long>{

}
