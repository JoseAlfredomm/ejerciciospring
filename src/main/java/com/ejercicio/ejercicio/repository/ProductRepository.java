package com.ejercicio.ejercicio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ejercicio.ejercicio.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> , CustomProductRepository {
	
	public List<Product> findByUniqueCode(String uniqueCode);
	
}
