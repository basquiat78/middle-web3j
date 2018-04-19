package com.basquiat.app.web3j.service.database;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.common.util.TransactionMapper;
import com.basquiat.app.web3j.common.vo.TransactionType;
import com.basquiat.app.web3j.observer.vo.ContractDataVO;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.account.vo.AccountVO;
import com.basquiat.app.web3j.service.block.BlockService;
import com.basquiat.app.web3j.service.block.vo.ResultBlockVO;
import com.basquiat.app.web3j.service.database.mapper.DatabaseMapper;

/**
 * 
 * Database Service
 * 
 * create by basquiat 2018.04.19
 *
 */
@Service("databaseService")
public class DatabaseServiceImpl implements DatabaseService {
	
	@Autowired
	private BlockService blockService;
	
	@Autowired
	private DatabaseMapper databaseMapper;
	
	/**
	 * 일반적인 이더 전송
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void insertNormalTransaction(Transaction transaction) throws Exception {
		TransactionHistoryVO transactionHistoryVO = TransactionMapper.mapper.mappingFrom(transaction);
		transactionHistoryVO.setTransactionType(TransactionType.NORMAL.name());
		ResultBlockVO resultBlockVO = blockService.getBlockByNumber(new BigInteger(transactionHistoryVO.getBlockNumber()), true);
		transactionHistoryVO.setTimeStamp(resultBlockVO.getBlock().getTimestamp());
		databaseMapper.insertTransaction(transactionHistoryVO);
	}
	
	/**
	 * 스마트 컨트랙트 트랜잭션 정보 저장
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void insertContractTransaction(Transaction transaction) throws Exception {
		TransactionHistoryVO transactionHistoryVO = TransactionMapper.mapper.mappingFrom(transaction);
		transactionHistoryVO.setTransactionType(TransactionType.CONTRACT.name());
		ResultBlockVO resultBlockVO = blockService.getBlockByNumber(new BigInteger(transactionHistoryVO.getBlockNumber()), true);
		transactionHistoryVO.setTimeStamp(resultBlockVO.getBlock().getTimestamp());
		transactionHistoryVO.setContractAddress(transaction.getTo());
		// contract주소로 이더를 보냈을 경우에는 inputData는 0x, value는  이더가 된다.
		// 이 때는 toAddress가 contractAddress와 동일하다.
		// 하지만 만일 이 값이 0x가 아니라면 inputdata로부터 실질적으로 넘어가는 toAddress와 토큰 vaule를 추출해서 VO에 담아야 한다.
		if(!"0x".equals(transactionHistoryVO.getInput())) {
			ContractDataVO cdVO = BasquiatUtils.makeFromInputData(transactionHistoryVO.getInput());
			transactionHistoryVO.setToAddress(cdVO.getToAddress());
			transactionHistoryVO.setContractValue(cdVO.getValue());
		}
		databaseMapper.insertTransaction(transactionHistoryVO);
	}
	
	/**
	 * 유저 아이디로 트랜잭션 정보 가져오기
	 * @param UserId
	 * @return
	 */
	@Override
	public List<TransactionHistoryVO> selectTransactionHistoryByUserId(String userId) {
		return databaseMapper.selectTransactionHistoryByUserId(userId);
	}
	
	/**
	 * 이더리움 계정으로 트랜잭션 정보 가져오기
	 * @param address
	 * @return
	 */
	@Override
	public List<TransactionHistoryVO> selectTransactionHistoryByAddress(String address) {
		return databaseMapper.selectTransactionHistoryByAddress(address);
	}
	
	/**
	 * DB에 저장된 마지막 블록 넘버를 가져온다
	 * @return String
	 */
	@Override
	public String getLastBlockNumber() {
		return databaseMapper.getLastBlockNumber();
	}

	/**
	 * account 정보를 인서트한다.
	 */
	@Override
	public void createAccount(AccountVO accountVO) {
		databaseMapper.createAccount(accountVO);
	}

	/**
	 * userId로 생성된 계정이 있는지 체크한
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public int checkAccount(String userId) throws Exception {
		int check = databaseMapper.checkAccount(userId);
		return check;
	}

	@Override
	public void backupWalletFile(String userId, String walletFileName, WalletFile walletFile) throws Exception {
		databaseMapper.backupWalletFile(userId, walletFileName, BasquiatUtils.convertJsonStringFromObject(walletFile));
	}
	
}
