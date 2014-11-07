package com.includesys.sm.dao.smsManager;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.smsManager.Emoticon;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class EmoticonDaoImpl implements EmoticonDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Emoticon> getUserSendMsgList(HashMap<String, Object> hmap) {
		return sqlSession.selectList("Emoticon.getUserSendMsgList", hmap);	
	}

	@Override
	public int getUserSendMsgListCount(HashMap<String, Object> hmap) {
		return sqlSession.selectOne("Emoticon.getUserSendMsgListCount", hmap);
	}

	@Override
	public List<Emoticon> getRecommendContentList(HashMap<String, Object> hmap) {
		return sqlSession.selectList("Emoticon.getRecommendContentList", hmap);	
	}
	
	@Override
	public List<Emoticon> getRecommendMsgList(HashMap<String, Object> hmap) {
		return sqlSession.selectList("Emoticon.getRecommendMsgList", hmap);	
	}

	@Override
	public int getRecommendMsgListCount(HashMap<String, Object> hmap) {
		return sqlSession.selectOne("Emoticon.getRecommendMsgListCount", hmap);
	}
}
