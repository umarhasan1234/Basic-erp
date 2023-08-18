package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nrt.entity.User;
import com.nrt.request.UserRequest;
import com.nrt.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@RequestBody UserRequest userRequest) {
		return userService.saveData(userRequest);
	}

	@GetMapping("/getuser")
	public ResponseEntity<User> getUserById(@RequestParam("userId") long userId) {
		return userService.getUserById(userId);
	}

	@PutMapping("/updatepassword")
	public ResponseEntity<User> updatePassword(@RequestParam("userId") long userId,
			@RequestParam("oldPassword")String oldPassword, @RequestParam("newPassword") String newPassword) {
		return userService.updatePassword(userId,oldPassword, newPassword);
	}
}