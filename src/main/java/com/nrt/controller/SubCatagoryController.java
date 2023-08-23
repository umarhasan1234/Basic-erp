package com.nrt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Catagory;
import com.nrt.entity.Product;
import com.nrt.entity.SubCatagory;
import com.nrt.service.SubCatagoryService;

@Controller
public class SubCatagoryController {
	
	
	    @Autowired
	    private SubCatagoryService subCatagoryService;
	    
	    @RequestMapping("/subCatagory")
		public ModelAndView subCatagoryMethod(ModelAndView modelAndView) {
			modelAndView.setViewName("/html/product/add_subcatagory");
			return modelAndView;
		}
	    
	    @RequestMapping("/saveSubCatagory")
	    public ModelAndView saveCatagoryMethod(@ModelAttribute SubCatagory subCatagory,ModelAndView modelAndView) {
	    	subCatagoryService.saveSubCatagory(subCatagory);
	    	List<SubCatagory> subCatagories = subCatagoryService.getAllSubCatagory();
	    	modelAndView.addObject("subCatagories", subCatagories);
	    	modelAndView.setViewName("/html/product/list_subcatagory");
	    	return modelAndView;
	    }
	    
	    @GetMapping("/listCatagory/{categoryId}")
	    public ModelAndView listSubCatagory(@PathVariable Long categoryId, ModelAndView modelAndView) {
	        Catagory catagory = new Catagory();
	        catagory.setCatagoryId(categoryId);
	        
	        // Get subcategories by category
	        List<SubCatagory> subCatagories = subCatagoryService.getSubCatagoriesByCatagory(catagory);
	        
	        modelAndView.addObject("subCatagories", subCatagories);
	        modelAndView.addObject("categoryId", categoryId);
	        modelAndView.setViewName("/html/product/list_subcatagory");
	        return modelAndView;
	    }
	    
	    @RequestMapping("/listSubCatagory")
	    public ModelAndView listSubCatagories(ModelAndView modelAndView) {
	    	List<SubCatagory> subCatagories = subCatagoryService.getAllSubCatagory();
			modelAndView.addObject("subCatagories", subCatagories);
			modelAndView.setViewName("/html/product/list_subcatagory");
			return modelAndView;
		}
	    
	    @GetMapping("/deleteSubCatagory/")
		public ModelAndView deleteSubCatagory(@RequestParam("id") Long id, ModelAndView modelAndView) {
	    	subCatagoryService.deleteSubCatagory(id);
	    	List<SubCatagory> subCatagories = subCatagoryService.getAllSubCatagory();
			modelAndView.addObject("subCatagories", subCatagories);
			modelAndView.setViewName("/html/product/list_subcatagory");
			return modelAndView;
		}
	    
	    @RequestMapping("/updateSubCatagoryById/")
		public ModelAndView updateSubCatagory(@RequestParam("id") Long id, @ModelAttribute SubCatagory subCatagory,
				ModelAndView modelAndView) {
	    	subCatagory = subCatagoryService.getSubCatagoryById(id);
			modelAndView.addObject("catagories", subCatagory);
			modelAndView.setViewName("/html/product/update_subcatagory"); // View name without extension
			return modelAndView;

		}
	    
	    @RequestMapping("/updateSubCatagory")
		public ModelAndView updateSubCatagory(@ModelAttribute SubCatagory subCatagory, ModelAndView modelAndView) {
			modelAndView.addObject("title", "Sub Catagory update");
			modelAndView.addObject("message", "Successfull");
			modelAndView.addObject("details", "\"Congratulations! Sub Catagory Update successfully !");
			modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
			modelAndView.setViewName(
					subCatagoryService.updateSubCatagory(subCatagory) ? "/html/product/response_message" : "/html/product/error_message");
			return modelAndView;

		}
	    
	}
	    



