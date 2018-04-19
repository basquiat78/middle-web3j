package com.basquiat.app.web3j.service.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * 
 * TransactionService Interface
 * 
 * create by basquiat 2018.04.19
 *
 */
public interface TransactionService {

	public TransactionReceipt sendTransaction(String toAddress, String unit, BigDecimal amount);
	
	public TransactionReceipt sendTransactionWithToken(String toAddress, BigInteger amount);
	
}
