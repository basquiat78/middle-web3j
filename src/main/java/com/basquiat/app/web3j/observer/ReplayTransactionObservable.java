package com.basquiat.app.web3j.observer;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;

import com.basquiat.app.web3j.observer.service.TransactionObservableService;
import com.basquiat.app.web3j.service.client.ClientInfo;
import com.basquiat.app.web3j.service.database.DatabaseService;

import rx.Subscription;

/**
 * When Server Start, web3j api transactionObservable spec regist EventListner
 * 
 * create by basquiat 2018.04.19
 *
 */
@Component
public class ReplayTransactionObservable {

	private static final Logger LOG = LoggerFactory.getLogger(ReplayTransactionObservable.class);
	
	@Autowired
	private Web3j web3j;
	
	@Autowired
	private TransactionObservableService transactionObservableService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@Autowired
	private ClientInfo clientInfo;
	
	/**
	 * 서버가 죽거나 혹시 모를 유실된 트랜잭션이 있는지 조회하고 db에 넣는 작업을 하는 로직
	 * 현재 코드는 휘발성코드로 서버가 구동되면 한번 실행하고 생성한 구독 채널을 해제한다.
	 * 
	 * 이 코드는 차후 별개의 어플리케이션으로 구동해야하기 때문에 @EventListener(ApplicationReadyEvent.class)
	 * 은 주석처리한다.
	 * @throws InterruptedException 
	 */
	//@EventListener(ApplicationReadyEvent.class)
	public void replayTransactionObservable() throws InterruptedException {
		
		LOG.info("Start Replay Transaction Obervable Process");
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		// 노드에서 최신 블록 넘버를 가져온다.
		BigInteger latestBlockNumber = clientInfo.getBlockNumber();
		// 서버가 죽은 시점에 DB에 저장된 최신 블록 넘버를 가져온다.
		String lastBlockNumber = databaseService.getLastBlockNumber();
		
		// lastBlockNumber 다음의 블록부터 조회를 해야하기 때문에 1을 더한다.
		Subscription subscription = web3j.replayTransactionsObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(Long.parseLong(lastBlockNumber)+1)), DefaultBlockParameter.valueOf(latestBlockNumber))
                .subscribe(
                        tx -> {
                        	transactionObservableService.excute(tx);
                        },
                        Throwable::printStackTrace,
                        countDownLatch::countDown);
		
		countDownLatch.await(5, TimeUnit.MINUTES);
        // 원하는 정보를 받았으면 해당 구독 채널을 해제한다.
		subscription.unsubscribe();
		
	}
	
}
