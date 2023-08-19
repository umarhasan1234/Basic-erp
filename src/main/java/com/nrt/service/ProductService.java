package com.nrt.service;

import java.util.List;

import com.nrt.entity.Product;


public interface ProductService {
	public void saveAllProduct(Product product);
	List<Product> getAllProduct();
	Product GetProductById(Long id);
	void deleteProduct(Long id);
	boolean updateProducts(Product product);
}
