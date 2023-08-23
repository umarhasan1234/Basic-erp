package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.nrt.request.UpdateRequest;
import com.nrt.request.UserRequest;
import com.nrt.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/user/page")
	public ModelAndView CouponHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/user-register");
		return modelAndView;

	}

	@PostMapping("/user/register")
	public ModelAndView addUser(@ModelAttribute UserRequest userRequest, ModelAndView modelAndView) {
		userService.saveData(userRequest);
		modelAndView.setViewName("redirect:/login");
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView Profile(ModelAndView modelAndView) {

		modelAndView.addObject("response", userService.getUserByEmail());
		modelAndView.setViewName("/html/login/profile");
		return modelAndView;
	}

	@GetMapping("/password/update")
	public ModelAndView UpdatePassword(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/password-update");
		return modelAndView;
	}

	@PostMapping("/password/update")
	public ModelAndView PasswordVerify(@ModelAttribute UpdateRequest updateRequest, ModelAndView modelAndView) {
		Boolean flag = userService.updatePassword(updateRequest.getOldPassword(), updateRequest.getNewPassword());
		if (flag) {
			modelAndView.addObject("title", "Successful password updated");
			modelAndView.addObject("message", "Password Updated Successfully..");
			modelAndView.addObject("url", "http://localhost:9090/profile");
			modelAndView.addObject("button", "to Profile");
			modelAndView.addObject("details", "\"Congratulations! Your Password Updated Successfully..");
			modelAndView.setViewName("/html/coupon/response_success");
		} else {
			modelAndView.addObject("title", "Faild to update password ");
			modelAndView.addObject("url", "http://localhost:9090/profile");
			modelAndView.addObject("button", "to Profile");
			modelAndView.addObject("error", "New password matches a previous password.");
			modelAndView.setViewName("/html/coupon/error");
		}
		return modelAndView;
	}

	@GetMapping("/index/page")
	public ModelAndView Index(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/index");
		return modelAndView;

	}
}
