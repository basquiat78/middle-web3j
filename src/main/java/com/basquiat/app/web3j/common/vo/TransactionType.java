package com.basquiat.app.web3j.common.vo;

/**
 * Transaction Type
 * 트랜잭션이 발생했을 때 해당 트랜잭션이 이더 전송시 발생한 트랜잭션인지 또는 스마트 컨트랙트 트랙잭션인지 구분할 필요가 있다.
 * normal은 일반적으로 이더 전송시의 트랜잭션을 타입일 지정한다.
 * 
 * create by basquiat 2018.04.19
 *
 */
public enum TransactionType {

	NORMAL(0),
	
	CONTRACT(1);
	
	private int value;
	
	TransactionType(int sValue) {
		value = sValue;
	}
	
	public int getValue() {
		return value;
	}
	
}
