package com.includesys.sm.service.manager;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.manager.AdminDao;
import com.includesys.sm.dto.manager.Admin;

@Service
public class AdminServiceImpl implements AdminService
{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired 
	private AdminDao adminDao;
	
	@Override
	public Admin get(String admin_id) 
	{
		return adminDao.get(admin_id);
	}

	@Override
	public int getCount(Map<String, Object> map) 
	{
		return adminDao.getCount(map);
	}

	@Override
	public List<Admin> getList(Map<String, Object> map) 
	{
		return adminDao.getList(map);
	}

	@Override
	public void register(Admin admin) 
	{
		adminDao.register(admin);
	}

	@Override
	public void modify(Admin admin) 
	{
		adminDao.modify(admin);
	}

	@Override
	public void remove(String admin_id) 
	{
		adminDao.remove(admin_id);
	}

}