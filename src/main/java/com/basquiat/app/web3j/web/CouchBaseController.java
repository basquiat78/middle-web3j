package com.basquiat.app.web3j.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.couchbase.service.CouchbaseService;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(tags = "couchbase")
@RequestMapping(BasquiatConstants.API_URL_ROOT)
public class CouchBaseController {

	@Autowired
    private CouchbaseService couchBaseService;
    
	@ApiOperation(value = "Transaction 정보", notes = "CouchBase에 저장된 모든 Transaction 정보를 가져온다. ")	
	@GetMapping("/couchbase/transactions/page/{page}")
	public BasquiatResponseVO<List<TransactionHistoryVO>> findAll(@PathVariable int page) throws Exception{
		return new BasquiatResponseVO<List<TransactionHistoryVO>>(couchBaseService.findAll(page));
	}
	
    @ApiOperation(value = "Transaction 정보", notes = "TransactionHash로 CouchBase에 저장된 Transaction 정보를 가져온다. ")	
    @GetMapping("/couchbase/transactions/{transactionHash}")
	public BasquiatResponseVO<TransactionHistoryVO> getTransactionFromCouchBase(@PathVariable String transactionHash) throws Exception{
    	return new BasquiatResponseVO<TransactionHistoryVO>(couchBaseService.findByTransactionHash(transactionHash));
    }
    
}