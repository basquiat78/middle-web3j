package com.basquiat.app.web3j.couchbase.service;

import static com.couchbase.client.java.query.Select.select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.core.methods.response.Transaction;

import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.common.util.TransactionMapper;
import com.basquiat.app.web3j.observer.vo.TransactionHistoryVO;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
/**
 * 
 * couchBase Service
 * 
 * create by basquiat 2018.04.19
 *
 */
@Service("couchbaseService")
public class CouchbaseServiceImpl implements CouchbaseService {
	
	@Autowired
	private Bucket bucket;

    @Value("${couchbase.bucket.name}")
    private String bucketName;
	
	/**
	 * 페이징시 10개씩 출력한다.
	 */
	private final int perPAGE = 10;
	
	/**
	 * 모든 트랜잭션을 가져온다.
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TransactionHistoryVO> findAll(int page) throws Exception {
		
		if(page == 0) {
			page = 1;
		}
		
		List<TransactionHistoryVO> resultList = new ArrayList<>();
		
		int offset = perPAGE * (page-1);
		
		N1qlQueryResult query = bucket.query(select("meta().id, *").from(bucketName).where("blockHash IS NOT NULL").limit(perPAGE).offset(offset));
		Iterator<N1qlQueryRow> result = query.iterator();
		while(result.hasNext()) {
			N1qlQueryRow nqr = result.next();
			resultList.add(BasquiatUtils.convertObjectFromJsonString(nqr.value().get(bucketName).toString(), TransactionHistoryVO.class));
		}
		
		return resultList;
	}
	
	/**
	 * 트랜잭션 정보를 upsert 시킨다.
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void upsertBucketTransaction(Transaction transaction, String transactionType) throws Exception {
		TransactionHistoryVO thVO = TransactionMapper.mapper.mappingFrom(transaction);
		thVO.setTransactionType(transactionType);
		JsonObject jsonObject = JsonObject.fromJson(BasquiatUtils.convertJsonStringFromObject(thVO));
        bucket.upsert(JsonDocument.create(transaction.getHash(), jsonObject));
	}
	
	/**
	 * WalletFile 정보를 upsert 시킨다.
	 * @param transaction
	 * @throws Exception
	 */
	@Override
	public void upsertBucketWalletFile(String fileName, WalletFile walletFile) throws Exception {
		JsonObject jsonObject = JsonObject.fromJson(BasquiatUtils.convertJsonStringFromObject(walletFile));
        bucket.upsert(JsonDocument.create(fileName, jsonObject));
	}
	
	/**
	 * transaction hash를 통해 트랜잭션 정보를 가져온다.
	 * @param transactionHash
	 * @return
	 * @throws Exception
	 */
	@Override
	public TransactionHistoryVO findByTransactionHash(String transactionHash) throws Exception {
		return BasquiatUtils.convertObjectFromJsonString(bucket.get(transactionHash).content().toString(), TransactionHistoryVO.class);
	}
	
}
