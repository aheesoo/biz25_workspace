package com.includesys.sm.service.csCenter;

import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.csCenter.Notice;
 

public interface NoticeService {

	/**
	 * 공지사항 리스트
	 * @param map
	 * @return
	 */
	public List<Notice> getNoticeList(HashMap<String, Object> map);
	
	/**
	 * 공지사항 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getNoticeListCount(HashMap<String, Object> map);
	
	
	/**
	 * 공지사항 상세보기
	 * @param seq
	 * @return
	 */
	public Notice getNoticeVew(String seq);
	
	/**
	 * 공지사항 이전글
	 * @param seq
	 * @return
	 */
	public Notice getNoticeVewPre(String seq);
	
	/**
	 * 공지사항 다음글
	 * @param seq
	 * @return
	 */
	public Notice getNoticeVewNext(String seq);
	
	
	/**
	 * 공지사항 뷰 카운트
	 * @param map
	 * @return
	 */
	public void updateViewHit(String seq);
}
