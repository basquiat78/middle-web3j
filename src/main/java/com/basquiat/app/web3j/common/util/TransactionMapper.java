package com.basquiat.app.web3j.common.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;

/**
 * It's TransactionMapper using Map Struct
 * 
 * create by basquiat 2018.04.19
 *
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

	TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);
	
	@Mappings({
	      @Mapping(source="hash", target="transactionHash"),
	      @Mapping(source="blockHash", target="blockHash"),
	      @Mapping(source="blockNumber", target="blockNumber"),
	      @Mapping(source="from", target="fromAddress"),
	      @Mapping(source="to", target="toAddress"),
	      @Mapping(source="value", target="value"),
	      @Mapping(source="input", target="input"),
	      @Mapping(source="nonce", target="nonce"),
	      @Mapping(source="transactionIndex", target="transactionIndex"),
	      @Mapping(source="gasPrice", target="gasPrice"),
	      @Mapping(source="gas", target="gas"),
	      @Mapping(source="creates", target="creates"),
	      @Mapping(source="publicKey", target="publicKey"),
	      @Mapping(source="raw", target="raw"),
	      @Mapping(source="r", target="r"),
	      @Mapping(source="s", target="s"),
	      @Mapping(source="v", target="v")
    })
	TransactionHistoryVO mappingFrom(Transaction transaction);
	 
}
