package com.ejercicio.ejercicio.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ejercicio.ejercicio.entity.Sale;
public interface SaleRepository extends  PagingAndSortingRepository<Sale, Integer>, CustomSaleRepository{
}
