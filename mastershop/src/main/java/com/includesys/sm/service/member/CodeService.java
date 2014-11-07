package com.includesys.sm.service.member;

import java.util.*;
import com.includesys.sm.dto.member.Code;

public interface CodeService {
	/**
	 * CODE 리스트 가져오기
	 * @param TBL_CODE의 FD_UP_CODE값 ( up_code )
	 * @return up_code 값을 FD_UP_CODE 값으로 쓰는 리스트 
	 * getFd_up_code,getPk_code,getFd_name,getFd_menu_yn,getFd_start,getFd_finish
	 */
	public List<Code> getCodeList(String up_code);
	
	/**
	 * CODE 가져오기
	 * @param TBL_CODE의 PK_CODE값 ( pk_code )
	 * @return PK_CODE의 값 
	 * getFd_up_code,getPk_code,getFd_name,getFd_menu_yn,getFd_start,getFd_finish
	 */
	public Code getCode(String pk_code);
}
