package com.basquiat.app.couchbase.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.couchbase.service.CouchbaseService;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouchBaseServiceTest {

	@Autowired
    private CouchbaseService couchBaseService;
	
    @Before
    public void setUp() {
    }
	
	@Test
	public void getPaging() throws Exception {
		List<TransactionHistoryVO> list = couchBaseService.findAll(1);
		System.out.println(BasquiatUtils.convertJsonStringFromObject(list));
		}
	
	/**
	 * below code that will test api
	 */
	
}
