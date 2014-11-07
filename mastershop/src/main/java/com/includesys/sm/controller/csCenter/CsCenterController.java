package com.includesys.sm.controller.csCenter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.csCenter.Faq;
import com.includesys.sm.dto.csCenter.Notice;
import com.includesys.sm.service.csCenter.FaqService;
import com.includesys.sm.service.csCenter.NoticeService;
import com.includesys.sm.webUtil.PageNavi;

@Controller
@RequestMapping("/csCenter")
public class CsCenterController {

	private static Logger logger = LoggerFactory.getLogger(CsCenterController.class);
	
	
	@Autowired
	private HttpSession session;
		
	@Autowired
	private NoticeService noticeServie;
	
	@Autowired
	private FaqService faqServie;
	
	
	
	/**
	 * 공지사항
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/noticeList.do" )
	public ModelAndView noticeList(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.noticeList()");
		
		int page, pageSize		= 10;
		String searchColumn	= request.getParameter("searchColumn")	!= null  && !request.getParameter("searchColumn").equals("") ? request.getParameter("searchColumn").trim() : "";
		String searchString		= request.getParameter("searchString")	!= null && !request.getParameter("searchString").equals("") ? request.getParameter("searchString").trim() : "";
		page							= Integer.parseInt(request.getParameter("page") != null && !request.getParameter("page").equals("")  ? request.getParameter("page").trim() : "1");
		
		
		boolean proceStatus = true;			// 처리 상황
		
		List<Notice> noticeList = null;  		// 리스트 
		int noticeListCount = 0;					// 리스트 카운트	
					
		PageHelper pageHelper 	= new PageHelper(page, (page-1)*pageSize, pageSize, searchColumn, searchString);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		map.remove("page");		
		map.put("pageStart", (page-1)*10);
		map.put("pageSize", pageSize);	
		
		map.put("fd_open_yn", "Y");
		map.put("searchString",searchString);
		
		if(proceStatus){
			try{
				noticeListCount = noticeServie.getNoticeListCount(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				noticeList = noticeServie.getNoticeList(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		//PageNavagation
		PageNavi pageNavi = new PageNavi();
		pageNavi.setPageHelper("/csCenter/noticeList.do", noticeListCount, pageHelper);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/notice/noticeList");
		mav.addObject("noticeListCount" , noticeListCount);		
		mav.addObject("noticeList" , noticeList);
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("pageHelper", pageHelper);
		mav.addObject("page", page);
	
		return mav;
	}
	
	/**
	 * 공지사항 상세보기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/noticeListView.do" )
	public ModelAndView noticeListView(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.noticeListView()");
				
		String pk_seq	= request.getParameter("seq")	!= null  && !request.getParameter("seq").equals("") ? request.getParameter("seq").trim() : "";
		String page		= request.getParameter("page")	!= null  && !request.getParameter("page").equals("") ? request.getParameter("page").trim() : "";
		
		boolean proceStatus = true;			// 처리 상황
				
		Notice viewMain = null;
		Notice viewPre = null;
		Notice viewNext = null;
		
		if(proceStatus){
			try{
				noticeServie.updateViewHit(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				viewMain = noticeServie.getNoticeVew(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		if(proceStatus){
			try{
				viewPre = noticeServie.getNoticeVewPre(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		if(proceStatus){
			try{
				viewNext = noticeServie.getNoticeVewNext(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/notice/noticeListView");				
		mav.addObject("viewMain" , viewMain);
		mav.addObject("viewPre", viewPre);
		mav.addObject("viewNext", viewNext);
		mav.addObject("page", page);
	
		return mav;
	}
	
	
	/**
	 * 상품안내
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/productInfo.do" )
	public ModelAndView productInfo(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.productInfo()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/product/productInfo");
	
		return mav;		
	}
	
	/**
	 * FAQ
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/faqList.do" )
	public ModelAndView faqList(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.faqList()");
		
		int page, pageSize		= 10;
		String searchColumn	= request.getParameter("searchColumn")	!= null  && !request.getParameter("searchColumn").equals("") ? request.getParameter("searchColumn").trim() : "";
		String searchString		= request.getParameter("searchString")	!= null && !request.getParameter("searchString").equals("") ? request.getParameter("searchString").trim() : "";
		page							= Integer.parseInt(request.getParameter("page") != null && !request.getParameter("page").equals("")  ? request.getParameter("page").trim() : "1");
		
		
		boolean proceStatus = true;			// 처리 상황
		
		List<Faq> faqList = null;  		// 리스트 
		List<Faq> faqCodeList = null;  		// 리스트 
		int faqListCount = 0;					// 리스트 카운트	
					
		PageHelper pageHelper 	= new PageHelper(page, (page-1)*pageSize, pageSize, searchColumn, searchString);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		map.remove("page");		
		map.put("pageStart", (page-1)*10);
		map.put("pageSize", pageSize);
		
		map.put("searchString",searchString);
		map.put("searchColumn",searchColumn);
		
		
		if(proceStatus){
			try{
				faqCodeList = faqServie.getFaqCodeList("3600");
			}catch(Exception e){		
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				faqListCount = faqServie.getFaqListCount(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				faqList = faqServie.getFaqList(map);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		//PageNavagation
		PageNavi pageNavi = new PageNavi();
		pageNavi.setPageHelper("/csCenter/faqList.do", faqListCount, pageHelper);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/faq/faqList");
		mav.addObject("faqListCount" , faqListCount);
		
		mav.addObject("faqCodeList" , faqCodeList);
		mav.addObject("faqList" , faqList);
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("pageHelper", pageHelper);
		mav.addObject("page", page);
	
		
		return mav;		
	}
	
	/**
	 * FAQ 상세보기
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/faqListView.do" )
	public ModelAndView faqListView(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.faqList()");
		
		String pk_seq	= request.getParameter("seq")	!= null  && !request.getParameter("seq").equals("") ? request.getParameter("seq").trim() : "";
		String page		= request.getParameter("page")	!= null  && !request.getParameter("page").equals("") ? request.getParameter("page").trim() : "";

		boolean proceStatus = true;			// 처리 상황
				
		Faq viewMain = null;
		Faq viewPre = null;
		Faq viewNext = null;
		
		if(proceStatus){
			try{
				faqServie.updateViewHit(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();
				
			}
		}
		
		if(proceStatus){
			try{
				viewMain = faqServie.getFaqVew(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		if(proceStatus){
			try{
				viewPre = faqServie.getFaqVewPre(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}
		
		if(proceStatus){
			try{
				viewNext = faqServie.getFaqVewNext(pk_seq);
			}catch(Exception e){
				proceStatus = false;
				e.printStackTrace();				
			}
		}		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/faq/faqListView");
		mav.addObject("page", page);
		mav.addObject("viewMain" , viewMain);
		mav.addObject("viewPre", viewPre);
		mav.addObject("viewNext", viewNext);
	
		return mav;	
	}
	
	/**
	 * 이용약관
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/agreement.do" )
	public ModelAndView agreement(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.agreement()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/agreement/agreement");
	
		return mav;		
	}
	
	/**
	 * 개인정보취급방침
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/policy.do" )
	public ModelAndView policy(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsCenterController.policy()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csCenter/policy/policy");
	
		return mav;		
	}

	
	
}
