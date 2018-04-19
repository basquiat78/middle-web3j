package com.basquiat.app.web3j.service.block;

import java.math.BigInteger;

import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthTransaction;

import com.basquiat.app.web3j.service.block.vo.ResultBlockVO;

/**
 * 
 * BlockService interface
 * 
 * create by basquiat 2018.04.19
 *
 */
public interface BlockService {

	public ResultBlockVO getBlockByHash(String blockHash, boolean returnFullTransactionObjects);
	public ResultBlockVO getBlockByNumber(BigInteger blockNumber, boolean returnFullTransactionObjects);

	public EthTransaction ethGetTransactionByHash(String transactionHash);
	
	public EthGetBlockTransactionCountByHash getBlockTxCountByHash(String blockHash);
	
	public EthGetBlockTransactionCountByNumber getBlockTxCountByNumber(BigInteger blockNumber);
	
}
