package com.basquiat.app.web3j.service.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.account.vo.AccountVO;

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

	public void backupWalletFile(@Param("userId") String userId, @Param("walletFileName") String walletFileName, @Param("walletContents") String walletContents);
	
	public int checkAccount(String userId);
	
	public String getLastBlockNumber();
	
	public List<TransactionHistoryVO> selectTransactionHistoryByUserId(String UserId);
	public List<TransactionHistoryVO> selectTransactionHistoryByAddress(String address);
	
}
