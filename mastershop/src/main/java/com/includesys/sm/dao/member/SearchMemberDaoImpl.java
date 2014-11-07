package com.includesys.sm.dao.member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.member.SearchMember;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class SearchMemberDaoImpl implements SearchMemberDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int selectMemberInfoCount(HashMap<String, Object> hmap) {
		return Integer.parseInt(sqlSession.selectOne("SearchMember.selectMemberInfoCount", hmap).toString());
	}

	@Override
	public SearchMember selectMemberInfo(HashMap<String, Object> hmap) {
		return sqlSession.selectOne("SearchMember.selectMemberInfo", hmap);
	}

	@Override
	public int insertCreateSecurityKey(HashMap<String, Object> hmap) {
		return sqlSession.update("SearchMember.insertCreateSecurityKey", hmap);
	}

	@Override
	public SearchMember selectMemberInfoSecurityKey(HashMap<String, Object> hmap) {
		return sqlSession.selectOne("SearchMember.selectMemberInfoSecurityKey", hmap);
	}

	@Override
	public int updateMemberTempPassword(HashMap<String, Object> hmap) {		
		return sqlSession.update("SearchMember.updateMemberTempPassword", hmap);
	}

	@Override
	public int insertMemberSearchSmsGroup(HashMap<String, Object> hmap) {
		return sqlSession.update("SearchMember.insertMemberSearchSmsGroup", hmap);
	}

	@Override
	public int insertMemberSearchSmsReservation(HashMap<String, Object> hmap) {
		return sqlSession.update("SearchMember.insertMemberSearchSmsReservation", hmap);
	}
	
	
}
