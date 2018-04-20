package com.basquiat.app.web3j.service.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.account.vo.AccountVO;
import com.basquiat.app.web3j.service.database.vo.DatabaseBalanceVO;

/**
 * 
 * DatabaseMapper
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Mapper
public interface DatabaseMapper {

	public void createAccount(AccountVO accountVO);
	
	public void insertTransaction(TransactionHistoryVO transactionHistoryVO);

	public void initializeBalance(DatabaseBalanceVO databaseBalanceVO);
	
	public void backupWalletFile(@Param("userId") String userId, @Param("walletFileName") String walletFileName, @Param("walletContents") String walletContents);
	
	public void updateBalance(DatabaseBalanceVO databaseBalanceVO);
	
	public int checkAccount(String userId);
	
	public String getLastBlockNumber();
	
	public DatabaseBalanceVO selectBalanceByUserId(String userId);
	public DatabaseBalanceVO selectBalanceByAddress(String address);
	
	public List<TransactionHistoryVO> selectTransactionHistoryByUserId(String userId);
	public List<TransactionHistoryVO> selectTransactionHistoryByAddress(String address);
	
}
