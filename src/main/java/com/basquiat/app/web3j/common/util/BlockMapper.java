package com.basquiat.app.web3j.common.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.web3j.protocol.core.methods.response.EthBlock.Block;

import com.basquiat.app.web3j.service.block.vo.BlockVO;

/**
 * It's BlockMapper using Map Struct
 * 
 * create by basquiat 2018.04.19
 *
 */
@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlockMapper {

	BlockMapper mapper = Mappers.getMapper(BlockMapper.class);
	
	@Mappings({
	      @Mapping(source="number", target="number"),
	      @Mapping(source="hash", target="hash"),
	      @Mapping(source="parentHash", target="parentHash"),
	      @Mapping(source="nonce", target="nonce"),
	      @Mapping(source="sha3Uncles", target="sha3Uncles"),
	      @Mapping(source="logsBloom", target="logsBloom"),
	      @Mapping(source="transactionsRoot", target="transactionsRoot"),
	      @Mapping(source="stateRoot", target="stateRoot"),
	      @Mapping(source="receiptsRoot", target="receiptsRoot"),
	      @Mapping(source="author", target="author"),
	      @Mapping(source="miner", target="miner"),
	      @Mapping(source="mixHash", target="mixHash"),
	      @Mapping(source="difficulty", target="difficulty"),
	      @Mapping(source="totalDifficulty", target="totalDifficulty"),
	      @Mapping(source="extraData", target="extraData"),
	      @Mapping(source="size", target="size"),
	      @Mapping(source="gasLimit", target="gasLimit"),
	      @Mapping(source="gasUsed", target="gasUsed"),
	      @Mapping(source="timestamp", target="timestamp")
    })
	BlockVO mappingFrom(Block block);
	 
}
