package com.includesys.sm.dao.telManager;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.telManager.TelManager;

public interface TelManagerDao {

	/**
	 * @param args
	 */
	public List<TelManager> getListView(Map<String, Object> map);

	public int getListCount(Map<String, Object> map);

	public TelManager getView(Map<String, String> map);

	public int getViewCount(TelManager telManager);

	public void getViewModify(TelManager telManager);

	public List<TelManager> getChartList(Map<String, String> map);

}