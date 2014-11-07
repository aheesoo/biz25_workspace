package com.includesys.sm.service.manager;

import java.util.List;

import com.includesys.sm.dto.manager.CMSMapping;

public interface CMSMappingService 
{
	/**
	 * 권한관계 정보를 가져온다.
	 * @param pk_url_code cms 메뉴 pk
	 * @param fk_admin_id 관리자 id
	 * @return 권한관계 정보
	 * @throws Exception 파라메터 입력오류
	 */
	CMSMapping get(String pk_url_code, String fk_admin_id) throws Exception;
	
	/**
	 * 권한관계 정보를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보
	 * @throws Exception 파라메터 입력오류 
	 */
	CMSMapping get(CMSMapping CMSMapping) throws Exception;
	
	/**
	 * 권한관계 정보 리스트 카운트를 가져온다.
	 * @param pk_url_code cms 메뉴 pk
	 * @param fk_admin_id 관리자 id
	 * @return 권한관계 정보 리스트 카운트
	 * @throws Exception 파라메터 입력오류 
	 */
	int getCount(String pk_url_code, String fk_admin_id) throws Exception;
	
	/**
	 * 권한관계 정보 리스트 카운트를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보 리스트 카운트
	 * @throws Exception 파라메터 입력오류 
	 */
	int getCount(CMSMapping CMSMapping) throws Exception;
	
	/**
	 * 권한관계 정보 리스트를 가져온다.
	 * @param pk_url_code cms 메뉴 pk
	 * @param fk_admin_id 관리자 id
	 * @return 권한관계 정보 리스트
	 * @throws Exception 파라메터 입력오류 
	 */
	List<CMSMapping> getList(String pk_url_code, String fk_admin_id) throws Exception;
	
	/**
	 * 권한관계 정보 리스트를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보 리스트
	 * @throws Exception 파라메터 입력오류 
	 */	
	List<CMSMapping> getList(CMSMapping CMSMapping) throws Exception;
	
	/**
	 * 권한관계 정보를 등록한다.
	 * @param CMSMapping 권한관계정보
	 */	
	void register(CMSMapping CMSMapping);
	
	/**
	 * 권한관계 정보를 수정한다.
	 * @param CMSMapping 권한관계정보
	 */	
	void modify(CMSMapping CMSMapping);
	
	/**
	 * 권한관계 정보를 삭제한다.
	 * @param pk_url_code cms 메뉴 pk
	 * @param fk_admin_id 관리자 id
	 */
	void remove(String pk_url_code, String fk_admin_id);
	
	/**
	 * 권한관계 정보를 삭제한다.
	 * @param CMSMapping 권한관계정보
	 */	
	void remove(CMSMapping CMSMapping);
}
