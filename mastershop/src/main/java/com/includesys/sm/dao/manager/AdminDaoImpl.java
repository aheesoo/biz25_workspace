package com.includesys.sm.dao.manager;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.manager.Admin;

@Repository
public class AdminDaoImpl implements AdminDao 
{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public Admin get(String admin_id) 
	{
		return (Admin)sqlSession.selectOne("Admin.get", admin_id);
	}

	@Override
	public int getCount(Map<String, Object> map) 
	{
		return Integer.parseInt(sqlSession.selectOne("Admin.getCount", map).toString());
	}

	@Override
	public List<Admin> getList(Map<String, Object> map) 
	{
		return sqlSession.selectList("Admin.getList", map);
	}

	@Override
	public void register(Admin admin) 
	{
		sqlSession.insert("Admin.register", admin);
	}

	@Override
	public void modify(Admin admin) 
	{
		sqlSession.update("Admin.modify", admin);
	}

	@Override
	public void remove(String admin_id) 
	{
		sqlSession.delete("Admin.remove", admin_id);
	}

}