package com.includesys.sm.controller.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.manager.Admin;
import com.includesys.sm.dto.manager.CMSMapping;
import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Code;
import com.includesys.sm.service.manager.AdminService;
import com.includesys.sm.service.manager.CMSMappingService;
import com.includesys.sm.service.manager.CMSMenuService;
import com.includesys.sm.service.manager.ReqService;
import com.includesys.sm.service.member.CodeService;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.webUtil.PageNavi;

@Controller("manager.AdminController")
@RequestMapping("/manager")
public class AdminController 
{
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private CMSMenuService cmsMenuService;

	@Autowired
	private CMSMappingService CMSMappingService;

	@Autowired
	private ReqService reqService;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired 
	private HttpSession session;

	
	@RequestMapping(value="/adminList.do")
	public ModelAndView adminList2(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller2] AdminController.adminList()");
		
		int page, pageSize	= 10;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		String searchColumn = map.get("searchColumn") == null ? "" : map.get("searchColumn").toString();
		String searchString = map.get("searchString") == null ? "" : map.get("searchString").toString();

		map.remove("page");
		map.put("pageStart", (page-1)*10);
		map.put("pageSize", pageSize);
		
		int totalCount 			= adminService.getCount(map);
		List<Admin> adminList	= adminService.getList(map);
	
		List<Code> admin_level_list		= new ArrayList<Code>();
		List<Code> admin_status_list	= new ArrayList<Code>();
		List<Code> admin_level_cd		= new ArrayList<Code>();
		List<Code> admin_status_cd		= new ArrayList<Code>();
		
		admin_level_list	= codeService.getCodeList("3100"); //권한
		admin_status_list	= codeService.getCodeList("3000"); //상태:신청,승인,거절,해지

		for(Admin admin : adminList)
		{
			for(Code item : admin_level_list){
				if(item.getPk_code().equals(admin.getFd_admin_level_cd())){
					admin_level_cd.add(item);
				}
			}
			for(Code item : admin_status_list){
				if(item.getPk_code().equals(admin.getFd_admin_status_cd())){
					admin_status_cd.add(item);
				}
			}
		}
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/manager/adminList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.setParameters("searchColumn", searchColumn);
		pageNavi.setParameters("searchString", searchString);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("adminList", adminList);
		mav.addObject("admin_level_cd", admin_level_cd);
		mav.addObject("admin_status_cd", admin_status_cd);
		mav.addObject("pageHelper", map);
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/manager/adminList");

		return mav;
	}

	
	
	/**
	 * 관리자 상세보기 / 수정화면
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adminModify.do")
	public ModelAndView adminModifyForm(@RequestParam Map<String, Object> map) throws Exception
	{
		System.out.println("\n[CALL Controller] AdminController.adminModifyForm()");

		String pk_admin_id = map.get("pk_admin_id").toString();

		System.out.println(" .adminModifyForm() 1");
		List<Code> codeStatus = codeService.getCodeList("3000");
		List<Code> codeLevels = codeService.getCodeList("3100");
		List<CMSMenu> cmsMenuList = cmsMenuService.getSortList();
		List<CMSMapping> aclMenuList = CMSMappingService.getList("", pk_admin_id);
		System.out.println(" .adminModifyForm() 2");
		
		ModelAndView mav = new ModelAndView("manager/adminModify");

		Admin admin = adminService.get(pk_admin_id);
		
		String[] email = admin.getFd_email().split("@");
		String[] phone = admin.getFd_phone().split("-");
		String[] mobile = admin.getFd_mobile().split("-");
		
		Map<String, String> splitInfo = new HashMap<String, String>();
		splitInfo.put("email1", email[0]);
		splitInfo.put("email2", email[1]);
		splitInfo.put("phone1", phone[0]);
		splitInfo.put("phone2", phone[1]);
		splitInfo.put("phone3", phone[2]);
		splitInfo.put("mobile1", mobile[0]);
		splitInfo.put("mobile2", mobile[1]);
		splitInfo.put("mobile3", mobile[2]);
		
		mav.addObject("admin", admin);
		mav.addObject("splitInfo", splitInfo);
		
		mav.addObject("pageHelper", map);
		mav.addObject("codeStatus", codeStatus);
		mav.addObject("codeLevels", codeLevels);
		mav.addObject("cmsMenuList", cmsMenuList);
		mav.addObject("aclMenuList", aclMenuList);
		System.out.println("adminModifyForm end");
		return mav;
	}
	
	/*
	 * 관리자 상세보기 / 수정처리
	 */
	@Transactional
	@RequestMapping(value="/adminModify.do", params="event=modify")
	public ModelAndView adminModify(@RequestParam Map<String, Object> map, @RequestParam(value="permissions", required=false) String[] permissions) throws Exception
	{
		System.out.println("\n[CALL Controller] AdminController.adminModify() event=modify");
		
		String script = "";
		String pk_admin_id		= map.get("pk_admin_id").toString();
		String fd_phone			= map.get("phone1").toString() + "-" + map.get("phone2").toString() + "-" + map.get("phone3").toString();
		String fd_mobile		= map.get("mobile1").toString() + "-" + map.get("mobile2").toString() + "-" + map.get("mobile3").toString();
		String fd_email			= map.get("email1").toString() + "@" + map.get("email2").toString();
		String fd_admin_pw		= map.get("fd_admin_pw").toString().trim();
		String re_fd_admin_pw	= map.get("re_fd_admin_pw").toString().trim();		

		ModelAndView mav = new ModelAndView("manager/adminModify");

		List<Code> codeStatus		= codeService.getCodeList("3000");
		List<Code> codeLevels		= codeService.getCodeList("3100");
		List<CMSMenu> cmsMenuList	= cmsMenuService.getSortList();
		List<CMSMapping> aclMenuList	= CMSMappingService.getList("", pk_admin_id);				

		System.out.println(" .adminModify event=modify 1");
		
		Admin admin = adminService.get(pk_admin_id);
		try	
		{
			admin.setFd_admin_name(map.get("fd_admin_name").toString());
			admin.setFd_admin_level_cd(map.get("fd_admin_level_cd").toString());
			admin.setFd_team(map.get("fd_team").toString());
			admin.setFd_phone(fd_phone);
			admin.setFd_mobile(fd_mobile);
			admin.setFd_email(fd_email);
			admin.setFd_admin_status_cd(map.get("fd_admin_status_cd").toString());
			
			System.out.println(" .adminModify event=modify 2");
			
			//권한 처리 ===== START =====
			if(!fd_admin_pw.equals("") && !re_fd_admin_pw.equals(""))
			{
				String nowDate;
				SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
				nowDate = ft.format(new Date());				
				HashEncrypt encrypt = new HashEncrypt(fd_admin_pw);
				
				admin.setFd_admin_pw(encrypt.getEncryptData());
				admin.setFd_change_pw_date(nowDate);
			}	
			
			adminService.modify(admin);
			
			System.out.println(" .adminModify event=modify 3");
			
			boolean targetPermission;
			List<CMSMapping> removeMenuList = new ArrayList<CMSMapping>();
			List<CMSMapping> registerMenuList = new ArrayList<CMSMapping>();
			
			for(CMSMapping mapp : aclMenuList)
			{
				targetPermission = true;
				for(String permission : permissions)
				{
					if(mapp.getPk_url_code().equals(permission))
					{
						targetPermission = false;
					}
				}
				
				if(targetPermission) 
				{
					CMSMapping cmsMapping = new CMSMapping();
					cmsMapping.setPk_url_code(mapp.getPk_url_code());
					cmsMapping.setPk_admin_id(pk_admin_id);			
					removeMenuList.add(cmsMapping);
				}
			}
			
			System.out.println(" .adminModify event=modify 4");
			for(String permission : permissions)
			{
				targetPermission = true;
				for(CMSMapping mapp : aclMenuList)
				{
					if(mapp.getPk_url_code().equals(permission))
					{
						targetPermission = false;
					}
				}
				
				if(targetPermission) 
				{
					CMSMapping cmsMapping = new CMSMapping();
					cmsMapping.setPk_url_code(permission);
					cmsMapping.setPk_admin_id(pk_admin_id);			
					registerMenuList.add(cmsMapping);
				}
			}		
			System.out.println(" .adminModify event=modify 5");
			
			for(CMSMapping mapp : removeMenuList)
			{
				CMSMappingService.remove(mapp.getPk_url_code(), mapp.getPk_admin_id());
			}
			
			for(CMSMapping mapp : registerMenuList)
			{
				CMSMappingService.register(mapp);
			}

			script  = "alert('정상적으로 수정처리 되었습니다.');";
			script += "go_list();";
		}
		catch(Exception ex)
		{
			System.out.println(" .adminModify Exception");
			
			String[] emailMap = admin.getFd_email().split("@");
			String[] phoneMap = admin.getFd_phone().split("-");
			String[] mobileMap = admin.getFd_mobile().split("-");

			Map<String, String> splitInfo = new HashMap<String, String>();
			splitInfo.put("email1", emailMap[0]);
			splitInfo.put("email2", emailMap[1]);
			splitInfo.put("phone1", phoneMap[0]);
			splitInfo.put("phone2", phoneMap[1]);
			splitInfo.put("phone3", phoneMap[2]);
			splitInfo.put("mobile1", mobileMap[0]);
			splitInfo.put("mobile2", mobileMap[1]);
			splitInfo.put("mobile3", mobileMap[2]);

			mav.addObject("splitInfo", splitInfo);
			mav.addObject("pageHelper", map);
			mav.addObject("admin", admin);

			script = "alert('수정처리가 실패했습니다.\\n다시 시도하여 주십시오.');";

			logger.error(ex.getMessage());
		}

		mav.addObject("codeStatus", codeStatus);
		mav.addObject("codeLevels", codeLevels);
		mav.addObject("cmsMenuList", cmsMenuList);
		mav.addObject("aclMenuList", aclMenuList);		
		mav.addObject("script", script);

		return mav;
	}
	
	
	/**
	 * 관리자 상세보기 / 삭제처리
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/adminModify.do", params="event=remove")
	public ModelAndView adminRemove(@RequestParam Map<String, String> map)
	{
		System.out.println("\n[CALL Controller] AdminController.adminModify() event=remove");
		
		String script = "";
		String pk_admin_id	= map.get("pk_admin_id");
		Admin admin = adminService.get(pk_admin_id);

		ModelAndView mav = new ModelAndView("manager/adminModify");
		mav.addObject("pageHelper", map);

		try
		{
			adminService.remove(pk_admin_id);
			CMSMappingService.remove("", pk_admin_id);
			script += "alert('정상적으로 삭제 처리되었습니다.');";
			script += "go_list();";
		}
		catch(Exception ex)
		{
			String[] email = admin.getFd_email().split("@");
			String[] phone = admin.getFd_phone().split("-");
			String[] mobile = admin.getFd_mobile().split("-");

			Map<String, String> splitInfo = new HashMap<String, String>();
			splitInfo.put("email1", email[0]);
			splitInfo.put("email2", email[1]);
			splitInfo.put("phone1", phone[0]);
			splitInfo.put("phone2", phone[1]);
			splitInfo.put("phone3", phone[2]);
			splitInfo.put("mobile1", mobile[0]);
			splitInfo.put("mobile2", mobile[1]);
			splitInfo.put("mobile3", mobile[2]);

			script += "alert('삭제 처리가 실패했습니다.\\n다시 시도하여 주십시오.');";

			mav.addObject("splitInfo", splitInfo);
			mav.addObject("admin", admin);

			logger.error(ex.getMessage());
		}

		mav.addObject("script", script);

		return mav;
	}
	
	
	@RequestMapping(value="/adminRegister.do")
	public ModelAndView adminRegisterForm(@RequestParam Map<String, Object> map) throws Exception
	{
		System.out.println("\n[CALL Controller] AdminController.adminRegisterForm() ");
		
		List<Code> codeStatus = codeService.getCodeList("3000");
		List<Code> codeLevels = codeService.getCodeList("3100");
		List<CMSMenu> cmsMenuList = cmsMenuService.getSortList();

		ModelAndView mav = new ModelAndView("manager/adminRegister");
		mav.addObject("pageHelper", map);
		mav.addObject("codeStatus", codeStatus);
		mav.addObject("codeLevels", codeLevels);
		mav.addObject("cmsMenuList", cmsMenuList);

		return mav;
	}

	@RequestMapping(value="/adminRegister.do", params="event=register")
	public ModelAndView adminRegister(@RequestParam Map<String, Object> map, @RequestParam(value="permissions", required=false) String[] permissions)
	{
		System.out.println("\n[CALL Controller] AdminController.adminRegisterForm() event=register ");
		
		String nowDt, admin_ip, script = "";
		admin_ip = 	request.getRemoteAddr();

		String pk_admin_id			= map.get("pk_admin_id").toString();
		String fd_admin_pw			= map.get("fd_admin_pw").toString();
		String fd_admin_name		= map.get("fd_admin_name").toString();
		String fd_admin_level_cd	= map.get("fd_admin_level_cd").toString();
		String fd_team				= map.get("fd_team").toString();
		String fd_phone				= map.get("phone1").toString() + "-" + map.get("phone2").toString() + "-" + map.get("phone3").toString();
		String fd_mobile			= map.get("mobile1").toString() + "-" + map.get("mobile2").toString() + "-" + map.get("mobile3").toString();
		String fd_email				= map.get("email1").toString() + "@" + map.get("email2").toString();
		String fd_admin_status_cd	= map.get("fd_admin_status_cd").toString();

		HashEncrypt encrypt = new HashEncrypt(fd_admin_pw);
		fd_admin_pw = encrypt.getEncryptData();

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		nowDt = ft.format(new Date( ));

		ModelAndView mav = new ModelAndView("manager/adminRegister");

		Admin admin = new Admin();

		try	
		{
			admin.setPk_admin_id(pk_admin_id);
			admin.setFd_admin_pw(fd_admin_pw);
			admin.setFd_admin_name(fd_admin_name);
			admin.setFd_admin_level_cd(fd_admin_level_cd);
			admin.setFd_team(fd_team);
			admin.setFd_phone(fd_phone);
			admin.setFd_mobile(fd_mobile);
			admin.setFd_email(fd_email);
			admin.setFd_reg_date(nowDt);
			admin.setFd_login_date(nowDt);
			admin.setFd_login_ip(admin_ip);
			admin.setFd_admin_status_cd(fd_admin_status_cd);

			adminService.register(admin);

			CMSMappingService.remove("", pk_admin_id);
			for(String permission : permissions)
			{
				CMSMapping cmsMapping = new CMSMapping();
				cmsMapping.setPk_url_code(permission);
				cmsMapping.setPk_admin_id(pk_admin_id);
				CMSMappingService.register(cmsMapping);
			}

			script  = "alert('정상적으로 수정처리 되었습니다.');";
			script += "go_list();";
		}
		catch(Exception ex)
		{
			List<Code> codeStatus = codeService.getCodeList("3000");
			List<Code> codeLevels = codeService.getCodeList("3100");

			mav.addObject("codeStatus",codeStatus);
			mav.addObject("codeLevels",codeLevels);

			script  = "alert('수정처리가 실패했습니다.\\n다시 시도하여 주십시오.');";
			mav.addObject("admin", map);
			logger.error(ex.getMessage());
		}

		mav.addObject("script", script);

		return mav;
	}


	/**
	 * 내정보 수정화면
	 * @param map
	 * @return
	 */

	@RequestMapping(value="/myInfo.do")
	public ModelAndView myInfo(@RequestParam Map<String, Object> map, HttpServletRequest request)
	{
		System.out.println("\n[CALL Controller] AdminController.myInfo");
		
		//LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		
		if(loginInfo == null){
			System.out.println(" .myInfo loginInfo = null");
		}
		System.out.println(" .myInfo session loginInfo.getId()="+loginInfo.getPk_member_id());
		System.out.println(" .myInfo session loginInfo.getName()="+loginInfo.getFd_user_name());
		
		Admin admin = adminService.get(loginInfo.getPk_member_id());
		
		Code code = codeService.getCode(admin.getFd_admin_level_cd());
		ModelAndView mav = new ModelAndView("manager/myInfo");
		String[] email = admin.getFd_email().split("@");
		String[] phone = admin.getFd_phone().split("-");
		String[] mobile = admin.getFd_mobile().split("-");
		Map<String, String> splitInfo = new HashMap<String, String>();
		
		splitInfo.put("email1", email[0]);
		splitInfo.put("email2", email[1]);
		splitInfo.put("phone1", phone[0]);
		splitInfo.put("phone2", phone[1]);
		splitInfo.put("phone3", phone[2]);
		splitInfo.put("mobile1", mobile[0]);
		splitInfo.put("mobile2", mobile[1]);
		splitInfo.put("mobile3", mobile[2]);		
		mav.addObject("admin", admin);
		mav.addObject("splitInfo", splitInfo);
		mav.addObject("fd_admin_level_cd", code.getFd_name());

		return mav;
	}
	

	@RequestMapping(value="/myInfo.do", params="event=myInfoModify")
	public ModelAndView myInfoModify(@RequestParam Map<String, String> map) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		System.out.println("\n[CALL Controller] AdminController.myInfoModify event=myInfoModify");
		
		String script = "";
		String scriptContainer = "<script language='javascript'>%s</script>";

		String pk_admin_id		= map.get("pk_admin_id").trim();
		String fd_phone			= map.get("phone1").trim() + "-" + map.get("phone2").trim() + "-" + map.get("phone3").trim();
		String fd_mobile		= map.get("mobile1").trim() + "-" + map.get("mobile2").trim() + "-" + map.get("mobile3").trim();
		String fd_email			= map.get("email1").trim() + "@" + map.get("email2").trim();
		String fd_admin_pw		= map.get("fd_admin_pw").trim();
		String re_fd_admin_pw	= map.get("re_fd_admin_pw").trim();

		Admin admin = adminService.get(pk_admin_id);

		try	
		{
			admin.setFd_admin_name(map.get("fd_admin_name").trim());
			admin.setFd_team(map.get("fd_team").trim());
			admin.setFd_phone(fd_phone);
			admin.setFd_mobile(fd_mobile);
			admin.setFd_email(fd_email);
			
			if(!fd_admin_pw.equals("") && !re_fd_admin_pw.equals(""))
			{
				SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
				String nowDate = ft.format(new Date());					
				HashEncrypt encrypt = new HashEncrypt(fd_admin_pw);
				
				admin.setFd_admin_pw(encrypt.getEncryptData());
				admin.setFd_change_pw_date(nowDate);
			}

			adminService.modify(admin);

			script  = "alert('정상적으로 수정처리 되었습니다.');";
		}
		catch(Exception ex)
		{
			script  = "alert('수정처리가 실패했습니다.\\n다시 시도하여 주십시오.');";
		}
		
		script += "location.href='/manager/myInfo.do';";
		
		mav.addObject("script", script);
		mav.setViewName("/manager/myInfo");
		//scriptContainer = String.format(scriptContainer, script);

		return mav;
	}
	
	
	/**
	 * CMS 계정 신청 화면
	 * @return
	 */
	@RequestMapping(value="/reqAccount.do")
	public ModelAndView reqAccount()
	{
		System.out.println("\n[CALL Controller] AdminController.reqAccount");
		
		List<Code> codeLevel = codeService.getCodeList("3100");

		ModelAndView mav = new ModelAndView();
		mav.addObject("event","insert");
		mav.addObject("codeLevel",codeLevel);

		return mav;
	}
	
	/**
	 * CMS 계정신청 처리
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/reqAccount.do", params="event=insert")
	public ModelAndView reqInsert(@RequestParam Map<String,String> map)
	{
		System.out.println("\n[CALL Controller] AdminController.reqAccount event=insert");
		
		String nowDt, admin_ip;
		admin_ip = 	request.getRemoteAddr();	

		String admin_id			= map.get("admin_id");
		String admin_pw			= map.get("admin_pw");
		String admin_name		= map.get("admin_name");
		String admin_level_cd	= map.get("admin_level_cd");
		String admin_team		= map.get("admin_team");
		String phone			= map.get("phone1") + "-" + map.get("phone2") + "-" + map.get("phone3");
		String mobile			= map.get("mobile1") + "-" + map.get("mobile2") + "-" + map.get("mobile3");
		String email			= map.get("email1") + "@" + map.get("email2");	
		
		HashEncrypt encrypt = new HashEncrypt(admin_pw);
		admin_pw = encrypt.getEncryptData();

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		nowDt = ft.format(new Date());

		ModelAndView mav = new ModelAndView("/manager/reqAccount");

		Admin admin = new Admin();
		
		try	
		{
			admin.setPk_admin_id(admin_id);
			Admin chkId = reqService.get(admin);
			
			if(chkId == null)
			{
				admin.setFd_admin_pw(admin_pw);
				admin.setFd_admin_name(admin_name);
				admin.setFd_admin_level_cd(admin_level_cd);
				admin.setFd_team(admin_team);
				admin.setFd_phone(phone);
				admin.setFd_mobile(mobile);
				admin.setFd_email(email);
				admin.setFd_reg_date(nowDt);
				admin.setFd_login_date(nowDt);
				admin.setFd_login_ip(admin_ip);
				admin.setFd_admin_status_cd("REQ");

				reqService.register(admin);
				mav.addObject("event", "close");
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception ex)
		{
			List<Code> codeLevel = codeService.getCodeList("3100");

			mav.addObject("codeLevel",codeLevel);

			mav.addObject("admin", map);
			mav.addObject("event","insert");

			logger.error(ex.getMessage());
		}

		return mav;
	}	



	@RequestMapping(value="/adminIdCheck.do")
	public ModelAndView adminIdCheck(@RequestParam Map<String,String> map) {
		
		System.out.println("\n[CALL Controller] AdminController.adminIdCheck");
		
		String chkId			= "f";
		String admin_id			= map.get("admin_id").trim();

		Admin admin = new Admin();
		try	{
			admin.setPk_admin_id(admin_id);
			Admin cId = reqService.get(admin);

			if(cId == null && !(admin_id.equals("") || admin_id == null)){
				chkId	= "t";
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		
		
		ModelAndView mav = new ModelAndView("/manager/adminIdCheck");
		mav.addObject("admin_id", admin_id);
		mav.addObject("chkId", chkId);
		return mav;		
	}
	
	
	
}