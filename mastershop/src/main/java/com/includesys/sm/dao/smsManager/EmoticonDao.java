package com.includesys.sm.dao.smsManager;
import java.util.HashMap;
import java.util.List;

import com.includesys.sm.dto.smsManager.Emoticon;

public interface EmoticonDao {
	/**
	 * 발송 문구 리스트 
	 * @param hmap
	 * @return
	 */
	public List<Emoticon> getUserSendMsgList(HashMap<String, Object> hmap);
		
	
	/**
	 * 발송 문구 리스트 카운트
	 * @param hmap
	 * @return
	 */
	public int getUserSendMsgListCount(HashMap<String, Object> hmap);
	
	/**
	 * 추천 문구 리스트 (문자 등록,수정에서 사용)
	 * @param hmap
	 * @return
	 */
	public List<Emoticon> getRecommendContentList(HashMap<String, Object> hmap);
	
	/**
	 * 추천 문구 리스트 (팝업)
	 * @param hmap
	 * @return
	 */
	public List<Emoticon> getRecommendMsgList(HashMap<String, Object> hmap);
	
	/**
	 * 추천 문구 리스트 카운트 (팝업)
	 * @param hmap
	 * @return
	 */
	public int getRecommendMsgListCount(HashMap<String, Object> hmap);
	
}
