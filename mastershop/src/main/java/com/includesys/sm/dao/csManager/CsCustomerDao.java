package com.includesys.sm.dao.csManager;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.csManager.CsCustomer;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
public interface CsCustomerDao {

	public int getCount(Map<String, Object> map);
	
	public List<CsCustomer> getList(Map<String, Object> map);
	
	public void register(CsCustomer member);
	
	public CsCustomer get(Map<String, Object> map);

	public void modify(CsCustomer csCustomer);
	
	public void remove(Map<String, Object> map);
	
	//
	/**
	 * 최근 통화 일시
	 * @param map
	 * @return
	 */
	public String getLatelyCallDate(Map<String, Object> map);
	
	/**
	 * 문자발송건수
	 * @param map
	 * @return
	 */
	public CsCustomer getSmsSendCount(Map<String, Object> map);
	
	/**
	 * 요일별 평균 통화 건수
	 * @param map
	 * @return
	 */
	public CsCustomer getWeekSmsReport(Map<String, Object> map);
	
	/**
	 * 시간대별 평균 통화 건수
	 * @param map
	 * @return
	 */
	public CsCustomer getTimeSmsReport(Map<String, Object> map);
	
	/**
	 * 문구발송내역
	 * @param map
	 * @return
	 */
	public List<CsCustomer> getSmsSendDate(Map<String, Object> map);
	
	/**
	 * 문구발송내역 상세
	 * @param map
	 * @return
	 */
	public CsCustomer getSmsSendDateDetail(Map<String, Object> map);

	/**
	 * 대량등록
	 * @param item
	 * @return
	 */
	public int registerList(CsCustomer item);

}
