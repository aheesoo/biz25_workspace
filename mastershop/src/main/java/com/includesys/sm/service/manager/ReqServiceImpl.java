package com.includesys.sm.service.manager;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.manager.ReqDao;
import com.includesys.sm.dto.manager.Admin;

@Service
public class ReqServiceImpl implements ReqService{

	private static Logger logger = LoggerFactory.getLogger(ReqServiceImpl.class);

	@Inject 
	private ReqDao reqDao;
	
	@Override
	public Admin get(Admin admin) 
	{
		return reqDao.get(admin);
	}

	@Override
	public void register(Admin admin) 
	{
		reqDao.register(admin);
	}

	@Override
	public void modify(Admin admin) 
	{
		reqDao.modify(admin);
	}

	@Override
	public void remove(Admin admin) 
	{
		reqDao.remove(admin);
	}

}
