package com.includesys.sm.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.member.CodeScis;

@Repository
public class CodeScisDaoImpl implements CodeScisDao{
	
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public List<CodeScis> getCodeList(String up_code) {
		return sqlSession.selectList("CodeScis.getCodeList", up_code);
	}
	@Override
	public List<CodeScis> getCodeSubList(String up_code, String sub_code) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("up_code", up_code);
		map.put("sub_code", sub_code);
		
		return sqlSession.selectList("CodeScis.getCodeSubList", map);
	}
	@Override
	public List<CodeScis> getCodeSub2List(String up_code, String sub_code) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("up_code", up_code);
		map.put("sub_code", sub_code);
		
		return sqlSession.selectList("CodeScis.getCodeSub2List", map);
	}

	@Override
	public CodeScis getCode(String pk_code){
		return (CodeScis)sqlSession.selectOne("CodeScis.getCode", pk_code);
	}
}