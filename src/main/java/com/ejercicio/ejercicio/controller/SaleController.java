package com.ejercicio.ejercicio.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.User;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;
import com.ejercicio.ejercicio.service.SaleService;
import com.ejercicio.ejercicio.utils.utilclasses.QualityByProduct;
import com.ejercicio.ejercicio.utils.uri.SaleUri;

@RestController
@RequestMapping(SaleUri.SALES)
public class SaleController {
	
	Logger logger = LoggerFactory.getLogger(SaleController.class);

	@Autowired
	private SaleService saleService;

	@PostMapping(SaleUri.SALE)
	public boolean saleOfProducts(@RequestBody @Valid List<QualityByProduct> qualityByProducts) {

		if (qualityByProducts != null && !qualityByProducts.isEmpty()) {
			return saleService.generateSale(qualityByProducts);
		} else {
			return false;
		}
	}
	
	@ResponseBody
	@GetMapping(SaleUri.DETAILS)
	public List<SalesDTO> detailsOfSale(@RequestBody Sale sale, Authentication authentication) {
		
		User userAuthenticated = (User) authentication.getPrincipal();
		logger.info(userAuthenticated.getName());
		
		return sale != null ? saleService.detailsOfOperation(sale) : Collections.emptyList();

	}

}
