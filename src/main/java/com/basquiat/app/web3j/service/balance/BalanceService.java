package com.basquiat.app.web3j.service.balance;

import com.basquiat.app.web3j.service.balance.vo.BalanceVO;
import com.basquiat.app.web3j.service.balance.vo.TokenBalanceVO;

/**
 * 
 * BalanceService Interface
 * 
 * create by basquiat 2018.04.19
 * 
 */
public interface BalanceService {

	public BalanceVO getBalance(String address, String option);
	
	public TokenBalanceVO getTokenBalance(String address);
}
