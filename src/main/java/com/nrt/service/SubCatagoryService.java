package com.nrt.service;

import java.util.List;

import com.nrt.entity.Catagory;
import com.nrt.entity.SubCatagory;

public interface SubCatagoryService {
	public List<SubCatagory> getSubCatagoriesByCatagory(Catagory category);

	public Object getAllSubCatagories();

	public void saveSubCatagory(SubCatagory subCatagory);

	public List<SubCatagory> getAllSubCatagory();

	public void deleteSubCatagory(Long id);

	public SubCatagory getSubCatagoryById(Long id);

	public boolean updateSubCatagory(SubCatagory subCatagory);

}
