package com.nrt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nrt.entity.Product;
import com.nrt.request.ProductRequest;

public interface ProductService {
	public boolean saveProduct(ProductRequest productRequest, MultipartFile file);

	public List<Product> getAllProduct();

	public Product GetProductById(Long id);

	public void deleteProduct(Long id);

	public boolean updateProducts(Product product, MultipartFile file);
}
