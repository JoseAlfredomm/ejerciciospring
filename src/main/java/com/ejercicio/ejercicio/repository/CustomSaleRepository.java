package com.ejercicio.ejercicio.repository;

import java.util.List;

import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;


public  interface CustomSaleRepository {
	
	public abstract List<SalesDTO> detailsOfOperation(Sale sale);
	
}
