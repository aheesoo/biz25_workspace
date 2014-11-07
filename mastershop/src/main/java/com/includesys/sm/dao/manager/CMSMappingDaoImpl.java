package com.includesys.sm.dao.manager;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.manager.CMSMapping;

@Repository
public class CMSMappingDaoImpl implements CMSMappingDao 
{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public CMSMapping get(CMSMapping CMSMapping) 
	{		
		return (CMSMapping)sqlSession.selectOne("CMSMapping.get", CMSMapping);
	}

	@Override
	public int getCount(CMSMapping CMSMapping) 
	{
		return (Integer)sqlSession.selectOne("CMSMapping.getCount", CMSMapping);
	}

	@Override
	public List<CMSMapping> getList(CMSMapping CMSMapping) 
	{
		return sqlSession.selectList("CMSMapping.getList", CMSMapping);
	}

	@Override
	public void register(CMSMapping CMSMapping) 
	{
		sqlSession.insert("CMSMapping.register", CMSMapping);
	}

	@Override
	public void modify(CMSMapping CMSMapping) 
	{
		sqlSession.update("CMSMapping.modify", CMSMapping);
	}

	@Override
	public void remove(CMSMapping CMSMapping) 
	{
		sqlSession.delete("CMSMapping.remove", CMSMapping);
	}

}

