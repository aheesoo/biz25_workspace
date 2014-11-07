package com.includesys.sm.dao.manager;

import java.util.*;
import com.includesys.sm.dto.manager.Admin;

public interface AdminDao {

	public Admin get(String admin_id);
	
	public int getCount(Map<String, Object> map);
	
	public List<Admin> getList(Map<String, Object> map);
	
	public void register(Admin admin);
	
	public void modify(Admin admin);
	
	public void remove(String admin_id);
}