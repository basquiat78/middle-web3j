package com.basquiat.app.observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.database.DatabaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObserverTransactionServiceTest {

	@Autowired
    private DatabaseService databaseService;
	
    @Before
    public void setUp() {
    }
	
	@Test
	public void getTransactionByUserId() throws Exception {
		String userId = "test";
		System.out.println(BasquiatUtils.convertJsonStringFromObject(databaseService.selectTransactionHistoryByUserId(userId)));
	}
	
	@Test
	public void getTransactionByUserAddress() throws Exception {
		String address = "0xb6C55e745Da1f4d6EA4Bf49E8c9870D24a302560";
		System.out.println(BasquiatUtils.convertJsonStringFromObject(databaseService.selectTransactionHistoryByAddress(address)));
	}
	
	@Test
	public void getLastBlockNumber() throws Exception {
		System.out.println(databaseService.getLastBlockNumber());
	}
	
	/**
	 * below code that will test api
	 */
	
}
