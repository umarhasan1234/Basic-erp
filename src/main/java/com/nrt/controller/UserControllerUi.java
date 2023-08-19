package com.nrt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.User;
import com.nrt.request.UpdateRequest;
import com.nrt.request.UserRequest;
import com.nrt.service.UserService;

@Controller
public class UserControllerUi {
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
		ResponseEntity<User> updatePassword = userService.updatePassword(updateRequest.getUserId(), updateRequest.getOldPassword(), updateRequest.getNewPassword());
        System.out.println(updatePassword.getStatusCode());
		if(updatePassword.getStatusCode().equals(200))
        {
        	System.out.println("rung successa");
		modelAndView.setViewName("redirect:/login");
        }
        else
        {
        	System.out.println("rung error");
        	modelAndView.setViewName("redirect:/login");
        }
		return modelAndView;
	}

}
