package com.includesys.sm.dao.report;
import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.smsManager.SmsManager;


public interface ReportDao {
	
	/**
	 * 로그인한 사용자의 청약가입된 회선 전화번호 리스트
	 * @param member_id
	 * @return
	 */
	public List<Map<String, Object>> getSendTelList(String member_id);
	
	/**
	 * 월별 리포트 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getMonthReportListCount(Map<String, Object> map);
	
	/**
	 * 월별 리포트 리스트
	 * @param map
	 * @return
	 */
	public List<SmsManager> getMonthReportList(Map<String, Object> map);
	
	/**
	 * 월별 리포트 그래프 데이터
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getMonthReportGraph(Map<String, Object> map);
	
	/**
	 * 리포트 상세 그룹 정보
	 * @param map
	 * @return
	 */
	public SmsManager getReportDetailGroup(Map<String, Object> map);
	
	/**
	 * 리포트 상세 수신리스트
	 * @param map
	 * @return
	 */
	public List<SmsManager> getReportDetailList(Map<String, Object> map);

}
