package com.bbms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bbms.entities.Donor;

public interface DonorRepository  extends JpaRepository<Donor, Integer>{
	
	@Query("select count(*) from Donor d where d.blood_group= :blood_group")
	public int countByBlood(@Param("blood_group") String blood_group);
	
	 

}
