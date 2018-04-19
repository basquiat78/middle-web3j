package com.basquiat.app.web3j.service.balance.vo;

import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * BalanceVO
 * 
 * create by basquiat 2018.04.19
 *
 */
@Setter
@Getter
public class TokenBalanceVO {

	/** address */
	private String address;
	
	@Setter(AccessLevel.NONE)
	private String tokenBalanceStringType;
	
	private BigInteger tokenBalance;
	
	public void setTokenBalance(BigInteger tokenBalance) {
		this.tokenBalance = tokenBalance;
		this.tokenBalanceStringType = tokenBalance.toString();
	}
}
