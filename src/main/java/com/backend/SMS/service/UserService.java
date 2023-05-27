package com.backend.SMS.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.backend.SMS.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.SMS.config.UserInfoUserDetails;
import com.backend.SMS.jpa.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void createUser(User user) {
        Optional<User> UserByMobile = userRepository.findByMobile(user.getMobile());
        if (UserByMobile.isPresent()) {
            throw new RuntimeException(
                "User already registered. Please use different username.");
        }
        
        LocalDateTime createdTime = LocalDateTime.now();
		user.setCreatedTs(createdTime);
		user.setUpdatedTs(createdTime);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserByMobile(String mobile) {
        Optional<User> UserByMobile = userRepository.findByMobile(mobile);
        
        return UserByMobile.get();
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        
        Optional<User> userInfo = userRepository.findByMobile(mobile);

        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found "+mobile));
    }

}