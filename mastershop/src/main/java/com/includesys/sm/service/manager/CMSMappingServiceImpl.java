package com.includesys.sm.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.manager.CMSMappingDao;
import com.includesys.sm.dto.manager.CMSMapping;

@Service
public class CMSMappingServiceImpl implements CMSMappingService 
{
	@Autowired
	private CMSMappingDao CMSMappingDao;
	
	@Override
	public CMSMapping get(String pk_url_code, String pk_admin_id) throws Exception
	{
		CMSMapping cmsMapping = new CMSMapping();
		cmsMapping.setPk_url_code(pk_url_code);
		cmsMapping.setPk_admin_id(pk_admin_id);

		return get(cmsMapping); 
	}

	@Override
	public CMSMapping get(CMSMapping cmsMapping) throws Exception 
	{
		if(cmsMapping.getPk_url_code().equals("") && cmsMapping.getPk_admin_id().equals(""))
		{
			throw new Exception("cms 메뉴pk 와 관리자 id 모두 필요합니다."); 
		}
		
		return CMSMappingDao.get(cmsMapping);
	}

	@Override
	public int getCount(String pk_url_code, String pk_admin_id) throws Exception 
	{
		CMSMapping cmsMapping = new CMSMapping();
		cmsMapping.setPk_url_code(pk_url_code);
		cmsMapping.setPk_admin_id(pk_admin_id);
		
		return getCount(cmsMapping);
	}

	@Override
	public int getCount(CMSMapping cmsMapping) throws Exception
	{
		if((cmsMapping.getPk_url_code().equals("") && cmsMapping.getPk_admin_id().equals("")) || 
				(!cmsMapping.getPk_url_code().equals("") && !cmsMapping.getPk_admin_id().equals("")))
		{
			throw new Exception("cms 메뉴pk 또는 관리자 id 중 하나만 필요합니다."); 
		}
		
		return CMSMappingDao.getCount(cmsMapping);
	}
	
	@Override
	public List<CMSMapping> getList(String pk_url_code, String pk_admin_id) throws Exception 
	{
		CMSMapping cmsMapping = new CMSMapping();
		cmsMapping.setPk_url_code(pk_url_code);
		cmsMapping.setPk_admin_id(pk_admin_id);	
		
		return getList(cmsMapping);
	}	

	@Override
	public List<CMSMapping> getList(CMSMapping cmsMapping) throws Exception
	{
		if((cmsMapping.getPk_url_code().equals("") && cmsMapping.getPk_admin_id().equals("")) 
				|| (!cmsMapping.getPk_url_code().equals("") && !cmsMapping.getPk_admin_id().equals("")))
		{
			throw new Exception("cms 메뉴pk 또는 관리자 id 중 하나만 필요합니다."); 
		}
		
		return CMSMappingDao.getList(cmsMapping);
	}

	@Override
	public void register(CMSMapping cmsMapping) 
	{
		CMSMappingDao.register(cmsMapping);
	}

	@Override
	public void modify(CMSMapping cmsMapping) 
	{
		CMSMappingDao.modify(cmsMapping);
	}

	@Override
	public void remove(String pk_url_code, String pk_admin_id) 
	{
		CMSMapping cmsMapping = new CMSMapping();
		cmsMapping.setPk_url_code(pk_url_code);
		cmsMapping.setPk_admin_id(pk_admin_id);
		
		remove(cmsMapping);
	}

	@Override
	public void remove(CMSMapping cmsMapping) 
	{
		CMSMappingDao.remove(cmsMapping);
	}

}
