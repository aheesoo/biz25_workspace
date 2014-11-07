package com.includesys.sm.service.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.member.CodeScisDao;
import com.includesys.sm.dto.member.CodeScis;

@Service
public class CodeScisServiceImpl implements CodeScisService{
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CodeScisServiceImpl.class);
	
	@Autowired
	private CodeScisDao codeScisDao;

	@Override
	public List<CodeScis> getCodeList(String up_code) 
	{	
		return codeScisDao.getCodeList(up_code);
	}
	public List<CodeScis> getCodeSubList(String up_code, String sub_code) 
	{
		return codeScisDao.getCodeSubList(up_code, sub_code);
	}
	public List<CodeScis> getCodeSub2List(String up_code, String sub_code) 
	{
		return codeScisDao.getCodeSub2List(up_code, sub_code);
	}

	@Override
	public CodeScis getCode(String pk_code)
	{
		return codeScisDao.getCode(pk_code);
	}
}
