package com.basquiat.app.web3j.service.database.vo;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * DatabaseBalanceVO
 * 
 * DB로부터 조회한 밸런스 정보를 담는다.
 *
 */
@Setter
@Getter
public class DatabaseBalanceVO {

	/** userId */
	private String userId;
	
	/** 이더리움 게정 주소 */
	private String accountAddress;
	
	/** 이더리움 balance */
	private BigInteger ethBalance;
	
	/** 토큰 balance */
	private BigInteger tokenBalance;
	
	/**
	 * NORMAL | CONTRACT
	 */
	private String type;
	
}
