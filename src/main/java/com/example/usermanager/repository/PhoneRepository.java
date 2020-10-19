package com.example.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usermanager.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long>{

}
