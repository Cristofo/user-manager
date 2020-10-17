package com.example.usermanager.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data	
@Table(name="PHONE")
public class PhoneEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPhone ;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", nullable=false)
	private UserManagerEntity userEntity;
	
	private Long number;
	private String cityCode;
	private String countryCode;
}
