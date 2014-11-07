package com.includesys.sm.dao.member;

import java.util.List;
import com.includesys.sm.dto.member.Code;

public interface CodeDao {
	/**
	 * CODE 리스트 가져오기
	 * @param TBL_CODE의 FD_UP_CODE값 ( up_code )
	 * @return FD_UP_CODE 값을 PK_CODE 값으로 쓰는 리스트 
	 * getFd_up_code,getPk_code,getFd_name,getFd_menu_yn,getFd_start,getFd_finish
	 */
	List<Code> getCodeList(Code code);

	Code getCode(Code code);
}