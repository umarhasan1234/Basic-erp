package com.nrt.service;

import org.springframework.http.ResponseEntity;

import com.nrt.entity.User;
import com.nrt.request.LoginRequest;
import com.nrt.request.UserRequest;
import com.nrt.responce.LoginResponce;

public interface UserService {
	public ResponseEntity<User> saveData(UserRequest userRequest);

	public ResponseEntity<User> getUserById(long userId);

	public ResponseEntity<User> updatePassword(long userId, String oldPassword, String newPassword);

	public ResponseEntity<LoginResponce> generateToken(LoginRequest loginRequest);

}