package com.includesys.sm.controller.sms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.webUtil.PageNavi;



@Controller("sms.SmsController")
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value="/smsList.do")
	public ModelAndView getSmsList(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Sms Controller ] getSmsList");
		int page, pageSize	= 10;
		int totalCount = 0;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/manager/adminList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/sms/smsList");
		
		return mav;
	}
	
	@RequestMapping(value="/smsDetail.do")
	public ModelAndView getSmsDetail(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Sms Controller ] getSmsDetail");
		
		ModelAndView mav = new ModelAndView("/sms/smsDetail");
		return mav;
	}
	
	
}

