package com.backend.SMS.user;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.SMS.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserRepository userRepository;

	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/user/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		System.out.println(user);
		if(user.getMobile()== null || user.getName() == null || user.getPassword() == null || user.getBirthDate() == null) {
			return new ResponseEntity<>("name, mobile, password, birthDate are required",  HttpStatus.BAD_REQUEST);
		}
		LocalDateTime createdTime = LocalDateTime.now(  );
		user.setCreatedTs(createdTime);
		user.setUpdatedTs(createdTime);
		try {			
			userRepository.save(user);
		}catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("mobile or email  already exist",  HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user,  HttpStatus.CREATED);
	}

}
