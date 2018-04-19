package com.basquiat.app.web3j.observer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.common.vo.TransactionType;
import com.basquiat.app.web3j.couchbase.service.CouchbaseService;
import com.basquiat.app.web3j.service.account.AccountService;
import com.basquiat.app.web3j.service.database.DatabaseService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * TransactionObservable Service
 * 
 * create by basquiat 2018.04.19
 *
 */
@Service("transactionObservableService")
public class TransactionObservableService {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionObservableService.class);
	
	@Value("${eth.token.address}")
	private String ethTokenAddress;
	
	@Autowired
    private DatabaseService databaseService;
	
	@Autowired
    private AccountService accountService;
	
	@Autowired
    private CouchbaseService couchbaseService;
	
	public void excute(Transaction transaction) {
		try {
			// 발생하는 모든 트랜잭션에 대해서 로그를 찍는다.
			LOG.info("Transaction : " + BasquiatUtils.convertJsonStringFromObject(transaction));
			
			// 토큰 스마트 컨트랙트 주소로 넘어오는 경우에는 다르게 처리를 해야한다.
			if(ethTokenAddress.equals(transaction.getTo())) {
				try {
					// database에 저장을 한다.
					databaseService.insertContractTransaction(transaction);
					// 폴리그랏 저장소 설정
					// 조건에 맞는 모든 트랜잭션은 couchbase에 저장한다.
					couchbaseService.upsertBucketTransaction(transaction, TransactionType.CONTRACT.name());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// 스맠트 컨트랙트 주소가 아닌 다른 Address일 경우에는 해당 트랜잭션의 정보가 내부에 설치된 이더리움 노드에서 생성한 계정인지를 체크해야한다.
				// 이유는 메인넷 또는 테스트넷에서 생성되는 모든 트랜잭션중 우리의 관심사인 내부 노드에 생성된 어카운트의 대한 트랜잭션만 걸러내고 저장해야하기 때문이다.
				try {
					if(this.checkTransactionAccount(transaction)) {
						// database에 저장을 한다.
						databaseService.insertNormalTransaction(transaction);
						// 폴리그랏 저장소 설정
						// 조건에 맞는 모든 트랜잭션은 couchbase에 저장한다.
						couchbaseService.upsertBucketTransaction(transaction, TransactionType.NORMAL.name());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 이더리움 노드에 생성된 계정과 트랜잭션의 to, from을 체크한다.
	 * @param transaction
	 * @return boolean
	 */
	private boolean checkTransactionAccount(Transaction transaction) {
		List<String> list = accountService.getEthAccounts().getAccounts();
		return list.stream().anyMatch(s -> ( s.equals(transaction.getTo()) || s.equals(transaction.getFrom()) ) );
	}
	
}
