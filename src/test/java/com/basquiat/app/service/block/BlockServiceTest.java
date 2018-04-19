package com.basquiat.app.service.block;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.core.methods.response.EthTransaction;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.block.BlockService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockServiceTest {

	@Autowired
	private BlockService blockService;
	
    @Before
    public void setUp() {
    }
	
	@Test
	public void ethGetTransactionByHash() throws Exception {
		EthTransaction ethTransaction = blockService.ethGetTransactionByHash("0xccdd63ffc997fe3e89a349a060def97496b038aa4248045935f3dc68bf4e9a90");
		System.out.println(BasquiatUtils.convertJsonStringFromObject(ethTransaction));
	}
	
	/**
	 * below code that will test api
	 */
	
}
