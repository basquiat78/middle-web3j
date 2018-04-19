package com.basquiat.app.web3j.service.client;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.NetListening;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import com.basquiat.app.web3j.service.client.vo.ClientInfoVO;

/**
 * 
 * if need more detail information, expanded this service
 * ClientInfo Service
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Service("clientInfo")
public class ClientInfoImpl implements ClientInfo {

	@Autowired
	private Web3j web3j;
	
	/**
	 * RPC Client Version
	 * @return String
	 */
	@Override
	public String getClientVersion() {
		String clientVersion = "";
		try {
			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
			clientVersion = web3ClientVersion.getWeb3ClientVersion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientVersion;
	}

	/**
	 * RPC net Peer Count
	 * @return BigInteger
	 */
	@Override
	public BigInteger getNetPeerCount() {
		BigInteger peerCount = null;
		try {
			NetPeerCount netPeerCount = web3j.netPeerCount().sendAsync().get();
			peerCount = netPeerCount.getQuantity();
		} catch (Exception e) {
			peerCount = BigInteger.ZERO;
		}
		
		return peerCount;
	}

	/**
	 * RPC ETH Coinbase
	 * @return String
	 */
	@Override
	public String getEthCoinbase() {
		String coinbase = null;
		try {
			EthCoinbase ethCoinbase = web3j.ethCoinbase().sendAsync().get();
			coinbase = ethCoinbase.getAddress();
		} catch (Exception e) {
			coinbase = null;
		}
		return coinbase;
	}
	
	/**
	 * RPC Net Listening
	 * @return boolean
	 */
	@Override
	public boolean getNetListening() {
		boolean isListening = false;
		try {
			NetListening netListening = web3j.netListening().sendAsync().get();
			isListening = netListening.isListening();
		} catch (Exception e) {
			isListening = false;
		}
		return isListening;
	}
	
	/**
	 * RPC Eth Gas Price
	 * @return BigInteger
	 */
	@Override
	public BigInteger getEthGasPrice() {
		BigInteger gasPrice = null;
		try {
			EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
			gasPrice = ethGasPrice.getGasPrice();
		} catch (Exception e) {
			gasPrice = BigInteger.ZERO;
		}
		return gasPrice;
	}
	
	/**
	 * RPC Block Number
	 * @return BigInteger
	 */
	@Override
	public BigInteger getBlockNumber() {
		BigInteger blockNumber = null;
		try {
			EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
			blockNumber = ethBlockNumber.getBlockNumber();
		} catch (Exception e) {
			blockNumber = BigInteger.ZERO;
		}
		return blockNumber;
	}
	
	/**
	 * RPC Eth Syncing
	 * @return boolean
	 */
	@Override
	public boolean getEthSyncing() {
		boolean isSyncing = false;
		try {
			EthSyncing ethSyncing = web3j.ethSyncing().sendAsync().get();
			isSyncing = ethSyncing.isSyncing();
		} catch (Exception e) {
			isSyncing = false;
		}
		return isSyncing;
	}
	
	/**
	 * RPC Total infomation
	 * @return ClientInfoVO
	 */
	@Override
	public ClientInfoVO getClientInfo() {
		ClientInfoVO info = new ClientInfoVO();
		info.setClientVersion(this.getClientVersion());
		info.setEthCoinbase(this.getEthCoinbase());
		info.setNetListening(this.getNetListening());
		info.setNetPeerCount(this.getNetPeerCount());
		info.setEthGasPrice(this.getEthGasPrice());
		info.setBlockNumber(this.getBlockNumber());
		info.setEthSyncing(this.getEthSyncing());
		return info;
	}
	
}
