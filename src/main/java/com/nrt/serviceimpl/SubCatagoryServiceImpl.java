package com.nrt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrt.entity.Catagory;
import com.nrt.entity.SubCatagory;
import com.nrt.repository.SubCatagoryRepository;
import com.nrt.service.SubCatagoryService;


@Service
public class SubCatagoryServiceImpl implements SubCatagoryService{
	
    @Autowired
    private SubCatagoryRepository subCatagoryRepository;

    @Override
    public List<SubCatagory> getSubCatagoriesByCatagory(Catagory catagory) {
        return subCatagoryRepository.findAll();
    }

	@Override
	public Object getAllSubCatagories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSubCatagory(SubCatagory subCatagory) {
		subCatagoryRepository.save(subCatagory);
	}

	@Override
	public List<SubCatagory> getAllSubCatagory() {
		return subCatagoryRepository.findAll();
	}

	@Override
	public void deleteSubCatagory(Long id) {
		subCatagoryRepository.deleteById(id);
	}

	@Override
	public SubCatagory getSubCatagoryById(Long id) {
		Optional<SubCatagory> findById = subCatagoryRepository.findById(id);
		return findById.get();
	}

	@Override
	public boolean updateSubCatagory(SubCatagory subCatagory) {
		try {
			SubCatagory updateSubCatagory = subCatagoryRepository.save(subCatagory);
            return updateSubCatagory != null; // Return true if the save operation was successful
        } catch (Exception e) {
            e.printStackTrace(); // You can handle the exception as needed
            return false; // Return false if an exception occurs during the save operation
        }
	}


}
