package com.basquiat.app.web3j.service.block;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.service.block.vo.BlockVO;
import com.basquiat.app.web3j.service.block.vo.ResultBlockVO;

import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthTransaction;

/**
 * 
 * BlockService
 * 
 * create by basquiat 2018.04.19
 *
 */
@Service("blockService")
public class BlockServiceImpl implements BlockService {

	@Autowired
	private Web3j web3j;
	
	@Override
	public ResultBlockVO getBlockByHash(String blockHash, boolean returnFullTransactionObjects) {
		EthBlock ethBlock = null;
		try {
			ethBlock = web3j.ethGetBlockByHash(blockHash, returnFullTransactionObjects).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return this.makeFromEthBlock(ethBlock);
	}

	@Override
	public ResultBlockVO getBlockByNumber(BigInteger blockNumber, boolean returnFullTransactionObjects) {
		EthBlock ethBlock = null;
		try {
			ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), returnFullTransactionObjects).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return this.makeFromEthBlock(ethBlock);
	}

	@Override
	public EthGetBlockTransactionCountByHash getBlockTxCountByHash(String blockHash) {
		EthGetBlockTransactionCountByHash ethGetBlockTransactionCountByHash = null;
		try {
			ethGetBlockTransactionCountByHash = web3j.ethGetBlockTransactionCountByHash(blockHash).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return ethGetBlockTransactionCountByHash;
	}

	@Override
	public EthGetBlockTransactionCountByNumber getBlockTxCountByNumber(BigInteger blockNumber) {
		EthGetBlockTransactionCountByNumber ethGetBlockTransactionCountByNumber = null;
		try {
			ethGetBlockTransactionCountByNumber = web3j.ethGetBlockTransactionCountByNumber(DefaultBlockParameter.valueOf(blockNumber)).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return ethGetBlockTransactionCountByNumber;
	}
	
	@Override
	public EthTransaction ethGetTransactionByHash(String transactionHash) {
		
		EthTransaction ethTransaction = null;
		
		try {
			ethTransaction = web3j.ethGetTransactionByHash(transactionHash).sendAsync().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return ethTransaction;
	}

	@SuppressWarnings("rawtypes")
	private ResultBlockVO makeFromEthBlock(EthBlock ethBlock) {
		ResultBlockVO resultBlockVO = new ResultBlockVO();
		resultBlockVO.setId(ethBlock.getId());
		resultBlockVO.setJsonRpc(ethBlock.getJsonrpc());
		
		// block in ethBlock
		Block block = ethBlock.getBlock();
		BlockVO blockVO = BasquiatUtils.convertBlockVO(block);

		List<EthTransaction> transactions = new ArrayList<>();
		// block 내부의 transactions의 transactionHash값을 가져온다. 
		List<TransactionResult> list=  block.getTransactions();
		for(TransactionResult tr : list ) {
			String transactionHash = tr.get().toString();
			EthTransaction et = this.ethGetTransactionByHash(transactionHash);
			transactions.add(et);
		}
		blockVO.setTransactions(transactions);
		resultBlockVO.setBlock(blockVO);
		return resultBlockVO;
	}
	
}
