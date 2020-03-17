package com.ejercicio.ejercicio.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {
	
	private String name;

	private String measure;

	private String uniqueCode;

	private String description;

	private double costSale;

	private double costPurchase;

	private double existence;
	
}
