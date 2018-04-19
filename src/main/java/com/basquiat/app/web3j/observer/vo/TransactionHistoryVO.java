package com.basquiat.app.web3j.observer.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * TransactionHistoryVO
 * 
 * Observable을 통해서 트랜잭션이 발생할 때마다 database에 저장할 정보를 해당 객체에 담는다.
 * 
 * create by basquiat 2018.04.19
 *
 */
@Setter
@Getter
public class TransactionHistoryVO {

	/**
	 * transaction hash
	 */
	private String transactionHash;
	
	/**
	 * 해당 트랜잭션을 포함한 blockHash를 담는다.
	 */
	private String blockHash;
	
	/**
	 * blockNumber
	 */
	private String blockNumber; 
	
	/**
	 * fromAddress
	 */
	private String fromAddress;
	
	/**
	 * toAddress
	 */
	private String toAddress;
	
	/**
	 * contractAddress
	 */
	private String contractAddress;
	
	/** nonce */
    private String nonce;
    
    /** transactionIndex */
    private String transactionIndex;
    
    /** gasPrice */
    private String gasPrice;
    
    /** gas */
    private String gas;
    
    /** creates */
    private String creates;
    
    /** publicKey */
    private String publicKey;
    
    /** raw */
    private String raw;
    
    /** r */
    private String r;
    
    /** s */
    private String s;
    
    /** v */
    private int v;
	
	/**
	 * value from transaction.getValue();
	 */
	private String value;

	/**
	 * contractValue
	 * 토큰 전송일 경우에는 이 변수에 담는다.
	 */
	private String contractValue;
	
	/**
	 * ether transfer or contract
	 * NORMAL | CONTRACT
	 * 
	 */
	private String transactionType;
	
	/**
	 * transaction contranct input data 
	 */
	private String input;
	
	/**
	 * regDttm
	 */
	private Date regDttm;
	
	/**
	 * 해당 트랜잭션의 timeStampe 정보
	 */
	private String timeStamp;
	
}
