package com.nrt.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Product;
import com.nrt.repository.ProductRepository;
import com.nrt.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productImpl;
	
	@Autowired
	private ProductRepository productRepository;

	// this method redirect product page
	@RequestMapping("/product")
	public ModelAndView defaultMethod(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/product/add_Product"); // View name without extension
		return modelAndView;
	}

	// this method add product
	@RequestMapping("/saveProduct")
	public ModelAndView addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,ModelAndView modelAndView) {
		
		
		
		
		
		 if (productRepository.existsById(product.getId())) {
	        	//modelAndView.addAttribute("errorMessage", "Product with ID " + product.getId() + " already exists.");
	        	
	        	modelAndView.addObject("title", "Save Product");
	    		modelAndView.addObject("message", "Successfull");
	    		modelAndView.addObject("details", "\"Congratulations! Use save Product successfully !");
	    		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
	    		modelAndView.setViewName("/html/product/error_message");
	            return modelAndView; // Return the name of the Thymeleaf template for displaying the error
	        }
		 
		 if (!file.isEmpty()) {
	            try {
	                product.setImage(file.getBytes());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		 productRepository.save(product);
	        modelAndView.addObject("title", "Save Product");
 		modelAndView.addObject("message", "Successfull");
 		modelAndView.addObject("details", "\"Congratulations! ADD Product successfully !");
 		modelAndView.setViewName("/html/product/respinse_message");
         return modelAndView;
	}

	// this method find all product
	@GetMapping("/listProduct")
	public ModelAndView findProduct(ModelAndView modelAndView) {
		List<Product> products = productImpl.getAllProduct();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	// this method find product by id
	@GetMapping("/getProduct/")
	public ModelAndView getProduct(@RequestParam("id") Long id, ModelAndView modelAndView) {
		Product products = productImpl.GetProductById(id);

		modelAndView.addObject("getProductById", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	// this method delete product by id
	@GetMapping("/delete/")
	public ModelAndView deleteProduct(@RequestParam("id") Long id, ModelAndView modelAndView) {
		
		productImpl.deleteProduct(id);
		
		List<Product> products = productImpl.getAllProduct();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	//update product call by id
	@GetMapping("/updateById/")
	public ModelAndView updateProduct(@RequestParam("id")Long id,@ModelAttribute Product product,ModelAndView modelAndView) {
		product = productImpl.GetProductById(id);
		modelAndView.addObject("product", product);
	     modelAndView.setViewName("/html/product/update_product"); // View name without extension
	     return modelAndView;
	     
	}
//	//update product 
	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct(@ModelAttribute Product product,ModelAndView modelAndView) {
		modelAndView.addObject("title", "Product update");
		modelAndView.addObject("message", "Successfull");
		modelAndView.addObject("details", "\"Congratulations! Product Update successfully !");
		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		modelAndView.setViewName
		(productImpl.updateProducts(product)? "/html/product/response_message" : "/html/product/error_message");	
		return modelAndView;
	     
	}
	

}
