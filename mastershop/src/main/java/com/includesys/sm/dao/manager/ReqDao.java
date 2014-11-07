package com.includesys.sm.dao.manager;

import com.includesys.sm.dto.manager.Admin;

public interface ReqDao {

	public Admin get(Admin admin);
	
	public void register(Admin admin);
	
	public void modify(Admin admin);
	
	public void remove(Admin admin);
}
