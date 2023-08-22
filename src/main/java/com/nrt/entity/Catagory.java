package com.nrt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Column(name="catagory_Description")
	private String catagoryDescription;

}
