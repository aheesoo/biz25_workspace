package com.includesys.sm.dao.member;

import java.util.List;
import com.includesys.sm.dto.member.CodeScis;

public interface CodeScisDao {
	/**
	 * CODE 리스트 가져오기
	 * @param TBL_CODE의 FD_UP_CODE값 ( up_code )
	 * @return FD_UP_CODE 값을 PK_CODE 값으로 쓰는 리스트 
	 * getFd_up_code,getPk_code,getFd_name,getFd_finish
	 */
	List<CodeScis> getCodeList(String up_code);
	List<CodeScis> getCodeSubList(String up_code, String sub_code);
	List<CodeScis> getCodeSub2List(String up_code, String sub_code);

	CodeScis getCode(String pk_code);
}