package com.backend.SMS.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.SMS.dto.AuthMobileReq;
import com.backend.SMS.dto.UserWithToken;
import com.backend.SMS.model.User;
import com.backend.SMS.service.JwtService;
import com.backend.SMS.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/hi")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("Hello",  HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

		if(user.getRole() != "USER"){
			return new ResponseEntity<>("role is not required",  HttpStatus.BAD_REQUEST);
		}
		
		if(user.getMobile()== null || user.getName() == null || user.getPassword() == null || user.getBirthDate() == null) {
			return new ResponseEntity<>("name, mobile, password, birthDate are required",  HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.createUser(user);
		}catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("mobile or email already exist",  HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(user,  HttpStatus.CREATED);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> registerUser(@RequestBody AuthMobileReq auth) throws Exception {
		Authentication authentication = authenticationManager
											.authenticate(new UsernamePasswordAuthenticationToken
												(auth.getMobile(), auth.getPassword()));
		if(authentication.isAuthenticated()){
			UserWithToken userWithToken = new UserWithToken(userService.getUserByMobile(auth.getMobile()));
			userWithToken.setToken(jwtService.generateToken(auth.getMobile()));
			return new ResponseEntity<>(userWithToken,  HttpStatus.CREATED);
		}

		return new ResponseEntity<>("BadInput",  HttpStatus.BAD_REQUEST);
	}

}
