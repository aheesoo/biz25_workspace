package com.includesys.sm.controller.member;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.includesys.sm.dto.member.Code;
import com.includesys.sm.service.member.CodeService;

@Controller
public class CodeController {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CodeController.class);

	@Autowired
	private CodeService codeService;

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}


	@RequestMapping("/board/test.do")
	public List<Code> codeListHandler(String up_code){

		System.out.println("\n[CALL Controller] CodeController.codeListHandler() upcode = "+up_code);
		
		List<Code> thisCodeList = null;
				
			thisCodeList = new ArrayList<Code>();
			
			//코드(tb_code) 리스트
			List<Code> codeArray = codeService.getCodeList(up_code);
			
			for(Code asCode : codeArray)
			{
				thisCodeList.add(asCode);
/*
				System.out.println("name="+asCode.getFd_name());
				System.out.println("name="+asCode.getFd_up_code());
				System.out.println("name="+asCode.getFd_start());
				System.out.println("name="+asCode.getFd_finish());
				System.out.println("name="+asCode.getFd_menu_yn());
				System.out.println("name="+asCode.getPk_code());
*/
			}
			
			//코드(tb_code) 단일값(pk_code)
			String pk_code="1000";
			//Code code = codeService.getCode(pk_code);
			Code code = codeService.getCode(pk_code);
/*
			System.out.println("name="+code.getFd_name());
			System.out.println("name="+code.getFd_up_code());
			System.out.println("name="+code.getFd_start());
			System.out.println("name="+code.getFd_finish());
			System.out.println("name="+code.getFd_menu_yn());
			System.out.println("name="+code.getPk_code());
*/

		return thisCodeList;
	}
	
}