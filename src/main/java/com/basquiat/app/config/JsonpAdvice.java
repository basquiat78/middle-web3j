package com.basquiat.app.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * 
 * JsonpAdvice
 * 
 * create by basquiat 2018.04.19
 * 
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	
	public JsonpAdvice() {
		super("callback");
	}
	
}
