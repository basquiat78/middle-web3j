package com.basquiat.app.web3j.couchbase.service;

import java.util.List;

import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
/**
 * 
 * couchBase Service interface
 * 
 * create by basquiat 2018.04.19
 *
 */
public interface CouchbaseService {
	
	public void upsertBucketTransaction(Transaction transaction, String transactionType) throws Exception;
	public void upsertBucketWalletFile(String fileName, WalletFile walletFile) throws Exception;
	
	public List<TransactionHistoryVO> findAll(int page) throws Exception;
	
	public TransactionHistoryVO findByTransactionHash(String transactionHash) throws Exception;
	
}
