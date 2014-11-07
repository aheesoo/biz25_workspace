package com.includesys.sm.service.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.includesys.sm.dao.member.CodeDao;
import com.includesys.sm.dto.member.Code;

@Service
public class CodeServiceImpl implements CodeService{
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CodeServiceImpl.class);
	
	@Autowired
	private CodeDao codeDao;

	@Override
	public List<Code> getCodeList(String fd_up_code) 
	{
		if(fd_up_code==null || fd_up_code.equals("")){
			fd_up_code="0000";
		}
		Code code = new Code();
		code.setFd_up_code(fd_up_code);
		
		return codeDao.getCodeList(code);
	}

	@Override
	public Code getCode(String pk_code)
	{
		Code code = new Code();
		code.setPk_code(pk_code);
		return codeDao.getCode(code);
	}
}
