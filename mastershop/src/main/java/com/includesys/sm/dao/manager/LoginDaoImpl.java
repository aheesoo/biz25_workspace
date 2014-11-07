package com.includesys.sm.dao.manager;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.member.Member;

@Repository
public class LoginDaoImpl implements LoginDao{

	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public Member isMember(HashMap<String, Object> map){
		return sqlSession.selectOne("test.mybatis.LoginMapper.isMember", map);
	}
}
