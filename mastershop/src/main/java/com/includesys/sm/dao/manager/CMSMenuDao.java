package com.includesys.sm.dao.manager;

import java.util.*;
import com.includesys.sm.dto.manager.CMSMenu;

public interface CMSMenuDao 
{
	/**
	 * CMS 메뉴를 가져온다.
	 * @param cmsMenu CMS 메뉴
	 * @return CMS 메뉴
	 */
	CMSMenu get(CMSMenu cmsMenu);
		
	/**
	 * 주어진 CMS 메뉴코드의 하위 메뉴 카운트 수 를 가져온다.
	 * @param cmsMenu CMS 메뉴
	 * @return 주어진 CMS 메뉴코드의 하위 메뉴 카운트 수
	 */
	int getCount(CMSMenu cmsMenu);
	
	/**
	 * 주어진 CMS 메뉴코드의 하위 메뉴리스트를 가져온다.
	 * @param cmsMenu CMS 메뉴
	 * @return 주어진 CMS 메뉴코드의 하위 메뉴리스트
	 */
	List<CMSMenu> getList(CMSMenu cmsMenu);
	
	/**
	 * 정렬된 CMS 메뉴 전체 리스트를 가져온다.
	 * @return 정렬된 CMS 메뉴 전체 리스트
	 */
	List<CMSMenu> getSortList();
	
	/**
	 * 관리자 id 를 값으로 CMS 메뉴 리스트를 가져온다.
	 * @param admin_id
	 * @return 관리자 id 를 값으로 하는 CMS 메뉴 리스트
	 */
	List<CMSMenu> getListByAdminID(String admin_id);
	
	/**
	 * CMS 메뉴를 등록한다.
	 * @param cmsMenu CMS 메뉴
	 */
	void register(CMSMenu cmsMenu);
	
	/**
	 * CMS 메뉴를 수정한다.
	 * @param cmsMenu CMS 메뉴
	 */
	void modify(CMSMenu cmsMenu);
		
	/**
	 * CMS 메뉴를 삭제한다.
	 * @param pk_url_code CMS 메뉴코드
	 */
	void remove(CMSMenu cmsMenu);	
}
