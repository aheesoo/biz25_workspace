package com.includesys.sm.dao.smsManager;
import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.Emoticon;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.dto.smsManager.BoardEvent;

public interface SmsManagerDao {
	
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
	 * 문자발송 수정페이지 정보
	 * @param String
	 * @return
	 */
	public SmsManager getSmsManager(String pk_group_code);
	
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
	 * tbl_sms_group
	 * @param smsGroupMap
	 * @return
	 */
	public List<BoardEvent> getSmsGroupList(Map<String, String> smsGroupMap);
	
	public List<BoardEvent> getSmsReserveGroupList(Map<String, String> smsGroupMap);
	
	
	/**
	 * tbl_sms_group register
	 * @param SmsManager
	 */
	public int registerSmsGroup(SmsManager smsManager);
	
	/**
	 * tbl_sms_reservation register
	 * @param smsManager
	 * @return
	 */
	public int registerSmsReservation(SmsManager smsManager);
	
	/**
	 * 문자 전송 사용 코인 로그 인서트
	 * @param map
	 * @return
	 */
	public int registerCoinUseLog(Map<String, Object> map);
	
	/**
	 * tbl_sms_group modify
	 * @param String
	 */
	public int modifySmsGroup(SmsManager smsManager);
	
	/**
	 * tbl_sms_reservation modify
	 * @param String
	 * @return
	 */
	public int modifySmsReservation(SmsManager smsManager);
	
	/**
	 * 사용코인 갖고오기.
	 * @param smsManager
	 * @return
	 */
	public Coin getCoinUseLog(SmsManager smsManager);
	
	/**
	 * 수정된 사용코인로그 업데이트
	 * @param smsManager
	 * @return
	 */
	public int modifyCoinUseLog(Map<String, Object> map);
	
	/**
	 * 문자 전송 사용 코인 업데이트
	 * @param map
	 * @return
	 */
	public int modifyCoin(Map<String, Object> map);
	
	/**
	 * tbl_sms_group 해당 컬럼 발송 취소
	 * @param smsManager
	 * @return
	 */
	public int cancelationSmsGroup(SmsManager smsManager);
	
	/**
	 * tbl_sms_reservation 해당 컬럼 발송 취소
	 * @param smsManager
	 * @return
	 */
	public int cancelationSmsReservation(SmsManager smsManager);
	
	/**
	 * 취소된 코인사용이력 취소값 업데이트
	 * @param fk_group_code
	 * @return
	 */
	public int cancelationCoinLog(SmsManager smsManager);
	
	/**
	 * 취소된 코인 총합 구하기
	 * @param fk_member_id
	 * @return
	 */
	public Coin getSumCancelUseCoin(SmsManager smsManager);
	
	/**
	 * 취소된 코인 사용자코인으로 업데이트
	 * @param fk_member_id
	 * @return
	 */
	public int updateCancelCoin(SmsManager smsManager);
	
	/**
	 * tbl_sms_reservation 발송 취소 컬럼을 tbl_sms_log로 복사
	 * @param String fk_member_id
	 * @return
	 */
	public int copySmsReservation(String fk_member_id);
	
	/**
	 * tbl_sms_reservation 에서 tbl_sms_log로 복사된 컬럼을 삭제 
	 * @param String fk_member_id
	 * @return
	 */
	public int deleteSmsReservation(String fk_member_id);

}
