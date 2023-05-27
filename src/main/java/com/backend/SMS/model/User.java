package com.backend.SMS.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity( name="user_details" )
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=2, message = "Name should have at least 2 characters")
	private String name;
	
	@Column (unique=true)
	private String mobile;
	
	@Column (unique=true)
	private String email;

	private String password;

	private String role = "USER";

	@Past(message = "Birth Date should be in the past")
	private LocalDate birthDate;
	
	private Boolean isVerified = false;
	
	private LocalDateTime createdTs;
	
	private LocalDateTime updatedTs;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Ticket> tickets;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile 
				+ ", email=" + email + ", role=" + role
				+ ", birthDate=" + birthDate + ", isVerified=" + isVerified 
				+ ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}

}
