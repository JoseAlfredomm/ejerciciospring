package com.ejercicio.ejercicio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_products")
@Getter
@Setter
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String measure;

	@NotNull
	@NotBlank
	private String uniqueCode;

	private String description;

	@NotNull
	@Column(columnDefinition = "numeric(10,4)")
	private double costSale;

	@NotNull
	@Column(columnDefinition = "numeric(10,4)")
	private double costPurchase;

	@NotNull
	@Column(columnDefinition = "numeric(10,4)")
	private double existence;

	public boolean isExist() {
		return existence > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
