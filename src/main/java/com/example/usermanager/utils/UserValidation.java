package com.example.usermanager.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.usermanager.dto.ErrorListDTO;
import com.example.usermanager.dto.UserManagerDTO;
import com.example.usermanager.entity.UserManagerEntity;
import com.example.usermanager.repository.UserManagerRepository;

@Component
public class UserValidation {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private UserConstants userConstants = new UserConstants();
	
	@Autowired
	private UserManagerRepository userRepository;
	
	
	public ResponseEntity<Object> validResponse(UserManagerDTO userManagerDTO, String error){
		
		if(Optional.of(userManagerDTO).isPresent()) return  new ResponseEntity<Object>(userManagerDTO, HttpStatus.OK);
		
		else return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public String getCurrentDate() {
		return formatter.format(new Date());
	}
	
	public ErrorListDTO getValidationsAddUser(String email, String password, String userName ){
		
		ErrorListDTO errors =  new ErrorListDTO();
		List<String> listErrors = new ArrayList<>();
		
		UserManagerEntity isEmailRepeated = userRepository.findByEmail(email);
		UserManagerEntity isUserNameRepeated = userRepository.findByName(userName);
		
		if(!validateRepeated(isEmailRepeated)) listErrors.add(userConstants.emailRepeated);
		
		if(!isValidPassword(password)) listErrors.add(userConstants.passwordInvalid);
		
		if(!isValidMail(email)) listErrors.add(userConstants.emailInvalid);
		
		if(!validateRepeated(isUserNameRepeated)) listErrors.add(userConstants.userRepeated);
		
		if(null == userName ||userName.trim().isEmpty()) listErrors.add(userConstants.userNameEmpty);
		
		errors.setErrors(listErrors);
		
		return errors;
	}
	
	
	public ErrorListDTO getValidationsUpdate(String email, boolean emailSame, String password, String userName, boolean userNameSame ){
		
		
		ErrorListDTO errors =  new ErrorListDTO();
		List<String> listErrors = new ArrayList<>();
		
		if(!emailSame) { 
			UserManagerEntity isEmailRepeated = userRepository.findByEmail(email);
			if(!validateRepeated(isEmailRepeated)) listErrors.add(userConstants.emailRepeated);
		}
		if(!userNameSame) {
			UserManagerEntity isUserNameRepeated = userRepository.findByName(userName);
			if(!validateRepeated(isUserNameRepeated)) listErrors.add(userConstants.userRepeated);
		}
		
		if(!isValidPassword(password)) listErrors.add(userConstants.passwordInvalid);
		
		if(!isValidMail(email)) listErrors.add(userConstants.emailInvalid);
		
		if(null == userName ||userName.trim().isEmpty()) listErrors.add(userConstants.userNameEmpty);
		
		errors.setErrors(listErrors);
		
		return errors;
	}
	
	private boolean isValidMail(String mail) {
		/**esta expresion valida que no haya 2 puntos consecutivos y también valida que no comience con punto o termine con punto*/
		String regEx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		 
		Pattern pattern = Pattern.compile(regEx);
		
		return pattern.matcher(mail).matches();
	}
	
	private boolean isValidPassword(String password) {
		String regEx =  "^(?=.*?[A-Z])(?=.*?[a-z])(?=(?:.*[0-9]){2}).*";
		
		Pattern pattern = Pattern.compile(regEx);
		
		return pattern.matcher(password).matches();
	}
	
	private boolean validateRepeated(UserManagerEntity isRepeated) {
		return null == isRepeated? true: false ;
	}
	
}
