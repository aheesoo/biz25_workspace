package com.includesys.sm.dao.csManager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.csManager.CsCustomer;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class CsCustomerDaoImpl implements CsCustomerDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getCount(Map<String, Object> map) 	{
		return Integer.parseInt(sqlSession.selectOne("CsCustomer.getCount",map).toString());
	}

	@Override
	public List<CsCustomer> getList(Map<String, Object> map) 	{
		return sqlSession.selectList("CsCustomer.getList", map);
	}
	
	@Override
	public void register(CsCustomer csCustomer) 	{
		sqlSession.insert("CsCustomer.register", csCustomer);
	}

	@Override
	public CsCustomer get(Map<String, Object> map) 	{
		return (CsCustomer) sqlSession.selectOne("CsCustomer.get", map);
	}
	
	@Override
	public void modify(CsCustomer csCustomer) 	{
		sqlSession.update("CsCustomer.modify", csCustomer);
	}
	
	@Override
	public void remove(Map<String, Object> map) 	{
		sqlSession.delete("CsCustomer.remove", map);
	}
	
	@Override
	public String getLatelyCallDate(Map<String, Object> map) {
		return sqlSession.selectOne("CsCustomer.getLatelyCallDate", map);
	}
	
	@Override
	public CsCustomer getSmsSendCount(Map<String, Object> map) {
		return sqlSession.selectOne("CsCustomer.getSmsSendCount", map);
	}
	
	@Override
	public CsCustomer getWeekSmsReport(Map<String, Object> map) {
		return sqlSession.selectOne("CsCustomer.getWeekSmsReport", map);
	}
	
	@Override
	public CsCustomer getTimeSmsReport(Map<String, Object> map) {
		return sqlSession.selectOne("CsCustomer.getTimeSmsReport", map);
	}
	
	@Override
	public List<CsCustomer> getSmsSendDate(Map<String, Object> map) {
		return sqlSession.selectList("CsCustomer.getSmsSendDate", map);
	}
	
	@Override
	public CsCustomer getSmsSendDateDetail(Map<String, Object> map){
		return sqlSession.selectOne("CsCustomer.getSmsSendDateDetail", map);
	}

	@Override
	public int registerList(CsCustomer csCustomer) {
		// TODO Auto-generated method stub
		return sqlSession.insert("CsCustomer.registerList", csCustomer);
	}
}
