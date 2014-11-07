package com.includesys.sm.dao.csCenter;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.csCenter.Notice;

/*
 * spring이 SqlSession의 라이프싸이클을 관리하고, 트랜잭션을 관리한다.
 */
@Repository
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 공지사항 리스트
	 */
	@Override
	public List<Notice> getNoticeList(HashMap<String, Object> map) {
		return sqlSession.selectList("Notice.getNoticeList", map);
	}

	/**
	 * 공지사항 리스트 카운트 
	 */
	@Override
	public int getNoticeListCount(HashMap<String, Object> map) {
		return sqlSession.selectOne("Notice.getNoticeListCount", map);	
	}

	/**
	 * 상세보기
	 */
	@Override
	public Notice getNoticeVew(String seq) {	
		return sqlSession.selectOne("Notice.getNoticeView", seq);	
	}

	/**
	 * 이전글
	 */
	@Override
	public Notice getNoticeVewPre(String seq) {
		return sqlSession.selectOne("Notice.getNoticeViewPre", seq);
	}

	/**
	 * 다음글
	 */
	@Override
	public Notice getNoticeVewNext(String seq) {
		return sqlSession.selectOne("Notice.getNoticeViewNext", seq);
	}

	/**
	 * 
	 */
	@Override
	public void updateViewHit(String seq) {
		//sqlSession.selectOne("Notice.updateViewHit", seq);	
		sqlSession.update("Notice.updateViewHit", seq);	
	}	
	
	
	
}
