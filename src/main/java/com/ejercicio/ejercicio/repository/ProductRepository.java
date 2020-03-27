package com.ejercicio.ejercicio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.ejercicio.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> , CustomProductRepository {
	
	public List<Product> findByUniqueCode(String uniqueCode);
	
}
