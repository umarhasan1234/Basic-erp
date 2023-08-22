package com.nrt.request;

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
	private String catagoryDescription;

}
