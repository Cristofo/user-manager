package com.example.usermanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.usermanager.entity.PhoneEntity;
import com.example.usermanager.entity.UserManagerEntity;
import com.example.usermanager.repository.UserManagerRepository;

@SpringBootApplication
public class UserManagerApplication {

	@Autowired
	private UserManagerRepository userManagerRepository;
	
	@PostConstruct
	public void initUsers() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = formatter.format(new Date());
		List<PhoneEntity> listPhone = new ArrayList<PhoneEntity>();
		UserManagerEntity userManagerEntity = new UserManagerEntity(1L,"admin", "admin@gmail.com", "Hunter22",date, date,date, "",false, listPhone);
		PhoneEntity phone =  new PhoneEntity(1L,userManagerEntity, 45654L, "4565CT", "BR123728378" );
		listPhone.add(phone);
		userManagerEntity.setPhones(listPhone);
		userManagerRepository.save(userManagerEntity);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

}
