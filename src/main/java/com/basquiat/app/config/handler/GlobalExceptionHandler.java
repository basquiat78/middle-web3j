package com.basquiat.app.config.handler;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.basquiat.app.web3j.common.vo.BasquiatErrorVO;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;

/**
 * 
 * GlobalExceptionHandler
 * 
 * create by basquiat 2018.04.19
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(SQLException e) {
		return createErrorObject(e);
	}
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(DataAccessException e) {
		return createErrorObject(e);
	}
	
	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(IOException e) {
		return createErrorObject(e);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(RuntimeException e) {
		return createErrorObject(e);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(Exception e) {
		return createErrorObject(e);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BasquiatResponseVO<?> handleException(Throwable e) {
		return createErrorObject(e);
	}

	private BasquiatResponseVO<?> createErrorObject(Throwable e) {
		return new BasquiatResponseVO<>(new BasquiatErrorVO(e.getMessage()));
	}
	
}
