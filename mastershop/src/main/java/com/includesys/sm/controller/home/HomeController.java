package com.includesys.sm.controller.home;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.includesys.sm.dto.csCenter.Notice;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.service.csCenter.NoticeService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.service.myinfo.CoinService;
import com.inicis.inipay.INIpay50;

@RequestMapping("/home")
@Controller("home.HomeController")
public class HomeController {
	
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NoticeService noticeServie;

	@Autowired
	private CoinService coinService;
	
	@Autowired
	private HttpSession session;
	
	
	/**
	 * 메인 프레임
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/main.do" )
	public ModelAndView mainFrame(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MemberController.mainFrame()");
		
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("home/main");

		return mav;		
	}
	
	/**
	 * 해상도에 따른 화면사이즈 변경 퍼블리싱 페이지
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value="/main_mod.do" )
	public ModelAndView mainFrame_mod(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] MemberController.main_mod()");
		
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("home/main_mod");

		return mav;		
	}*/
	
	
	/**
	 * 메인 페이지
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mainFrame.do" )
	public ModelAndView main(HttpServletRequest request)	{
		
		System.out.println("\n####1");
		System.out.println("\n[CALL Controller] MemberController.memberRegister()");
		

		int page;
		String script = "";
		String pk_seq		= request.getParameter("pk_seq")				!= null ? request.getParameter("pk_seq").trim()			 : "";
		String searchColumn	= request.getParameter("searchColumn")			!= null ? request.getParameter("searchColumn").trim()	 : "";
		String searchString	= request.getParameter("searchString")			!= null ? request.getParameter("searchString").trim()	 : "";
		page				= Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page").trim()			 : "1");

		String reg_mod		= request.getParameter("reg_mod")				!= null ? request.getParameter("reg_mod").trim()		 : "";
		String fd_type_code	= request.getParameter("fd_type_code")			!= null ? request.getParameter("fd_type_code").trim()	 : "";
		String fd_open_yn	= request.getParameter("fd_open_yn")			!= null ? request.getParameter("fd_open_yn").trim()		 : "";
		String fd_title		= request.getParameter("fd_title")				!= null ? request.getParameter("fd_title").trim()		 : "";
		String fd_content	= request.getParameter("fd_content")			!= null ? request.getParameter("fd_content").trim()		 : "";

		String searchParam	= "?pk_seq="+pk_seq+"&page="+page+"&searchColumn="+searchColumn+"&searchString="+searchString;

		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//String attrStr = (String)request.getAttribute("A");
		//System.out.print("=========attr:"+attrStr);
		
		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String now_date =  ft.format(new Date( ));


		//DB값 가져오기 Notice - s
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fd_open_yn", "Y");
		map.put("viewType", "main");
		
		boolean proceStatus = true;					// 처리 상황
		
		List<Notice> noticeList = null;				// 리스트 
		int noticeListCount = 0;					// 리스트 카운트	

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
		//DB값 가져오기 Notice - e

		
	    //========================== 약관동의 START ==================================
		int agreeCount = 0;
		try {
			agreeCount = memberService.getAgreeCount(loginInfo.getPk_member_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    //-------------------------- 약관동의 END ------------------------------------
		
		
		//요금 납부 확인
		Map<String, Object> result_map = checkPrice(loginInfo.getPk_member_id());
	    String sum_fd_price		= ""+ result_map.get("sum_fd_price");
	    String sum_pay_price	= ""+ result_map.get("sum_pay_price");
	    int cnt					= Integer.parseInt(""+result_map.get("cnt"));
	
	    if(cnt >= 2){
	    	//1개월 이상 체납된 사용자 (알림처리)
	    	
	    	script = "payMsg2("+sum_fd_price+","+sum_pay_price+");";
			
	    }else if(cnt >= 1){
	    	//1개월 이상 체납된 사용자 (알림처리)
	    	
	    	script = "payMsg("+sum_fd_price+","+sum_pay_price+");";
			
	    }
	    
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginInfo", loginInfo);
		mav.setViewName("/home/mainFrame");
		mav.addObject("noticeListCount" , noticeListCount);		
		mav.addObject("noticeList" , noticeList);
		mav.addObject("agreeCount" , agreeCount);
		mav.addObject("sum_fd_price",sum_fd_price);
		mav.addObject("sum_pay_price",sum_pay_price);
		mav.addObject("script",script);
		
		mav.addObject("payCnt",cnt);
		
		
		
		//login 처리controller에서 redirect로 전달값 받기
/*		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String tmp_script = (String)flashMap.get("script");
		if(tmp_script.length()>0){
			mav.addObject("script", script);
		}*/

		//List<Code> gubun = memberService.getGubun(); //회원상태 구분	
		//mav.addObject("gubun", gubun);
		
		return mav;		
	}
	
	
	public Map<String, Object> checkPrice(String member_id)	{
		
	    //-------------------------- 미납요금 체크 START ------------------------------------
		//사용자 정보 취득
		Map<String, Object> result_map = null;
		
	    try {
		    
		    result_map = memberService.checkPay(member_id);

		} catch (Exception e) {
			System.out.println("요금 테이블 정보 확인바람"+member_id);
		}
	    
	    return result_map;
	    //-------------------------- 미납요금 체크   END ------------------------------------
	}
	
	/**
	 * 코인 충전
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/coinCharge.do" )
	public ModelAndView coinCharge(@RequestParam Map<String, Object> map)	{
		
		System.out.println("\n####1");
		System.out.println("\n[CALL Controller] MemberController.coinCharge()");
		

		String script="";
		
		ModelAndView mav = new ModelAndView();				
		
		mav.setViewName("/home/coin/charge");
		

		return mav;		
	}
	
	/**
	 * 코인 충전 처리 페이지
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/coinCharge.do" , params="event=proc")
	public JSONObject coinChargeProc(HttpServletRequest request )	{
		
		System.out.println("\n####1");
		System.out.println("\n[CALL Controller] MemberController.coinChargeProc()");
				
		String req_chargeBasePoint 		= request.getParameter("chargeBasePoint")	!= null ? request.getParameter("chargeBasePoint").trim() : "0";
		String req_chargeBonusPoint 	= request.getParameter("chargeBonusPoint")	!= null ? request.getParameter("chargeBonusPoint").trim() : "0";
		String req_chargeMoney 			= request.getParameter("chargeMoney")			!= null ? request.getParameter("chargeMoney").trim() : "0";
		String req_pay_type	 				= request.getParameter("gopaymethod")		!= null ? request.getParameter("gopaymethod").trim() : "0";
		String req_product_code	 		= request.getParameter("goodname")				!= null ? request.getParameter("goodname").trim() : "";
		
		 
		int fd_base_coin = 0;
		int fd_recharge_coin 	= Integer.parseInt(req_chargeBasePoint);
		int fd_bonus_coin 		= Integer.parseInt(req_chargeBonusPoint);
		int fd_total_coin 			= fd_recharge_coin+fd_bonus_coin;
	
		int fd_pay_mount 		= Integer.parseInt(req_chargeMoney);
		int fd_pay_type 			= Integer.parseInt(req_pay_type);
		
		String fk_member_id = "";
		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		fk_member_id = loginInfo.getPk_member_id();
		
		String rtn_code = "200";
		
		int result_total_coin = 0;
		int result_recharge_coin = 0;
		int result_base_coin = 0;
		
		JSONObject jsonObj = new JSONObject();
		
		HashMap<String, Object> hmap = new HashMap<>();
		
		hmap.put("fk_member_id", fk_member_id);
		
		hmap.put("fd_base_coin", fd_base_coin);
		hmap.put("fd_recharge_coin", fd_recharge_coin);
		hmap.put("fd_bonus_coin", fd_bonus_coin);
		hmap.put("fd_total_coin", fd_total_coin);
		
		hmap.put("fd_pay_mount", fd_pay_mount);
		hmap.put("fd_pay_type", fd_pay_type);
		hmap.put("fd_product_code", req_product_code);
		
		
		if(fd_pay_type!=0){
			try{
				int result = coinService.insertCoinChargeProc(hmap);
						
				if(result>0){
					
					HashMap<String, String> getMap = new HashMap<>();
					getMap.put("member_id", fk_member_id);
					Coin coin = coinService.getCoin(getMap);
					
					if(coin!=null){
						result_total_coin = coin.getFd_total_coin();
						result_recharge_coin = coin.getFd_recharge_coin();
						result_base_coin = (coin.getFd_base_coin()+coin.getFd_bonus_coin()); 
						
						jsonObj.put("result_total_coin" , result_total_coin);
						jsonObj.put("result_recharge_coin" , result_recharge_coin);
						jsonObj.put("result_base_coin" , result_base_coin);
						
					}
				}else{
					rtn_code = "310";
				}
				
			}catch(Exception e){
				rtn_code = "300";
				e.printStackTrace();
				
			}
		}else{
			rtn_code = "301";
		}
		
		
		jsonObj.put("rtn_code" , rtn_code);
		
		return jsonObj;		
	}
	
	
	
}
