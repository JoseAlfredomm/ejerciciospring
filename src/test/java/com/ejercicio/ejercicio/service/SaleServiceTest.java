package com.ejercicio.ejercicio.service;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;
import com.ejercicio.ejercicio.service.SaleService;
import com.ejercicio.ejercicio.service.impl.SaleServiceImpl;
import com.ejercicio.ejercicio.repository.ProductRepository;
import com.ejercicio.ejercicio.repository.SaleRepository;
import com.ejercicio.ejercicio.repository.TicketRepository;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import mockit.integration.springframework.FakeBeanFactory;

@RunWith(JMockit.class)
public class SaleServiceTest {

	 @BeforeClass
	   public static void applySpringIntegration() {
	
		 new FakeBeanFactory();
	  
	 }
	
	 
	@Injectable public SaleRepository saleRepository;
	
	@Injectable public TicketRepository ticketRepository;
	
	@Injectable public ProductRepository productRepository;
	
	@Tested (fullyInitialized=true) public SaleServiceImpl saleServiceImpl;
	
	
	@Test
	public void detailsOfOperationTest() {
		
		
		Sale sale = new Sale();
		List<Product> list = new ArrayList<>();
		list.add(new Product());
		new Expectations(){{
			saleRepository.detailsOfOperation(sale); result = list; 
		}};
		
		
		
		List<SalesDTO> aux = saleServiceImpl.detailsOfOperation(sale);
		
		assertSame(list, sale);
		
		new Verifications() {
			{
				saleRepository.detailsOfOperation(sale); times = 1;
			}
		};
		

	}

	
	
	
	
}
