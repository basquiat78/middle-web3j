package com.basquiat.app.web3j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.DefaultBlockParameterName;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.service.balance.BalanceService;
import com.basquiat.app.web3j.service.balance.vo.BalanceVO;
import com.basquiat.app.web3j.service.balance.vo.TokenBalanceVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Balance")
@RequestMapping(BasquiatConstants.API_URL_ROOT)
public class BalanceController {

    @Autowired
    private BalanceService balanceService;
	
    @ApiOperation(value = "Balance 정보", notes = "Balance를 가져온다. option는 기본적으로 LATEST로 조회한다.")	
    @GetMapping("/balances/{address}")
	public BasquiatResponseVO<BalanceVO> getBalance(@PathVariable String address){
    	return new BasquiatResponseVO<BalanceVO>(balanceService.getBalance(address, DefaultBlockParameterName.LATEST.name()));
    }
    
    @ApiOperation(value = "Balance 정보", notes = "Balance를 가져온다. Option 정보: earliest|pending|latest ")	
    @GetMapping("/balances/{address}/{option}")
	public BasquiatResponseVO<BalanceVO> getBalance(@PathVariable String address, @PathVariable String option){
    	return new BasquiatResponseVO<BalanceVO>(balanceService.getBalance(address, option));
    }
    
    @ApiOperation(value = "Token Balance 정보", notes = "Token Balance를 가져온다. ")	
    @GetMapping("/tokenbalances/{address}")
	public BasquiatResponseVO<TokenBalanceVO> getTokenBalance(@PathVariable String address){
    	return new BasquiatResponseVO<TokenBalanceVO>(balanceService.getTokenBalance(address));
    }
    
}