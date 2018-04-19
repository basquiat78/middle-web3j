package com.basquiat.app.web3j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.EthAccounts;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.service.account.AccountService;
import com.basquiat.app.web3j.service.account.vo.AccountVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "account")
@RequestMapping(BasquiatConstants.API_URL_ROOT + "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
	
    @ApiOperation(value = "Account 정보", notes = "노드의 Account 리스트를 가져온다. ")	
    @GetMapping("")
	public BasquiatResponseVO<EthAccounts> ethAccounts(){
    	return new BasquiatResponseVO<EthAccounts>(accountService.getEthAccounts());
    }
    
    @ApiOperation(value = "Account 정보", notes = "이더리움 노드에 신규 어카운트를 생성한다.")	
    @PostMapping("/create")
	public BasquiatResponseVO<AccountVO> ethCreateAccounts(@RequestBody AccountVO accountVO){
    	return new BasquiatResponseVO<AccountVO>(accountService.createAccount(accountVO));
    }

}
