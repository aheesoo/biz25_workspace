package com.includesys.sm.service.smsManager;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.smsManager.EmoticonDao;
import com.includesys.sm.dto.smsManager.Emoticon;

@Service
public class EmoticonServiceImpl  implements EmoticonService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(EmoticonServiceImpl.class);
	
	@Autowired
	private EmoticonDao emoticonDao;	
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Emoticon> getUserSendMsgList(HashMap<String, Object> hmap) {		
		return emoticonDao.getUserSendMsgList(hmap);
	}


	@Override
	public int getUserSendMsgListCount(HashMap<String, Object> hmap) {
		return emoticonDao.getUserSendMsgListCount(hmap);
	}


	@Override
	public List<Emoticon> getRecommendContentList(HashMap<String, Object> hmap) {
		return emoticonDao.getRecommendContentList(hmap);
	}
	
	
	@Override
	public List<Emoticon> getRecommendMsgList(HashMap<String, Object> hmap) {
		return emoticonDao.getRecommendMsgList(hmap);
	}


	@Override
	public int getRecommendMsgListCount(HashMap<String, Object> hmap) {
		return emoticonDao.getRecommendMsgListCount(hmap);
	}
	 
} 
