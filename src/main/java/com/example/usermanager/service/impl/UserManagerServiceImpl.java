package com.example.usermanager.service.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.usermanager.dto.ErrorListDTO;
import com.example.usermanager.dto.UserManagerDTO;
import com.example.usermanager.entity.PhoneEntity;
import com.example.usermanager.entity.UserManagerEntity;
import com.example.usermanager.repository.UserManagerRepository;
import com.example.usermanager.service.UserManagerService;
import com.example.usermanager.utils.UserConstants;
import com.example.usermanager.utils.UserValidation;


@Service
public class UserManagerServiceImpl implements UserManagerService {

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserManagerRepository userRepository;
	
	
	private UserConstants userConstants = new UserConstants();
	
	
	@Autowired
	private UserValidation userValidation;
	
	/**
	 * Add User
	 * @param UserManagerDTO
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> addUser(UserManagerDTO userManagerDto){ 
		ErrorListDTO errorListDto = userValidation.getValidationsAddUser(userManagerDto.getEmail(), userManagerDto.getPassword(), userManagerDto.getName());
		
		if(errorListDto.getErrors().isEmpty()) {
			UserManagerEntity userManagerEntity = this.modelMapper.map(userManagerDto, UserManagerEntity.class);
			List<PhoneEntity> phones = userManagerEntity.getPhones();
			phones.forEach(phone -> phone.setUserEntity(userManagerEntity));
			userManagerEntity.setCreationDate(userValidation.getCurrentDate());
			userManagerEntity.setModificationDate(userValidation.getCurrentDate());
	    
		
		return userValidation
				.validResponse(this.modelMapper
						.map(userRepository.save(userManagerEntity),UserManagerDTO.class), userConstants.addUserError);
		}
		return new ResponseEntity<Object>(errorListDto, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Update User
	 * @param userManagerDto
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> updateUser(UserManagerDTO userManagerDto){
		
		Optional<UserManagerEntity> userManagerEntity  = userRepository.findById(userManagerDto.getId());
			if(userManagerEntity.isPresent()) {
				final boolean emailSame = userManagerEntity.get().getEmail().equals(userManagerDto.getEmail())?true:false;
				final boolean userNameSame = userManagerEntity.get().getName().equals(userManagerDto.getName())?true:false;
				ErrorListDTO errorListDto = userValidation.getValidationsUpdate(userManagerDto.getEmail(),emailSame,
						 userManagerDto.getPassword(),userManagerDto.getName(), userNameSame);
				if(errorListDto.getErrors().isEmpty()) {
					UserManagerEntity userManagerEntityRes = this.modelMapper.map(userManagerDto, UserManagerEntity.class);
					userManagerEntityRes.setModificationDate(userValidation.getCurrentDate());
					
					return userValidation
							.validResponse(this.modelMapper
									.map(userRepository.save(userManagerEntityRes),UserManagerDTO.class), userConstants.updateUserError);
				}else {
					return new ResponseEntity<Object>(errorListDto, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				return new ResponseEntity<Object>(userConstants.notExitsUserError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	/**
	 * Delete User
	 * @param idUser
	 * @return ResponseEntity<Object>
	 */
	public ResponseEntity<Object> deleteUser(Long idUser){
		
			try {
				userRepository.deleteById(idUser);
			
				if(!userRepository.findById(idUser).isPresent())
					return new ResponseEntity<Object>(userConstants.userDeletedSuccess, HttpStatus.OK);
			}catch (EmptyResultDataAccessException e) {
				return new ResponseEntity<Object>(userConstants.userDeletedError, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Object>(userConstants.userDeletedError, HttpStatus.INTERNAL_SERVER_ERROR);
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

	@Override
	public void updateLoginData(String userName, String token) {
		UserManagerEntity userManagerEntity = userRepository.findByName(userName);
		userManagerEntity.setLastLoginDate(userValidation.getCurrentDate());
		userManagerEntity.setToken(token);
		userManagerEntity.setActive(true);
		updateUser(this.modelMapper.map(userManagerEntity, UserManagerDTO.class));
		
		
	}
	
	

	
}
