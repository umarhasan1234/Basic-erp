package com.nrt.service;

import org.springframework.http.ResponseEntity;

import com.nrt.entity.User;
import com.nrt.request.LoginRequest;
import com.nrt.request.UserRequest;
import com.nrt.responce.LoginResponce;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
	public ResponseEntity<User> saveData(UserRequest userRequest);

	public ResponseEntity<User> getUserById(long userId);

	public ResponseEntity<User> updatePassword(String oldPassword, String newPassword);

	public ResponseEntity<LoginResponce> generateToken(LoginRequest loginRequest);

	public UserRequest getUserByEmail();

	public Boolean SendOTP(String email, HttpServletResponse response);

	public Boolean OTPVelidation(Integer otp,HttpServletRequest request);

	public void ForgotPassword(String newPassword);

}