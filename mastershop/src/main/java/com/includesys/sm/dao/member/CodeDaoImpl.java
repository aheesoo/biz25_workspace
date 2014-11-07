package com.includesys.sm.dao.member;

import java.util.List;
import com.includesys.sm.dto.member.Code;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDaoImpl implements CodeDao{
	
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public List<Code> getCodeList(Code fd_up_code) {
		return sqlSession.selectList("Code.getCodeList", fd_up_code);
	}

	@Override
	public Code getCode(Code pk_code){
		return (Code)sqlSession.selectOne("Code.getCode", pk_code);
	}
}