package com.basquiat.app.web3j.service.account.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * AccountVO
 * 
 * create by basquiat 2018.04.19
 *
 */
@Setter
@Getter
public class AccountVO {

	/**
	 * 사용자 계정 아이디
	 */
	private String userId;
	
	/**
	 * 생성된 계정
	 */
	private String createdAddress;
	/**
	 * 이더리움 어카운트에 사용될 패스워드
	 * 리턴 정보에서는 패스워드 정보는 제외시킨다.
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
}
