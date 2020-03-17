package com.ejercicio.ejercicio.service;

import java.util.List;

import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;
import com.ejercicio.ejercicio.utils.utilclasses.QualityByProduct;


public interface SaleService  {
	
	public boolean generateSale(List<QualityByProduct> qualityByProducts);
	public List<SalesDTO> detailsOfOperation(Sale sale);
}
