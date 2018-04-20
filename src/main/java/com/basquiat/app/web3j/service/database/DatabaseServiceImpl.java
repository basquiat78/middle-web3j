package com.basquiat.app.web3j.service.database;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.config.exception.ServiceException;
import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.common.vo.TransactionType;
import com.basquiat.app.web3j.observer.vo.ContractDataVO;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.basquiat.app.web3j.service.account.AccountService;
import com.basquiat.app.web3j.service.account.vo.AccountVO;
import com.basquiat.app.web3j.service.block.BlockService;
import com.basquiat.app.web3j.service.block.vo.ResultBlockVO;
import com.basquiat.app.web3j.service.database.mapper.DatabaseMapper;
import com.basquiat.app.web3j.service.database.vo.DatabaseBalanceVO;

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
	private AccountService accountService;
	
	@Autowired
	private DatabaseMapper databaseMapper;
	
	/**
	 * 일반적인 이더 전송
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void insertNormalTransaction(Transaction transaction) throws Exception {
		TransactionHistoryVO transactionHistoryVO = BasquiatUtils.convertTransactionHistoryVO(transaction);
		transactionHistoryVO.setTransactionType(TransactionType.NORMAL.name());
		ResultBlockVO resultBlockVO = blockService.getBlockByNumber(new BigInteger(transactionHistoryVO.getBlockNumber()), true);
		transactionHistoryVO.setTimeStamp(resultBlockVO.getBlock().getTimestamp());
		databaseMapper.insertTransaction(transactionHistoryVO);
		// 해당 계정에 대한 입출금 밸런스를 업데이트한다.
		this.updateBalance(transactionHistoryVO);
	}
	
	/**
	 * 스마트 컨트랙트 트랜잭션 정보 저장
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void insertContractTransaction(Transaction transaction) throws Exception {
		TransactionHistoryVO transactionHistoryVO = BasquiatUtils.convertTransactionHistoryVO(transaction);
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
		// 해당 계정에 대한 입출금 밸런스를 업데이트한다.
		this.updateBalance(transactionHistoryVO);
	}
	
	/**
	 * 유저 아이디로 트랜잭션 정보 가져오기
	 * @param UserId
	 * @return List<TransactionHistoryVO>
	 */
	@Override
	public List<TransactionHistoryVO> selectTransactionHistoryByUserId(String userId) {
		return databaseMapper.selectTransactionHistoryByUserId(userId);
	}
	
	/**
	 * 이더리움 계정으로 트랜잭션 정보 가져오기
	 * @param address
	 * @return List<TransactionHistoryVO>
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
	 * @param accountVO
	 * @throws Exception 
	 */
	@Override
	public void createAccount(AccountVO accountVO) throws Exception {
		// 계정 정보 인서트
		databaseMapper.createAccount(accountVO);
		// 계정 밸런스 초기화 설정
		this.initializeBalance(accountVO);
	}

	/**
	 * userId로 생성된 계정이 있는지 체크한
	 * @param userId
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int checkAccount(String userId) throws Exception {
		int check = databaseMapper.checkAccount(userId);
		return check;
	}

	/**
	 * 신규 계정이 생성되면 해당 walletFile을 db에 백업한다.
 	 * @param userId
 	 * @param walletFileName
 	 * @param walletFile
 	 * @throws Exception
	 */
	@Override
	public void backupWalletFile(String userId, String walletFileName, WalletFile walletFile) throws Exception {
		databaseMapper.backupWalletFile(userId, walletFileName, BasquiatUtils.convertJsonStringFromObject(walletFile));
	}

	/**
	 * 계정 생성시에 계정에 대한 밸런스 초기화 정보를 인서트한다.
	 * @throws Exception
	 */
	@Override
	public void initializeBalance(AccountVO accountVO) throws Exception {
		DatabaseBalanceVO databaseBalanceVO = new DatabaseBalanceVO();
		databaseBalanceVO.setUserId(accountVO.getUserId());
		databaseBalanceVO.setAccountAddress(accountVO.getCreatedAddress());
		databaseBalanceVO.setEthBalance(BigInteger.ZERO);
		databaseBalanceVO.setTokenBalance(BigInteger.ZERO);
		databaseMapper.initializeBalance(databaseBalanceVO);
	}

	/**
	 * userId로 해당 계정의 발란스를 가져온다. 
  	 * @param userId
 	 * @throws Exception
	 */
	@Override
	public DatabaseBalanceVO selectBalanceByUserId(String userId) {
		DatabaseBalanceVO databaseBalanceVO = databaseMapper.selectBalanceByUserId(userId);
		if(databaseBalanceVO == null) {
			throw new ServiceException("해당 유저 아이디 정보가 존재하지 않습니다.");
		}
		return databaseBalanceVO;
	}

	/**
	 * address로 해당 계정의 발란스를 가져온다. 
  	 * @param userId
 	 * @throws Exception
	 */
	@Override
	public DatabaseBalanceVO selectBalanceByAddress(String address) {
		DatabaseBalanceVO databaseBalanceVO = databaseMapper.selectBalanceByAddress(address);
		if(databaseBalanceVO == null) {
			throw new ServiceException("해당 계정 정보가 존재하지 않습니다.");
		}
		return databaseBalanceVO;
	}

	/**
	 * 
	 * fromAddress에 대한 부분은 주석처리한다.
	 * 이것은 ICO를 진행할때는 생성된 계정으로 들어온 이더 또는 토큰을 하드 월렛에 옮길 수 있기 때문에
	 * ICO에 참여한 클라이언트에게 실제 자신이 보낸 이더에 대한 잔액을 보여주기 위해서는 이 부분을 주석처리한다.
	 * 
	 * @param transactionHistoryVO
	 */
	@Override
	public void updateBalance(TransactionHistoryVO transactionHistoryVO) {
		// 이더리움일 경우
		if(TransactionType.NORMAL.name().equals(transactionHistoryVO.getTransactionType())) {
			
			// transactionHistoryVO의 fromAddress가 해당 이더리움 노드에 있는 계정이라면 해당 보낸 값만큼 빼서 balance를 업데이트 한다.
			if(isExist(transactionHistoryVO.getFromAddress())) {
//				DatabaseBalanceVO vo = new DatabaseBalanceVO();
//				vo.setAccountAddress(transactionHistoryVO.getFromAddress());
//				vo.setEthBalance(new BigInteger(transactionHistoryVO.getValue()).negate());
//				vo.setType(transactionHistoryVO.getTransactionType());
//				databaseMapper.updateBalance(vo);		
			}
			// transactionHistoryVO의 toAddress가 해당 이더리움 노드에 있는 계정이라면 해당 보낸 값만큼 더해서 balance를 업데이트 한다.
			if(isExist(transactionHistoryVO.getToAddress())) {
				DatabaseBalanceVO vo = new DatabaseBalanceVO();
				vo.setAccountAddress(transactionHistoryVO.getToAddress());
				vo.setEthBalance(new BigInteger(transactionHistoryVO.getValue()));
				vo.setType(transactionHistoryVO.getTransactionType());
				databaseMapper.updateBalance(vo);		
			}
			
		// 토큰일 경우에는	
		} else {
			// transactionHistoryVO의 fromAddress가 해당 이더리움 노드에 있는 계정이라면 해당 보낸 값만큼 빼서 balance를 업데이트 한다.
			if(isExist(transactionHistoryVO.getFromAddress())) {
//				DatabaseBalanceVO vo = new DatabaseBalanceVO();
//				vo.setAccountAddress(transactionHistoryVO.getFromAddress());
//				vo.setTokenBalance(new BigInteger(transactionHistoryVO.getContractValue()).negate());
//				vo.setType(transactionHistoryVO.getTransactionType());
//				databaseMapper.updateBalance(vo);		
			}
			// transactionHistoryVO의 toAddress가 해당 이더리움 노드에 있는 계정이라면 해당 보낸 값만큼 더해서 balance를 업데이트 한다.
			if(isExist(transactionHistoryVO.getToAddress())) {
				DatabaseBalanceVO vo = new DatabaseBalanceVO();
				vo.setAccountAddress(transactionHistoryVO.getToAddress());
				vo.setTokenBalance(new BigInteger(transactionHistoryVO.getContractValue()));
				vo.setType(transactionHistoryVO.getTransactionType());
				databaseMapper.updateBalance(vo);		
			}
		}
		
	}
	
	/**
	 * 해당 계정이 노드에 존재하는지 체크하는 로직
	 * @param address
	 * @return boolean
	 */
	private boolean isExist(String address) {
		List<String> list = accountService.getEthAccounts().getAccounts();
		return list.stream().anyMatch(s -> s.equals(address) );
	}
	
}
