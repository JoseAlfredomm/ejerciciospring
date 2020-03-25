package com.ejercicio.ejercicio.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.repository.ProductRepository;
import com.ejercicio.ejercicio.repository.SaleRepository;
import com.ejercicio.ejercicio.repository.TicketRepository;
import com.ejercicio.ejercicio.service.SaleService;
import com.ejercicio.ejercicio.service.impl.SaleServiceImpl;

import mockit.Tested;


@RunWith(SpringRunner.class)
public class SaleControllerTestMokito {
	
	@MockBean
	private SaleRepository saleRepository;
	
	@MockBean
	private TicketRepository ticketRepository;

	@MockBean
	private ProductRepository productRepository;
	
	
	private SaleService saleService;
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public SaleServiceImpl accountManager() {
            return new SaleServiceImpl();
        }
    }
	
	@Test
	public void detailsOfOperation() {
		saleService.detailsOfOperation(new Sale());
		
		
	}

	@Autowired
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	
	
	
}
