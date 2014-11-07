package com.includesys.sm.service.report;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.report.ReportDao;
import com.includesys.sm.dto.smsManager.SmsManager;

@Service
public class ReportServiceImpl  implements ReportService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, Object>> getSendTelList(String member_id) {
		return reportDao.getSendTelList(member_id);
	}
	
	@Override
	public int getMonthReportListCount(Map<String, Object> map) {
		return reportDao.getMonthReportListCount(map);
	}
	
	@Override
	public List<SmsManager> getMonthReportList(Map<String, Object> map) {
		return reportDao.getMonthReportList(map);
	}
	
	@Override
	public List<Map<String, Object>> getMonthReportGraph(Map<String, Object> map) {
		return reportDao.getMonthReportGraph(map);
	}
	
	@Override
	public SmsManager getReportDetailGroup(Map<String, Object> map) {
		return reportDao.getReportDetailGroup(map);
	}
	
	@Override
	public List<SmsManager> getReportDetailList(Map<String, Object> map) {
		return reportDao.getReportDetailList(map);
	}
} 
