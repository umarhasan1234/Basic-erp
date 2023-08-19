package com.nrt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrt.entity.Product;
import com.nrt.repository.ProductRepository;
import com.nrt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveAllProduct(Product product) {
	 productRepository.save(product);
	 
	}

	@Override
	public List<Product> getAllProduct() {
		productRepository.findAll();
		return productRepository.findAll();
	}
	
	@Override
	public Product GetProductById(Long id) {
		Optional<Product> findById = productRepository.findById(id);
		return findById.get();
	}
	
	@Override
	public void deleteProduct(Long id) {
	  productRepository.deleteById(id);
	}

	 @Override
	    public boolean updateProducts(Product productUpdate) {
	        try {
	            Product updatedProduct = productRepository.save(productUpdate);
	            return updatedProduct != null; // Return true if the save operation was successful
	        } catch (Exception e) {
	            e.printStackTrace(); // You can handle the exception as needed
	            return false; // Return false if an exception occurs during the save operation
	        }
	    }

}
