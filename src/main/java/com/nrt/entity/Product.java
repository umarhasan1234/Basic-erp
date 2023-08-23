package com.nrt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "products_table")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_description")
	private String description;

	@Column(name = "Purchase_Price")
	private int purchasePrice;

	@Column(name = "Max_retail_Price")
	private int maxRetailPrice;

	@Column(name = "Selling_price")
	private int sellingPrice;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "product_path")
	private String imagePath;
	
	@ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCatagory subCatagory;
}
