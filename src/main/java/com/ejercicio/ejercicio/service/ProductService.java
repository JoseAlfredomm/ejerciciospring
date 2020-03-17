package com.ejercicio.ejercicio.service;


import java.util.List;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.dto.ProductDTO;
import com.ejercicio.ejercicio.utils.utilclasses.Filter;

public interface ProductService {
	 public boolean register(Product product);
	 public List<ProductDTO> filterProducts(Filter filter);
}
