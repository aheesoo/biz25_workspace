package com.includesys.sm.dao.member;

public interface MembersDao {
	int insert(String data);
	int delete(String data);
	int update(String data);
	String select(String id);
}
