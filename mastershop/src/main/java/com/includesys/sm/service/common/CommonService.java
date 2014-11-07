package com.includesys.sm.service.common;

import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.common.Common;
import com.includesys.sm.dto.common.Customer;
import com.includesys.sm.dto.common.CallLog;


public interface CommonService {
	
	public List<Common> getProductCodeList(Common common);

	public Common getProductCodeListView(String search_code);

	//로그카운트
	public int callLogCount(Map<String,  String> map);

	//로그저장
	public void callLogReg(CallLog callLog);

	//로그업데이트
	public void callLogUp(CallLog callLog);

	public void customerIns(Customer customer);
	
	public Customer customerGet(String pk_tel);

	public List<CallLog> getListView(Map<String, Object> map);

	public CallLog getCustom(Map<String, Object> logCustomMap);

}
