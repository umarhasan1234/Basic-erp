package com.nrt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Product;
import com.nrt.request.ProductRequest;


public interface ProductService {
	boolean saveProduct(ProductRequest productRequest,MultipartFile file);
	List<Product> getAllProduct();
	Product GetProductById(Long id);
	void deleteProduct(Long id);
	boolean updateProducts(Product product);
}
