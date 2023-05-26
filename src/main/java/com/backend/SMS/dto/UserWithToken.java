package com.backend.SMS.dto;

import java.time.LocalDate;

import com.backend.SMS.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithToken {

    private String name;
	
	private String mobile;
	
	private String email;

	private String role;
    
	private LocalDate birthDate;
	
	private Boolean isVerified;

	private String token;

    public UserWithToken(User user){
        name = user.getName();
        mobile = user.getMobile();
        email = user.getEmail();
        role = user.getRole();
        birthDate = user.getBirthDate();
        isVerified = user.getIsVerified();
    }

}
