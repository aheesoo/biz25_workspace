package com.includesys.sm.controller.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.common.CallLog;
import com.includesys.sm.dto.common.Common;
import com.includesys.sm.dto.common.Customer;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.service.common.CommonService;
import com.includesys.sm.service.manager.AdminService;
import com.includesys.sm.service.manager.LoginService;
import com.includesys.sm.service.myinfo.CoinService;
import com.includesys.sm.util.StrUtils;

@Controller("common.CommonController")
@RequestMapping("/common")
public class CommonController {
	private static Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AdminService adminService; 
	
	@Autowired
   private CoinService coinService;	
		
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
    private CommonService commonService;
	
	@Value("#{config['openapi.authkey']}")
	private  String openapi_authkey; 

	@Value("#{config['openapi.server']}")
	private  String openapi_server; 


	
	@SuppressWarnings("unchecked")
	@ResponseBody	
	@RequestMapping(value="/productCodeList.do")
	public JSONObject getProductCodeList(@RequestParam Map<String, String> map, HttpServletRequest request){
		//생성자에서 받은 문자열은 뷰이름을 의미
		System.out.println("[CALL Common Controller ] getProductCodeList");
		
		//String fd_up_code = map.get("up_code") == null ? "" : map.get("up_code").toString();
		String search_code = map.get("search_code") == null ? "" : map.get("search_code").toString();
		String search_type = map.get("search_type") == null ? "" : map.get("search_type").toString();
		
		String rtn_code = "200";
		List<Common> depth_list = null;
		
		boolean skip_process = true;
		
		JSONObject jsonObj = new JSONObject();	//object
		
		if(search_type.equals("list")){								// 리스트 타입 검색
			try{			
				Common common = new Common();
				common.setFd_up_code(search_code);
				depth_list = commonService.getProductCodeList(common);
				JSONArray jsonArr 	= new JSONArray();	//list
				
				if(depth_list.size()>0){
				
					for(Common item:depth_list){
						JSONObject jsonItem	= new JSONObject();	// json code_list

						jsonItem.put("pk_product_code"		, item.getPk_product_code()		);
						jsonItem.put("fd_up_code"			, item.getFd_up_code() 			);
						jsonItem.put("fd_code_name"			, item.getFd_code_name() 		);
						jsonItem.put("fd_point_level_5"		, item.getFd_point_level_5() 	);
						jsonItem.put("fd_point_level_10"	, item.getFd_point_level_10() 	);

						jsonArr.add(jsonItem);
					}	
				}
				jsonObj.put("list"		, jsonArr);
			}catch(Exception e){
				skip_process = false;
				rtn_code = "301";
				e.printStackTrace();
			}
		}else if(search_type.equals("view")){						// 뷰페이지 검색시
				try{
					
					Common item = commonService.getProductCodeListView(search_code);
					
					if(item !=null){
						jsonObj.put("pk_product_code"		, item.getPk_product_code()		);
						jsonObj.put("fd_up_code"			, item.getFd_up_code() 			);
						jsonObj.put("fd_code_name"			, item.getFd_code_name() 		);
						jsonObj.put("fd_point_level_5"		, item.getFd_point_level_5() 	);
						jsonObj.put("fd_point_level_10"		, item.getFd_point_level_10() 	);
					}
				}catch(Exception e){skip_process = false;
					rtn_code = "301";
					e.printStackTrace();
				}
		}
			
		jsonObj.put("rtn_code", rtn_code);
		
		return jsonObj;
	}
	

	
	/**
	 * Open Api 로그인 처리 페이지
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/openApiLogin.do" )
	public ModelAndView main(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] openApiLoginController.openApiLoginRegister()");
		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
			
		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//LNB 메뉴 코인정보 표기
		Coin coin = new Coin();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("member_id", loginInfo.getPk_member_id());
		coin = coinService.getCoin(map);
		
		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String now_date =  ft.format(new Date( ));

		ModelAndView mav = new ModelAndView();
		mav.addObject("loginInfo", loginInfo);
		mav.addObject("coin", coin);
		mav.addObject("openapi_authkey", openapi_authkey);
		mav.addObject("openapi_server", openapi_server);
		mav.setViewName("/common/openApiLogin");
		
		return mav;
	}


	
	
	/**
	 * Open Api 전화 수신 리스트 가져오기
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/ajaxBlank.do", params="type=member_log_select")
	public JSONObject member_log_list(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CommonController.member_log_list()");
		
		String fd_tel		= request.getParameter("fd_tel")		!= null ? request.getParameter("fd_tel").trim()		 : "";
		String fd_rcv_tel	= request.getParameter("fd_rcv_tel")	!= null ? request.getParameter("fd_rcv_tel").trim()	 : "";
		String sResult		= request.getParameter("sResult")		!= null ? request.getParameter("sResult").trim()	 : "";
		String sClSeqno		= request.getParameter("sClSeqno")		!= null ? request.getParameter("sClSeqno").trim()	 : "";
		String result		= "false";

		//리턴할 json
		JSONObject jsonObj = new JSONObject();	//object
		jsonObj.put("sResult", sResult);

		
		//로그인 값 가져오기
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));

		String[] nowDataArray = now_date.split("-");
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);
		
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
//		sClSeqno
		//로그저장 삭제
		//카운트에 따라 Insert/Update
/*
		Map<String, String> callMap = new HashMap();
		callMap.put("fk_member_id", loginInfo.getPk_member_id());
		callMap.put("pk_rcv_tel", fd_rcv_tel);
		callMap.put("fd_seqno", sClSeqno);
		
		int logCount = commonService.callLogCount(callMap);
		
		System.out.println("logCount = "+logCount);
		
		try {
			if(logCount<1){
				CallLog callLog = new CallLog();
				
				callLog.setFk_member_id(loginInfo.getPk_member_id());
				callLog.setFd_seqno(sClSeqno);
				callLog.setFk_tel(fd_tel);
				callLog.setPk_rcv_tel(fd_rcv_tel);
				callLog.setPk_reg_date(re_now);
				callLog.setFd_reg_yy(nowDataArray[0]);
				callLog.setFd_reg_mm(nowDataArray[1]);
				callLog.setFd_reg_dd(nowDataArray[2]);
				callLog.setFd_reg_hh(nowDataArray[3]);
				callLog.setFd_reg_mi(nowDataArray[4]);
				callLog.setFd_reg_ss(nowDataArray[5]);
				callLog.setFd_reg_week(fd_reg_week);
				callLog.setFd_openapi_rc_code(sResult);
				

				callLog.setFd_call_start(re_now);
				commonService.callLogReg(callLog);
				System.out.println("call 전화번호 등록 완료");
			}else{
				CallLog callLog = new CallLog();
				
				callLog.setFk_member_id(loginInfo.getPk_member_id());
				callLog.setFd_seqno(sClSeqno);
				callLog.setFk_tel(fd_tel);
				callLog.setPk_rcv_tel(fd_rcv_tel);
				callLog.setFd_openapi_rc_code(sResult);

				commonService.callLogUp(callLog);
				System.out.println("call 전화번호 수정 완료");
			}
*/			

			//폰북 사용자 정보(phonebook) - s
			Map<String, Object> logCustomMap = new HashMap<String, Object>();
			logCustomMap.put("fk_member_id", 	loginInfo.getPk_member_id());
			logCustomMap.put("fd_rcv_tel", 		fd_rcv_tel);
			logCustomMap.put("pk_customer_tel", fd_rcv_tel);
			CallLog listCustom = commonService.getCustom(logCustomMap);
			//폰북 사용자 정보(phonebook) - e

			
			//최근 수신내역 리스트
			Map<String, Object> logListMap = new HashMap<String, Object>();
			logListMap.put("fd_member_id", loginInfo.getPk_member_id());
			List<CallLog> logListCustom = commonService.getListView(logListMap);
			
			try{
				JSONArray jsonArr 	= new JSONArray();	//list
				//폰북 사용자 정보(phonebook) - s:
				//CALL_LOG 테이블에는 현재 전화온 내역이 바로 DB에 인서트 되기 전 상황으로 바로 보여줘야 하기에 첫번째는 사용자 정보를 바로 표기

				JSONObject jsonOneItem	= new JSONObject();	// json code_list

				/**
				var fd_call_date	= listdata.list[i].pk_date;
				var fd_user_name	= listdata.list[i].fd_user_name;
				var fk_tel			= listdata.list[i].fk_tel;
				var chkFlag			= listdata.list[i].chkData;
				var fd_seqno		= listdata.list[i].fd_seqno;
				
				var htmlS = "<tr style='cursor:pointer' onclick=\"chkOpen('"+chkFlag+"','"+fk_tel+"','"+fd_seqno+"')\">"
					+"<td class=\"pop_tb_nor_l\">"+fd_call_date+"</td>"
					+"<td class=\"pop_tb_nor_l\">"+fd_user_name+"</td>"
					+"<td class=\"pop_tb_nor_l\">"+fk_tel+"</td></tr>";
				*/

				String pk_date	 = re_now;
				String fd_name	 = "";
				String fk_tel	 = StrUtils.formatPhoneNo(fd_rcv_tel.toString(), "-");
				String fd_seqno	 = "";
				String chkData	 = "Ins";

				System.out.println("listCustom = "+listCustom);

				if(listCustom != null){
					pk_date	 = listCustom.getFd_reg_date() == null ? re_now : listCustom.getFd_reg_date().substring(0, pk_date.length()-2);

					fd_name	 = listCustom.getFd_user_name() == null ? "" : listCustom.getFd_user_name();
					fk_tel	 = listCustom.getFd_rcv_tel() == null ? StrUtils.formatPhoneNo(fd_rcv_tel.toString(), "-") : StrUtils.formatPhoneNo(listCustom.getFd_rcv_tel().toString(), "-");
					fd_seqno = listCustom.getPk_seqno() == null ? "" : listCustom.getPk_seqno();
					chkData	 = listCustom.getFd_new_date() == null	? "Ins" : "Mod";
				}

				jsonOneItem.put("pk_date", pk_date);
				jsonOneItem.put("fd_user_name", fd_name);
				jsonOneItem.put("fk_tel", fk_tel);
				jsonOneItem.put("fd_seqno", fd_seqno);
				jsonOneItem.put("chkData", chkData);

				jsonArr.add(jsonOneItem);
				//폰북 사용자 정보 (phonebook) - e

				
				//최근 수신내역 리스트
				if(logListCustom.size()>0){
					
					
					
					for(CallLog item : logListCustom){
						
						JSONObject jsonItem	= new JSONObject();	// json code_list
						
						pk_date	 = item.getFd_reg_date() == null ? "" : item.getFd_reg_date();
						pk_date	 = pk_date.substring(0, pk_date.length()-2);

						fd_name	 = item.getFd_user_name() == null	? "" : item.getFd_user_name();
						fk_tel	 = item.getFd_rcv_tel() == null		? "" : StrUtils.formatPhoneNo(item.getFd_rcv_tel().toString(), "-");
						fd_seqno = item.getPk_seqno() == null		? "" : item.getPk_seqno();
						chkData	 = item.getFd_new_date() == null	? "Ins" : "Mod";

						jsonItem.put("pk_date", pk_date);
						jsonItem.put("fd_user_name", fd_name);
						jsonItem.put("fk_tel", fk_tel);
						jsonItem.put("fd_seqno", fd_seqno);
						jsonItem.put("chkData", chkData);
						jsonArr.add(jsonItem);
					}
				}
				jsonObj.put("list", jsonArr);
				
				System.out.println("jsonObj = "+jsonObj);
			}catch(Exception e){
				e.printStackTrace();
			}

			System.out.println("회원 검색 완료 : "+jsonObj);

			//최근 수신내역 가져오기

			
/*
		}catch(Exception ex){
			logger.error(ex.getMessage());
			System.out.println("call 전화번호 등록/수정 실패");
		}
*/
		return jsonObj;
	}


	/**
	 * Open Api 리스트 가져오기
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/ajaxBlank.do", params="type=member_listView")
	public JSONObject member_listView(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CommonController.member_listView()");
		
		String fd_tel	= request.getParameter("fd_tel")		!= null ? request.getParameter("fd_tel").trim()		 : "";
		String opMod	= "";

		//리턴할 json
		JSONObject jsonObj = new JSONObject();	//object

		
		//로그인 값 가져오기
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));

		String[] nowDataArray = now_date.split("-");
		
		//요일
		Calendar cal = Calendar.getInstance();

		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		try {
			
			//최근 수신내역 가져오기
			String refd_tel = fd_tel.replaceAll("-", "");
			
			Map<String, Object> logListMap = new HashMap<String, Object>();
			logListMap.put("fd_member_id", 	loginInfo.getPk_member_id());
			logListMap.put("fd_rcv_tel", 	refd_tel);
			
/*			if(opMod.equals("Ins")){
				logListMap.put("limit", 1);
			}else if(opMod.equals("Mod")){
				
			}*/
			
			logListMap.put("limit", 5);

			//최근 수신 내역(콜리스트)
			List<CallLog> logListCustom = commonService.getListView(logListMap);

			try{
				JSONArray jsonArr 	= new JSONArray();	//list

				//DB의 Log정보 가져오기 - s
				if(logListCustom.size()>0){
					
					opMod = "Mod";
					//logListMap.put("limit", 5);
					
					String pk_date="";
					
					for(CallLog item : logListCustom){
						JSONObject jsonItem	= new JSONObject();	// json code_list

						pk_date	 = item.getFd_reg_date() == null ? "" : item.getFd_reg_date();
						pk_date	 = pk_date.substring(0, pk_date.length()-2);

						String fd_name	 = item.getFd_user_name() == null ? "" : item.getFd_user_name();
						String fk_tel	 = item.getFd_rcv_tel() == null ? "" : StrUtils.formatPhoneNo(item.getFd_rcv_tel().toString(), "-");
						String fd_seqno	 = item.getPk_seqno() == null ? "" : item.getPk_seqno();
						String fd_memo	 = item.getFd_memo() == null ? "" : item.getFd_memo();
						String fd_addr	 = item.getFd_addr() == null ? "" : item.getFd_addr();

						jsonItem.put("pk_date", pk_date);
						jsonItem.put("fd_name", fd_name);
						jsonItem.put("fk_tel", fk_tel);
						jsonItem.put("fd_seqno", fd_seqno);
						jsonItem.put("fd_memo", fd_memo);
						jsonItem.put("fd_addr", fd_addr);

						jsonArr.add(jsonItem);
					}
				}else{
					
					opMod = "Ins";
					JSONObject jsonItem	= new JSONObject();	// json code_list

					String pk_date	 = re_now;
					String fd_name	 = "";
					String fk_tel	 = StrUtils.formatPhoneNo(fd_tel.toString(), "-");
					String fd_seqno	 = "";
					String fd_memo	 = "";
					String fd_addr	 = "";

					jsonItem.put("pk_date", pk_date);
					jsonItem.put("fd_name", fd_name);
					jsonItem.put("fk_tel", fk_tel);
					jsonItem.put("fd_seqno", fd_seqno);
					jsonItem.put("fd_memo", fd_memo);
					jsonItem.put("fd_addr", fd_addr);

					jsonArr.add(jsonItem);

				}
				
				//DB의 Log정보 가져오기 - e
				
				jsonObj.put("opMod", opMod);
				jsonObj.put("fk_tel", fd_tel);
				jsonObj.put("list", jsonArr);
				jsonObj.put("call_date", re_now);
				
			}catch(Exception e){
				logger.error(e.getMessage());
				System.out.println("회원검색 실패");
			}

			System.out.println("회원 검색 완료 : "+jsonObj);

			//최근 수신내역 가져오기
		
		}catch(Exception ex){
			logger.error(ex.getMessage());
			System.out.println("call 전화번호 등록/수정 실패");
		}
		return jsonObj;
	}

	

	/**
	 * Open Api 전화 사용자 저장/수정
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxBlank.do", params="type=memberPro")
	public String memberPro(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CommonController.memberPro()");
/*
		type"			 : "memberPro"
		, "memberName"	 : memberName
		, "memberTel"	 : memberTel
		, "memberAddr"	 : memberAddr
		, "memberMemo"	 : memberMemo
		, "memberSeqno"	 : memberSeqno
		, "opMod"		 : opMod
*/
		String opMod			= request.getParameter("opMod")			!= null ? request.getParameter("opMod").trim()				: ""; // 저장, 수정구분(Ins/Mod)
		String memberName		= request.getParameter("memberName")	!= null ? request.getParameter("memberName").trim()			: ""; // 이름
		String memberTel		= request.getParameter("memberTel")		!= null ? request.getParameter("memberTel").trim()			: ""; // 전화번호
		String memberAddr		= request.getParameter("memberAddr")	!= null ? request.getParameter("memberAddr").trim()			: ""; // 주소
		String memberMemo		= request.getParameter("memberMemo")	!= null ? request.getParameter("memberMemo").trim()			: ""; // 메모
		String memberSeqno		= request.getParameter("memberSeqno")	!= null ? request.getParameter("memberSeqno").trim()		: ""; // api seqno
		String memberViewChk	= request.getParameter("memberViewChk")	!= null ? request.getParameter("memberViewChk").trim()		: ""; // 0:자동저장, 1:자동저장안함
		String result		= "false";

		//로그인 값 가져오기
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));

		String[] nowDataArray = now_date.split("-");

		//요일	
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);
		
		Customer customer = new Customer();
		customer.setPk_customer_tel(memberTel);
		customer.setFk_member_id(loginInfo.getPk_member_id());
		customer.setFd_user_name(memberName);
		customer.setFd_addr(memberAddr);
		customer.setFd_view_flag(memberViewChk);
		customer.setFd_new_date(nowDataArray[0]+nowDataArray[1]+nowDataArray[2]);
		customer.setFd_mod_date(nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5]);
		customer.setFd_rev_sms_flag("N");

		if(opMod.equals("Ins")){
			try {
				commonService.customerIns(customer);
				System.out.println("회원 등록 완료");
				result = "true";
			}catch(Exception ex){
				logger.error(ex.getMessage());
				System.out.println("회원 등록 실패");
			}
		}else if(opMod.equals("Mod")){
			try {
				commonService.customerIns(customer);
				System.out.println("회원 수정 완료");

				CallLog callLog = new CallLog();
				callLog.setFd_rcv_tel(memberTel);
				callLog.setPk_seqno(memberSeqno);
				callLog.setFk_seq(memberSeqno);
				callLog.setFd_memo(memberMemo);
				callLog.setFd_member_id(loginInfo.getPk_member_id());
				try {
					commonService.callLogUp(callLog);
					System.out.println("로그 회원 수정 완료");
					result = "true";
				}catch(Exception ex){
					logger.error(ex.getMessage());
					System.out.println("로그 회원 수정 실패");
					result = "false";
				}

				result = "true";
			}catch(Exception ex){
				logger.error(ex.getMessage());
				System.out.println("회원 수정 실패");
			}
		}

		return result;
	}


	/**
	 * Open Api 전화 사용자 검색
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxBlank.do", params="type=member_select")
	public Customer member_select(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CommonController.member_select()");
		
		String pk_tel1		= request.getParameter("memberTel1")	!= null ? request.getParameter("memberTel1").trim()		 : ""; //전화번호
		String pk_tel2		= request.getParameter("memberTel2")	!= null ? request.getParameter("memberTel2").trim()		 : ""; //전화번호
		String pk_tel3		= request.getParameter("memberTel3")	!= null ? request.getParameter("memberTel3").trim()		 : ""; //전화번호

		Customer result		= new Customer();

		//로그인 값 가져오기
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		loginInfo.getPk_member_id();

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));

		String[] nowDataArray = now_date.split("-");

		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);
		
		try {
			result = commonService.customerGet(pk_tel1+""+pk_tel2+""+pk_tel3);
			System.out.println("회원 검색 완료");
		}catch(Exception ex){
			logger.error(ex.getMessage());
			System.out.println("회원 검색 실패");
		}
		
		return result;
	}
}