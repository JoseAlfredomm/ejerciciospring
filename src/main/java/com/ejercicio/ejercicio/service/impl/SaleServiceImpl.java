package com.ejercicio.ejercicio.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.Ticket;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;
import com.ejercicio.ejercicio.repository.ProductRepository;
import com.ejercicio.ejercicio.repository.SaleRepository;
import com.ejercicio.ejercicio.repository.TicketRepository;
import com.ejercicio.ejercicio.service.SaleService;
import com.ejercicio.ejercicio.utils.utilclasses.QualityByProduct;
import com.ejercicio.ejercicio.utils.validation.ValidateQualityByProduct;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired(required = true)
	SaleRepository saleRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public boolean generateSale(List<QualityByProduct> qualityByProducts) {

		if (validSale(qualityByProducts)) {
			
			Sale sale = new Sale();
			sale.setDate(new Timestamp(System.currentTimeMillis()));
			
			for (QualityByProduct qualityByProduct : qualityByProducts) {

				Ticket ticket = new Ticket(qualityByProduct);
				
				if(ticket.isEmpty()) {
					return false;
				}
				
				updateSale(sale, ticket);

				updateProductExistence(qualityByProduct);

				sale = saleRepository.save(sale);

				ticket.setSale(sale);

				ticketRepository.save(ticket);

			}
			return true;
		}

		return false;
	}

	private boolean validSale(List<QualityByProduct> qualityByProducts) {
		for (QualityByProduct qualityByProduct : qualityByProducts) {

			qualityByProduct.setProduct(getProductById(qualityByProduct));

			ValidateQualityByProduct validation = new ValidateQualityByProduct(qualityByProduct);

			if (!validation.isValid()) {
				return false;

			}

		}
		return true;

	}

	private void updateSale(Sale sale, Ticket ticket) {

		sale.setTotalSale(sale.getTotalSale() + ticket.getPriceTotalOfSale());
		sale.setTotalOff(sale.getTotalOff() + ticket.getOffOfSale());
		sale.setTotalProducts(sale.getTotalProducts() + ticket.getQuality());

	}

	private Product getProductById(QualityByProduct qualityByProduct) {
		if (qualityByProduct.getProduct() != null) {
			
			Optional<Product> optionalProduct = productRepository.findById(qualityByProduct.getProductId());
			
			return optionalProduct.isPresent() 
					? optionalProduct.get()
					: null;
			
		}
		return null;
	}

	private void updateProductExistence(QualityByProduct qualityByProduct) {
		qualityByProduct.getProduct()
				.setExistence(qualityByProduct.getProduct().getExistence() - qualityByProduct.getQuality());
		
		productRepository.save(qualityByProduct.getProduct());
	}

	@Override
	public List<SalesDTO> detailsOfOperation(Sale sale) {

		return saleRepository.detailsOfOperation(sale);
	}

}
