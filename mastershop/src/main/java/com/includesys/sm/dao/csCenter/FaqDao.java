package com.includesys.sm.dao.csCenter;
import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.csCenter.Faq;

public interface FaqDao {
	
	/**
	 * FAQ 리스트
	 * @param map
	 * @return
	 */
	public List<Faq> getFaqList(HashMap<String, Object> map);
	
	/**
	 * FAQ 리스트 카운트
	 * @param map
	 * @return
	 */
	public int getFaqListCount(HashMap<String, Object> map);
	
	/**
	 * FAQ 상세보기
	 * @param seq
	 * @return
	 */
	public Faq getFaqVew(String seq);
	
	/**
	 * FAQ 이전글
	 * @param seq
	 * @return
	 */
	public Faq getFaqVewPre(String seq);
	
	/**
	 * FAQ 다음글
	 * @param seq
	 * @return
	 */
	public Faq getFaqVewNext(String seq);
	
	
	/**
	 * FAQ 뷰 카운트
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
