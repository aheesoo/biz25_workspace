package com.includesys.sm.dao.common;
import java.util.List;
import java.util.Map;

import com.includesys.sm.dto.common.Common;
import com.includesys.sm.dto.common.CallLog;
import com.includesys.sm.dto.common.Customer;


public interface CommonDao {
	
	
	public List<Common> getProductCodeList(Common common);
	public Common getProductCodeListView(String search_code);
	
	public int callLogCount(Map<String, String> map);
	
	public void callLogReg(CallLog callLog);

	public void callLogUp(CallLog callLog);

	public void customerIns(Customer customer);

	public Customer customerGet(String pk_tel);

	public List<CallLog> getListView(Map<String, Object> map);
	
	public CallLog getCustom(Map<String, Object> logCustomMap);
}
