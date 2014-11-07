package com.includesys.sm.dao.csCenter;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.csCenter.Faq;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class FaqDaoImpl implements FaqDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * FAQ 리스트
	 */
	@Override
	public List<Faq> getFaqList(HashMap<String, Object> map) {
		return sqlSession.selectList("Faq.getFaqList", map);
	}

	/**
	 * FAQ 리스트 카운트 
	 */
	@Override
	public int getFaqListCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("Faq.getFaqListCount", map);	
	}

	/**
	 * 상세보기
	 */
	@Override
	public Faq getFaqVew(String seq) {	
		return sqlSession.selectOne("Faq.getFaqView", seq);	
	}

	/**
	 * 이전글
	 */
	@Override
	public Faq getFaqVewPre(String seq) {
		return sqlSession.selectOne("Faq.getFaqViewPre", seq);
	}

	/**
	 * 다음글
	 */
	@Override
	public Faq getFaqVewNext(String seq) {
		return sqlSession.selectOne("Faq.getFaqViewNext", seq);
	}

	/**
	 * Faq view Hit
	 */
	@Override
	public void updateViewHit(String seq) {
		//sqlSession.selectOne("Faq.updateViewHit", seq);	
		sqlSession.update("Faq.updateViewHit", seq);
	}

	@Override
	public List<Faq> getFaqCodeList(String fd_up_code) {
		return sqlSession.selectList("Faq.getFaqCodeList", fd_up_code);
	}	
	
	
	
}
