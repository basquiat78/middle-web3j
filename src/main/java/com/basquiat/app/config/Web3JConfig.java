package com.basquiat.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import com.basquiat.app.ethereum.contract.DCToken;
import com.basquiat.app.web3j.common.util.BasquiatUtils;

/**
 * 
 * Web3JClient
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Configuration
@Component
public class Web3JConfig {

	private Web3j web3j;
	
	private Credentials credentials;

	/**
	 * token contract
	 */
	private DCToken tokenContract;
	
	/**
	 * Constructor
	 * @param rpcProtocol
	 * @param rpcHost
	 * @param rpcPort
	 * @throws CipherException 
	 * @throws IOException 
	 */
	public Web3JConfig(@Value("${eth.rpc.protocol}") final String rpcProtocol,
					   @Value("${eth.rpc.host}") final String rpcHost,
					   @Value("${eth.rpc.port}") final String rpcPort,
					   @Value("${eth.coinbase.password}") final String coinbasePassword,
					   @Value("${eth.coinbase.pathtowallet}") final String pathToWallet,
					   @Value("${eth.token.address}") final String ethTokenAddress
					  ) throws IOException, CipherException {
		
		String clientUrl = BasquiatUtils.makeUrl(rpcProtocol, rpcHost, rpcPort);
		web3j = Web3j.build(new HttpService(clientUrl));
		credentials = WalletUtils.loadCredentials(coinbasePassword, pathToWallet);
		tokenContract = DCToken.load(ethTokenAddress, web3j, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
	}
	
	/**
	 * create bean Web3j
	 * @return Web3j
	 */
	public @Bean Web3j web3j() {
		return this.web3j;
	}
	
	/**
	 * create bean Credentials
	 * @return Web3j
	 */
	public @Bean Credentials credentials() {
		return this.credentials;
	}
	
	/**
	 * create bean Credentials
	 * @return Web3j
	 */
	public @Bean DCToken tokenContract() {
		return this.tokenContract;
	}
	
}
