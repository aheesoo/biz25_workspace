package com.includesys.sm.service.csManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.csManager.CsCustomer;

public interface CsCustomerService {
	
	public int getCount(Map<String, Object> map);
	
	public List<CsCustomer> getList(Map<String, Object> map);
	
	public void register(CsCustomer csCustomer);

	public CsCustomer get(Map<String, Object> map);

	public void modify(CsCustomer csCustomer);
	
	public void remove(Map<String, Object> map);
	
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
	 * 회원별 사용자 저장
	 * @param customerList
	 * @return
	 */
	public int registerList(ArrayList<CsCustomer> customerList);
	
}
