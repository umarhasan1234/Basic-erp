package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			Cookie tokenCookie = new Cookie(JWT_COOKIE_NAME, loginResponse.getUserToken());
			tokenCookie.setMaxAge(24 * 60 * 60);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			System.out.println(loginResponse.getUserLogin());
		}

		return loginResponseEntity;
	}

	@GetMapping("/login")
	public ModelAndView LoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/login");
		return modelAndView;

	}

	@GetMapping("/forgot/password")
	public ModelAndView ForgotPasswordPage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/forgot_password");
		return modelAndView;

	}

	@GetMapping("/forgot")
	public ModelAndView SendOTPToUpadatePassword(@RequestParam("email") String email, ModelAndView modelAndView,
			HttpServletResponse response) {
		Boolean sendOTP = userService.SendOTP(email, response);
		modelAndView.addObject("requestEmialId", email);
		if (sendOTP) {
			modelAndView.setViewName("/html/login/OTP.html");
		} else {
			modelAndView.setViewName("/html/login/forgot_password");
		}
		return modelAndView;

	}

	@PostMapping("/forgot/velidate")
	public ModelAndView ValidateOTP(@RequestParam("otp") String otp, ModelAndView modelAndView,
			HttpServletRequest request, HttpServletResponse response) {
		Boolean velidation = userService.OTPVelidation(otp, request);
		if (!velidation)
			modelAndView.setViewName("/html/login/forgot_password");
		else
			modelAndView.setViewName("/html/login/Forgot");
		return modelAndView;

	}

	@GetMapping("/forgot/update")
	public ModelAndView PasswordUpdate(@RequestParam("newPassword") String newPassword, ModelAndView modelAndView,
			HttpServletRequest request, HttpServletResponse response) {

		Boolean flag = userService.ForgotPassword(newPassword, request, response);
		if (flag) {
			modelAndView.addObject("title", "Successful ForgotPassword");
			modelAndView.addObject("message", "Password Updated successfully!");
			modelAndView.addObject("url", "http://localhost:9090/login");
			modelAndView.addObject("button", "to login");
			modelAndView.addObject("details", "\"Congratulations! Your Password Forgot was successful.!");
			modelAndView.setViewName("/html/coupon/response_success");
		} else {
			modelAndView.addObject("title", "Faild to update password ");
			modelAndView.addObject("url", "http://localhost:9090/login");
			modelAndView.addObject("button", "to login");
			modelAndView.addObject("error", "New password matches a previous password.");
			modelAndView.setViewName("/html/coupon/error");
		}
		return modelAndView;

	}
}