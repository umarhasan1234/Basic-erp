package com.nrt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nrt.entity.Product;
import com.nrt.entity.SubCatagory;



public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findBySubCatagory(SubCatagory subCatagory);
	
}


