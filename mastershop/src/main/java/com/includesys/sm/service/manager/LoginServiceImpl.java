package com.includesys.sm.service.manager;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.manager.LoginDao;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.service.manager.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public boolean isMember(String id, String pwd){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", pwd);
		Member dto = loginDao.isMember(map);
		if(dto == null){
			return false;
		}else{
			return true;
		}
	}

}
