package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.request.LoginRequest;
import com.nrt.responce.LoginResponce;
import com.nrt.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/jwt", method = RequestMethod.POST)
	public ResponseEntity<LoginResponce> userLogin(@RequestBody LoginRequest loginRequest,
			HttpServletResponse response) {
		ResponseEntity<LoginResponce> loginResponseEntity = userService.generateToken(loginRequest);
		LoginResponce loginResponse = loginResponseEntity.getBody();
		if (loginResponse != null) {
			Cookie tokenCookie = new Cookie("jwtToken", loginResponse.getUserToken());
			tokenCookie.setMaxAge(24 * 60 * 60);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
		}

		return loginResponseEntity;
	}

	@GetMapping("/login")
	public ModelAndView CouponHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/login");
		return modelAndView;

	}
}