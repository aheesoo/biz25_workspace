package com.includesys.sm.dao.telManager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.dto.telManager.TelManager;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class TelManagerImpl implements TelManagerDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<TelManager> getListView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("TelManager.getListView", map);
	}

	@Override
	public int getListCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return Integer.parseInt(sqlSession.selectOne("TelManager.getListCount", map).toString());
	}

	@Override
	public TelManager getView(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (TelManager) sqlSession.selectOne("TelManager.getView",map);
	}

	@Override
	public int getViewCount(TelManager telManager) {
		// TODO Auto-generated method stub
		return Integer.parseInt(sqlSession.selectOne("TelManager.getViewCount",telManager).toString());
	}

	@Override
	public void getViewModify(TelManager telManager) {
		// TODO Auto-generated method stub
		sqlSession.insert("TelManager.getViewModify",telManager);
	}

	@Override
	public List<TelManager> getChartList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("TelManager.getChartList",map);
	}
}