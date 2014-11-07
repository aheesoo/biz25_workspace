package com.includesys.sm.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.dto.member.SiteMapPath;
import com.includesys.sm.service.manager.CMSMenuService;

@Aspect
@Order(3)
public class ControllerLoggingAspect
{
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest requestI;
	
	@Autowired
	private CMSMenuService cmsMenuService;		
	
	//@Before("execution(public * com.includesys.sm.service.*.*.*getList*(..))")		
	//@Before("execution(public * com.includesys.sm.service.*.*.*(..))")
	
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

	@Pointcut("execution(public * com.includesys.sm.service.websites..*(..))")
	public void requestWebsites(){ }	

	@Pointcut("execution(public * com.includesys.sm.service.csManager..*(..))")
	public void requestCsManager(){ }	

	@Pointcut("execution(public * com.includesys.sm.service.smsManager..*(..))")
	public void requestSmsManager(){ }	
	/**
	 * 뎁스 표시
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before(value="requestManager() || requestcashback() || requestProduct() || requestPoint() || requestCommon() || requestWebsites() || requestCsManager() || requestSmsManager()")
	public void controllerLoggingHandler(JoinPoint joinPoint) throws Throwable
	{
		
		
		/*
		System.out.println("[CALL AOP] ControllerLoggingAspect.controllerLoggingHandler() ");
		
		HttpServletRequest requestM = null;
		
		for(Object obj : joinPoint.getArgs())
		{
			if(obj instanceof HttpServletRequest)
			{
				requestM = (HttpServletRequest)obj;
			}
		}	
		
		if(requestM == null){
			//System.out.println(" 1.controllerLoggingHandler() requestM=NULL");
		}else{
			//System.out.println(" 1.controllerLoggingHandler() requestM=NOT NULL");
		}

		if(requestI == null){
			System.out.println(" 1.controllerLoggingHandler() requestI=NULL");
		}else{
			System.out.println(" 1.controllerLoggingHandler() requestI=NOT NULL");
		}
		
		HttpServletRequest request = requestM != null ? requestM : requestI;
		
		String requestURI 	= request.getRequestURI() != null ? request.getRequestURI().replaceAll("//", "/") : "";
		String queryString 	= request.getQueryString();
		System.out.println(" 2.controllerLoggingHandler() requestURI="+requestURI);
		
		if(!requestURI.equals("/manager/login.do") && !requestURI.equals("/manager/reqAccount.do") 
				&& !requestURI.equals("/manager/adminIdCheck.do") && !requestURI.equals("/manager/logout.do")
				&& !requestURI.equals("/member/zipcode.do") 
				&& !requestURI.equals("/manager/myInfo.do") && requestURI.indexOf("ifrm")==-1
			){
			

			System.out.println(" 3.controllerLoggingHandler() 3");
			CMSMenu nowMainMenuInfo = null, nowSubMenuInfo = null;
			//List<CMSMenu> menusInfo = (List<CMSMenu>)session.getAttribute("aclList");	//id의 tbl_mapping join tbl_cmsmenu 
			List<CMSMenu> menusInfo = (List<CMSMenu>)session.getAttribute("allList");
			
			System.out.println(" .controllerLoggingHandler()        #requestURI = "+requestURI);
			if(menusInfo != null){
				for(CMSMenu cmsMenu : menusInfo)
				{
					
					System.out.println(" .controllerLoggingHandler()cmsMenu.getFd_url() = "+cmsMenu.getFd_url());
					if(requestURI.equals(cmsMenu.getFd_url()))
					{
						nowSubMenuInfo = cmsMenu;
						System.out.println(" 4.controllerLoggingHandler() URL SET="+cmsMenu.getFd_url());
					}
				}
				
				System.out.println(" 5.controllerLoggingHandler() queryString="+queryString);
				if(queryString != null && !queryString.equals(""))
				{
					System.out.println(" 6.controllerLoggingHandler() 4");
					requestURI = requestURI + "?" + queryString;
					
					for(CMSMenu cmsMenu : menusInfo)
					{
						if(requestURI.equals(cmsMenu.getFd_url()))
						{
							nowSubMenuInfo = cmsMenu;
							System.out.println(" 7.controllerLoggingHandler() URL SET="+cmsMenu.getFd_url());
						}
					}				
				}
				System.out.println(" 6-1.controllerLoggingHandler()");
				nowMainMenuInfo = cmsMenuService.get(nowSubMenuInfo.getFd_group_code());
				System.out.println(" 6-2.controllerLoggingHandler()");
				SiteMapPath siteMapPath = new SiteMapPath(nowMainMenuInfo.getPk_url_code(), nowMainMenuInfo.getFd_name(), nowSubMenuInfo.getPk_url_code(), nowSubMenuInfo.getFd_name());
				System.out.println(" 6-3.controllerLoggingHandler()");
							
				request.setAttribute("siteMapPath", siteMapPath);
				System.out.println(" .controllerLoggingHandler() 9");
			}
		}*/
	}
	
	//수정
	//@Before("execution(public * com.includesys.sm.service.*.*.*modify*(..))")
	//public void controllerLoggingHandler1(JoinPoint joinPoint) throws Throwable {
	//	controllerLoggingHandler(joinPoint);
	//}
}
