package com.nrt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserControllerUi {
	
	@GetMapping("/user/page")
	public ModelAndView CouponHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/user_register");
		return modelAndView;

	}
}
