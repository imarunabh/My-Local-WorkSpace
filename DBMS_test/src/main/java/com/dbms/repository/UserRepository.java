package com.dbms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dbms.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	@Query(value="SELECT u.email FROM Users u WHERE u.email= :email",nativeQuery = true)
	List<String> findtByEmail( String email);

}
