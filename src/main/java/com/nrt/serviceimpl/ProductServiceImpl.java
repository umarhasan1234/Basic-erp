package com.nrt.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nrt.entity.Catagory;
import com.nrt.entity.Product;
import com.nrt.entity.SubCatagory;
import com.nrt.repository.ProductRepository;
import com.nrt.request.ProductRequest;
import com.nrt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Value("${image.upload.path}")
	private String imageUploadPath;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {
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
	public boolean updateProducts(Product productUpdate, MultipartFile file) {
		try {
			productUpdate.setImagePath(file.getOriginalFilename());
			Product updatedProduct = productRepository.save(productUpdate);
			String imagePath = imageUploadPath + file.getOriginalFilename();
			Path destination = Paths.get(imagePath);
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
			return updatedProduct != null; // Return true if the save operation was successful
		} catch (Exception e) {
			e.printStackTrace(); // You can handle the exception as needed
			return false; // Return false if an exception occurs during the save operation
		}
	}

	@Override
	public boolean saveProduct(ProductRequest productRequest, MultipartFile file) {
		Product product = new Product();
		product.setId(productRequest.getId());
		product.setDescription(productRequest.getDescription());
		product.setName(productRequest.getName());
		product.setMaxRetailPrice(productRequest.getMaxRetailPrice());
		product.setPurchasePrice(productRequest.getPurchasePrice());
		product.setSellingPrice(productRequest.getSellingPrice());
		product.setQuantity(productRequest.getQuantity());
		product.setImagePath(file.getOriginalFilename());

//		SubCatagory subCategory=new SubCatagory();
//		product.setSubCatagory(subCategory);
//		
	    
	    if (productRepository.existsById(product.getId())) {
			return false;
		} else {
			String imagePath = imageUploadPath + file.getOriginalFilename();
			Path destination = Paths.get(imagePath);
			try {
				Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();

			}
			productRepository.save(product);
			return true;
		}
	}

}
