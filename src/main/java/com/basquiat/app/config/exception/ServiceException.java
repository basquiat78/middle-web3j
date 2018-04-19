package com.basquiat.app.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;

/**
 * 
 * ServiceException
 * 
 * create by basquiat 2018.04.19
 * 
 */
@SuppressWarnings("serial")
public class ServiceException extends NestedRuntimeException {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);
	
	public ServiceException(String msg) {
		super(msg);
		LOG.error(msg);
	}
	
	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
		LOG.error(msg);
	}

}
