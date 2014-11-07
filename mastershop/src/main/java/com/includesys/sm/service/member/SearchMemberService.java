package com.includesys.sm.service.member;

import java.util.HashMap;

import com.includesys.sm.dto.member.SearchMember;

public interface SearchMemberService {
	
	//회원 정보 조회 카운트
	public int selectMemberInfoCount(HashMap<String, Object> hmap);
	
	// 회원 정보 조회
	public SearchMember selectMemberInfo(HashMap<String, Object> hmap);
	
	// 인증키 생성 저장
	public int insertCreateSecurityKey(HashMap<String, Object> hmap);
	
	// 인증키 확인
	public SearchMember selectMemberInfoSecurityKey(HashMap<String, Object> hmap);
	
	// 임시 비밀번호 변경
	public int updateMemberTempPassword(HashMap<String, Object> hmap);
	
	// SMS 그룹 테이블 저장
	public int insertMemberSearchSmsGroup(HashMap<String, Object> hmap);
	
	//SMS 발송 테이블 저장
	public int insertMemberSearchSmsReservation(HashMap<String, Object> hmap);
	
	
	// 아이디 찾기 SMS 발송
	public int insertSearchSmsSend(HashMap<String, Object> hmap);
}
