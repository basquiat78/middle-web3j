package com.basquiat.app.web3j.service.balance;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.basquiat.app.ethereum.contract.DCToken;
import com.basquiat.app.web3j.service.balance.vo.BalanceVO;
import com.basquiat.app.web3j.service.balance.vo.TokenBalanceVO;

/**
 * 
 * BalanceService Interface
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Service("balanceService")
public class BalanceServiceImpl implements BalanceService {

	@Autowired
	private Web3j web3j;
	
	@Autowired
	private DCToken tokenContract;
	
	/**
	 * 계정의 밸런스 가져오기.
	 * @param address
	 * @param option
	 * @return BalanceVO
	 */
	@Override
	public BalanceVO getBalance(String address, String option) {
		BalanceVO balanceVO = new BalanceVO();
		balanceVO.setAddress(address);
		balanceVO.setOptionType(DefaultBlockParameterName.fromString(option));
		try {
			EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.fromString(option)).sendAsync().get();
			BigInteger balance = ethGetBalance.getBalance();
			balanceVO.setEthGetBalance(ethGetBalance);
			balanceVO.setBalance(balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balanceVO;
	}
	
	/**
	 * 계정의 토큰 밸런스 가져오기.
	 * @param address
	 * @return TokenBalanceVO
	 */
	@Override
	public TokenBalanceVO getTokenBalance(String address) {
		TokenBalanceVO tokenBalanceVO = new TokenBalanceVO();
		tokenBalanceVO.setAddress(address);
		try {
			BigInteger balance = tokenContract.balanceOf(address).sendAsync().get();
			tokenBalanceVO.setTokenBalance(balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenBalanceVO;
	}
	
}
