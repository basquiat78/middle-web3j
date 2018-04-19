package com.basquiat.app.web3j.service.block.vo;

import java.util.List;

import org.web3j.protocol.core.methods.response.EthTransaction;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * BlockVO
 * 
 * create by basquiat 2018.04.19
 *
 */
@Setter
@Getter
public class BlockVO {

	public BlockVO() {}
	
	private String number;
    
    private String hash;
    
    private String parentHash;
    
    private String nonce;
    
    private String sha3Uncles;
    
    private String logsBloom;
    
    private String transactionsRoot;
    
    private String stateRoot;
    
    private String receiptsRoot;
    
    private String author;
    
    private String miner;
    
    private String mixHash;
    
    private String difficulty;
    
    private String totalDifficulty;
    
    private String extraData;
    
    private String size;
    
    private String gasLimit;
    
    private String gasUsed;
    
    private String timestamp;
    
    private List<EthTransaction> transactions;
    
}
