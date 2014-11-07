package com.includesys.sm.service.csCenter;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.csCenter.NoticeDao;
import com.includesys.sm.dto.csCenter.Notice;

@Service
public class NoticeServiceImpl  implements NoticeService{
	//service클래스의 역할 : 필요한 dao호출
	private static Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public List<Notice> getNoticeList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeList(map);
	}

	@Override
	public int getNoticeListCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeListCount(map);
	}

	@Override
	public Notice getNoticeVew(String seq) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeVew(seq);
	}

	@Override
	public Notice getNoticeVewPre(String seq) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeVewPre(seq);
	}

	@Override
	public Notice getNoticeVewNext(String seq) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeVewNext(seq);
	}

	@Override
	public void updateViewHit(String seq) {
		noticeDao.updateViewHit(seq);
	}
	 
} 
