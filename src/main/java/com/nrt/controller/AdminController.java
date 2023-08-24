package com.nrt.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.service.PermissionService;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Controller
public class AdminController {

	@Autowired
	PermissionService permissionServic;
	// Add permissions to specific user

	@GetMapping("/permission/page")
	public ModelAndView PermissionHomePage(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/login/permission");
		return modelAndView;

	}

	@PostMapping("/permission")
	public ModelAndView addPermission(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "permissions", required = true) String[] permissions, ModelAndView modelAndView) {
		Boolean isSaved = permissionServic.assignPermissionsToUser(userId, Arrays.asList(permissions));
		if (isSaved) {
			log.info("is saved is true ");
			modelAndView.setViewName("/html/login/sucess-permissions");
			return modelAndView;
		}
		log.info("is saved is false ");
		modelAndView.setViewName("/html/login/error-permission");

		return modelAndView;
	}

}
