package com.basquiat.app.web3j.service.transaction;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.basquiat.app.ethereum.contract.DCToken;
/**
 * 
 * Transaction Service
 * 
 * create by basquiat 2018.04.19
 *
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private Web3j web3j;

	@Autowired
	private Credentials credentials;
	
	@Autowired
	private DCToken tokenContract;
	
	@Override
	public TransactionReceipt sendTransaction(String toAddress, String unit, BigDecimal amount) {
		TransactionReceipt transactionReceipt = null;
		try {
			transactionReceipt = Transfer.sendFunds(web3j, credentials, toAddress, amount, Convert.Unit.fromString(unit)).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionReceipt;
	}
	
	@Override
	public TransactionReceipt sendTransactionWithToken(String toAddress, BigInteger amount) {
		TransactionReceipt transactionReceipt = null;
		try {
			transactionReceipt = tokenContract.transfer(toAddress, amount).send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transactionReceipt;
	}
	
}
