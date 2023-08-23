package com.nrt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Catagory;
import com.nrt.entity.Product;
import com.nrt.request.CatagoryRequest;
import com.nrt.request.ProductRequest;
import com.nrt.service.CatagoryService;

@Controller
@PreAuthorize("hasRole('CATAGORY')")
public class CatagoryController {
	
	@Autowired
	private CatagoryService catagoryService;
	
	
	//updoad  product catagory
		 @RequestMapping("/saveCatagory")
		    public ModelAndView addProduct(@ModelAttribute("catagoryRequest") CatagoryRequest catagoryRequest, ModelAndView modelAndView) {
			 
			 
		        boolean b = catagoryService.saveCatagory(catagoryRequest);//call sevice layer saveProduct method
		        if (!b) {
		            modelAndView.addObject("errorMessage", "This type of Catagory is already exists.");
		            modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		            modelAndView.setViewName("/html/product/error_message");
		        } else {
		            modelAndView.addObject("title", "Save Catagory");
		            modelAndView.addObject("message", "Successfully added");
		            modelAndView.addObject("details", "Congratulations! New Type of Product added successfully!");
		            modelAndView.setViewName("/html/product/response_message");
		        }
		        return modelAndView;
		    }


	
	
	//find all catagory
	@GetMapping("/listCatagory")
	public ModelAndView findProduct(ModelAndView modelAndView) {
		List<Catagory> catagories = catagoryService.getAllCatagory();
		modelAndView.addObject("catagories", catagories);
		modelAndView.setViewName("/html/product/list_of_catagory");
		return modelAndView;
	}
	

}
