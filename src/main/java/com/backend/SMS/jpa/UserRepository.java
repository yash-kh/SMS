package com.backend.SMS.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SMS.user.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
