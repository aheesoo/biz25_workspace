package com.includesys.sm.dao.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.smsManager.SmsManager;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class ReportDaoImpl implements ReportDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, Object>> getSendTelList(String member_id) {
		return sqlSession.selectList("Report.getSendTelList", member_id);	
	}
	
	@Override
	public int getMonthReportListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Report.getMonthReportListCount", map);	
	}
	
	@Override
	public List<SmsManager> getMonthReportList(Map<String, Object> map) {
		return sqlSession.selectList("Report.getMonthReportList", map);	
	}
	
	@Override
	public List<Map<String, Object>> getMonthReportGraph(Map<String, Object> map) {
		return sqlSession.selectList("Report.getMonthReportGraph", map);	
	}
	
	@Override
	public SmsManager getReportDetailGroup(Map<String, Object> map) {
		return sqlSession.selectOne("Report.getReportDetailGroup", map);	
	}
	
	@Override
	public List<SmsManager> getReportDetailList(Map<String, Object> map) {
		return sqlSession.selectList("Report.getReportDetailList", map);	
	}
}
