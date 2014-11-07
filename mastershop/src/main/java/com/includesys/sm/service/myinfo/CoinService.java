package com.includesys.sm.service.myinfo;

import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.myinfo.Coin;
 

public interface CoinService {

	
	/**
	 * Myinfo 충전 내역 리스트
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
	 * Myinfo 충전 사용 리스트 카운트
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
	 * 코인 충전 내용과 로그 동시에 처리 하기 위한 메소드
	 * @param map
	 * @return
	 */
	public int insertCoinChargeProc(HashMap<String, Object> map);
	
	
	/**
	 * 코인 충전 내용과 로그 동시에 처리 하기 위한 메소드 + PG
	 * @param map
	 * @return
	 */
	public int insertCoinChargePgProc(HashMap<String, Object> map);
	
	
	/**
	 * 코인 충전 시 에러 로그 저장
	 * @param map
	 * @return
	 */
	public int insertCoinChargePgFailLog(HashMap<String, Object> map);
}
