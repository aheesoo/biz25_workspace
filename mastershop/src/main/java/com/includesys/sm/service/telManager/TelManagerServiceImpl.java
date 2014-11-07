package com.includesys.sm.service.telManager;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.telManager.TelManagerDao;
import com.includesys.sm.dto.telManager.TelManager;

@Service
public class TelManagerServiceImpl  implements TelManagerService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(TelManagerServiceImpl.class);

	@Autowired
	private TelManagerDao telManagerDao;

	@Override
	public List<TelManager> getListView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return telManagerDao.getListView(map);
	}

	@Override
	public int getListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return telManagerDao.getListCount(map);
	}

	@Override
	public TelManager getView(Map<String, String> map) {
		// TODO Auto-generated method stub
		return telManagerDao.getView(map);
	}

	@Override
	public int getViewCount(TelManager telManager) {
		// TODO Auto-generated method stub
		return telManagerDao.getViewCount(telManager);
	}

	@Override
	public void getViewModify(TelManager telManager) {
		// TODO Auto-generated method stub
		telManagerDao.getViewModify(telManager);
	}

	@Override
	public List<TelManager> getChartList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return telManagerDao.getChartList(map);
	}

}
