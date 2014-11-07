package com.includesys.sm.service.member;

import java.util.List;

import com.includesys.sm.dto.member.CodeScis;

public interface CodeScisService {
	/**
	 * scis CODE 리스트 가져오기
	 * @param TBL_CODE의 FD_UP_CODE값 ( up_code )
	 * @return up_code 값을 FD_UP_CODE 값으로 쓰는 리스트 
	 * getFd_up_code,getPk_code,getFd_name,getFd_finish
	 */
	public List<CodeScis> getCodeList(String up_code);
	public List<CodeScis> getCodeSubList(String up_code, String sub_code);
	public List<CodeScis> getCodeSub2List(String up_code, String sub_code);
	
	/**
	 * scis CODE 가져오기
	 * @param TBL_CODE의 PK_CODE값 ( pk_code )
	 * @return PK_CODE의 값 
	 * getFd_up_code,getPk_code,getFd_name,getFd_finish
	 */
	public CodeScis getCode(String pk_code);
}
