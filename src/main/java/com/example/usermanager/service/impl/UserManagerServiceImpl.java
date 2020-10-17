package com.example.usermanager.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.usermanager.dto.UserManagerDTO;
import com.example.usermanager.entity.PhoneEntity;
import com.example.usermanager.entity.UserManagerEntity;
import com.example.usermanager.repository.UserManagerRepository;
import com.example.usermanager.service.UserManagerService;
import com.example.usermanager.utils.UserValidation;


@Service
public class UserManagerServiceImpl implements UserManagerService {

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserManagerRepository userRepository;
	
	
	private UserValidation userValidation = new UserValidation();
	
	
	
	/**
	 * Add User
	 * @param userDto
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> addUser(UserManagerDTO userManagerDto){
		
		UserManagerEntity userManagerEntity = this.modelMapper.map(userManagerDto, UserManagerEntity.class);
		List<PhoneEntity> phones = userManagerEntity.getPhones();
		phones.forEach(phone -> phone.setUserEntity(userManagerEntity));
		
		return userValidation
				.validResponse(this.modelMapper
						.map(userRepository.save(userManagerEntity),UserManagerDTO.class));
	}
	
	/**
	 * Update User
	 * @param userManagerDto
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> updateUser(UserManagerDTO userManagerDto){
		
		if(userRepository.findById(userManagerDto.getId()).isPresent()) {
			
			if(null == userRepository.save(this.modelMapper.map(userManagerDto, UserManagerEntity.class)).getId())
				return new ResponseEntity<Object>("It was not possible to update the user", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return new ResponseEntity<Object>("User succesfully updated", HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Object>("User doesn't exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Delete User
	 * @param idUser
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> deleteUser(Long idUser){
		
			userRepository.deleteById(idUser);
			
			if(null == userRepository.findById(idUser))
				return new ResponseEntity<Object>("User deleted succesfully", HttpStatus.OK);
			
			return new ResponseEntity<Object>("It was not possible to delete the user", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * Get Users
	 * @return ResponseEntity<List<UserDto>>
	 */
	public ResponseEntity<List<UserManagerDTO>> getUsers(){
		Type listType = new TypeToken<List<UserManagerDTO>>(){}.getType();
		List<UserManagerDTO> userManagerDtoList = modelMapper.map(userRepository.findAll(),listType);
		return new ResponseEntity<List<UserManagerDTO>>(userManagerDtoList,HttpStatus.OK);
	}
	
}
