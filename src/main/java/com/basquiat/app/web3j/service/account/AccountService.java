package com.basquiat.app.web3j.service.account;

import org.web3j.protocol.core.methods.response.EthAccounts;

import com.basquiat.app.web3j.service.account.vo.AccountVO;

/**
 * 
 * Account Interface
 * 
 * create by basquiat 2018.04.19
 * 
 */
public interface AccountService {

	public EthAccounts getEthAccounts();
	
	public AccountVO createAccount(AccountVO accountVO);
	
}
