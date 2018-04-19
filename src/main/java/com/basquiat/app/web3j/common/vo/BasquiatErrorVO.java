package com.basquiat.app.web3j.common.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 
 * BasquiatErrorVO
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Setter
@Getter
@RequiredArgsConstructor
public class BasquiatErrorVO {
	
	/** 에러 코드 */
	private String code;
	
	/** 메세지 */
	@NonNull
	private String message;
	
}
