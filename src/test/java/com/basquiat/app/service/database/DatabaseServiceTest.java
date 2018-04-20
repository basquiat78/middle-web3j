package com.basquiat.app.service.database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.database.DatabaseService;
import com.basquiat.app.web3j.service.database.vo.DatabaseBalanceVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceTest {

	@Autowired
	private DatabaseService databaseService;
	
	@Test
	public void getBalanceByUserId() throws Exception {
		DatabaseBalanceVO databaseBalanceVO = databaseService.selectBalanceByUserId("test1");
		System.out.println(BasquiatUtils.convertJsonStringFromObject(databaseBalanceVO));
		System.out.println("Wei --> ETHER : " + BasquiatUtils.convertToEtherFromWei(databaseBalanceVO.getEthBalance()));
		System.out.println("convert real token value : " + BasquiatUtils.convertToRealTokenValue(databaseBalanceVO.getTokenBalance()) );
	}
	
	@Test
	public void getBalanceByAddress() throws Exception {
		DatabaseBalanceVO databaseBalanceVO = databaseService.selectBalanceByAddress("0xb6C55e745Da1f4d6EA4Bf49E8c9870D24a302560");
		System.out.println(BasquiatUtils.convertJsonStringFromObject(databaseBalanceVO));
		System.out.println("Wei --> ETHER : " + BasquiatUtils.convertToEtherFromWei(databaseBalanceVO.getEthBalance()));
		System.out.println("convert real token value : " + BasquiatUtils.convertToRealTokenValue(databaseBalanceVO.getTokenBalance()) );
	}
	
	/**
	 * below code that will test api
	 */
	
}
