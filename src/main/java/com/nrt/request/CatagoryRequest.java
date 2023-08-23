package com.nrt.request;

import java.util.List;

import com.nrt.entity.SubCatagory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CatagoryRequest {
	
	private long catagoryId;
	private String catagoryName;
	private List<SubCatagory> subcategories;
}
