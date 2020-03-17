package com.ejercicio.ejercicio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.dto.ProductDTO;
import com.ejercicio.ejercicio.repository.ProductRepository;
import com.ejercicio.ejercicio.service.ProductService;
import com.ejercicio.ejercicio.utils.utilclasses.Filter;
import com.ejercicio.ejercicio.utils.validation.ValidateProduct;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	public ProductRepository productRepository;

	@Override
	public boolean register(Product product) {

		ValidateProduct validation = new ValidateProduct(product);

		if (validation.isValid()) {

			product = productRepository.save(product);
			return productRepository.existsById(product.getId());
		}

		return false;
	}

	@Override
	public List<ProductDTO> filterProducts(Filter filter) {
		return productRepository.filteringProducts(filter);
	}

}