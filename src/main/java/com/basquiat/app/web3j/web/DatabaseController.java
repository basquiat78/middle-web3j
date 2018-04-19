package com.basquiat.app.web3j.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.database.DatabaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "database")
@RequestMapping(BasquiatConstants.API_URL_ROOT + "/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;
	
    @ApiOperation(value = "database transaction history", notes = "User Id로 트랜잭션 히스토리 정보를 가져온다. ")	
    @GetMapping("/transactions/users/{userId}")
	public BasquiatResponseVO<List<TransactionHistoryVO>> findTransactionHistoryByUserId(@PathVariable String userId){
    	return new BasquiatResponseVO<List<TransactionHistoryVO>>(databaseService.selectTransactionHistoryByUserId(userId));
    }
    
    @ApiOperation(value = "database transaction history", notes = "Ethereum Account로 트랜잭션 히스토리 정보를 가져온다. ")	
    @GetMapping("/transactions/addresses/{address}")
	public BasquiatResponseVO<List<TransactionHistoryVO>> findTransactionHistoryByAddress(@PathVariable String address){
    	return new BasquiatResponseVO<List<TransactionHistoryVO>>(databaseService.selectTransactionHistoryByAddress(address));
    }

}
