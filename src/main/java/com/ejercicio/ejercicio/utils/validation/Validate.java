package com.ejercicio.ejercicio.utils.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Validate {
	
	@Getter(value=AccessLevel.NONE)
	private String errors;
	
	private boolean valid=true;

	private  void notValid() {
		if(valid) {
			valid=!valid;
		}
	}
	
	public  String getErrors() {
		
		return valid ?  "No errors here":"Have next errors:"+this.errors;
	
	}
	
	public void addError(String error) {
		this.errors+=error;
		notValid();
		
	}
}
