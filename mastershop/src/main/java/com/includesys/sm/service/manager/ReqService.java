package com.includesys.sm.service.manager;

import com.includesys.sm.dto.manager.Admin;

public interface ReqService {

	public Admin get(Admin admin);
	
	public void register(Admin admin);
	
	public void modify(Admin admin);
	
	public void remove(Admin admin);

}
