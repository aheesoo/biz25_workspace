package com.includesys.sm.service.smsManager;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.dto.smsManager.SmsManager;
 


public interface SmsManagerService {
	
	/**
	 * 발송완료 상세보기 사용자 전화번호 리스트
	 * @param member_id
	 * @return
	 */
	public List<SmsManager> getSmsCustomerTelList(Map<String, Object> map);
	
	/**
	 * 문자 발송된 고객들에게 수신받은 응답건수
	 * @param member_id
	 * @return
	 */
	public int getCallCount(String fk_group_code);
	
	/**
	 * 예약불가 날짜 체크
	 * @param member_id
	 * @return
	 */
	public String getUnvailDay(Map<String, Object> map);
	
	/**
	 * 발송 500건 이상 제한 유무를 가지고 온다.
	 * @param member_id
	 * @return
	 */
	public String getSendLimit(String pk_member_id);
	
	/**
	 * 상품이 가입된 전화번호 리스트를 가지고 온다.
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getProductTel(String member_id);
	
	/**
	 * 현재 사용자의 사용 가능한 코인을 가지고 온다.
	 * @param member_id
	 * @return
	 */
	public Coin getCoin(String member_id);
	
	/**
	 * 발송 대상 수
	 * @param map
	 * @return
	 */
	public int getCustomerCount(SmsManager smsManager);
	
	/**
	 * 발송 대상 전화번호 리스트
	 * @param map
	 * @return
	 */
	public List<SmsManager> getCustomerTelList(SmsManager smsManager);
	
	/**
	 * 문자 발송 수정 페이지 정보
	 * @param String
	 * @return
	 */
	public SmsManager getSmsManager(String pk_group_code);
	
	/**
	 * tbl_sms_group, tbl_sms_reservation register
	 * @param SmsManager
	 */
	public String registerSms(SmsManager smsManager) throws Exception;
	
	/**
	 * tbl_sms_group, tbl_sms_reservation modify
	 * @param String
	 */
	public String modifySms(SmsManager smsManager) throws Exception;

	/**
	 * tbl_board_event (달력)
	 * @param PageHelper
	 * @return int
	 */
	public int getCountCal(PageHelper pageHelper);

	/**
	 * tbl_board_event (달력)
	 * @param PageHelper
	 * @return boradEvent
	 */
	public List<BoardEvent> getListCal(Map<String, Object> map);

	/**
	 * tbl_member_tel (회원 전화번호 리스트)
	 * @param map
	 * @return
	 */
	public List<BoardEvent> getMemberTelList(Map<String, String> map);

	/**
	 * tbl_sms_group (리스트)
	 * @param smsGroupMap
	 * @return
	 */
	public List<BoardEvent> getSmsGroupList(Map<String, String> smsGroupMap);
	
	public List<BoardEvent> getSmsReserveGroupList(Map<String, String> smsGroupMap);
	
	
	/**
	 * tbl_sms_group, tbl_sms_reservation SMS 취소 및 tbl_coin_use_log 의 사용로그 취소값 업데이트
	 * @param smsManager
	 * @return
	 */
	public String cancelationSms(SmsManager smsManager);
	
	/**
	 * tbl_sms_reservation 에서 취소된 SMS를 Log로 옮김
	 * @param smsManager
	 * @return
	 */
	public String gotoLog(String fk_member_id);
	
	
}
