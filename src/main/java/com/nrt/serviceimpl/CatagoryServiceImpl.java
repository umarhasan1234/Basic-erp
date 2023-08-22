package com.nrt.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nrt.entity.Catagory;
import com.nrt.entity.Product;
import com.nrt.repository.CatagoryRepository;
import com.nrt.request.CatagoryRequest;
import com.nrt.service.CatagoryService;



@Service
public class CatagoryServiceImpl implements CatagoryService{
	
	
	@Autowired
	private CatagoryRepository catagoryRepository;

	@Override
	public boolean saveCatagory(CatagoryRequest catagoryRequest) {
		Catagory catagory=new Catagory();
		catagory.setCatagoryId(catagoryRequest.getCatagoryId());
		catagory.setCatagoryDescription(catagoryRequest.getCatagoryDescription());
		catagory.setCatagoryName(catagoryRequest.getCatagoryName());
		if (catagoryRepository.existsById(catagory.getCatagoryId())) {
            return false;
        } else {
        	catagoryRepository.save(catagory);
            return true;
        }
	}
	

	@Override
	public Catagory GetProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCatagory(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateCatagory(Catagory catagory) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Catagory> getAllCatagory() {
	 return catagoryRepository.findAll();
	
	}

}
