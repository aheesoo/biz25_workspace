package com.includesys.sm.dao.myinfo;
import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.product.Product;

public interface CoinDao {
	
	
	/**
	 * 코인 내역
	 * @param map
	 * @return
	 */
	public Coin getCoin(HashMap<String, String> map);
	
	/**
	 * Myinfo 충전 내역 리스트
	 * @param map
	 * @return
	 */
	public List<Coin> getCoinChargeLogList(HashMap<String, Object> map);
	
	/**
	 * Myinfo 충전 내역 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getCoinChargeLogListCount(HashMap<String, Object> map);
	
	/**
	 * Myinfo 사용 내역 리스트
	 * @param map
	 * @return
	 */
	public List<Coin> getCoinUseLogList(HashMap<String, Object> map);
	
	/**
	 * Myinfo 사용 내역 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getCoinUseLogListCount(HashMap<String, Object> map);
	
	
	/**
	 * Myinfo 사용자 전화번호 리스트
	 * @param map
	 * @return
	 */
	public List<Coin> getUserTelList(HashMap<String, Object> map);
	
	/**
	 * 코인 충전
	 * @param map
	 * @return
	 */
	public int insertCoinCharge(HashMap<String, Object> map);
	
	/**
	 * 코인 충전 로그
	 * @param map
	 * @return
	 */
	public int insertCoinChargeLog(HashMap<String, Object> map);
	
	/**
	 * 코인 충전 PG 로그
	 * @param map
	 * @return
	 */
	public int insertCoinChargePgLog(HashMap<String, Object> map);
	
	
	/**
	 * 코인 충전 PG 실패 로그
	 * @param map
	 * @return
	 */
	public int insertCoinChargePgFailLog(HashMap<String, Object> map);
	
	
}
