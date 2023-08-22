package com.nrt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Product;
import com.nrt.request.ProductRequest;
import com.nrt.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	// this method redirect Add Product page
	@RequestMapping("/product")
	public ModelAndView defaultMethod(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/product/add_Product");
		return modelAndView;
	}

	// this method Add product
	@RequestMapping("/saveProduct")
	public ModelAndView addProduct(@ModelAttribute("productRequest") ProductRequest productRequest,
			@RequestParam("file") MultipartFile file, ModelAndView modelAndView) {

		boolean b = productService.saveProduct(productRequest, file);// call sevice layer saveProduct method
		if (!b) {
			modelAndView.addObject("errorMessage", "Product is already exists.");
			modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
			modelAndView.setViewName("/html/product/error_message");
		} else {
			modelAndView.addObject("title", "Save Product");
			modelAndView.addObject("message", "Successfully added");
			modelAndView.addObject("details", "Congratulations! Product added successfully!");
			modelAndView.setViewName("/html/product/response_message");
		}
		return modelAndView;
	}

	// this method find all product
	@GetMapping("/listProduct")
	public ModelAndView findProduct(ModelAndView modelAndView) {
		List<Product> products = productService.getAllProduct();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	// this method find product by id
	@GetMapping("/getProduct/")
	public ModelAndView getProduct(@RequestParam("id") Long id, ModelAndView modelAndView) {
		Product products = productService.GetProductById(id);

		modelAndView.addObject("getProductById", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	// this method delete product by id
	@GetMapping("/delete/")
	public ModelAndView deleteProduct(@RequestParam("id") Long id, ModelAndView modelAndView) {

		productService.deleteProduct(id);

		List<Product> products = productService.getAllProduct();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("/html/product/list_product");
		return modelAndView;
	}

	// update product call by id
	@GetMapping("/updateById/")
	public ModelAndView updateProduct(@RequestParam("id") Long id, @ModelAttribute Product product,
			ModelAndView modelAndView) {
		product = productService.GetProductById(id);
		modelAndView.addObject("product", product);
		modelAndView.setViewName("/html/product/update_product"); // View name without extension
		return modelAndView;

	}

//	//update product 
	@RequestMapping("/updateProduct")
	public ModelAndView updateProduct(@ModelAttribute Product product, ModelAndView modelAndView) {
		modelAndView.addObject("title", "Product update");
		modelAndView.addObject("message", "Successfull");
		modelAndView.addObject("details", "\"Congratulations! Product Update successfully !");
		modelAndView.addObject("error", "An error occurred while processing your request. Please try again later.");
		modelAndView.setViewName(productService.updateProducts(product) ? "/html/product/response_message"
				: "/html/product/error_message");
		return modelAndView;

	}

}
