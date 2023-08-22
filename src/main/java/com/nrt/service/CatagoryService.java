package com.nrt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nrt.entity.Catagory;
import com.nrt.request.CatagoryRequest;

public interface CatagoryService {
	
	public boolean saveCatagory(CatagoryRequest catagoryRequest);
	public List<Catagory> getAllCatagory();
	public Catagory GetProductById(Long id);
	public void deleteCatagory(Long id);
	public boolean updateCatagory(Catagory catagory);

}
