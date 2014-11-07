package com.includesys.sm.dao.manager;

import java.util.*;

public interface IPACLDao
{
	Map<String, Object> get(int pk_ip_acl);
	
	int getCount(String fd_menu_yn);
	
	List<Map<String, Object>> getList(String fd_menu_yn);
	
	void register(Map<String, Object> ipACL);
	
	void modify(Map<String, Object> ipACL);
	
	void remove(int pk_ip_acl);
}

