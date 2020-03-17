package com.ejercicio.ejercicio.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_Sales")
@Setter
@Getter
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
public class Sale {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(columnDefinition = "numeric(10,4)")
	private double totalSale;

	@Column(columnDefinition = "numeric(10,4)")
	private double totalProducts;

	@Column(columnDefinition = "numeric(10,4)")
	private double totalOff;

	private Timestamp date;

}
