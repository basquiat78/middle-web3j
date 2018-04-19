package com.basquiat.app.web3j.service.client.vo;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * ClientInfoVO
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Setter
@Getter
public class ClientInfoVO {

	/** rpc version */
	private String clientVersion;
	
	/** rpc eth coinbase */
	private String ethCoinbase;
	
	/** rpc net Listening */
	private boolean netListening;
	
	/** rpc net peer count */
	private BigInteger netPeerCount;
	
	/** rpc eth gas price */
	private BigInteger ethGasPrice;
	
	/** rpc block Number */
	private BigInteger blockNumber;
	
	/** rpc eth Syncing */
	private boolean ethSyncing;
}
