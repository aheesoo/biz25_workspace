package com.includesys.sm.dao.manager;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IPACLDaoImpl implements IPACLDao
{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Map<String, Object> get(int pk_ip_acl)
	{
		return (Map<String, Object>)sqlSession.selectOne("IPACL.get", pk_ip_acl);
	}

	@Override
	public int getCount(String fd_menu_yn)
	{
		return (Integer)sqlSession.selectOne("IPACL.getCount", fd_menu_yn);
	}

	@Override
	public List<Map<String, Object>> getList(String fd_menu_yn)
	{
		return sqlSession.selectList("IPACL.getList", fd_menu_yn);
	}

	@Override
	public void register(Map<String, Object> ipACL)
	{
		sqlSession.insert("IPACL.register", ipACL);
	}

	@Override
	public void modify(Map<String, Object> ipACL)
	{
		sqlSession.update("IPACL.modify", ipACL);
	}

	@Override
	public void remove(int pk_ip_acl)
	{
		sqlSession.delete("IPACL.remove", pk_ip_acl);
	}

}

