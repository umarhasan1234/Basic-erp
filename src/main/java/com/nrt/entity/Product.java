package com.nrt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_table")
@Entity
public class Product {
	@Id
	@Column(name = "product_id", unique = true)
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
	private String image_path;

}
