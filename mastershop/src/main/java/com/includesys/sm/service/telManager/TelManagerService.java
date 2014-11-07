package com.includesys.sm.service.telManager;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.telManager.TelManager;

/**
 * @author
 *
 */
public interface TelManagerService {
	/**
	 * 리스트 join
	 * @param Map
	 * @return
	 */
	public List<TelManager> getListView(Map<String, Object> map);

	/**
	 * 리스트 카운트 join
	 * @param Map
	 * @return
	 */
	public int getListCount(Map<String, Object> map);
	
	/**
	 * 뷰
	 * @param Map
	 * @return
	 */
	public TelManager getView(Map<String, String> map);
	
	/**
	 * 뷰 카운트
	 * @param int
	 * @return
	 */
	public int getViewCount(TelManager telManager);
	
	/**
	 * 뷰 저장/수정
	 * @param telManager
	 * @return
	 */
	public void getViewModify(TelManager telManager);

	/**
	 * 차트리스트
	 * @param telManager
	 * @return
	 */
	public List<TelManager> getChartList(Map<String, String> map);
}
