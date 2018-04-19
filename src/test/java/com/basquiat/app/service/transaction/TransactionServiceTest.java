package com.basquiat.app.service.transaction;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.basquiat.app.ethereum.contract.DCToken;
import com.basquiat.app.web3j.common.util.BasquiatUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	private Web3j web3j;

	@Autowired
	private Credentials credentials;

	private DCToken tokenContract;

	private String targetAddress;
	
	private long amount;
	
    @Before
    public void setUp() {
        this.targetAddress = "0x999048374370FF47aCE98367FFbaa28729846898";
        this.amount = 100;
    }
    
	@Test
	public void sendEther() throws InterruptedException, IOException, TransactionException, Exception {
		TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, credentials, targetAddress, BigDecimal.valueOf(amount), Convert.Unit.ETHER).send();
		String result = BasquiatUtils.convertJsonStringFromObject(transactionReceipt);
		System.out.println(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void sendToken() throws InterruptedException, IOException, TransactionException, Exception {
		TransactionReceipt transactionReceipt = tokenContract.transfer(targetAddress, BigInteger.valueOf(amount)).send();
		System.out.println(BasquiatUtils.convertJsonStringFromObject(transactionReceipt));
	}
	
	/**
	 * below code that will test api
	 */
	
}
