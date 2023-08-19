package com.nrt.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Product;


public interface ProductService {
	List<Product> getAllProduct();
	Product GetProductById(Long id);
	void deleteProduct(Long id);
	boolean updateProducts(Product product);
}
