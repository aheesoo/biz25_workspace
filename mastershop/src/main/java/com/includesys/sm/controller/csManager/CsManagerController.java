package com.includesys.sm.controller.csManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.service.csManager.CsCustomerService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.util.StrUtils;
import com.includesys.sm.webUtil.PageNavi;
import com.includesys.sm.util.JxlSpinglManager;

@Controller
@RequestMapping("/csManager")
public class CsManagerController {

	private static Logger logger = LoggerFactory.getLogger(CsManagerController.class);
	
	
	@Autowired
	private HttpSession session;
	

	@Autowired
	private CsCustomerService csCustomerService;

	@Autowired
	private MemberService memberService;
	
	/**
	 * 고객관리 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/csManagerMain.do" )
	public ModelAndView csManagerMain(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsManagerController.csManagerMain()");
		//로그인 값 가져오기

		int page, pageSize	 = 10;
		page 				 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());
		
		//검색
		String fd_rev_sms_flag = request.getParameter("fd_rev_sms_flag") == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn  = request.getParameter("searchColumn") == null ? "" : request.getParameter("searchColumn").toString();
		String searchString  = request.getParameter("searchString") == null ? "" : request.getParameter("searchString").toString().trim().replaceAll("-", "");

		//정렬
		String orderColumn	 = request.getParameter("orderColumn") == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp	 = request.getParameter("orderTyp") == null ? "d" : request.getParameter("orderTyp").toString();
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		Member member = memberService.get(loginInfo.getPk_member_id());
		
		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		Map<String, Object> customerListMap = new HashMap<String, Object>();
		customerListMap.put("fk_member_id", loginInfo.getPk_member_id());							//로그인아이디
		customerListMap.put("pageStart", (page-1)*pageSize);										//시작페이지
		customerListMap.put("pageSize", pageSize);													//페이지사이즈
		customerListMap.put("fd_rev_sms_flag", fd_rev_sms_flag);									//수신옵션

		customerListMap.put("orderColumn", orderColumn+"_"+(orderTyp.equals("d") ? "d" : "a"));		//정렬방식
		
		customerListMap.put("searchColumn", searchColumn);											//검색컬럼
		customerListMap.put("searchString", searchString);											//검색어

		
		List<CsCustomer> customerArray = csCustomerService.getList(customerListMap);
		System.out.println("fd_rev_sms_flag="+fd_rev_sms_flag);
		ArrayList<Object> customerList = new ArrayList<Object>();
		
		for(CsCustomer item:customerArray){
			Map<String, Object> customerMap = new HashMap<String, Object>();
			customerMap.put("fk_member_id"			,item.getFk_member_id());													// 고객 ID
			customerMap.put("pk_customer_tel"		,StrUtils.formatPhoneNo(item.getPk_customer_tel().toString(), "-"));		// 고객 전화번호
			customerMap.put("pk_customer_tel_org"	,item.getPk_customer_tel());												// 고객 전화번호
			customerMap.put("fd_view_flag"			,item.getFd_view_flag());													// 보기옵션 0:보기 1:숨김
			customerMap.put("fd_user_name"			,item.getFd_user_name());													// 고객 이름
			customerMap.put("fd_rev_sms_flag"		,item.getFd_rev_sms_flag());												// 수신거부 거부:Y 승락:N (기본값:N)
			customerMap.put("fd_new_date"			,item.getFd_new_date());													// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
			customerMap.put("fd_mod_date"			,item.getFd_mod_date());													// 정보 수정일
			customerMap.put("fd_memo"				,item.getFd_memo());														// 고객 메모
			customerMap.put("fd_addr"				,item.getFd_addr());														// 주소
			customerMap.put("fd_last_date"			,item.getFd_last_date());													// 최근 수신일
			customerMap.put("fd_call_cnt"			,item.getFd_call_cnt());													// 전화 카운트
			customerMap.put("fd_sms_cnt"			,item.getFd_sms_cnt());														// SMS 카운트
			customerList.add(customerMap);
			System.out.println("customerMap"+customerMap);
		}

		int totalCount = csCustomerService.getCount(customerListMap);

		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/csManager/csManagerMain.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.setParameters("searchColumn", searchColumn);
		pageNavi.setParameters("searchString", searchString);
		pageNavi.setParameters("orderColumn", orderColumn);
		pageNavi.setParameters("orderTyp", orderTyp);
		pageNavi.setParameters("fd_rev_sms_flag", fd_rev_sms_flag);
		pageNavi.make();


		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerMain");
		
		mav.addObject("view_name_flag", member.getFd_view_name());
		mav.addObject("customerList", customerList);
		mav.addObject("pageNavi",pageNavi);
		mav.addObject("page",page);
		
		return mav;
	}
	
	/**
	 * 고객관리 상세
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/csManagerDetail.do" )
	public ModelAndView csManagerDetail(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] CsManagerController.csManagerDetail()");
		
		int page			 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());
		String radioMonth	 = request.getParameter("radioMonth")	 == null ? "6" : request.getParameter("radioMonth").toString();

		//검색
		String fd_rev_sms_flag	 = request.getParameter("fd_rev_sms_flag")	 == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn 	 = request.getParameter("searchColumn")		 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString 	 = request.getParameter("searchString")		 == null ? "" : request.getParameter("searchString").toString().trim();

		//정렬
		String orderColumn		 = request.getParameter("orderColumn")		 == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			 = request.getParameter("orderTyp")			 == null ? "d" : request.getParameter("orderTyp").toString();
		
		//키값
		String pk_customer_tel	 = request.getParameter("pk_customer_tel")	 == null ? "" : request.getParameter("pk_customer_tel").toString();
		
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;
		Member member = memberService.get(loginInfo.getPk_member_id());

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		Map<String, Object> customerMap = new HashMap<String, Object>();
		customerMap.put("fk_member_id", loginInfo.getPk_member_id());
		customerMap.put("pk_customer_tel", pk_customer_tel);
		
		CsCustomer infoCustomer = csCustomerService.get(customerMap);
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("fk_member_id"			,infoCustomer.getFk_member_id());													// 고객 ID
		infoMap.put("pk_customer_tel"		,StrUtils.formatPhoneNo(infoCustomer.getPk_customer_tel().toString(), "-"));		// 고객 전화번호
		infoMap.put("pk_customer_tel_org"	,infoCustomer.getPk_customer_tel());												// 고객 전화번호
		infoMap.put("fd_view_flag"			,infoCustomer.getFd_view_flag());													// 보기옵션 0:보기 1:숨김
		infoMap.put("fd_user_name"			,infoCustomer.getFd_user_name());													// 고객 이름
		infoMap.put("fd_rev_sms_flag"		,infoCustomer.getFd_rev_sms_flag());												// 수신거부 거부:Y 승락:N (기본값:N)
		infoMap.put("fd_new_date"			,infoCustomer.getFd_new_date());													// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
		infoMap.put("fd_mod_date"			,infoCustomer.getFd_mod_date());													// 정보 수정일
		//infoMap.put("fd_memo"				,infoCustomer.getFd_memo());														// 고객 메모
		infoMap.put("fd_memo_tarea", infoCustomer.getFd_memo());
		infoMap.put("fd_memo_html", StrUtils.replaceHTML_Format(infoCustomer.getFd_memo()));
		infoMap.put("fd_addr"				,infoCustomer.getFd_addr());														// 주소
		infoMap.put("fd_last_date"			,infoCustomer.getFd_last_date());													// 최근 수신일
		infoMap.put("fd_call_cnt"			,infoCustomer.getFd_call_cnt());													// 전화 카운트
		infoMap.put("fd_sms_cnt"			,infoCustomer.getFd_sms_cnt());														// SMS 카운트
			
			
		/*******************/
		//대상 조회 기간 설정.
		SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = ft1.format(new Date());
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		String search_date;
		String search_year;
		String search_month;
		String search_day;
		try 
		{
			temp_now = ft1.parse(nowDate);
			calendar.setTime(temp_now );
			calendar.add(Calendar.MONTH, - Integer.parseInt(radioMonth));	
			search_date = ft1.format(calendar.getTime());
			String[] date_arry = search_date.split("-");
			search_year = date_arry[0];
			search_month = date_arry[1];
			search_day = date_arry[2];
			
			customerMap.put("search_year", search_year);
			customerMap.put("search_month", search_month);
			customerMap.put("search_day", search_day);
			customerMap.put("search_date", search_year+search_month+search_day + "0000");
		} 
		catch (ParseException e1){e1.printStackTrace();}
		
		
		String latelyCallDate = "";
		String sumSmsSendCount = "";
		CsCustomer smsSendCount = new CsCustomer();
		CsCustomer weekSmsReport = new CsCustomer();
		CsCustomer timeSmsReport = new CsCustomer();
		List<CsCustomer> smsSendDate = new ArrayList<CsCustomer>();
		customerMap.put("fd_member_id", loginInfo.getPk_member_id());
		customerMap.put("fd_rcv_tel", pk_customer_tel);
		try {
			latelyCallDate = csCustomerService.getLatelyCallDate(customerMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			smsSendCount = csCustomerService.getSmsSendCount(customerMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			weekSmsReport = csCustomerService.getWeekSmsReport(customerMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			timeSmsReport = csCustomerService.getTimeSmsReport(customerMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			smsSendDate = csCustomerService.getSmsSendDate(customerMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		sumSmsSendCount = Integer.toString(smsSendCount.getFd_sms_success_cnt() +  smsSendCount.getFd_sms_fail_cnt());
		/*******************/	

		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerDetail");
		mav.addObject("view_name_flag", member.getFd_view_name());
		
		mav.addObject("latelyCallDate", latelyCallDate);
		mav.addObject("sumSmsSendCount", sumSmsSendCount);
		mav.addObject("weekSmsReport", weekSmsReport);
		mav.addObject("timeSmsReport", timeSmsReport);
		mav.addObject("smsSendDate", smsSendDate);
		mav.addObject("radioMonth", radioMonth);
		
		mav.addObject("infoMap",infoMap);
		
		return mav;		
	}
	
	/**
	 * 
	 * @param map, request
	 * @return
	 */
	@RequestMapping("/ifrmManagerSmsDetail.do")
	public ModelAndView ifrmManagerSmsDetail(@RequestParam Map<String, String> map, HttpServletRequest request){
		String fk_seq 					= map.get("fk_seq") == null ? "" : map.get("fk_seq").toString().trim();
		String fk_tel 					= map.get("fk_tel") == null ? "" : map.get("fk_tel").toString().trim();
		String receive_number 					= map.get("receive_number") == null ? "" : map.get("receive_number").toString().trim();

		CsCustomer csCustomer = new CsCustomer();
		Map<String, Object> customer_map = new HashMap<String, Object>();
		customer_map.put("fk_seq", fk_seq);
		customer_map.put("fk_tel", fk_tel);
		customer_map.put("receive_number", receive_number);
		
		try {
			csCustomer = csCustomerService.getSmsSendDateDetail(customer_map);
			csCustomer.setFk_tel(StrUtils.formatPhoneNo(csCustomer.getFk_tel(), "-"));
			csCustomer.setReceive_number(StrUtils.formatPhoneNo(csCustomer.getReceive_number(), "-"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("csCustomer", csCustomer);
		mav.setViewName("/csManager/ifrmManagerSmsDetail");
		return mav;		
	}

	/**
	 * 고객관리 저장
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/csManagerPro.do", params="goType=ins")
	public ModelAndView csManagerIns(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CsManagerController.csManagerIns()");
		
		String script			 = "";

		String csName = request.getParameter("csName") == null ? "" : request.getParameter("csName").toString();	// 이름
		String csTel1 = request.getParameter("csTel1") == null ? "" : request.getParameter("csTel1").toString();	// 전화번호1
		String csTel2 = request.getParameter("csTel2") == null ? "" : request.getParameter("csTel2").toString();	// 전화번호2
		String csTel3 = request.getParameter("csTel3") == null ? "" : request.getParameter("csTel3").toString();	// 전화번호3
		String csAddr = request.getParameter("csAddr") == null ? "" : request.getParameter("csAddr").toString();	// 주소
		String csMemo = request.getParameter("csMemo") == null ? "" : request.getParameter("csMemo").toString();	// 메모
		String csType = request.getParameter("csType") == null ? "" : request.getParameter("csType").toString();	// 수신거부 (거부:Y, 수신:N(기본값))

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		System.out.println("tetetst= "+csTel1+""+csTel2+""+csTel3);
		
		//중복체크
		Map<String, Object> csChkMap = new HashMap<String, Object>();
		csChkMap.put("pk_customer_tel", csTel1+""+csTel2+""+csTel3);
		csChkMap.put("fk_member_id", loginInfo.getPk_member_id());
		CsCustomer chkGet = csCustomerService.get(csChkMap);
		
//		System.out.println("chkGet = "+chkGet);

		if(chkGet == null){
			//고객정보 중복없음 (저장)
			CsCustomer customer = new CsCustomer();
			customer.setFk_member_id(loginInfo.getPk_member_id());
			customer.setFd_user_name(csName);
			customer.setPk_customer_tel(csTel1+""+csTel2+""+csTel3);
			customer.setFd_addr(csAddr);
			customer.setFd_memo(csMemo);
			//customer.setFd_memo(StrUtils.replaceHTML_Format(csMemo));
			customer.setFd_new_date(nowDataArray[0]+""+nowDataArray[1]+""+nowDataArray[2]);
			customer.setFd_mod_date(re_now);
			customer.setFd_rev_sms_flag(csType);
	
			csCustomerService.register(customer);
	
			script = "alert('고객정보 등록 완료');\n";
			script += "location.href='/csManager/csManagerMain.do';";
		}else{
			//고객정보 중복 이미 있습니다. 출력
			script = "alert('이미 고객정보가 있습니다.');\n";
			script += "location.href='/csManager/csManagerMain.do';";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerPro");
		mav.addObject("script",script);
		
		return mav;		
	}

	/**
	 * 고객관리 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/csManagerPro.do", params="goType=mod")
	public ModelAndView csManagerMod(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CsManagerController.csManagerMod()");
		
		String script			 = "";

		String csName = request.getParameter("csName") == null ? "" : request.getParameter("csName").toString();
		String csTel1 = request.getParameter("csTel1") == null ? "" : request.getParameter("csTel1").toString();
		String csTel2 = request.getParameter("csTel2") == null ? "" : request.getParameter("csTel2").toString();
		String csTel3 = request.getParameter("csTel3") == null ? "" : request.getParameter("csTel3").toString();
		String csAddr = request.getParameter("csAddr") == null ? "" : request.getParameter("csAddr").toString();
		String csMemo = request.getParameter("csMemo") == null ? "" : request.getParameter("csMemo").toString();
		String csType = request.getParameter("csType") == null ? "" : request.getParameter("csType").toString();

		int page;
		page 					 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());

		//System.out.println("csMemo1 : " + csMemo);
		//csMemo = StrUtils.replaceHTML_Format(csMemo);
		//System.out.println("csMemo2 : " + csMemo);
		
		//검색
		String fd_rev_sms_flag	 = request.getParameter("fd_rev_sms_flag")	 == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn 	 = request.getParameter("searchColumn")		 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString 	 = request.getParameter("searchString")		 == null ? "" : request.getParameter("searchString").toString();

		//정렬
		String orderColumn		 = request.getParameter("orderColumn")		 == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			 = request.getParameter("orderTyp")			 == null ? "d" : request.getParameter("orderTyp").toString();
		
		//키값
		String pk_customer_tel	 = request.getParameter("pk_customer_tel")	 == null ? "" : request.getParameter("pk_customer_tel").toString();

		String reqParam = "?orderColumn="+orderColumn+"&orderTyp="+orderTyp+"&page="+page+"&pk_customer_tel="+pk_customer_tel+"&fd_rev_sms_flag="+fd_rev_sms_flag+"&searchColumn="+searchColumn+"&searchString="+searchString;
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		CsCustomer customer = new CsCustomer();
		customer.setFk_member_id(loginInfo.getPk_member_id());
		customer.setFd_user_name(csName);
		customer.setPk_customer_tel(csTel1+""+csTel2+""+csTel3);
		customer.setFd_addr(csAddr);
		customer.setFd_memo(csMemo);
		customer.setFd_new_date(nowDataArray[0]+""+nowDataArray[1]+""+nowDataArray[2]);
		customer.setFd_mod_date(re_now);
		customer.setFd_rev_sms_flag(csType);

		csCustomerService.modify(customer);

		script = "alert('고객정보 수정 완료');\n";
		script += "location.href='/csManager/csManagerDetail.do"+reqParam+"';";
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerPro");
		mav.addObject("script",script);
		
		return mav;		
	}

	/**
	 * 고객관리 삭제
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/csManagerPro.do", params="goType=del")
	public ModelAndView csManagerDel(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CsManagerController.csManagerDel()");
		
		int page;
		page					 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());
		String script			 = "";

		//검색
		String fd_rev_sms_flag	 = request.getParameter("fd_rev_sms_flag")	 == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn		 = request.getParameter("searchColumn")		 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString		 = request.getParameter("searchString")		 == null ? "" : request.getParameter("searchString").toString();

		//정렬
		String orderColumn		 = request.getParameter("orderColumn")		 == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			 = request.getParameter("orderTyp")			 == null ? "d" : request.getParameter("orderTyp").toString();

		//삭제할 리스트(tel)
		String[] checkArray		 = request.getParameterValues("check");

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		for(String item:checkArray){
			Map<String, Object> customerMap = new HashMap<String, Object>();
			customerMap.put("fk_member_id", loginInfo.getPk_member_id());
			customerMap.put("pk_customer_tel", item);

			csCustomerService.remove(customerMap);
		}

		script = "alert('고객정보가 삭제되었습니다.');\n";
		script += "location.href='/csManager/csManagerMain.do';";
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerPro");
		mav.addObject("script",script);
		
		return mav;		
	}

	//엑셀로 저장 (고객관리)
	@RequestMapping("/csManagerListExcel.do")
	public ModelAndView eventEntryListExcel(HttpServletRequest request) 
	{

		//검색
		String fd_rev_sms_flag = request.getParameter("fd_rev_sms_flag") == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn  = request.getParameter("searchColumn") == null ? "" : request.getParameter("searchColumn").toString();
		String searchString  = request.getParameter("searchString") == null ? "" : request.getParameter("searchString").toString().trim().replaceAll("-", "");

		//정렬
		String orderColumn	 = request.getParameter("orderColumn") == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp	 = request.getParameter("orderTyp") == null ? "d" : request.getParameter("orderTyp").toString();
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));

		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		Map<String, Object> customerListMap = new HashMap<String, Object>();
		customerListMap.put("fk_member_id", loginInfo.getPk_member_id());							//로그인아이디
		customerListMap.put("lType", "exl");														//엑셀 저장을 위하여(DB에서 limit 없이 select)
		customerListMap.put("fd_rev_sms_flag", fd_rev_sms_flag);									//수신옵션
		customerListMap.put("orderColumn", orderColumn+"_"+(orderTyp.equals("d") ? "d" : "a"));		//정렬방식
		customerListMap.put("searchColumn", searchColumn);											//검색컬럼
		customerListMap.put("searchString", searchString);											//검색어

		List<CsCustomer> customerList = csCustomerService.getList(customerListMap);
		System.out.println("fd_rev_sms_flag="+fd_rev_sms_flag);

		ModelAndView mav = new ModelAndView(new ManagerExl());								//view를 엑셀 저장 페이지로
		mav.addObject("itemList",	customerList);											//DB값 가지고 이동
		mav.addObject("exl_chk", "cs");														//엑셀 출력페이지 구분 (cs = 고객관리, tel = 통화관리)
		
		return mav;
		
	}	

	
	
	//엑셀파일을 DB에 저장 (고객관리)
	@RequestMapping(value="/csManagerMain.do", params="goType=exlSave")
	public ModelAndView eventEntryFileExcel(HttpServletRequest request,MultipartHttpServletRequest fRequest) 
	{
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String now_date =  ft.format(new Date( ));
		String[] nowDataArray = now_date.split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		String script = "";
		int lenIdx = 0;
		int lenCount = 0;


		MultipartFile file = fRequest.getFile("inputFile");

		System.out.println("파일 존재 여부 : "+file.isEmpty());  //비어있으면 true
		if(file.isEmpty() == false){
			if(!file.getContentType().equals("application/vnd.ms-excel")){				//파일 형식 체크(xls 파일만)
				script = "alert('\"xls\"확장자만 가능합니다.'); \n location.href='/csManager/csManagerMain.do';";
				ModelAndView mav = new ModelAndView();
				mav.setViewName("csManager/csManagerMain");
				mav.addObject("script",script);
				
				return mav;
			}

			String name = "";								// 첫번째 열(이름)
			String tel = "";								// 두번째 열(전화번호)
			String addr = "";								// 세번째 열(주소)
			String res_chk = "";							// 네번째 열(수신거부 : "Y", 수신 : "N")
			String errorChk = "0";							// 롤백을 위하여 (1:엑셀파일 읽기 에러, 0:정상)
			
			ArrayList<CsCustomer> customerList = new ArrayList<CsCustomer>();
			
			
			//엑셀 가져오기
			try {
				//업로드 폴더 생성
				String os_patt = System.getProperty("file.separator");				//OS별 파일구분자
				String fPath =request.getSession().getServletContext().getRealPath(os_patt+"resources")+os_patt+"upload";

				System.out.println("fPath = "+fPath);
				
//				+os_patt+file.getOriginalFilename()
				File fPath_dir = new File(fPath, os_patt+"xml_upload");
				if(!fPath_dir.exists()){
					fPath_dir.mkdirs();												//resources/upload/에 xml_upload 디렉토리가 없을 경우 생성
				}
				
				File fPath_save = new File(fPath_dir+os_patt+file.getOriginalFilename());
				file.transferTo(fPath_save);
				
/*
				System.out.println("파일 : "+fPath_save);
				System.out.println("파라미터 이름 : "+fPath_save.getName());
				System.out.println("파일 경로 : "+fPath_save.getAbsolutePath());
*/				

				// 엑셀파일 워크북 객체 생성
				Workbook workbook = Workbook.getWorkbook(fPath_save);

				// 시트 지정
				Sheet sheet = workbook.getSheet(0);

				// 행 길이
				int endIdx = 0;
				endIdx = sheet.getColumn(1).length-1;
				lenIdx = sheet.getColumn(1).length;
				
				for(int i=1; i <= endIdx; i++){										//0번 라인은 타이틀 이라 1번 부터
					// 첫번째 열(A)
					name = sheet.getCell(0, i).getContents() ;
					
					// 두번째 열(B)
					tel = sheet.getCell(1, i).getContents().replace("-", "") ;
					
					// 세번째 열(C)
					addr = sheet.getCell(2, i).getContents() ;
					
					
					// 네번째 열(D)
					res_chk = (sheet.getCell(3, i).getContents().equals("거부"))?"Y":"N";

//					System.out.println(name + " : " + tel + " : " + addr + " : " + res_chk) ;

					CsCustomer customer = new CsCustomer();
					customer.setPk_customer_tel(tel);
					customer.setFk_member_id(loginInfo.getPk_member_id());
					customer.setFd_user_name(name);
					customer.setFd_addr(addr);
					customer.setFd_view_flag("0");
					customer.setFd_new_date(nowDataArray[0]+nowDataArray[1]+nowDataArray[2]);
					customer.setFd_mod_date(nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5]);
					customer.setFd_rev_sms_flag(res_chk);
					
					if(customer.getFk_member_id()!="" && customer.getPk_customer_tel()!=""){		//유효성검사
						customerList.add(customer);													//PK값이 있으면 저장
						lenCount++;
					}else{
						--lenIdx;																	//PK값이 없으면 카운트에서 뺀다
					}

				}
			} catch (Exception e) {
				script = "alert('엑셀 파일 읽기에 실패 하였습니다 \\n "+name+", "+tel+", "+addr+", "+res_chk+" 회원 이하');  \n location.href='/csManager/csManagerMain.do';";
				e.printStackTrace();
				errorChk = "1";
			}

			int res = 0;
			if(errorChk.equals("0")){																				//엑셀 파일을 정상적으로 불러왔으면
				try{
					res = csCustomerService.registerList(customerList);												//엑셀의 값을 배열로 던져서 트랜젝션 처리후 저장/수정 개수 가져온다
					
					if(res>0){
						script = "alert('"+lenCount+"건의 고객 정보가 등록되었습니다.');  \n location.href='/csManager/csManagerMain.do';";
					}else{
						script = "alert('엑셀 저장(수정) 실패 \\n 엑셀 파일을 확인해 주세요.');  \n location.href='/csManager/csManagerMain.do';";
					}
				}catch(Exception e){
					script = "alert('엑셀 저장(수정) 실패 \\n DB오류 관리자 문의.');  \n location.href='/csManager/csManagerMain.do';";
					e.printStackTrace();
				}
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("csManager/csManagerMain");
		mav.addObject("script",script);
		
		return mav;
	}

}
