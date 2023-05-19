package com.backend.SMS.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity( name="user_details" )
public class User {
	
	protected User() {
		
	}
	
	@Id
	@GeneratedValue
	private Long id;

	@Size(min=2, message = "Name should have at least 2 characters")
	private String name;
	
	@Column (unique=true)
	private int mobile;
	
	@Column (unique=true)
	private String email;
	
	private String password;
	
	@Past(message = "Birth Date should be in the past")
	private LocalDate birthDate;
	
	private Boolean isVerified = false;
	
	private LocalDateTime createdTs;
	
	private LocalDateTime updatedTs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public LocalDateTime getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}

	public LocalDateTime getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(LocalDateTime updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email +
				", birthDate=" + birthDate + ", isVerified=" + isVerified + ", createdTs=" + createdTs
				+ ", updatedTs=" + updatedTs + "]";
	}
	
}
