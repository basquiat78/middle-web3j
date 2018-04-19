package com.basquiat.app.web3j.service.database;

import java.util.List;

import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.account.vo.AccountVO;

/**
 * 
 * Database Service inteface
 * 
 * create by basquiat 2018.04.19
 *
 */
public interface DatabaseService {
	
	public void insertNormalTransaction(Transaction transaction) throws Exception;
	public void insertContractTransaction(Transaction transaction) throws Exception;

	public void createAccount(AccountVO accountVO);

	public void backupWalletFile(String userId, String walletFileName, WalletFile walletFile) throws Exception ;
	
	public String getLastBlockNumber();
	
	public int checkAccount(String userId) throws Exception;
	
	public List<TransactionHistoryVO> selectTransactionHistoryByUserId(String userId);
	public List<TransactionHistoryVO> selectTransactionHistoryByAddress(String address);
	
}
