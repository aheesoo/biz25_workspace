package com.includesys.sm.dao.manager;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.manager.Admin;

@Repository
public class ReqDaoImpl implements ReqDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Admin get(Admin admin) 
	{
		return (Admin)sqlSession.selectOne("Admin.get", admin);
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
	public void remove(Admin admin) 
	{
		sqlSession.delete("Admin.remove", admin);
	}

}
