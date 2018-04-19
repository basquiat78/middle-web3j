package com.basquiat.app.web3j.observer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

import com.basquiat.app.web3j.observer.service.TransactionObservableService;

/**
 * When Server Start, web3j api transactionObservable spec regist EventListner
 * 
 * create by basquiat 2018.04.19
 *
 */
@Component
public class EthTransactionObservable {

	private static final Logger LOG = LoggerFactory.getLogger(EthTransactionObservable.class);
	
	@Autowired
	private Web3j web3j;
	
	@Autowired
	private TransactionObservableService transactionObservableService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void transactionObservable() {
		LOG.info("Start Eth Transaction Obervable Process");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		web3j.transactionObservable().subscribe(tx -> {
				transactionObservableService.excute(tx);
			}, Throwable::printStackTrace // will adjuest custom exception handler
			,countDownLatch::countDown
		);
	
	}
}
