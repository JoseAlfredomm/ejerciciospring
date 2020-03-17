package com.ejercicio.ejercicio.repository.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ejercicio.ejercicio.entity.Product;
import com.ejercicio.ejercicio.entity.dto.ProductDTO;
import com.ejercicio.ejercicio.repository.CustomProductRepository;
import com.ejercicio.ejercicio.utils.reference.ProductProperties;
import com.ejercicio.ejercicio.utils.utilclasses.Filter;
import com.ejercicio.ejercicio.utils.utilclasses.Ordering.OrderAs;
import com.ejercicio.ejercicio.utils.utilclasses.Ordering.OrderBy;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

	@Autowired
	private EntityManager entityManager;

	/*
	 * @deprecated (in future versions an alternative is criteriaBuilder)
	 */
	@Deprecated
	@Override
	public List<ProductDTO> filteringProducts(Filter filter) {
		if (filter == null) {
			return Collections.emptyList();

		}
		Criteria filteringProducts = openSession().createCriteria(Product.class);

		if (filter.getMaxSale() > 0 || filter.getMinSale() > 0) {
			findByRange(filter, filteringProducts);

		}

		if (filter.getNameProduct() != null && !filter.getNameProduct().isEmpty()) {
			filteringProducts
					.add(Restrictions.ilike(ProductProperties.NAME, filter.getNameProduct(), MatchMode.ANYWHERE));

		}

		if (filter.getProductCode() != null && !filter.getProductCode().isEmpty()) {
			filteringProducts
					.add(Restrictions.ilike(ProductProperties.UNIQUECODE, filter.getProductCode(), MatchMode.ANYWHERE));

		}

		if (filter.getOrderBy() != null && filter.getOrderBy() != OrderBy.NONE) {
			filteringProducts = orderProducts(filteringProducts, filter);
		}
		
		filteringProducts.setProjection(
				Projections.projectionList()
					.add(Projections.property(ProductProperties.UNIQUECODE),ProductProperties.UNIQUECODE)
					.add(Projections.property(ProductProperties.NAME),ProductProperties.NAME)
					.add(Projections.property(ProductProperties.MEASURE),ProductProperties.MEASURE)
					.add(Projections.property(ProductProperties.EXISTENCE),ProductProperties.EXISTENCE)
					.add(Projections.property(ProductProperties.COSTPURCHASE),ProductProperties.COSTPURCHASE)
					.add(Projections.property(ProductProperties.COSTSALE),ProductProperties.COSTSALE)
					.add(Projections.property(ProductProperties.DESCRIPTION),ProductProperties.DESCRIPTION)
				).setResultTransformer(Transformers.aliasToBean(ProductDTO.class));

		List<ProductDTO> list = filteringProducts.list() != null ? filteringProducts.list() : null;

		return list == null || list.isEmpty() ? Collections.emptyList() : list;

	}

	private Session openSession() {
		return entityManager.unwrap(Session.class);

	}

	private Criteria findByRange(Filter filter, Criteria filteringProducts) {
		if (filter.getMaxSale() == 0) {
			filteringProducts.add(Restrictions.ge(ProductProperties.COSTSALE, filter.getMinSale()));
		} else {
			filteringProducts
					.add(Restrictions.between(ProductProperties.COSTSALE, filter.getMinSale(), filter.getMaxSale()));
		}
		return filteringProducts;
	}

	private Criteria orderProducts(Criteria filteringProducts, Filter filter) {

		if (filter.getOrderAs() != null && filter.getOrderAs() != OrderAs.NONE) {

			if (filter.getOrderBy() == OrderBy.NAME) {
				return filter.getOrderAs() == OrderAs.ASC
						? filteringProducts.addOrder(Order.asc(ProductProperties.NAME))
						: filteringProducts.addOrder(Order.desc(ProductProperties.NAME));

			}
			return filter.getOrderAs() == OrderAs.ASC
					? filteringProducts.addOrder(Order.asc(ProductProperties.COSTSALE))
					: filteringProducts.addOrder(Order.desc(ProductProperties.COSTSALE));
		} else {

			filteringProducts = filter.getOrderBy() == OrderBy.NAME
					? filteringProducts.addOrder(Order.asc(ProductProperties.NAME))
					: filteringProducts.addOrder(Order.asc(ProductProperties.COSTSALE));

		}
		return filteringProducts;
	}

}
