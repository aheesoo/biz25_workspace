package com.includesys.sm.controller.status;

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



@Controller("status.StatusController")
@RequestMapping("/status")
public class StatusController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(value="/statusSmsList.do")
	public ModelAndView getstatusSmsList(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Status Controller ] getstatusSmsList");
		int page, pageSize	= 10;
		int totalCount = 0;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/status/statusSmsList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/status/statusSmsList");
		
		return mav;
	}
	
	@RequestMapping(value="/statusSmsTotalList.do")
	public ModelAndView getstatusSmsTotalList(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Status Controller ] getstatusSmsTotalList");
		int page, pageSize	= 10;
		int totalCount = 0;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/status/statusSmsTotalList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/status/statusSmsTotalList");
		
		return mav;
	}
	
	@RequestMapping(value="/statusBillList.do")
	public ModelAndView getstatusBillList(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Status Controller ] getstatusBillList");
		int page, pageSize	= 10;
		int totalCount = 0;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/status/statusBillList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/status/statusBillList");
		
		return mav;
	}
	
}

