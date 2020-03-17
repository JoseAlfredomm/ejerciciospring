package com.ejercicio.ejercicio.repository.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ejercicio.ejercicio.entity.Sale;
import com.ejercicio.ejercicio.entity.Ticket;
import com.ejercicio.ejercicio.entity.dto.SalesDTO;
import com.ejercicio.ejercicio.repository.CustomSaleRepository;
import com.ejercicio.ejercicio.utils.reference.OperationDTOProperties;
import com.ejercicio.ejercicio.utils.reference.SaleProperties;
import com.ejercicio.ejercicio.utils.reference.TicketProperties;

@Repository
public class CustomSaleRepositoryImpl implements CustomSaleRepository {

	@Autowired
	private EntityManager entityManager;

	/*
	 * @deprecated (in future versions an alternative is criteriaBuilder)
	 */
	@Deprecated
	@Override
	public List<SalesDTO> detailsOfOperation(Sale sale) {

		Criteria detailsSale = entityManager.unwrap(Session.class).createCriteria(Ticket.class,
				TicketProperties.ENTITYTICKET);

		detailsSale.createAlias(SaleProperties.ENTITYSALE, SaleProperties.ENTITYSALE, JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.eq(SaleProperties.ENTITYSALE + "." + SaleProperties.ID, sale.getId()))
				.setProjection(Projections.projectionList()
						.add(Projections.property(TicketProperties.PRODUCTCODE), OperationDTOProperties.AS_CODE)
						.add(Projections.property(TicketProperties.NAMEPRODUCT), OperationDTOProperties.AS_NAME)
						.add(Projections.property(TicketProperties.DESCRIPTION), OperationDTOProperties.AS_DESCRIPTION)
						.add(Projections.property(TicketProperties.OFFOFSALE), OperationDTOProperties.AS_OFFSALE)
						.add(Projections.property(TicketProperties.PERCENTOFF), OperationDTOProperties.AS_PERCENTOFF)
						.add(Projections.property(TicketProperties.UTILITYOFSALE), OperationDTOProperties.AS_UTILITY)
						.add(Projections.property(TicketProperties.PRICE_OF_PURCHASE), OperationDTOProperties.AS_COST)
						.add(Projections.property(TicketProperties.PRICE_OF_SALE), OperationDTOProperties.AS_PRICE)
						.add(Projections.property(TicketProperties.QUALITY), OperationDTOProperties.AS_PRODUCTS)
						.add(Projections.property(TicketProperties.MEASURE), OperationDTOProperties.AS_MEASURE)
						.add(Projections.property(TicketProperties.PRICETOTALOFSALE), OperationDTOProperties.AS_TOTAL))
				.setResultTransformer(Transformers.aliasToBean(SalesDTO.class));

		return detailsSale.list() != null && !detailsSale.list().isEmpty() ? detailsSale.list() :	Collections.emptyList();
		

	}

}
