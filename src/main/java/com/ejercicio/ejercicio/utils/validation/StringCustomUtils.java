package com.ejercicio.ejercicio.utils.validation;

import org.apache.commons.lang3.StringUtils;


public class StringCustomUtils {

	private StringCustomUtils() {}
	public static boolean isEmpty(String string) {
		return StringUtils.isEmpty(string);
		
	}
	
	public static boolean isBlank(String string){
		return StringUtils.isBlank(string);
	}
	
}
