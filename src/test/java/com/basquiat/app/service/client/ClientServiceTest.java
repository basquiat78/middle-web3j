package com.basquiat.app.service.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private Web3j web3j;
	
	@Test
	public void web3ClientVersion() throws InterruptedException, ExecutionException {
		
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		System.out.println("client version : " + clientVersion);
		assertFalse(clientVersion.isEmpty());
	}
	
	@Test
	public void netPeerCount() throws InterruptedException, ExecutionException {
		
		NetPeerCount netPeerCount = web3j.netPeerCount().sendAsync().get();
		BigInteger npc = netPeerCount.getQuantity();
		System.out.println("netPeerCount : " + npc);
		assertTrue(npc.signum() == 1);
	}
	
	@Test
	public void blockNumber() throws InterruptedException, ExecutionException {
		
		EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().sendAsync().get();
		System.out.println("blockNumber : " + ethBlockNumber.getBlockNumber());
	}
	
	/**
	 * below code that will test api
	 */
	
}
