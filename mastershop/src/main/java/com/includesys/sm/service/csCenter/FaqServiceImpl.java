package com.includesys.sm.service.csCenter;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.csCenter.FaqDao;
import com.includesys.sm.dto.csCenter.Faq;

@Service
public class FaqServiceImpl  implements FaqService{ 
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(FaqServiceImpl.class);
	
	@Autowired
	private FaqDao faqDao;

	@Override
	public List<Faq> getFaqList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return faqDao.getFaqList(map);
	}

	@Override
	public int getFaqListCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return faqDao.getFaqListCount(map);
	}

	@Override
	public Faq getFaqVew(String seq) {
		// TODO Auto-generated method stub
		return faqDao.getFaqVew(seq);
	}

	@Override
	public Faq getFaqVewPre(String seq) {
		// TODO Auto-generated method stub
		return faqDao.getFaqVewPre(seq);
	}

	@Override
	public Faq getFaqVewNext(String seq) {
		// TODO Auto-generated method stub
		return faqDao.getFaqVewNext(seq);
	}

	@Override
	public void updateViewHit(String seq) {
		faqDao.updateViewHit(seq);
	}

	@Override
	public List<Faq> getFaqCodeList(String fd_up_code) {
		// TODO Auto-generated method stub
		return faqDao.getFaqCodeList(fd_up_code);
	}
	 
} 
