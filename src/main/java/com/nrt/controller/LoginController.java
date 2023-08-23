package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.request.LoginRequest;
import com.nrt.responce.LoginResponce;
import com.nrt.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	private static final String JWT_COOKIE_NAME = "jwtToken";

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
	public ModelAndView LoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/login");
		return modelAndView;

	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String jwtToken = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(JWT_COOKIE_NAME)) {
					jwtToken = cookie.getValue();
					break;
				}
			}
		}

		if (jwtToken != null) {
			Cookie tokenCookie = new Cookie(JWT_COOKIE_NAME, "");
			tokenCookie.setMaxAge(0);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
		}

		return "redirect:/login"; // Redirect to the login page, change URL as needed
	}

	@GetMapping("/forgot/password")
	public ModelAndView ForgotPasswordPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/forgot_password");
		return modelAndView;

	}

	@GetMapping("/forgot")
	public ModelAndView SendOTPToUpadatePassword(@RequestParam("email") String email, ModelAndView modelAndView) {
		System.out.println(email);
		modelAndView.setViewName("/html/login/forgot_password");
		return modelAndView;

	}
}