package com.example.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usermanager.entity.UserManagerEntity;

public interface UserManagerRepository extends JpaRepository<UserManagerEntity, Long>{
	 public UserManagerEntity findByEmail(String email);
	 public UserManagerEntity findByName(String name);
	 
}
