package com.includesys.sm.controller.counsel;

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



@Controller("counsel.CounselController")
@RequestMapping("/counsel")
public class CounselController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/counselList.do")
	public ModelAndView getcounselList(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Counsel Controller ] getcounselList");
		int page, pageSize	= 10;
		int totalCount = 0;
		page 				= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/counsel/counselList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.setViewName("/counsel/counselList");
		
		return mav;
	}
	
	@RequestMapping(value="/counselRegister.do")
	public ModelAndView getcounselRegister(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Counsel Controller ] getcounselRegister");
		
		ModelAndView mav = new ModelAndView("/counsel/counselRegister");
		return mav;
	}
	
	@RequestMapping(value="/counselModify.do")
	public ModelAndView getcounselModify(@RequestParam Map<String, Object> map){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Counsel Controller ] getcounselModify");
		
		ModelAndView mav = new ModelAndView("/counsel/counselModify");
		return mav;
	}
	
	
	
	
}

