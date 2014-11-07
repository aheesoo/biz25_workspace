package com.includesys.sm.service.common;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.common.CommonDao;
import com.includesys.sm.dto.common.Common;
import com.includesys.sm.dto.common.CallLog;
import com.includesys.sm.dto.common.Customer;

@Service
public class CommonServiceImpl  implements CommonService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private CommonDao commonDao;
		
	@Override
	public List<Common> getProductCodeList(Common common){
		return commonDao.getProductCodeList(common);
	}

	@Override
	public Common getProductCodeListView(String search_code) {
		return commonDao.getProductCodeListView(search_code);		
	}
	
	@Override
	public int callLogCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return commonDao.callLogCount(map);
	}

	@Override
	public void callLogReg(CallLog callLog) 	{
		commonDao.callLogReg(callLog);
	}

	@Override
	public void callLogUp(CallLog callLog) {
		// TODO Auto-generated method stub
		commonDao.callLogUp(callLog);
	}

	@Override
	public void customerIns(Customer customer){
		commonDao.customerIns(customer);
	}

	@Override
	public Customer customerGet(String pk_tel) {
		// TODO Auto-generated method stub
		return commonDao.customerGet(pk_tel);
	}

	@Override
	public List<CallLog> getListView(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commonDao.getListView(map);
	}

	@Override
	public CallLog getCustom(Map<String, Object> logCustomMap) {
		// TODO Auto-generated method stub
		return commonDao.getCustom(logCustomMap);
	}

} 
