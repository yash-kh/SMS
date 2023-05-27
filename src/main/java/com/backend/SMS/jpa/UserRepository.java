package com.backend.SMS.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SMS.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByMobile(String mobile);

}
