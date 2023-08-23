package com.nrt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		catagory.setCatagoryName(catagoryRequest.getCatagoryName());
		if (catagoryRepository.existsById(catagory.getCatagoryId())) {
            return false;
        } else {
        	catagoryRepository.save(catagory);
            return true;
        }
	}
	

	@Override
	public Catagory getCatagoryById(Long id) {
		Optional<Catagory> findById = catagoryRepository.findById(id);
		return findById.get();
	}

	@Override
	public void deleteCatagory(Long id) {
		catagoryRepository.deleteById(id);
		
	}

	@Override
	public boolean updateCatagory(Catagory catagory) {
		 try {
	            Catagory updateCatagory = catagoryRepository.save(catagory);
	            return updateCatagory != null; // Return true if the save operation was successful
	        } catch (Exception e) {
	            e.printStackTrace(); // You can handle the exception as needed
	            return false; // Return false if an exception occurs during the save operation
	        }
	}


	@Override
	public List<Catagory> getAllCatagory() {
	 return catagoryRepository.findAll();
	
	}

}
