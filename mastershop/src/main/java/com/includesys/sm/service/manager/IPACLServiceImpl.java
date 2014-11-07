package com.includesys.sm.service.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.manager.IPACLDao;

@Service
public class IPACLServiceImpl implements IPACLService
{
	@Autowired
	private IPACLDao ipACLDao;
	
	@Override
	public Map<String, Object> get(int pk_ip_acl)
	{
		return ipACLDao.get(pk_ip_acl);
	}

	@Override
	@Cacheable(value="ipACLGetCount", key="#fd_menu_yn")
	public int getCount(String fd_menu_yn)
	{
		return ipACLDao.getCount(fd_menu_yn);
	}

	@Override
	@Cacheable(value="ipACLGetList", key="#fd_menu_yn")
	public List<Map<String, Object>> getList(String fd_menu_yn)
	{
		return ipACLDao.getList(fd_menu_yn);
	}

	@Override
	@Caching(evict = { @CacheEvict(value="ipACLGet", allEntries=true), @CacheEvict(value="ipACLGetCount", allEntries=true), @CacheEvict(value="ipACLGetList", allEntries=true) })
	public void register(Map<String, Object> ipACL)
	{
		ipACLDao.register(ipACL);
	}

	@Override
	@Caching(evict = { @CacheEvict(value="ipACLGet", allEntries=true), @CacheEvict(value="ipACLGetList", allEntries=true) })
	public void modify(Map<String, Object> ipACL)
	{
		ipACLDao.modify(ipACL);
	}

	@Override
	@Caching(evict = { @CacheEvict(value="ipACLGet", key="#pk_ip_acl"), @CacheEvict(value="ipACLGetCount", allEntries=true), @CacheEvict(value="ipACLGetList", allEntries=true) })
	public void remvoe(int pk_ip_acl)
	{
		ipACLDao.remove(pk_ip_acl);
	}

}
