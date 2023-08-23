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
import com.nrt.entity.SubCatagory;
import com.nrt.request.CatagoryRequest;
import com.nrt.request.ProductRequest;
import com.nrt.service.CatagoryService;
import com.nrt.service.SubCatagoryService;

@Controller
@PreAuthorize("hasRole('CATAGORY')")
public class CatagoryController {
	
	@Autowired
	private CatagoryService catagoryService;
	
	@Autowired
	private SubCatagoryService subCatagoryService;

	
	// Call for adding the catagory
	@RequestMapping("/catagory")
	public ModelAndView defaultMethod(ModelAndView modelAndView) {
		List<SubCatagory> subCatagories=subCatagoryService.getAllSubCatagory();
		modelAndView.addObject("subCatagories", subCatagories);
		modelAndView.setViewName("/html/product/add_catagory");
		return modelAndView;
	}
	
	
	
	//call for save   product catagory
		 @RequestMapping("/saveCatagory")
		    public ModelAndView addProduct(@ModelAttribute("catagoryRequest") CatagoryRequest catagoryRequest, ModelAndView modelAndView) {
		        boolean b = catagoryService.saveCatagory(catagoryRequest);//call sevice layer saveProduct method
		        if (!b) {
		            modelAndView.addObject("errorMessage", "This type of Catagory is already exists.");
		            modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		            modelAndView.setViewName("/html/product/error_message");
		        } else {
		        	List<Catagory> catagories = catagoryService.getAllCatagory();
					modelAndView.addObject("catagories", catagories);
		            modelAndView.setViewName("/html/product/list_of_catagory");
		        }
		        return modelAndView;
		    }
		 
	//this method call for find all catagory
	@GetMapping("/listCatagory")
	public ModelAndView findProduct(ModelAndView modelAndView) {
		List<Catagory> catagories = catagoryService.getAllCatagory();
		modelAndView.addObject("catagories", catagories);
		modelAndView.setViewName("/html/product/list_of_catagory");
		return modelAndView;
	}
	
	// this method call for delete catagory by id
	@GetMapping("/deleteCatagory/")
	public ModelAndView deleteProduct(@RequestParam("id") Long id, ModelAndView modelAndView) {
		catagoryService.deleteCatagory(id);
		List<Catagory> catagories = catagoryService.getAllCatagory();
		modelAndView.addObject("catagories", catagories);
		modelAndView.setViewName("/html/product/list_of_catagory");
		return modelAndView;
	}

	// this method call for updating catagory by its id
	@RequestMapping("/updateByCatagoryId/")
	public ModelAndView updateProduct(@RequestParam("id") Long id, @ModelAttribute Catagory catagory,
			ModelAndView modelAndView) {
		catagory = catagoryService.getCatagoryById(id);
		modelAndView.addObject("catagories", catagory);
		modelAndView.setViewName("/html/product/update_catagory"); // View name without extension
		return modelAndView;

	}

//	 this method call for update the catagory  
	@RequestMapping("/updateCatagory")
	public ModelAndView updateProduct(@ModelAttribute Catagory catagory, ModelAndView modelAndView) {
		modelAndView.addObject("title", "Catagory update");
		modelAndView.addObject("message", "Successfull");
		modelAndView.addObject("details", "\"Congratulations! Catagory Update successfully !");
		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		modelAndView.setViewName(
				catagoryService.updateCatagory(catagory) ? "/html/product/response_message" : "/html/product/error_message");
		return modelAndView;

	}
	

}
