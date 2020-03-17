package com.ejercicio.ejercicio.utils.validation;



import org.apache.commons.lang3.StringUtils;

import com.ejercicio.ejercicio.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ValidateProduct extends Validate {
	

	
	public  ValidateProduct( Product product ) {
		
		if(	product!=null ) {
				validated(product);
		}else {
			addError("\n-> this product is null");	
		
		}

	}

	private void validated( Product product ) {
		
		if( StringUtils.isBlank( product.getUniqueCode() ) ) {
			addError("\n-> uniqueCode is null or void");
	
		}
		
		if( StringUtils.isBlank(product.getMeasure()) ){
			addError("\n-> measure is null or void");
			
			
		}
		
		if(StringUtils.isBlank(product.getName())) {
			addError("\n-> name is null or void");
			
		}
		
		if(product.getCostPurchase()<=0) {
			
			addError("\n-> costPurchase is less that 0");
		
		}
		
		if(product.getCostSale()<=0) {
			
			addError("\n-> costSale is less that 0");
		
		}
		
		if(product.getCostPurchase()>product.getCostSale()) {
			addError("\n-> costPurchase: "+product.getCostPurchase()
			+" is not valid for costSale: "+ product.getCostSale());
		}
		
		if(product.getExistence()<0) {
			addError("\n-> Existence: "+ product.getExistence()+" is not valid");
		}
	}
	
	
	
	
	
	
	
	
}
