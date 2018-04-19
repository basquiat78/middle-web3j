package com.basquiat.app.web3j.common.vo;

import com.basquiat.app.web3j.common.util.BasquiatConstants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * BasquiatResponseVO
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Setter
@Getter
@NoArgsConstructor
public class BasquiatResponseVO<T> {

	private String homepage = "http://www." + BasquiatConstants.HOST + "/";
	
	private String license = homepage + "license";
	
	private T data;
	
	private BasquiatErrorVO error;
	
	public BasquiatResponseVO(T data) {
		this.data = data;
	}

	public BasquiatResponseVO(BasquiatErrorVO error) {
		this.error = error;
	}

}