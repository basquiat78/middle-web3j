package com.basquiat.app.web3j.service.account;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;

import com.basquiat.app.config.exception.ServiceException;
import com.basquiat.app.web3j.common.util.BasquiatConstants;
import com.basquiat.app.web3j.common.util.BasquiatUtils;
import com.basquiat.app.web3j.couchbase.service.CouchbaseService;
import com.basquiat.app.web3j.service.account.vo.AccountVO;
import com.basquiat.app.web3j.service.database.DatabaseService;

/**
 * 
 * Account Service
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private Web3j web3j;
	
	@Autowired
    private CouchbaseService couchbaseService;
	
	@Autowired
	private DatabaseService databaseService;
	
	@Value("${eth.coinbase.path}")
    private String ETH_DIRECTORY;
	
	/**
	 * Account List
	 * @return String
	 */
	@Override
	public EthAccounts getEthAccounts() {
		EthAccounts ethAccounts = null;
		try {
			ethAccounts = web3j.ethAccounts().sendAsync().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ethAccounts;
	}

	@Override
	public AccountVO createAccount(AccountVO accountVO) {
		
		try {
			// 생성하기 전에 혹시 모를 같은 아이디의 중복 생성을 방지하기 위해 다음 로직을 먼저 체크한다.
			int checkIdx = databaseService.checkAccount(accountVO.getUserId());
			if(checkIdx > 0) {
				throw new ServiceException("해당 아이디는 이미 계정을 가지고 있습니다. 중복 생성을 할 수 없습니다.");
			}

			/**
			 * 일단은 생성된 주소만 리턴해서 보여준다.
			 */
			String walletFileName = WalletUtils.generateFullNewWalletFile(accountVO.getPassword(), new File(ETH_DIRECTORY));
			
			WalletFile walletFile = BasquiatUtils.makeWalletFile(walletFileName, ETH_DIRECTORY);

			String address = BasquiatConstants.Address_Prefix + walletFile.getAddress();
			
			// 생성된 주소를 파싱한다.
			accountVO.setCreatedAddress(address);
			
			// 생성된 walletFile의 json정보를 공유를 위해서 DB에 저장한다.
			// couchbase와 MySql에 폴리그랏으로 저장한다.
			//1. couchbase에 객체를 저장한다.
			couchbaseService.upsertBucketWalletFile(walletFileName, walletFile);
			
			// 2.1 생성한 계정 정보를 인서트 한다.
			databaseService.createAccount(accountVO);
			// 2.2 생성한 계정에 대한 백업 정보를 인서트한다.
			databaseService.backupWalletFile(accountVO.getUserId(), walletFileName, walletFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountVO;
	}

}
