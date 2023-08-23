package com.nrt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="sub_catagory")
public class SubCatagory {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="subCatagory_id")
	    private Long subCatagoryId;

	 @Column(name="subCatagory_name")
	    private String subCatagoryName;
	 
	 @Column(name="subCatagory_description")
	    private String subCatagoryDescription;
	    
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Catagory category;

}
