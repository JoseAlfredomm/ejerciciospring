package com.ejercicio.ejercicio.utils.utilclasses;

import com.ejercicio.ejercicio.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QualityByProduct {
	
	private Product product;
	private double off , quality;
	
	public int getProductId() {
		return this.product.getId();
	}
	public double getProductExistence() {
		return this.product.getExistence();
	}
	
}
