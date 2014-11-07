package com.includesys.sm.controller.smsManager;

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
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.smsManager.Emoticon;
import com.includesys.sm.service.smsManager.EmoticonService;
import com.includesys.sm.webUtil.PageNavi;


@Controller
@RequestMapping("/emoticon")
public class EmoticonController {

	private static Logger logger = LoggerFactory.getLogger(EmoticonController.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmoticonService emoticonService;
	
	/**
	 * 문자관리 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list.do" )
	public ModelAndView emoticonList(HttpServletRequest request)	{
		
		System.out.println("\n[EmoticonController] SmsManagerController.smsManagerMain()");
		
		int page, pageSize		= 9;
		String searchColumn	= request.getParameter("searchColumn")	!= null  && !request.getParameter("searchColumn").equals("") ? request.getParameter("searchColumn").trim() : "U";
		String searchString		= request.getParameter("searchString")	!= null && !request.getParameter("searchString").equals("") ? request.getParameter("searchString").trim() : "";
		String search_type		= request.getParameter("search_type")	!= null && !request.getParameter("search_type").equals("") ? request.getParameter("search_type").trim() : "S";
		
		//String fk_tel				= request.getParameter("fk_tel")	!= null && !request.getParameter("fk_tel").equals("") ? request.getParameter("fk_tel").trim() : "";
		page							= Integer.parseInt(request.getParameter("page") != null && !request.getParameter("page").equals("")  ? request.getParameter("page").trim() : "1");
		
		
		
		boolean proceStatus = true;			// 처리 상황
		
		String  msg_type = "";
		String  msg_sub_type = "";		
		 if(search_type.equals("L")){
			 msg_type = "4";
			 msg_sub_type = "1";
			 
		 }else if(search_type.equals("M")){
			 msg_type = "4";
			 msg_sub_type = "3";
			 pageSize = 6;
		 }else{			 
			 msg_type = "1";
			 msg_sub_type = "1";
			 
		 }
		 
		//PageHelper pageHelper 	= new PageHelper(page,(page-1)*pageSize, pageSize, searchColumn, searchString);
		PageHelper pageHelper 	= new PageHelper();
	
		pageHelper.setPage(pageSize);
		pageHelper.setPageStart( (page-1)*pageSize);
		pageHelper.setPageSize(pageSize);
		pageHelper.setSearchColumn(searchColumn);
		pageHelper.setSearchString(searchString);
		pageHelper.setSearch_type(search_type);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.remove("page");		
		
		
		
		map.put("pageStart", (page-1)*pageSize);
		map.put("pageSize", pageSize);	
		
		map.put("searchString",searchString);
		//map.put("searchColumn",searchColumn);
		
		//map.put("fk_tel",fk_tel);

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		String member_id = "";
		member_id= loginInfo.getPk_member_id();
		
		map.put("fk_member_id",member_id);
		
		 map.put("msg_type",msg_type);
		 map.put("msg_sub_type",msg_sub_type);
		 map.put("fd_sms_type",search_type);
		 
		
		 int emoticonListCount = 0;		
		List<Emoticon> emoticonList = null;
		 
		
		if(searchColumn.equals("U")){
			if(proceStatus){
				try{
					emoticonListCount = emoticonService.getUserSendMsgListCount(map);
				}catch(Exception e){
					proceStatus = false;
					e.printStackTrace();
					
				}
			}
			
			if(proceStatus && emoticonListCount>0){
				try{
					emoticonList = emoticonService.getUserSendMsgList(map);
				}catch(Exception e){
					proceStatus = false;
					e.printStackTrace();
					
				}
			}
		}else{
			if(proceStatus){
				try{
					emoticonListCount = emoticonService.getRecommendMsgListCount(map);
				}catch(Exception e){
					proceStatus = false;
					e.printStackTrace();
					
				}
			}
			
			
			if(proceStatus && emoticonListCount>0){
				try{
					emoticonList = emoticonService.getRecommendMsgList(map);
				}catch(Exception e){
					proceStatus = false;
					e.printStackTrace();
					
				}
			}
			
		}
		//PageNavagation
		PageNavi pageNavi = new PageNavi();
		pageNavi.setPageHelper("/emoticon/list.do", emoticonListCount, pageHelper);
		pageNavi.make();
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/emoticon/ifrmList");
		
		//mav.addObject("userMsgListCount", userMsgListCount);
		//mav.addObject("userMsgList", userMsgList);
		//mav.addObject("recommendMsgListCount", recommendMsgListCount);
		//mav.addObject("recommendMsgList", recommendMsgList);
		
		mav.addObject("emoticonListCount", emoticonListCount);
		mav.addObject("emoticonList", emoticonList);
		
		mav.addObject("searchColumn", searchColumn);
		mav.addObject("searchString", searchString);
		mav.addObject("search_type", search_type);
		
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("pageHelper", pageHelper);
		mav.addObject("page", page);
	
		return mav;
	}

}
