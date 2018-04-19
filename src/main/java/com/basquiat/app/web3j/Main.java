package com.basquiat.app.web3j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.common.util.TransactionMapper;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.fasterxml.jackson.core.JsonProcessingException;

import rx.Observable;
import rx.Subscription;

public class Main {

	private static final int COUNT = 1;
	
	private static Logger log = LoggerFactory.getLogger(Main.class);
	
	private final Web3j web3j;

    public Main() {
        web3j = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
    }
	
	private void run() throws Exception {
		test2();
		/*
		simpleFilterExample();
		blockInfoExample();
		countingEtherExample();
		clientVersionExample();
		*/
		System.exit(0);  // we explicitly call the exit to clean up our ScheduledThreadPoolExecutor used by web3j
	}
	
	void test() throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		web3j.transactionObservable().subscribe(tx -> {
				System.out.println("blockNumber------------------------------> " + tx.getBlockNumber());
				System.out.println("value------------------------------> " + Convert.fromWei(new BigDecimal(tx.getValue()), Convert.Unit.ETHER));
			}, Throwable::printStackTrace
			,countDownLatch::countDown
		);
	
		try {
			Thread.sleep(TimeUnit.MINUTES.toMillis(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void test2() throws Exception {
		CountDownLatch countDownLatch = new CountDownLatch(1);
        Web3j web3j = Web3j.build(new HttpService());
        // 구독 채널을 생성하고 startBlockNumber에서 endBlockNumber까지의 생성된 트랜잭션 정보를 구독한다.
        Subscription sub = web3j.replayTransactionsObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(300000)), DefaultBlockParameter.valueOf(BigInteger.valueOf(301849)))
                .subscribe(
                        tx -> {
                        	TransactionHistoryVO transactionHistoryVO = TransactionMapper.mapper.mappingFrom(tx);
                        	System.out.println("======================== tx ========================");
                        	try {
								System.out.println(BasquiatUtils.convertJsonStringFromObject(transactionHistoryVO));
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
                        	System.out.println("=====================================================");
                        },
                        Throwable::printStackTrace,
                        countDownLatch::countDown);
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
        // 원하는 정보를 받았으면 해당 구독 채널을 해제한다.
        sub.unsubscribe();
	}
	
    void simpleFilterExample() throws Exception {

        Subscription subscription = web3j.blockObservable(false).subscribe(block -> {
            log.info("Sweet, block number " + block.getBlock().getNumber()
                    + " has just been created");
        }, Throwable::printStackTrace);

        TimeUnit.MINUTES.sleep(2);
        subscription.unsubscribe();
    }

    void blockInfoExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);

        log.info("Waiting for " + COUNT + " transactions...");
        Subscription subscription = web3j.blockObservable(true)
                .take(COUNT)
                .subscribe(ethBlock -> {
                    EthBlock.Block block = ethBlock.getBlock();
                    LocalDateTime timestamp = Instant.ofEpochSecond(
                            block.getTimestamp()
                                    .longValueExact()).atZone(ZoneId.of("UTC")).toLocalDateTime();
                    int transactionCount = block.getTransactions().size();
                    String hash = block.getHash();
                    String parentHash = block.getParentHash();

                    log.info(
                            timestamp + " " +
                                    "Tx count: " + transactionCount + ", " +
                                    "Hash: " + hash + ", " +
                                    "Parent hash: " + parentHash
                    );
                    countDownLatch.countDown();
                }, Throwable::printStackTrace);

        countDownLatch.await(10, TimeUnit.MINUTES);
        subscription.unsubscribe();
    }

    void countingEtherExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        log.info("Waiting for " + COUNT + " transactions...");
        Observable<BigInteger> transactionValue = web3j.transactionObservable()
                .take(COUNT)
                .map(Transaction::getValue)
                .reduce(BigInteger.ZERO, BigInteger::add);

        Subscription subscription = transactionValue.subscribe(total -> {
            BigDecimal value = new BigDecimal(total);
            log.info("Transaction value: " +
                    Convert.fromWei(value, Convert.Unit.ETHER) + " Ether (" +  value + " Wei)");
            countDownLatch.countDown();
        }, Throwable::printStackTrace);

        countDownLatch.await(10, TimeUnit.MINUTES);
        subscription.unsubscribe();
    }

    void clientVersionExample() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Subscription subscription = web3j.web3ClientVersion().observable().subscribe(x -> {
            log.info("Client is running version: {}", x.getWeb3ClientVersion());
            countDownLatch.countDown();
        });

        countDownLatch.await();
        subscription.unsubscribe();
    }
    
	public static void main(String[] args) throws Exception {
		new Main().run();
	}

}
