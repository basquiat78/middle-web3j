package com.basquiat.app.web3j.web;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.service.transaction.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "transaction")
@RequestMapping(BasquiatConstants.API_URL_ROOT + "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
	
    @ApiOperation(value = "Transaction Send", notes = "Ethereum을 해당 주소로 전송합니다. ")	
    @GetMapping("/send/{toAddress}/{amount}")
	public BasquiatResponseVO<TransactionReceipt> sendTransaction(@PathVariable String toAddress, @PathVariable BigDecimal amount){
    	return new BasquiatResponseVO<TransactionReceipt>(transactionService.sendTransaction(toAddress, "ether", amount));
    }
    
    @ApiOperation(value = "Transaction Send", notes = "unit에 해당하는 Unit을 해당 주소로 전송합니다. unit type: wei|kwei|mwei|gwei|szabo|finney|ether|kether|mether|gether")	
    @GetMapping("/send/{toAddress}/{unit}/{amount}")
	public BasquiatResponseVO<TransactionReceipt> sendTransaction(@PathVariable String toAddress, @PathVariable String unit, @PathVariable BigDecimal amount){
    	return new BasquiatResponseVO<TransactionReceipt>(transactionService.sendTransaction(toAddress, unit, amount));
    }
    
    @ApiOperation(value = "Transaction Send", notes = "토큰을 해당 주소로 전송합니다. ")	
    @GetMapping("/sendtoken/{toAddress}/{amount}")
	public BasquiatResponseVO<TransactionReceipt> sendTransactionWithToken(@PathVariable String toAddress, @PathVariable BigInteger amount){
    	return new BasquiatResponseVO<TransactionReceipt>(transactionService.sendTransactionWithToken(toAddress, amount));
    }

}
