package com.nrt.request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {
	
	private long id;

	private String name;

	private String description;

	private int purchasePrice;

	private int maxRetailPrice;

	private int sellingPrice;

	private int quantity;

	private String imagePath;


}
