package com.basquiat.app.config.handler;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * 
 * AsyncExceptionHandler
 * 
 * create by basquiat 2018.04.19
 * 
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		System.out.println("Exception message - " + throwable.getMessage());
		System.out.println("Method name - " + method.getName());
		for(Object param : obj) {
			System.out.println("Parameter value - " + param);
		}
	}

}
