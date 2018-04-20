package com.basquiat.app.web3j.common.util;

import java.math.BigDecimal;

/**
 * 
 * BasquiatConstants
 * 
 * create by basquiat 2018.04.19
 *
 */
public class BasquiatConstants {
	
	public static final String API_URL_ROOT = "/api";
	
	public static final String HOST = "basquiat.com";
	
	public static final String PROTOCOL_SEPARATOR = "://";
	
	public static final String PORT_SEPARATOR = ":";

	public static final String Address_Prefix = "0x";

	/**
	 * 토큰 마다 단위에 대한 환산값이 다를 수 있을까?
	 * 테스트를 해보지 못해서 이 부분을 Constants로 뺀다.
	 */
	public static final BigDecimal TOKEN_DIVIDE_VALUE = BigDecimal.valueOf(100000000);
	
}
