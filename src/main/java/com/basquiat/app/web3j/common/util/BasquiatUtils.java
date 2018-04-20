package com.basquiat.app.web3j.common.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.basquiat.app.web3j.observer.vo.ContractDataVO;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.block.vo.BlockVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * BasquiatUtils
 * 
 * create by basquiat 2018.04.19
 *
 */
public class BasquiatUtils {

	/**
	 * make url
	 * @param rpcProtocol
	 * @param rpcHost
	 * @param rpcPort
	 * @return
	 */
	public static String makeUrl(String rpcProtocol, String rpcHost, String rpcPort) {
		StringBuffer sb = new StringBuffer();
		sb.append(rpcProtocol)
		  .append(BasquiatConstants.PROTOCOL_SEPARATOR)
		  .append(rpcHost)
		  .append(BasquiatConstants.PORT_SEPARATOR)
		  .append(rpcPort);
		return sb.toString();
	}

	/**
	 * Object convert to json String
	 * 
	 * @param object
	 * @return String
	 * @throws JsonProcessingException
	 */
	public static String convertJsonStringFromObject(Object object) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(object);
	}
	
	/**
	 * json String convert Object
	 * 
	 * @param content
	 * @param clazz
	 * @return T
	 * @throws Exception
	 */
	public static <T> T convertObjectFromJsonString(String content, Class<T> clazz) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		T object = (T) mapper.readValue(content, clazz);
		
		return object;
	}
	
	/**
	 * Using Map Struct
	 * convert to BlockVO from ethBlock
	 * @param block
	 * @return
	 */
	public static BlockVO convertBlockVO(Block block) {
		BlockVO blockVO = BlockMapper.mapper.mappingFrom(block);
		return blockVO;
	}
	
	/**
	 * using Map Struct
	 * convert to TransactionHistoryVO from eth transaction
	 * @param transaction
	 * @return
	 */
	public static TransactionHistoryVO convertTransactionHistoryVO(Transaction transaction) {
		TransactionHistoryVO transactionHistoryVO = TransactionMapper.mapper.mappingFrom(transaction);
		return transactionHistoryVO;
	}
	
	/**
	 * cut byte String
	 * @param value
	 * @param startIdx
	 * @param length
	 * @return
	 */
	public static String cutByByteUnit(String value, int startIdx, int length) {
		byte[] resouceByte = null;
		byte[] targetByte = null;
		try {
			if(value== null) {
				return "";
			}
			resouceByte = value.getBytes();
			targetByte = new byte[length];
			if(length> (resouceByte.length - startIdx)) {
				length = resouceByte.length - startIdx;
			}
			System.arraycopy(resouceByte, startIdx, targetByte, 0, length);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new String(targetByte);
		
	}
	
	/**
	 * inputdata로부터 toAddress와 value를 추출한다.
	 * @param inputData
	 * @return
	 */
	public static ContractDataVO makeFromInputData(String inputData) {
		ContractDataVO resultVO = new ContractDataVO();
		String address = BasquiatUtils.cutByByteUnit(inputData, 34, 40);
		String toAddress = BasquiatConstants.Address_Prefix + address;
		resultVO.setToAddress(toAddress);
		String[] array = inputData.split(address);
		String value = new BigInteger(array[1], 16).toString();
		resultVO.setValue(value);
		return resultVO;
	}

	/**
	 * 파일명과 directory로 이더리움 WalletFile 객체를 생성한다.
	 * @param fileName
	 * @param destinationDirectory
	 * @return WalletFile
	 * @throws Exception
	 */
	public static WalletFile makeWalletFile(String fileName, String destinationDirectory) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		File destination = new File(destinationDirectory, fileName);
		WalletFile walletFile = mapper.readValue(destination, WalletFile.class);
		return walletFile;
	}
	
	/**
	 * Wei 단위로 저장된 value값을 실제 이더 단위로 변환한다.
	 * @param amount
	 * @return BigDecimal
	 */
	public static BigDecimal convertToEtherFromWei(BigInteger amount) {
		return Convert.fromWei(amount.toString(), Unit.ETHER);
	}
	
	/**
	 * 실제 토큰 발란스로 변환한다.
	 * @param amount
	 * @return BigDecimal
	 */
	public static BigDecimal convertToRealTokenValue(BigInteger amount) {
		BigDecimal devideValue = new BigDecimal(100000000);
		return new BigDecimal(amount).divide(devideValue);
	}
	
}
