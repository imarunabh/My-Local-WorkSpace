package com.test.entities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
