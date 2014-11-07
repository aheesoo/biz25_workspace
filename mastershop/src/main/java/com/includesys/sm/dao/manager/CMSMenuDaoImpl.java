package com.includesys.sm.dao.manager;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.includesys.sm.dto.manager.CMSMenu;

@Repository
public class CMSMenuDaoImpl implements CMSMenuDao 
{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public CMSMenu get(CMSMenu cmsMenu) {
		return (CMSMenu)sqlSession.selectOne("CMSMenu.get", cmsMenu);
	}

	@Override
	public int getCount(CMSMenu cmsMenu) {
		return (Integer)sqlSession.selectOne("CMSMenu.getCount", cmsMenu);
	}

	@Override
	public List<CMSMenu> getList(CMSMenu cmsMenu) {
		return sqlSession.selectList("CMSMenu.getList", cmsMenu);
	}

	@Override
	public List<CMSMenu> getSortList() {
		return sqlSession.selectList("CMSMenu.getSortList");
	}

	@Override
	public List<CMSMenu> getListByAdminID(String admin_id) {
		return sqlSession.selectList("CMSMenu.getListByAdminID", admin_id);
	}

	@Override
	public void register(CMSMenu cmsMenu) {
		sqlSession.insert("CMSMenu.register", cmsMenu);
	}

	@Override
	public void modify(CMSMenu cmsMenu) {
		sqlSession.update("CMSMenu.modify", cmsMenu);
	}

	@Override
	public void remove(CMSMenu cmsMenu) {
		sqlSession.delete("CMSMenu.remove", cmsMenu);
	}	
	

}

