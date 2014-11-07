package com.includesys.sm.intercepter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.service.manager.CMSMenuService;

/**
 * LEFT 메뉴구성
 * @author Administrator
 *
 */
@Aspect
@Order(2)
@Component
public class AccessControlListByURIAspect 
{	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;		
	
	@Autowired
	private CMSMenuService cmsMenuService;	
	
	
	//@Pointcut("execution(public * com.includesys.sm.service.*.*.*getList*(..))")
	//@Pointcut("execution(public * com.includesys.sm.controller.*.*.*(..))")
	//@Pointcut("execution(public * com.includesys.sm.service.*.*.*(..))")
	
	@Pointcut("execution(public * com.includesys.sm.service.member..*(..))")
	public void requestManager(){ }
		
	@Pointcut("execution(public * com.includesys.sm.service.cashback..*(..))")	
	public void requestcashback(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.product..*(..))")
	public void requestProduct(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.point..*(..))")
	public void requestPoint(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.common..*(..))")
	public void requestCommon(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.serial..*(..))")
	public void requestSerial(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.terms..*(..))")
	public void requestTerms(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.messages..*(..))")
	public void requestMessages(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.log..*(..))")
	public void requestLog(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.report..*(..))")
	public void requestReport(){ }
	
	@Pointcut("execution(public * com.includesys.sm.service.pomoney..*(..))")
	public void requestPomoney(){ }

	@Pointcut("execution(public * com.includesys.sm.service.websites..*(..))")
	public void requestWebsites(){ }

	@Pointcut("execution(public * com.includesys.sm.service.csManager..*(..))")
	public void requestCsManager(){ }

	@Pointcut("execution(public * com.includesys.sm.service.smsManager..*(..))")
	public void requestSmsManager(){ }

	@Before(value="requestManager() || requestcashback() || requestProduct() || requestPoint() || requestCommon() || requestSerial() || requestTerms() || requestMessages() || requestLog() || requestReport() || requestPomoney() || requestWebsites() || requestCsManager() || requestSmsManager()")
	public void accessControlListHandler()
	{		
		System.out.println("[CALL AOP] AccessControlListByURIAspect.accessControlListHandler() ");
		
		/*
		String requestURI 	= request.getRequestURI().replaceAll("//", "/");
		List<CMSMenu> mainMenuList = null, subMenuList = null;
		//System.out.println("AccessControlListByURIAspect1");
		if(!requestURI.equals("/manager/login.do") && !requestURI.equals("/manager/reqAccount.do") 
				&& !requestURI.equals("/manager/adminIdCheck.do") && !requestURI.equals("/manager/logout.do")
				&& requestURI.indexOf("ifrm")==-1
			)
				
		{
			//System.out.println("AccessControlListByURIAspect2");
			if(session.getAttribute("leftMainMenuList") == null || session.getAttribute("leftSubMenuList") == null)
			{	
				//System.out.println("AccessControlListByURIAspect3");
				boolean flag = true;
				mainMenuList = new ArrayList<CMSMenu>();
				subMenuList = new ArrayList<CMSMenu>();
				
				//System.out.println("AccessControlListByURIAspect !1");
				List<CMSMenu> sessionCheckAllSortMenus = (List<CMSMenu>)session.getAttribute("allSortMenus");
				//System.out.println("AccessControlListByURIAspect !2");
				List<CMSMenu> allSortMenus = null;
				if(sessionCheckAllSortMenus == null){
					//System.out.println("널체크 AccessControlListByURIAspect !3-1");
					allSortMenus = cmsMenuService.getSortList();
					//System.out.println("널체크 AccessControlListByURIAspect !3-2");
					session.setAttribute("allSortMenus", allSortMenus);

				}else if(sessionCheckAllSortMenus.isEmpty()){
					//System.out.println("널체크 AccessControlListByURIAspect !3-3");
					allSortMenus = cmsMenuService.getSortList();
					//System.out.println("널체크 AccessControlListByURIAspect !3-4");
					session.setAttribute("allSortMenus", allSortMenus);
				}
				//System.out.println("AccessControlListByURIAspect !4");
				
				
				@SuppressWarnings("unchecked")
				List<CMSMenu> aclMenus = (List<CMSMenu>)session.getAttribute("aclList");
				//List<CMSMenu> aclMenus = new ArrayList<CMSMenu>();
				//aclMenus.add((CMSMenu) session.getAttribute("aclList"));
				

				//System.out.println("AccessControlListByURIAspect4");

				for(CMSMenu asMenu : allSortMenus)
				{
					//System.out.println("널체크 AccessControlListByURIAspect !5-1");
					for(CMSMenu aMenu : aclMenus)
					{
						//System.out.println("널체크 AccessControlListByURIAspect !5-2");
						if(asMenu.getPk_url_code().equals(aMenu.getPk_url_code()))
						{
							//System.out.println("널체크 AccessControlListByURIAspect !5-3");
							if(asMenu.getFd_group_code().equals("0000"))
							{
								//System.out.println("널체크 AccessControlListByURIAspect !5-4");
								if(asMenu.getFd_menu_yn().equals("Y"))
								{
									flag = true;
									mainMenuList.add(asMenu);
								}
								else
								{
									flag = false;
								}
							}
							else
							{
								//System.out.println("널체크 AccessControlListByURIAspect !5-5");
								if(asMenu.getFd_menu_yn().equals("Y") && flag)
								{
									subMenuList.add(asMenu);
								}
							}
						}
					}
				}
			
				//System.out.println("널체크 AccessControlListByURIAspect !5-6");
				session.setAttribute("leftMainMenuList", mainMenuList);
				session.setAttribute("leftSubMenuList", subMenuList);					

			
				request.setAttribute("staticServerURL", request.getSession().getServletContext().getInitParameter("staticServerURL"));
				//System.out.println("AccessControlListByURIAspect5");
			}	
			
		}*/
	}	
}
