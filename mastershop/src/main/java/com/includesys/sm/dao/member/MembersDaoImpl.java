package com.includesys.sm.dao.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//스프링에서는 AOP를 적용하려면 인터페이스를 만들고 이를 상속받아야 한다.
@Repository
public class MembersDaoImpl implements MembersDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(String data) {
		System.out.println(data+"를 추가했습니다.");
		return 1;
	}

	@Override
	public int delete(String data) {
		System.out.println(data+"를 삭제했습니다.");
		return 1;
	}

	@Override
	public int update(String data) {
		System.out.println(data+"를 수정했습니다.");
		return 1;
	}

	@Override
	public String select(String id) {
		System.out.println(id+"를 조회했습니다.");
		return id;
	}

}
