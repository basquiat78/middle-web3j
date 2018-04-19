package com.basquiat.app.service.balance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.balance.BalanceService;
import com.basquiat.app.web3j.service.balance.vo.BalanceVO;
import com.basquiat.app.web3j.service.balance.vo.TokenBalanceVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BalanceServiceTest {

	@Autowired
	private BalanceService balanceService;
	
	private String targetAddress;
	
	private String option;
	
    @Before
    public void setUp() {
        this.targetAddress = "0x577005Be0CABc7853d304360216b7835c921b7C7";
        this.option = "latest";
    }
	
	@Test
	public void getEthBalance() throws Exception {
		BalanceVO balanceVO = balanceService.getBalance(targetAddress, option);
		System.out.println("target address balance : " + BasquiatUtils.convertJsonStringFromObject(balanceVO));
	}
	
	@Test
	public void getTokenBalance() throws Exception {
		TokenBalanceVO tokenBalanceVO = balanceService.getTokenBalance(targetAddress);
		System.out.println("target address balance : " + BasquiatUtils.convertJsonStringFromObject(tokenBalanceVO));
	}
	
	/**
	 * below code that will test api
	 */
	
}
