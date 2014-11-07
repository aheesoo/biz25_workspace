package com.includesys.sm.controller.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.service.manager.IPACLService;
import com.includesys.sm.util.MapKeyConvert;

@Controller
@RequestMapping("/manager")
public class IPACLController
{
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private IPACLService ipACLService;
	
	@RequestMapping("/ipList.do")
	public ModelAndView ipList()
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipList()");
		
		int totalCount = ipACLService.getCount("");
		List<Map<String, Object>> ipList = MapKeyConvert.toLowersVO(ipACLService.getList(""));
		
		ModelAndView mav = new ModelAndView("manager/ipList");
		
		mav.addObject("ipList", ipList);
		mav.addObject("totalCount", totalCount);
		
		return mav;
	}
	
	@RequestMapping("/ipRegister.do")
	public ModelAndView ipRegisterForm(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipRegisterForm()");
		
		return new ModelAndView("manager/ipRegister");
	}
	
	@RequestMapping(value="/ipRegister.do", params="event=register")
	public ModelAndView ipRegister(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipRegister() event=register");
		
		String script = "";
		ModelAndView mav = new ModelAndView("manager/ipRegister");
		
		try
		{
			LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
			SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
			String dateStr = ft.format(new Date());
			
			map.put("fd_admin_id", loginInfo.getPk_member_id());
			map.put("fd_reg_date", dateStr);
			
			ipACLService.register(map);
			script = "alert('정상적으로 등록 처리 되었습니다.');location.href = '/manager/ipList.do';";
		}
		catch(Exception ex)
		{
			script = "alert('등록 과정중 오류가 발생하였습니다.\\n다시 시도하여 주십시오.');";
		}
		
		mav.addObject("script", script);
		return mav;
	}
	
	@RequestMapping("/ipModify.do")
	public ModelAndView ipModifyForm(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipModifyForm()");
		
		int pk_ip_acl = Integer.parseInt(map.get("pk_ip_acl").toString());
		Map<String, Object> ipACL = MapKeyConvert.toLowerVO(ipACLService.get(pk_ip_acl));
		
		ModelAndView mav = new ModelAndView("manager/ipModify");
		mav.addObject("ipACL", ipACL);
		return mav;
	}
	
	@RequestMapping(value="/ipModify.do", params="event=modify")
	public ModelAndView ipModify(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipModify()");
		
		String script = "";
		ModelAndView mav = new ModelAndView("manager/ipModify");
		
		try
		{
			ipACLService.modify(map);
			script = "alert('정상적으로 수정 처리 되었습니다.');location.href = '/manager/ipList.do';";			
		}
		catch(Exception ex)
		{
			mav.addObject("ipACL", map);
			script = "alert('수정 과정중 오류가 발생하였습니다.\\n다시 시도하여 주십시오.');";
		}
		
		mav.addObject("script", script);
		return mav;		
	}	
	
	@RequestMapping(value="/ipModify.do", params="event=remove")
	public ModelAndView ipRemove(@RequestParam Map<String, Object> map)
	{
		System.out.println("\n[CALL Controller ] IPACLController.ipRemove()");
		
		int pk_ip_acl = Integer.parseInt(map.get("pk_ip_acl").toString()); 
		
		String script = "";
		ModelAndView mav = new ModelAndView("manager/ipModify");
		
		try
		{
			ipACLService.remvoe(pk_ip_acl);
			script = "alert('정상적으로 삭제 처리 되었습니다.');location.href = '/manager/ipList.do';";			
		}
		catch(Exception ex)
		{
			mav.addObject("ipACL", map);
			script = "alert('삭제 과정중 오류가 발생하였습니다.\\n다시 시도하여 주십시오.');";
		}
		
		mav.addObject("script", script);
		return mav;			
	}	
}
