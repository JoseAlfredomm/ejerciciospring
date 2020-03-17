package com.ejercicio.ejercicio.utils.utilclasses;

import com.ejercicio.ejercicio.utils.utilclasses.Ordering.*;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Filter {
	
	
	private OrderAs orderAs;
	
	private OrderBy orderBy;
	
	private String nameProduct;
	
	private String productCode;
	
	private double minSale;
	
	private double maxSale;
	
}
