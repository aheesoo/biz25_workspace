package com.includesys.sm.dao.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.common.Common;
import com.includesys.sm.dto.common.CallLog;
import com.includesys.sm.dto.common.Customer;
/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class CommonDaoImpl implements CommonDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Common> getProductCodeList(Common common){		
		return sqlSession.selectList("Common.getProductCodeList", common);	
	}

	@Override
	public Common getProductCodeListView(String search_code) {
		return sqlSession.selectOne("Common.getProductCodeListView", search_code);	
	
	}

	//로그 카운트
	@Override
	public int callLogCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return Integer.parseInt(sqlSession.selectOne("CallLog.getCount", map).toString());
	}

	//로그 저장
	@Override
	public void callLogReg(CallLog callLog) 	{
		sqlSession.insert("CallLog.register", callLog);
	}

	//로그 업데이트
	@Override
	public void callLogUp(CallLog callLog) {
		// TODO Auto-generated method stub
		sqlSession.update("CallLog.modify", callLog);
	}

	//로그&사용자정보 리스트
	@Override
	public List<CallLog> getListView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CallLog.getListView", map);
	}

	//주소록저장
	@Override
	public void customerIns(Customer customer){
		sqlSession.insert("Customer.register", customer);
	}

	//주소록 가져오기
	@Override
	public Customer customerGet(String pk_tel) {
		// TODO Auto-generated method stub
		return (Customer) sqlSession.selectOne("Customer.get", pk_tel);
	}

	@Override
	public CallLog getCustom(Map<String, Object> logCustomMap) {
		// TODO Auto-generated method stub
		return (CallLog) sqlSession.selectOne("CallLog.getCustom",logCustomMap);
	}
}
