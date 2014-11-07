package com.includesys.sm.controller.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.service.manager.CMSMenuService;

@Controller
@RequestMapping("/manager")
public class CMSMenuController 
{
	@Autowired
	private CMSMenuService cmsMenuService;
	
	@RequestMapping(value="/mainMenu.do")
	public ModelAndView mainMenu(@RequestParam(value="event", defaultValue="") String event, @RequestParam(value="pk_url_code", defaultValue="") String pk_url_code)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.mainMenu()");
		
		CMSMenu cmsMenu = null;
		List<CMSMenu> mainMList = cmsMenuService.getList();			
		ModelAndView mav = new ModelAndView("/manager/mainMenu");
		mav.addObject("mainMList", mainMList);		
		if(event.equals("modifyForm")) {	cmsMenu = cmsMenuService.get(pk_url_code); }
		mav.addObject("cmsMenu", cmsMenu);
									
		System.out.println(" .mainMenu END ");
		
		return mav;
	}
	
	@RequestMapping(value="/mainMenu.do", params="event=register")
	public ModelAndView mainMenuRegister(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.mainMenuRegister() event=register");
		
		int fd_sort_no = (map.get("fd_sort_no") != "") ? Integer.parseInt(map.get("fd_sort_no")) : 1;
		
		CMSMenu cmsMenu = new CMSMenu();
		cmsMenu.setFd_group_code("0000");
		cmsMenu.setPk_url_code(map.get("pk_url_code").trim());
		cmsMenu.setFd_name(map.get("fd_name").trim());
		cmsMenu.setFd_url(map.get("fd_url").trim());
		cmsMenu.setFd_menu_yn(map.get("fd_menu_yn"));
		cmsMenu.setFd_sort_no(fd_sort_no);

		ModelAndView mav = new ModelAndView("manager/mainMenu");		
		
		try	
		{ 
			cmsMenuService.register(cmsMenu);			
			mav.addObject("script", String.format("alert('%s')", "정상적으로 등록 처리 되었습니다."));
		}
		catch(Exception ex)
		{
			mav.addObject("cmsMenu", map);
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));
		}				
		
		List<CMSMenu> mainMList = cmsMenuService.getList();
		mav.addObject("mainMList", mainMList);
		
		return mav;
	}
	
	@RequestMapping(value="/mainMenu.do", params="event=modify")
	public ModelAndView mainMenuModify(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.mainMenuModify() event=modify");
		
		int fd_sort_no = (map.get("fd_sort_no") != "") ? Integer.parseInt(map.get("fd_sort_no")) : 1;
		
		CMSMenu cmsMenu = new CMSMenu();
		cmsMenu.setFd_group_code("0000");
		cmsMenu.setOrg_pk_url_code(map.get("org_pk_url_code").trim());
		cmsMenu.setPk_url_code(map.get("pk_url_code").trim());
		cmsMenu.setFd_name(map.get("fd_name").trim());
		cmsMenu.setFd_url(map.get("fd_url").trim());
		cmsMenu.setFd_menu_yn(map.get("fd_menu_yn"));
		cmsMenu.setFd_sort_no(fd_sort_no);

		ModelAndView mav = new ModelAndView("manager/mainMenu");
		
		try	
		{ 
			cmsMenuService.modify(cmsMenu);			
			mav.addObject("script", String.format("alert('%s')", "정상적으로 수정 처리 되었습니다."));
		}
		catch(Exception ex)
		{
			mav.addObject("cmsMenu", map);
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));
		}				
		
		List<CMSMenu> mainMList = cmsMenuService.getList();
		mav.addObject("mainMList", mainMList);
		
		return mav;		
	}
	
	@RequestMapping(value="/mainMenu.do", params="event=remove")
	public ModelAndView mainMenuRemove(@RequestParam(value="pk_url_code", defaultValue="") String pk_url_code)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.mainMenuRemove() event=remove");
		
		ModelAndView mav = new ModelAndView("manager/mainMenu");
		
		try
		{
			cmsMenuService.remove(pk_url_code);
			mav.addObject("script", String.format("alert('%s')", "정상적으로 삭제 처리 되었습니다."));
		}
		catch(Exception ex)
		{
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));			
		}
		
		List<CMSMenu> mainMList = cmsMenuService.getList();
		mav.addObject("mainMList", mainMList);
		
		return mav;				
	}
	
	@RequestMapping(value="/subMenu.do")
	public ModelAndView subMenu(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.subMenu()");
		
		String fd_group_code = map.get("fd_group_code");
		String pk_url_code = map.get("pk_url_code");
		String event = (map.get("event") == null) ? "" : map.get("event");
		
		CMSMenu cmsSubMenu = null;
		CMSMenu cmsMainMenu = cmsMenuService.get(fd_group_code);
		List<CMSMenu> subMList = cmsMenuService.getList(fd_group_code);	
		
		ModelAndView mav = new ModelAndView("manager/subMenu");
				
		if(event.equals("modifyForm")) {	cmsSubMenu = cmsMenuService.get(pk_url_code); }
		mav.addObject("cmsMainMenu", cmsMainMenu);
		mav.addObject("cmsSubMenu", cmsSubMenu);
		mav.addObject("subMList", subMList);
									
		return mav;		
	}
	
	@RequestMapping(value="/subMenu.do", params="event=register")
	public ModelAndView subMenuRegister(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.subMenuRegister() event=register");
		
		String fd_group_code = map.get("fd_group_code");
		int fd_sort_no = (map.get("fd_sort_no") != "") ? Integer.parseInt(map.get("fd_sort_no")) : 1;
		
		CMSMenu cmsSubMenu = new CMSMenu();
		cmsSubMenu.setFd_group_code(fd_group_code);
		cmsSubMenu.setPk_url_code(map.get("pk_url_code").trim());
		cmsSubMenu.setFd_name(map.get("fd_name").trim());
		cmsSubMenu.setFd_url(map.get("fd_url").trim());
		cmsSubMenu.setFd_menu_yn(map.get("fd_menu_yn"));
		cmsSubMenu.setFd_sort_no(fd_sort_no);

		ModelAndView mav = new ModelAndView("manager/subMenu");		
		
		try	
		{ 
			cmsMenuService.register(cmsSubMenu);			
			mav.addObject("script", String.format("alert('%s')", "정상적으로 등록 처리 되었습니다."));
		}
		catch(Exception ex)
		{		
			mav.addObject("cmsSubMenu", map);
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));
		}				
		
		CMSMenu cmsMainMenu = cmsMenuService.get(fd_group_code);
		List<CMSMenu> subMList = cmsMenuService.getList(fd_group_code);
		mav.addObject("cmsMainMenu", cmsMainMenu);
		mav.addObject("subMList", subMList);
		
		return mav;
	}	
	
	@RequestMapping(value="/subMenu.do", params="event=modify")
	public ModelAndView subMenuModify(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.subMenuModify() event=modify");
		
		String fd_group_code = map.get("fd_group_code");
		int fd_sort_no = (map.get("fd_sort_no") != "") ? Integer.parseInt(map.get("fd_sort_no")) : 1;
		
		CMSMenu cmsSubMenu = new CMSMenu();
		cmsSubMenu.setFd_group_code(fd_group_code);
		cmsSubMenu.setOrg_pk_url_code(map.get("org_pk_url_code").trim());
		cmsSubMenu.setPk_url_code(map.get("pk_url_code").trim());
		
		cmsSubMenu.setFd_name(map.get("fd_name").trim());
		cmsSubMenu.setFd_url(map.get("fd_url").trim());
		cmsSubMenu.setFd_menu_yn(map.get("fd_menu_yn"));
		cmsSubMenu.setFd_sort_no(fd_sort_no);

		ModelAndView mav = new ModelAndView("manager/subMenu");
		
		try	
		{ 
			cmsMenuService.modify(cmsSubMenu);			
			mav.addObject("script", String.format("alert('%s')", "정상적으로 수정 처리 되었습니다."));
		}
		catch(Exception ex)
		{
			mav.addObject("cmsSubMenu", map);
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));
		}				
		
		CMSMenu cmsMainMenu = cmsMenuService.get(fd_group_code);
		List<CMSMenu> subMList = cmsMenuService.getList(fd_group_code);
		mav.addObject("cmsMainMenu", cmsMainMenu);
		mav.addObject("subMList", subMList);
		
		return mav;		
	}
	
	@RequestMapping(value="/subMenu.do", params="event=remove")
	public ModelAndView subMenuRemove(@RequestParam(value="fd_group_code", defaultValue="") String fd_group_code, @RequestParam(value="pk_url_code", defaultValue="") String pk_url_code)
	{
		System.out.println("\n[CALL Controller ] CMSMenuController.subMenuRemove() event=remove");
		
		ModelAndView mav = new ModelAndView("manager/subMenu");
		
		try
		{
			cmsMenuService.remove(pk_url_code);
			mav.addObject("script", String.format("alert('%s')", "정상적으로 삭제 처리 되었습니다."));
		}
		catch(Exception ex)
		{
			mav.addObject("script", String.format("alert('%s')", ex.getMessage()));			
		}
		
		CMSMenu cmsMainMenu = cmsMenuService.get(fd_group_code);
		List<CMSMenu> subMList = cmsMenuService.getList(fd_group_code);
		mav.addObject("cmsMainMenu", cmsMainMenu);
		mav.addObject("subMList", subMList);
		
		return mav;				
	}	
}
