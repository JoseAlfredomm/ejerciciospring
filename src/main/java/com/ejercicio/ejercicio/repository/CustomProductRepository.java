package com.ejercicio.ejercicio.repository;

import java.util.List;

import com.ejercicio.ejercicio.entity.dto.ProductDTO;
import com.ejercicio.ejercicio.utils.utilclasses.Filter;

public interface CustomProductRepository {

	public  List<ProductDTO> filteringProducts(Filter filter);
	
}
