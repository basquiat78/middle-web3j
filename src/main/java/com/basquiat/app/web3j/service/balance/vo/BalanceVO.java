package com.basquiat.app.web3j.service.balance.vo;

import java.math.BigInteger;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

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
public class BalanceVO {

	/** address */
	private String address;
	
	/**
	 * type
	 * earliest | pending | latest
	 */
	private DefaultBlockParameterName optionType;
	
	private EthGetBalance ethGetBalance;
	
	/** balance string type */
	@Setter(AccessLevel.NONE)
	private String balanceStringType;
	
	public void setBalance(BigInteger balance) {
		this.balanceStringType = balance.toString();
	}
}
