package com.nrt.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.nrt.entity.User;
import com.nrt.repository.UserRepository;

@Component
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) {

		Optional<User> userOptional = userRepository.findByEmail(username);

		return new CustomUserDetails(userOptional.get());
	}

}