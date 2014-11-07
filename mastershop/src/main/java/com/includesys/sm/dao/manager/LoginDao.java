package com.includesys.sm.dao.manager;

import java.util.HashMap;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.member.Member;

@Repository
public interface LoginDao {

	public Member isMember(HashMap<String, Object> map);
	
}
