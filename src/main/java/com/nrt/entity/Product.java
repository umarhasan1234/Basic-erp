package com.nrt.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "products_table")
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_description")
	private String description;

	@Column(name = "Purchase_Price")
	@Min(value = 0, message = "Purchase price must be a non-negative value")
	private int purchasePrice;

	@Column(name = "Max_retail_Price")
	@Min(value = 0, message = "Max retail price must be a non-negative value")
	private int maxRetailPrice;

	@Column(name = "Selling_price")
	@Min(value = 0, message = "Selling price must be a non-negative value")
	private int sellingPrice;

	@Column(name = "Quantity")
	@Min(value = 0, message = "Quantity must be a non-negative value")
	private int quantity;

	@Column(name = "product_path")
	private String imagePath;
}
