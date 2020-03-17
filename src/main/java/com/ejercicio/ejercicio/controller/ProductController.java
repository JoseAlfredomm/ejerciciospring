package com.ejercicio.ejercicio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.dto.ProductDTO;
import com.ejercicio.ejercicio.service.ProductService;
import com.ejercicio.ejercicio.utils.uri.ProductUri;
import com.ejercicio.ejercicio.utils.utilclasses.Filter;


@RestController
@RequestMapping(ProductUri.PRODUCT)
public class ProductController  {

	
	@Autowired
	private ProductService productService;
	
	@PostMapping(ProductUri.REGISTER)
	public boolean register(@RequestBody @Valid Product product) {
		if (product != null) {
			return productService.register(product);
		}
		return false;
	}
	@GetMapping( name=ProductUri.SEARCH )
	public List<ProductDTO> filterProducts(@RequestBody @Valid Filter filter) {
		return productService.filterProducts(filter);
		
	}
}
