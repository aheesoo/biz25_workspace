package com.includesys.sm.service.csCenter;

import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.csCenter.Faq;
 

public interface FaqService {

	/**
	 * 공지사항 리스트
	 * @param map
	 * @return
	 */
	public List<Faq> getFaqList(HashMap<String, Object> map);
	
	/**
	 * 공지사항 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getFaqListCount(HashMap<String, Object> map);
	
	
	/**
	 * 공지사항 상세보기
	 * @param seq
	 * @return
	 */
	public Faq getFaqVew(String seq);
	
	/**
	 * 공지사항 이전글
	 * @param seq
	 * @return
	 */
	public Faq getFaqVewPre(String seq);
	
	/**
	 * 공지사항 다음글
	 * @param seq
	 * @return
	 */
	public Faq getFaqVewNext(String seq);
	
	
	/**
	 * 공지사항 뷰 카운트
	 * @param map
	 * @return
	 */
	public void updateViewHit(String seq);
	
	/**
	 * FAQ 분류 리스트
	 * @param map
	 * @return
	 */
	public List<Faq> getFaqCodeList(String fd_up_code);
	
}
