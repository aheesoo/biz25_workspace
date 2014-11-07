package com.includesys.sm.controller.dashboard;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("dashboard.DashboardController")
@RequestMapping("/dashboard")
public class DashboardController {
	
/*	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdminService adminService; 
	
	@Autowired
	private CMSMenuService cmsMenuService;	
	*/
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/info.do")
	public ModelAndView abcd(){
		System.out.println("[CALL Controller] dashboard info");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/dashboard/info");

		return mav;
	}
	
	
}

