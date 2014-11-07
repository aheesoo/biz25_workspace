package com.includesys.sm.dao.manager;

import java.util.*;
import com.includesys.sm.dto.manager.CMSMapping;

public interface CMSMappingDao 
{
	/**
	 * 권한관계 정보를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보
	 */
	CMSMapping get(CMSMapping CMSMapping);
	
	/**
	 * 권한관계 정보 리스트 카운트를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보 리스트 카운트
	 */
	int getCount(CMSMapping CMSMapping);
	
	/**
	 * 권한관계 정보 리스트를 가져온다.
	 * @param CMSMapping 권한관계정보
	 * @return 권한관계 정보 리스트
	 */
	List<CMSMapping> getList(CMSMapping CMSMapping);
	
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
	 * @param CMSMapping 권한관계정보
	 */
	void remove(CMSMapping CMSMapping);
}
