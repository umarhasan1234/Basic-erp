package com.nrt.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="catagory")
public class Catagory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="catagory_id")
	private long catagoryId;
	
	@Column(name="catagory_name")
	private String catagoryName;
	

	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCatagory> subcategories = new ArrayList<>();

}
