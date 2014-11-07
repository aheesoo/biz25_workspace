package com.includesys.sm.service.myinfo;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.includesys.sm.dao.myinfo.CoinDao;
import com.includesys.sm.dto.myinfo.Coin;

@Service
public class CoinServiceImpl  implements CoinService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);
	
	@Autowired
	private CoinDao coinDao;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Override
	public Coin getCoin(HashMap<String, String> map){
		return coinDao.getCoin(map);
	}
	
	@Override
	public List<Coin> getCoinChargeLogList(HashMap<String, Object> map) {		
		return coinDao.getCoinChargeLogList(map);
	}

	@Override
	public int getCoinChargeLogListCount(HashMap<String, Object> map) {
		return coinDao.getCoinChargeLogListCount(map);
	}

	@Override
	public List<Coin> getCoinUseLogList(HashMap<String, Object> map) {
		return coinDao.getCoinUseLogList(map);
	}

	@Override
	public int getCoinUseLogListCount(HashMap<String, Object> map) {
		return coinDao.getCoinUseLogListCount(map);
	}

	@Override
	public List<Coin> getUserTelList(HashMap<String, Object> map) {
		return coinDao.getUserTelList(map);
	}

	@Override
	public int insertCoinCharge(HashMap<String, Object> map) {
		return coinDao.insertCoinCharge(map);
	}

	@Override
	public int insertCoinChargeLog(HashMap<String, Object> map) {
		return coinDao.insertCoinChargeLog(map);
	}

	@Transactional
	@Override
	public int insertCoinChargeProc(HashMap<String, Object> map) {
		int result = 0;
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		def.setName("registerSms-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			result = coinDao.insertCoinCharge(map);
			
			if(result>0){
				result = -1;
				try{
					result = coinDao.insertCoinChargeLog(map);
				}catch(Exception e){
					result = -1;
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			result = -1;
			e.printStackTrace();
		}
		
		
		if(result>0){
			transactionManager.commit(status);
		}else{			
			transactionManager.rollback(status);
		}
		return result;
	}

	@Transactional
	@Override
	public int insertCoinChargePgProc(HashMap<String, Object> map) {
		int result = 0;
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		
		def.setName("registerSms-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try{
			result = coinDao.insertCoinCharge(map);
			
			if(result>0){
				result = -1;
				try{
					result = coinDao.insertCoinChargeLog(map);
					
					if(result>0){
						result = -1;
						try{
							result = coinDao.insertCoinChargePgLog(map);
						}catch(Exception e){
							result = -1;
							e.printStackTrace();
						}
					}
				}catch(Exception e){
					result = -1;
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			result = -1;
			e.printStackTrace();
		}
		
		
		if(result>0){
			transactionManager.commit(status);
		}else{			
			transactionManager.rollback(status);
		}
		return result;
	}

	@Override
	public int insertCoinChargePgFailLog(HashMap<String, Object> map) {
		return coinDao.insertCoinChargePgFailLog(map);
	}

	
	 
} 
