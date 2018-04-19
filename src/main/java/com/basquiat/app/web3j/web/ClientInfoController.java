package com.basquiat.app.web3j.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.service.client.ClientInfo;
import com.basquiat.app.web3j.service.client.vo.ClientInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "ClientInfo")
@RequestMapping(BasquiatConstants.API_URL_ROOT)
public class ClientInfoController {

    @Autowired
    private ClientInfo clientInfo;
	
    @ApiOperation(value = "RPC 버전 정보", notes = "RPC의 정보를 가져온다.")	
    @GetMapping("/clientInfo")
	public BasquiatResponseVO<ClientInfoVO> getClientInfo(){
    	ClientInfoVO vo = clientInfo.getClientInfo();
    	return new BasquiatResponseVO<ClientInfoVO>(vo);
    }
    
}
