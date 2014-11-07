package com.includesys.sm.dao.myinfo;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.product.Product;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class CoinDaoImpl implements CoinDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Coin getCoin(HashMap<String, String> map){
		return sqlSession.selectOne("Coin.getCoin", map);	
	}
	
	
	/**
	 * 
	 */
	@Override
	public List<Coin> getCoinChargeLogList(HashMap<String, Object> map) {
		return sqlSession.selectList("Coin.getCoinChargeLogList", map);	
	}

	/**
	 * 
	 */
	@Override
	public int getCoinChargeLogListCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("Coin.getCoinChargeLogListCount", map);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.includesys.sm.dao.myinfo.CoinDao#getCoinUseLogList(java.util.HashMap)
	 */
	@Override
	public List<Coin> getCoinUseLogList(HashMap<String, Object> map) {		
		return sqlSession.selectList("Coin.getCoinUseLogList", map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.includesys.sm.dao.myinfo.CoinDao#getCoinUseLogListCount(java.util.HashMap)
	 */
	@Override
	public int getCoinUseLogListCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("Coin.getCoinUseLogListCount", map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.includesys.sm.dao.myinfo.CoinDao#getUserTelList(java.util.HashMap)
	 */
	@Override
	public List<Coin> getUserTelList(HashMap<String, Object> map) {
		return sqlSession.selectList("Coin.getUserTelList", map);
	}


	@Override
	public int insertCoinCharge(HashMap<String, Object> map) {
		return sqlSession.insert("Coin.insertCoinCharge", map);
	}


	@Override
	public int insertCoinChargeLog(HashMap<String, Object> map) {
		return sqlSession.insert("Coin.insertCoinChargeLog", map);
	}


	@Override
	public int insertCoinChargePgLog(HashMap<String, Object> map) {
		return sqlSession.insert("Coin.insertCoinChargePgLog", map);
	}


	@Override
	public int insertCoinChargePgFailLog(HashMap<String, Object> map) {
		return sqlSession.insert("Coin.insertCoinChargePgFailLog", map);
	}
	
}
