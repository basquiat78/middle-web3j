package com.basquiat.app.web3j.service.client;

import java.math.BigInteger;

import com.basquiat.app.web3j.service.client.vo.ClientInfoVO;

/**
 * 
 * ClientInfo Interface
 * 
 * create by basquiat 2018.04.19
 * 
 */
public interface ClientInfo {

	public boolean getNetListening();
	public boolean getEthSyncing();
	
	public String getClientVersion();
	public String getEthCoinbase();
	
	public BigInteger getNetPeerCount();
	public BigInteger getEthGasPrice();
	public BigInteger getBlockNumber();
	
	public ClientInfoVO getClientInfo();
	
}
