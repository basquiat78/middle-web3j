package com.basquiat.app.service.account;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.EthAccounts;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.account.AccountService;
import com.basquiat.app.web3j.service.account.vo.AccountVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
    @Before
    public void setUp() {
    }
	
	@Test
	public void getEthAccount() throws Exception {
		EthAccounts ea = accountService.getEthAccounts();
		System.out.println(BasquiatUtils.convertJsonStringFromObject(ea));
	}
	
	@Test
	public void createAccount() throws Exception {
		AccountVO av = new AccountVO();
		av.setUserId("test1");
		av.setPassword("123456789");
		System.out.println(BasquiatUtils.convertJsonStringFromObject(accountService.createAccount(av)));
	}
	
	@Test
	public void walletFile() throws Exception {
		String destinationDirectory = "C:/Users/basquiat/AppData/Roaming/Ethereum/basquiat/keystore";
		String fileName = "UTC--2018-04-16T06-11-36.280000000Z--b6c55e745da1f4d6ea4bf49e8c9870d24a302560.json";
		File destination = new File(destinationDirectory, fileName);
		WalletFile walletFile = new ObjectMapper().readValue(destination, WalletFile.class);
		System.out.println(BasquiatUtils.convertJsonStringFromObject(walletFile));
	}
	
	
	/**
	 * below code that will test api
	 */
	
}
