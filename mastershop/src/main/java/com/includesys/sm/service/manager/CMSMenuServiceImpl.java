package com.includesys.sm.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.includesys.sm.dao.manager.CMSMenuDao;
import com.includesys.sm.dto.manager.CMSMapping;
import com.includesys.sm.dto.manager.CMSMenu;

@Service
public class CMSMenuServiceImpl implements CMSMenuService 
{
	@Autowired
	private CMSMenuDao cmsMenuDao;
	
	@Autowired
	private CMSMappingService CMSMappingService;
	
	@Override
	public CMSMenu get(String pk_url_code)
	{
		CMSMenu cmsMenu = new CMSMenu();
		cmsMenu.setPk_url_code(pk_url_code);
		return get(cmsMenu);
	}
	
	@Override
	public CMSMenu get(CMSMenu cmsMenu) 
	{
		return cmsMenuDao.get(cmsMenu);
	}

	@Override
	public int getCount() 
	{
		return getCount("0000");
	}
	
	@Override
	public int getCount(String fd_group_code)
	{
		CMSMenu cmsMenu = new CMSMenu();
		cmsMenu.setFd_group_code(fd_group_code);
		return getCount(cmsMenu);
	}

	@Override
	public int getCount(CMSMenu cmsMenu) 
	{
		return cmsMenuDao.getCount(cmsMenu);
	}

	@Override
	public List<CMSMenu> getList() 
	{
		return getList("0000");
	}
	
	@Override
	public List<CMSMenu> getList(String fd_group_code) 
	{	
		CMSMenu cmsMenu = new CMSMenu();
		cmsMenu.setFd_group_code(fd_group_code);
		return getList(cmsMenu);
	}	
	
	@Override
	public List<CMSMenu> getList(CMSMenu cmsMenu) 
	{
		return cmsMenuDao.getList(cmsMenu);
	}
	
	@Override
	public List<CMSMenu> getSortList()
	{
		return cmsMenuDao.getSortList();
	}
	
	@Override
	public List<CMSMenu> getListByAdminID(String admin_id)
	{
		return cmsMenuDao.getListByAdminID(admin_id);
	}
	@Override
	public void updateSortNo(CMSMenu cmsMenu, boolean plus) throws Exception
	{
		boolean equalsSortNo = false;
		List<CMSMenu> cmsMenuList = this.getList(cmsMenu);
		
		for(CMSMenu menu : cmsMenuList) 
		{ 
			if(!cmsMenu.getPk_url_code().equals(menu.getPk_url_code()) && menu.getFd_sort_no() == cmsMenu.getFd_sort_no())
				equalsSortNo = true;
		}
		
		if((equalsSortNo && plus) || !plus)
		{
			for(CMSMenu menu : cmsMenuList)
			{		
				if(!cmsMenu.getPk_url_code().equals(menu.getPk_url_code()))
				{
					if(cmsMenu.getFd_sort_no() <= menu.getFd_sort_no())
					{
						int updateNum = (plus) ? menu.getFd_sort_no() + 1 : menu.getFd_sort_no() - 1;
						menu.setFd_sort_no(updateNum);						
						cmsMenuDao.modify(menu);
					}
				}
			}	
		}
	}
	@Override
	public void updateSortNo(CMSMenu cmsMenu, CMSMenu orgMenu) throws Exception
	{
		boolean equalsSortNo = false;
		List<CMSMenu> cmsMenuList = this.getList(cmsMenu);
		
		for(CMSMenu menu : cmsMenuList) 
		{ 
			if(!cmsMenu.getPk_url_code().equals(menu.getPk_url_code()) && menu.getFd_sort_no() == cmsMenu.getFd_sort_no())
				equalsSortNo = true;
		}		
		
		if(equalsSortNo)
		{
			if(cmsMenu.getFd_sort_no() > orgMenu.getFd_sort_no())
			{
				for(CMSMenu menu : cmsMenuList)
				{
					if(!cmsMenu.getPk_url_code().equals(menu.getPk_url_code()))
					{
						if(cmsMenu.getFd_sort_no() >= menu.getFd_sort_no() && orgMenu.getFd_sort_no() < menu.getFd_sort_no())
						{
							menu.setFd_sort_no(menu.getFd_sort_no() - 1);
							cmsMenuDao.modify(menu);
						}
					}
				}
			}
			else
			{
				for(CMSMenu menu : cmsMenuList)
				{
					if(!cmsMenu.getPk_url_code().equals(menu.getPk_url_code()))
					{
						if(cmsMenu.getFd_sort_no() <= menu.getFd_sort_no() && orgMenu.getFd_sort_no() > menu.getFd_sort_no())
						{
							menu.setFd_sort_no(menu.getFd_sort_no() + 1);
							cmsMenuDao.modify(menu);
						}
					}
				}				
			}			
		}
	}

	@Override
	@Transactional
	public void register(CMSMenu cmsMenu) throws Exception
	{
		if(this.get(cmsMenu) != null)
			throw new Exception("중복된 코드 입니다.");
				
		cmsMenuDao.register(cmsMenu);
		updateSortNo(cmsMenu, true);
	}

	@Override
	@Transactional
	public void modify(CMSMenu cmsMenu) throws Exception 
	{		
		CMSMenu orgMenu = this.get(cmsMenu.getOrg_pk_url_code());
			
		if(!orgMenu.getPk_url_code().equals(cmsMenu.getPk_url_code()))
		{		
			if(this.get(cmsMenu) != null)
				throw new Exception("중복된 코드 입니다.");
			
			List<CMSMapping> CMSMappingList = CMSMappingService.getList(orgMenu.getPk_url_code(), "");
			for(CMSMapping CMSMapping : CMSMappingList)
			{
				CMSMapping.setOrg_pk_url_code(CMSMapping.getPk_url_code());
				CMSMapping.setOrg_pk_admin_id(CMSMapping.getPk_admin_id());
				CMSMapping.setPk_url_code(cmsMenu.getPk_url_code());
				CMSMappingService.modify(CMSMapping);
			}
			
			if(cmsMenu.getFd_group_code().equals("0000"))
			{
				List<CMSMenu> childMenuList = this.getList(cmsMenu);
				for(CMSMenu menu : childMenuList)
				{
					menu.setFd_group_code(cmsMenu.getPk_url_code());
					cmsMenuDao.modify(menu);					
				}											
			}
		}
		
		cmsMenuDao.modify(cmsMenu);
		updateSortNo(cmsMenu, orgMenu);		
	}
	
	@Override
	public void remove(String pk_url_code) throws Exception 
	{
		CMSMenu cmsMenu = this.get(pk_url_code);
		remove(cmsMenu);
	}	

	@Override
	@Transactional
	public void remove(CMSMenu cmsMenu) throws Exception 
	{
		if(cmsMenu.getFd_group_code().equals("0000"))
		{
			List<CMSMenu> childMenuList = this.getList(cmsMenu.getPk_url_code());
			for(CMSMenu menu : childMenuList)
			{
				cmsMenuDao.remove(menu);
				CMSMappingService.remove(menu.getPk_url_code(), "");
			}
		}
		cmsMenuDao.remove(cmsMenu);
		CMSMappingService.remove(cmsMenu.getPk_url_code(), "");
		updateSortNo(cmsMenu, false);		
	}

}
