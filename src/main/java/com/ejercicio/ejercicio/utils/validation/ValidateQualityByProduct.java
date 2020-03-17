package com.ejercicio.ejercicio.utils.validation;


import com.ejercicio.ejercicio.utils.utilclasses.QualityByProduct;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ValidateQualityByProduct extends Validate {
	
	
	
	public ValidateQualityByProduct(QualityByProduct qualityByProduct) {
		if(qualityByProduct != null ) {
			if(qualityByProduct.getProduct()==null){

				addError("\n -> This product not exist");
				
			}else {
				
				validated(qualityByProduct);
			
			}
			
			}
		else {
			addError("\n -> Invalid parameter is null request");
		}
	}
	
	

	private void validated(QualityByProduct qualityByProduct) {
		
		if(qualityByProduct.getQuality()<=0) {
			addError("\n ->Quality: "+qualityByProduct.getQuality()+" is not valid");
		}else if (qualityByProduct.getProduct().getExistence()<qualityByProduct.getQuality()) {

			addError("\n ->Existence product is: "+ qualityByProduct.getProduct().getExistence());
		}
		
		
		if(qualityByProduct.getOff()<0||qualityByProduct.getOff()>100) {
			addError("\n ->Off: "+qualityByProduct.getOff()+" is not valid");
		}
		
		if(qualityByProduct.getProduct()==null){

			addError("\n -> This product not exist");
		}
		
	}
	
	

}
