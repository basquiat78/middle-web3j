package com.basquiat.app.web3j.web;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;

import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.vo.BasquiatResponseVO;
import com.basquiat.app.web3j.service.block.BlockService;
import com.basquiat.app.web3j.service.block.vo.ResultBlockVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Blocks")
@RequestMapping(BasquiatConstants.API_URL_ROOT)
public class BlockController {

    @Autowired
    private BlockService blockService;
	
    @ApiOperation(value = "Block 정보", notes = "Block Hash값으로 Block 정보를 가져온다. option는 기본적으로 false로 조회한다. 옶션은 블록의 트랜잭션의 전체 정보를 가져올지 여부를 결정한다.")	
    @GetMapping("/blocks/hash/{blockHash}")
	public BasquiatResponseVO<ResultBlockVO> ethBlockByHash(@PathVariable String blockHash){
    	return new BasquiatResponseVO<ResultBlockVO>(blockService.getBlockByHash(blockHash, false));
    }
    
    @ApiOperation(value = "Block 정보", notes = "Block Hash값으로 Block 정보를 가져온다. option은 블록의 트랜잭션의 전체 정보를 가져올지 여부를 결정한다. option 정보: true|false")	
    @GetMapping("/blocks/hash/{blockHash}/{option}")
	public BasquiatResponseVO<ResultBlockVO> ethBlockByHash(@PathVariable String blockHash, @PathVariable boolean option){
    	return new BasquiatResponseVO<ResultBlockVO>(blockService.getBlockByHash(blockHash, option));
    }
    
    @ApiOperation(value = "Block 정보", notes = "Block Number값으로 Block 정보를 가져온다. option는 기본적으로 false로 조회한다. option은 블록의 트랜잭션의 전체 정보를 가져올지 여부를 결정한다.")	
    @GetMapping("/blocks/number/{blockNumber}")
	public BasquiatResponseVO<ResultBlockVO> ethBlockByNumber(@PathVariable BigInteger blockNumber){
    	return new BasquiatResponseVO<ResultBlockVO>(blockService.getBlockByNumber(blockNumber, false));
    }
    
    @ApiOperation(value = "Block 정보", notes = "Block Number값을 통해 Block 정보를 가져온다. option은 블록의 트랜잭션의 전체 정보를 가져올지 여부를 결정한다. option 정보: true|false")	
    @GetMapping("/blocks/number/{blockNumber}/{option}")
	public BasquiatResponseVO<ResultBlockVO> ethBlockByNumber(@PathVariable BigInteger blockNumber, @PathVariable boolean option){
    	return new BasquiatResponseVO<ResultBlockVO>(blockService.getBlockByNumber(blockNumber, option));
    }
    
    @ApiOperation(value = "Block Transaction Count 정보", notes = "Block Hash값을 통해 Block Transaction Count 정보를 가져온다.")	
    @GetMapping("/blocks/txcnt/hash/{blockHash}")
	public BasquiatResponseVO<EthGetBlockTransactionCountByHash> ethBlockTxCountByHash(@PathVariable String blockHash){
    	return new BasquiatResponseVO<EthGetBlockTransactionCountByHash>(blockService.getBlockTxCountByHash(blockHash));
    }
    
    @ApiOperation(value = "Block Transaction Count 정보", notes = "Block Number값을 통해 Block Transaction Count 정보를 가져온다.")	
    @GetMapping("/blocks/txcnt/number/{blockNumber}")
	public BasquiatResponseVO<EthGetBlockTransactionCountByNumber> ethBlockTxCountByNumber(@PathVariable BigInteger blockNumber){
    	return new BasquiatResponseVO<EthGetBlockTransactionCountByNumber>(blockService.getBlockTxCountByNumber(blockNumber));
    }
    
    
}