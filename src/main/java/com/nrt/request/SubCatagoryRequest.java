package com.nrt.request;

import com.nrt.entity.Catagory;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCatagoryRequest {
	
    private Long subCatagoryId;

    private String subCatagoryName;
    
    private String subCatagoryDescription;

    private Catagory category;

}
