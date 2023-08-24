package com.nrt.responce;

import com.nrt.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class ProductResponce {
	private Long id;
	
	private String name;

	private String description;

	private int purchasePrice;

	private int maxRetailPrice;

	private int sellingPrice;

	private int quantity;

	private Long categoryId;

	private Long subCategoryId;

	    private boolean success;
	    private Product product;

	    public ProductResponce(boolean success, Product product) {
	        this.success = success;
	        this.product = product;
	    }

	    public boolean isSuccess() {
	        return success;
	    }

	    public Product getProduct() {
	        return product;
	    }
	
	

}
