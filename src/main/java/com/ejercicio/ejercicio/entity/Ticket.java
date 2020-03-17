package com.ejercicio.ejercicio.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ejercicio.ejercicio.utils.reference.Variant;
import com.ejercicio.ejercicio.utils.reference.TicketProperties;
import com.ejercicio.ejercicio.utils.utilclasses.QualityByProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = TicketProperties.TBLTICKET)

@Setter
@Getter
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = TicketProperties.FKSALE)
	private Sale sale;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = TicketProperties.FKPRODUCT)
	private Product product;
	
	@NotBlank
	@NotNull
	private String percentOff;

	@NotNull
	@NotBlank
	private String description;

	@NotNull
	@NotBlank
	private String nameProduct;

	@NotNull
	@NotBlank
	private String productCode;

	@NotNull
	@NotBlank
	private String measure;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double offOfSale;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double utilityOfSale;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double priceOfPurchase;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double priceOfSale;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double priceTotalOfSale;

	@Column(columnDefinition = Variant.AS_DOUBLE)
	private double quality;

	public Ticket() {
	}

	public Ticket(QualityByProduct qualityByProduct) {
		percentOff = "";
		description = "";
		nameProduct = "";
		productCode = "";
		measure = "";
		
		if (qualityByProduct != null) {
			
			quality = qualityByProduct.getQuality();

			product = qualityByProduct.getProduct();

			offOfSale = qualityByProduct.getOff();

			setInfoProductSold();

			utilityGenerator();

			percentGenerator();
		} else {
			product = null;
			
			quality = 0.0;

			offOfSale = 0.0;

		}

	}

	private void setInfoProductSold() {

		setMeasure(product.getMeasure());

		setPriceOfPurchase(product.getCostPurchase());

		setPriceOfSale(product.getCostSale());

		setProductCode(product.getUniqueCode());

		setDescription(product.getDescription());

		this.setNameProduct(product.getName());
	}

	private void utilityGenerator() {
		if (this.offOfSale > 1) {
			this.offOfSale /= 100;
		}

		double margin = (this.priceOfSale - (priceOfPurchase + (priceOfSale * offOfSale)));

		this.utilityOfSale = (margin / priceOfPurchase) * 100;

	}

	private void percentGenerator() {

		double priceTotal = this.priceOfSale * this.quality;

		double offTotal = priceTotal * this.offOfSale;

		this.percentOff = String.valueOf(this.offOfSale * 100) + "%";

		this.offOfSale = offTotal;

		this.priceTotalOfSale = priceTotal - offOfSale;
	}

	public boolean isEmpty() {
		return product == null || quality == 0.0;

	}

}
