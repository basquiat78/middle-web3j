package com.basquiat.app.web3j.service.block.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * ResultBlockVO
 * 
 * create by basquiat 2018.04.19
 *
 */
@Setter
@Getter
public class ResultBlockVO {
	
    private long id;
    
    private String jsonRpc;
	
	private BlockVO block;
    
}
