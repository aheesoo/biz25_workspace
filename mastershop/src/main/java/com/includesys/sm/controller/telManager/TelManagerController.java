package com.includesys.sm.controller.telManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.controller.csManager.ManagerExl;
import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.telManager.TelManager;
import com.includesys.sm.service.csManager.CsCustomerService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.service.telManager.TelManagerService;
import com.includesys.sm.util.StrUtils;
import com.includesys.sm.webUtil.PageNavi;

@Controller
@RequestMapping("/telManager")
public class TelManagerController {

	private static Logger logger = LoggerFactory.getLogger(TelManagerController.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private TelManagerService telManagerService;

	@Autowired
	private CsCustomerService csCustomerService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 통화관리 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/telManagerMain.do" )
	public ModelAndView telManagerMain(HttpServletRequest request)	{
		System.out.println("\n[CALL Controller] TelManagerController.telManagerMain()");

		int page, pageSize	 = 20;
		page 				 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());
			
		//검색
		String sYear			= request.getParameter("sYear")			 != null ? request.getParameter("sYear").trim() : "";				//년
		String sMonth			= request.getParameter("sMonth")		 != null ? request.getParameter("sMonth").trim() : "";				//월
		String searchColumn		= request.getParameter("searchColumn")	 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString		= request.getParameter("searchString")	 == null ? "" : request.getParameter("searchString").toString().trim();
		//정렬
		String orderColumn		= request.getParameter("orderColumn") == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			= request.getParameter("orderTyp") == null ? "d" : request.getParameter("orderTyp").toString();

		System.out.println("\n[CALL Controller] sMonth==="+sMonth);
		
		//하이픈 제거
		String searchString_temp = searchString.replace("-", "");
		
		//로그인정보
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		Member member = memberService.get(loginInfo.getPk_member_id());
		

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//오늘날짜
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String[] nowDataArray = ft.format(new Date( )).split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];
		
		//요일
		Calendar cal = Calendar.getInstance();
		String fd_reg_week = ""+cal.get(Calendar.DAY_OF_WEEK);

		//통화수신통계 - s
		Calendar cal_today = Calendar.getInstance();

		String year = sYear;
		String month = sMonth;

		//지금 년,월 또는 파라미터의 년,월의 1일을 가져온다
		if(year != null && month != null && !"".equals(year) && !"".equals(month)) {
			cal_today.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
		}else{
			cal_today.set(Calendar.DATE, 1);
		}
		
		int i_year = cal_today.get(Calendar.YEAR);
		int i_month = cal_today.get(Calendar.MONTH) + 1;
		//int i_minday = cal_today.get(Calendar.DATE);
		//int i_maxday = cal_today.getActualMaximum(Calendar.DAY_OF_MONTH);
		int i_maxday = 0;
		
		year = String.valueOf(i_year);
		month = (String.valueOf(i_month).length() < 2)?"0"+String.valueOf(i_month):String.valueOf(i_month);
		
		String[] dateArray = ft.format(new Date()).split("-");
		String yymmTemp = dateArray[0] + dateArray[1];
		String yymm = year + month;
		//System.out.println("yymm = " + yymm);
		//System.out.println("yymmTemp = " + yymmTemp);
		
		//현재 날짜와 월이 같을경우 오늘날짜까지만 그래프를 표시.
		if(yymm.equals(yymmTemp)){
			i_maxday = Integer.parseInt(dateArray[2]) - 1;
		}else{
			//해당 월의 day max값을 갖고온다.
			i_maxday = cal_today.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		Map<String, String> chartMap = new HashMap<String, String>();
		chartMap.put("fd_yy", year);
		chartMap.put("fd_mm", month);
		chartMap.put("fd_member_id", loginInfo.getPk_member_id());
		
		//차트 데이터
		List<TelManager> charList = telManagerService.getChartList(chartMap);

		ArrayList<Map<String, String>> chartArray = new ArrayList<Map<String, String>>();

		int chkNum = 0;																		// DB 에서 가져온 값을 for 로 돌릴때 사용
		int tot_month = 0;																	// 월간 합계
		int maxCnt = 0;
		for(int i=1 ; i <= i_maxday ; i++){
			Map<String, String> chartListMap = new HashMap<String, String>();					// chartArray 에 담아서 view 페이지의 차트에 뿌려준다
			String chk_m = (String.valueOf(i).length() < 2) ? "0"+String.valueOf(i):String.valueOf(i);
			if(charList.size() > 0 && charList.size() > chkNum){
				if(chk_m.equals(charList.get(chkNum).getFd_dd())){
					if(maxCnt < Integer.parseInt(charList.get(chkNum).getFd_tot_day().toString())){
						maxCnt = Integer.parseInt(charList.get(chkNum).getFd_tot_day().toString());
					}
					chartListMap.put("cMonth",charList.get(chkNum).getFd_dd());
					chartListMap.put("cMonthCnt",charList.get(chkNum).getFd_tot_day());
					tot_month += Integer.parseInt(charList.get(chkNum).getFd_tot_day());
					chkNum++;
				}else{
					chartListMap.put("cMonth",chk_m);
					chartListMap.put("cMonthCnt","0");
				}
			}else{
				chartListMap.put("cMonth",chk_m);
				chartListMap.put("cMonthCnt","0");
			}
			chartArray.add(chartListMap);
		}
		System.out.println("##4");
		//통화수신통계 - e
		
		
		
		//통화관리 리스트
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("fd_member_id", loginInfo.getPk_member_id());							//로그인아이디
		listMap.put("pageStart", (page-1)*pageSize);										//시작페이지
		listMap.put("pageSize", pageSize);													//페이지사이즈

		listMap.put("orderColumn", orderColumn+"_"+(orderTyp.equals("d") ? "d" : "a"));		//정렬방식

		listMap.put("fd_reg_yy", year);														//년도
		listMap.put("fd_reg_mm", month);													//월
		listMap.put("searchColumn", searchColumn);											//검색컬럼
		listMap.put("searchString", searchString_temp);											//검색어
		
		List<TelManager> mList = telManagerService.getListView(listMap);
		ArrayList<Map<String, Object>> mArray = new ArrayList<Map<String, Object>>();

		for(TelManager item:mList){

			Map<String, Object> mMap = new HashMap<String, Object>();
			mMap.put("pk_seqno"				,item.getPk_seqno()												);	//통화오픈api_일련번호
			mMap.put("fd_member_id"			,item.getFd_member_id()											);	//로그인 ID
			mMap.put("fd_tel"				,StrUtils.formatPhoneNo(item.getFd_tel().toString(), "-")		);	//수신 전화번호
			mMap.put("fd_tel_org"			,item.getFd_tel()												);	//수신 전화번호
			mMap.put("fd_rcv_tel"			,StrUtils.formatPhoneNo(item.getFd_rcv_tel().toString(), "-")	);	//로그인 전화번호(통화오픈API 번호)
			mMap.put("fd_rcv_tel_org"		,item.getFd_rcv_tel()											);	//로그인 전화번호(통화오픈API 번호)
			mMap.put("fd_search_time"		,item.getFd_search_time()										);	//시간대 (A~Z)
			
			mMap.put("fd_reg_yy"			,item.getFd_reg_yy()											);	//수신 년도
			mMap.put("fd_reg_mm"			,item.getFd_reg_mm()											);	//수신 월
			mMap.put("fd_reg_dd"			,item.getFd_reg_dd()											);	//수신 일
			mMap.put("fd_reg_hh"			,item.getFd_reg_hh()											);	//수신 시각
			mMap.put("fd_reg_week"			,item.getFd_reg_week()											);	//수신요일구분 1~7 (일요일~토요일)
			mMap.put("fd_reg_mi"			,item.getFd_reg_mi()											);	//수신 분
			mMap.put("fd_reg_ss"			,item.getFd_reg_ss()											);	//수신 초
			mMap.put("fd_call_date	"		,item.getFd_call_date()											);	//통화 일시
			mMap.put("fd_openapi_rc_code"	,item.getFd_openapi_rc_code()									);	//통화오픈api_수신상태 코드
			mMap.put("fd_memo"				,item.getFd_memo()												);	//전화 수신시 메모 2000byte 체크
			mMap.put("fd_user_name"			,item.getFd_user_name()											);	//고객 이름
			mMap.put("fd_addr"				,item.getFd_addr()												);	//주소
			mMap.put("fd_memo_cus"			,item.getFd_memo_cus()											);	//고객 메모
			mMap.put("fd_view_flag"			,item.getFd_view_flag()											);	//보기옵션 0:보기 1:숨김
			mMap.put("fd_rev_sms_flag"		,item.getFd_rev_sms_flag()										);	//수신거부 거부:Y 승락:N (기본값:N)
			mMap.put("fd_new_date"			,item.getFd_new_date()											);	//최초 등록 년월일 ex:20140101(신규고객 대상추출)
			mMap.put("fd_mod_date"			,item.getFd_mod_date()											);	//정보 수정일

			mArray.add(mMap);
		}

		System.out.println("##7");
		int totalCount = telManagerService.getListCount(listMap);

		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/telManager/telManagerMain.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("get");
		pageNavi.setPageSize(pageSize);
		pageNavi.setParameters("searchColumn", searchColumn);
		pageNavi.setParameters("searchString", searchString);
		pageNavi.setParameters("orderColumn", orderColumn);
		pageNavi.setParameters("orderTyp", orderTyp);
		pageNavi.setParameters("sYear", year);
		pageNavi.setParameters("sMonth", month);
		pageNavi.make();
		System.out.println("##8");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("telManager/telManagerMain");
		mav.addObject("pageNavi",pageNavi);
		mav.addObject("mArray",mArray);
		mav.addObject("page",page);
		mav.addObject("year",year);
		mav.addObject("month",month);
		
		mav.addObject("view_name_flag", member.getFd_view_name());
		mav.addObject("tot_month", tot_month);
		mav.addObject("chartArray", chartArray);
		mav.addObject("totalCount",totalCount);
		mav.addObject("maxCnt",maxCnt );
		System.out.println("##9");
		return mav;		
	}

	/**
	 * 통화관리 상세
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/telManagerDetail.do" )
	public ModelAndView telManagerDetail(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] TelManagerController.telManagerDetail()");

		int page;
		page 					 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());
		
		String radioMonth		 = request.getParameter("radioMonth") == null ? "6" : request.getParameter("radioMonth").toString();

		//검색
		String fd_rev_sms_flag	 = request.getParameter("fd_rev_sms_flag")	 == null ? "" : request.getParameter("fd_rev_sms_flag").toString();
		String searchColumn 	 = request.getParameter("searchColumn")		 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString 	 = request.getParameter("searchString")		 == null ? "" : request.getParameter("searchString").toString().trim();

		//정렬
		String orderColumn		= request.getParameter("orderColumn")		 == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			= request.getParameter("orderTyp")			 == null ? "d" : request.getParameter("orderTyp").toString();
		
		//키값
		String fd_rcv_tel		= request.getParameter("fd_rcv_tel")	 == null ? "" : request.getParameter("fd_rcv_tel").toString();
		String pk_customer_tel	= request.getParameter("pk_customer_tel")	 == null ? "" : request.getParameter("pk_customer_tel").toString();
		if(pk_customer_tel.equals("")){
			pk_customer_tel = fd_rcv_tel;
		}
		
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

		Map<String, Object> customerMap = new HashMap<String, Object>();
		customerMap.put("fk_member_id", loginInfo.getPk_member_id());
		customerMap.put("pk_customer_tel", pk_customer_tel);
		
		CsCustomer infoCustomer = null;

		Map<String, String> infoMap = new HashMap<String, String>();
		
		try{
			infoCustomer = csCustomerService.get(customerMap);
			if(infoCustomer.getPk_customer_tel().equals("")){
				infoMap.put("fk_member_id"			,loginInfo.getPk_member_id());														// 고객 ID
				infoMap.put("pk_customer_tel"		,StrUtils.formatPhoneNo(pk_customer_tel.toString(), "-"));																	// 고객 전화번호
				infoMap.put("pk_customer_tel_org"	,pk_customer_tel);																				// 고객 전화번호
				infoMap.put("fd_view_flag"			,"");																				// 보기옵션 0:보기 1:숨김
				infoMap.put("fd_user_name"			,"");																				// 고객 이름
				infoMap.put("fd_rev_sms_flag"		,"");																				// 수신거부 거부:Y 승락:N (기본값:N)
				infoMap.put("fd_new_date"			,"");																				// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
				infoMap.put("fd_mod_date"			,"");																				// 정보 수정일
				infoMap.put("fd_memo"				,"");																				// 고객 메모
				infoMap.put("fd_addr"				,"");																				// 주소
				infoMap.put("fd_last_date"			,"");																				// 최근 수신일
				infoMap.put("fd_call_cnt"			,"");																				// 전화 카운트
				infoMap.put("fd_sms_cnt"			,"");																				// SMS 카운트
			}else{
				infoMap.put("fk_member_id"			,infoCustomer.getFk_member_id());													// 고객 ID
				infoMap.put("pk_customer_tel"		,StrUtils.formatPhoneNo(infoCustomer.getPk_customer_tel().toString(), "-"));		// 고객 전화번호
				infoMap.put("pk_customer_tel_org"	,infoCustomer.getPk_customer_tel());												// 고객 전화번호
				infoMap.put("fd_view_flag"			,infoCustomer.getFd_view_flag());													// 보기옵션 0:보기 1:숨김
				infoMap.put("fd_user_name"			,infoCustomer.getFd_user_name());													// 고객 이름
				infoMap.put("fd_rev_sms_flag"		,infoCustomer.getFd_rev_sms_flag());												// 수신거부 거부:Y 승락:N (기본값:N)
				infoMap.put("fd_new_date"			,infoCustomer.getFd_new_date());													// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
				infoMap.put("fd_mod_date"			,infoCustomer.getFd_mod_date());													// 정보 수정일
				//infoMap.put("fd_memo"				,infoCustomer.getFd_memo());														// 고객 메모
				infoMap.put("fd_memo", StrUtils.replaceHTML_Format(infoCustomer.getFd_memo()));
				infoMap.put("fd_addr"				,infoCustomer.getFd_addr());														// 주소
				infoMap.put("fd_last_date"			,infoCustomer.getFd_last_date());													// 최근 수신일
				infoMap.put("fd_call_cnt"			,infoCustomer.getFd_call_cnt());													// 전화 카운트
				infoMap.put("fd_sms_cnt"			,infoCustomer.getFd_sms_cnt());														// SMS 카운트
			}
		}catch(Exception e){
			infoMap.put("fk_member_id"			,loginInfo.getPk_member_id());														// 고객 ID
			infoMap.put("pk_customer_tel"		,StrUtils.formatPhoneNo(pk_customer_tel.toString(), "-"));																	// 고객 전화번호
			infoMap.put("pk_customer_tel_org"	,pk_customer_tel);																				// 고객 전화번호
			infoMap.put("fd_view_flag"			,"");																				// 보기옵션 0:보기 1:숨김
			infoMap.put("fd_user_name"			,"");																				// 고객 이름
			infoMap.put("fd_rev_sms_flag"		,"");																				// 수신거부 거부:Y 승락:N (기본값:N)
			infoMap.put("fd_new_date"			,"");																				// 최초 등록 년월일 ex:20140101(신규고객 대상추출)
			infoMap.put("fd_mod_date"			,"");																				// 정보 수정일
			infoMap.put("fd_memo"				,"");																				// 고객 메모
			infoMap.put("fd_addr"				,"");																				// 주소
			infoMap.put("fd_last_date"			,"");																				// 최근 수신일
			infoMap.put("fd_call_cnt"			,"");																				// 전화 카운트
			infoMap.put("fd_sms_cnt"			,"");																				// SMS 카운트
		}
			
			
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
		mav.setViewName("telManager/telManagerDetail");
		
		mav.addObject("latelyCallDate", latelyCallDate);
		mav.addObject("sumSmsSendCount", sumSmsSendCount);
		mav.addObject("weekSmsReport", weekSmsReport);
		mav.addObject("timeSmsReport", timeSmsReport);
		mav.addObject("smsSendDate", smsSendDate);
		mav.addObject("radioMonth", radioMonth);

		mav.addObject("view_name_flag", member.getFd_view_name());
		mav.addObject("infoMap",infoMap);
		
		return mav;	
				
	}

	
	/**
	 * 통화관리 수정
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/telManagerPro.do", params="goType=mod")
	public ModelAndView telManagerMod(HttpServletRequest request){
		
		System.out.println("\n[CALL Controller] CsManagerController.telManagerMod()");
		
		String script			 = "";
		String scriptTxt		 = "";

		String csName = request.getParameter("csName") == null ? "" : request.getParameter("csName").toString();
		String csTel1 = request.getParameter("csTel1") == null ? "" : request.getParameter("csTel1").toString();
		String csTel2 = request.getParameter("csTel2") == null ? "" : request.getParameter("csTel2").toString();
		String csTel3 = request.getParameter("csTel3") == null ? "" : request.getParameter("csTel3").toString();
		String csAddr = request.getParameter("csAddr") == null ? "" : request.getParameter("csAddr").toString();
		String csMemo = request.getParameter("csMemo") == null ? "" : request.getParameter("csMemo").toString();
		String csType = request.getParameter("csType") == null ? "" : request.getParameter("csType").toString();

		int page;
		page 					 = Integer.parseInt(request.getParameter("page") == null || request.getParameter("page").toString().trim() == "" ? "1" : request.getParameter("page").toString());

		//검색
		String searchColumn 	 = request.getParameter("searchColumn")		 == null ? "" : request.getParameter("searchColumn").toString();
		String searchString 	 = request.getParameter("searchString")		 == null ? "" : request.getParameter("searchString").toString();

		//정렬
		String orderColumn		 = request.getParameter("orderColumn")		 == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			 = request.getParameter("orderTyp")			 == null ? "d" : request.getParameter("orderTyp").toString();
		
		//키값
		String pk_customer_tel	 = request.getParameter("pk_customer_tel")	 == null ? "" : request.getParameter("pk_customer_tel").toString();

		String reqParam = "?orderColumn="+orderColumn+"&orderTyp="+orderTyp+"&page="+page+"&pk_rcv_tel="+pk_customer_tel+"&searchColumn="+searchColumn+"&searchString="+searchString;
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

		TelManager customer = new TelManager();
		customer.setFk_member_id(loginInfo.getPk_member_id());
		customer.setFd_user_name(csName);
		customer.setPk_customer_tel(csTel1+""+csTel2+""+csTel3);
		customer.setFd_addr(csAddr);
		customer.setFd_memo(csMemo);
		customer.setFd_new_date(nowDataArray[0]+""+nowDataArray[1]+""+nowDataArray[2]);
		customer.setFd_mod_date(re_now);
		customer.setFd_rev_sms_flag(csType);

		if(telManagerService.getViewCount(customer) > 0){
			scriptTxt = "수정";
		}else{
			scriptTxt = "저장";
		}
		
		try{
			telManagerService.getViewModify(customer);
			scriptTxt += " 완료";
		}catch(Exception e){
			scriptTxt += " 실패";
		}

		script = "alert('고객정보 "+scriptTxt+"');\n";
		script += "location.href='/telManager/telManagerDetail.do"+reqParam+"';";
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("telManager/telManagerPro");
		mav.addObject("script",script);
		
		return mav;		
	}

	//엑셀처리 (통화관리 : 저장)
	@RequestMapping("/telManagerListExcel.do")
	public ModelAndView telListExcel(HttpServletRequest request) 
	{
		String sYear			= request.getParameter("sYear")			 != null ? request.getParameter("sYear").trim() : "";				//년
		String sMonth			= request.getParameter("sMonth")		 != null ? request.getParameter("sMonth").trim() : "";				//월
		String searchColumn  = request.getParameter("searchColumn") == null ? "" : request.getParameter("searchColumn").toString();
		String searchString  = request.getParameter("searchString") == null ? "" : request.getParameter("searchString").toString().trim().replaceAll("-", "");
		//정렬
		String orderColumn		= request.getParameter("orderColumn") == null ? "" : request.getParameter("orderColumn").toString();
		String orderTyp			= request.getParameter("orderTyp") == null ? "d" : request.getParameter("orderTyp").toString();

		String year = sYear;
		String month = sMonth;

		Calendar cal_today = Calendar.getInstance();

		//지금 년,월 또는 파라미터의 년,월의 1일을 가져온다
		if(year != null && month != null && !"".equals(year) && !"".equals(month)) {
			cal_today.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
		}else{
			cal_today.set(Calendar.DATE, 1);
		}
		
		int i_year = cal_today.get(Calendar.YEAR);
		int i_month = cal_today.get(Calendar.MONTH) + 1;
		int i_minday = cal_today.get(Calendar.DATE);
		int i_maxday = cal_today.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		year = String.valueOf(i_year);
		month = (String.valueOf(i_month).length() < 2)?"0"+String.valueOf(i_month):String.valueOf(i_month);

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");;

		loginInfo.getPk_member_id();

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

		//통화관리 리스트
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("lType", "exl");														//엑셀 저장을 위하여(DB에서 limit 없이 select)

		listMap.put("fd_member_id", loginInfo.getPk_member_id());							//로그인아이디
		listMap.put("orderColumn", orderColumn+"_"+(orderTyp.equals("d") ? "d" : "a"));		//정렬방식

		listMap.put("fd_reg_yy", year);														//년도
		listMap.put("fd_reg_mm", month);													//월
		listMap.put("searchColumn", searchColumn);											//검색컬럼
		listMap.put("searchString", searchString);											//검색어

		List<TelManager> mList = telManagerService.getListView(listMap);
		ArrayList<Map<String, Object>> mArray = new ArrayList<Map<String, Object>>();

		for(TelManager item:mList){

			Map<String, Object> mMap = new HashMap<String, Object>();
			mMap.put("fd_tel"				,StrUtils.formatPhoneNo(item.getFd_tel().toString(), "-")		);	//수신 전화번호
			mMap.put("fd_user_name"			,item.getFd_user_name()											);	//고객 이름
			mMap.put("fd_addr"				,item.getFd_addr()												);	//주소
			mMap.put("fd_rcv_tel"			,StrUtils.formatPhoneNo(item.getFd_rcv_tel().toString(), "-")	);	//로그인 전화번호(통화오픈API 번호)
			mMap.put("fd_reg_yy"			,item.getFd_reg_yy()											);	//수신 년도
			mMap.put("fd_reg_mm"			,item.getFd_reg_mm()											);	//수신 월
			mMap.put("fd_reg_dd"			,item.getFd_reg_dd()											);	//수신 일
			mMap.put("fd_reg_hh"			,item.getFd_reg_hh()											);	//수신 시각
			mMap.put("fd_reg_mi"			,item.getFd_reg_mi()											);	//수신 분
			mMap.put("fd_reg_ss"			,item.getFd_reg_ss()											);	//수신 초
			mMap.put("fd_openapi_rc_code"	,item.getFd_openapi_rc_code()									);	//통화오픈api_수신상태 코드
			
			mArray.add(mMap);
		}

		ModelAndView mav = new ModelAndView(new ManagerExl());
		mav.addObject("itemList",	mArray);
		mav.addObject("exl_chk", "tel");														//엑셀 출력페이지 구분 (cs = 고객관리, tel = 통화관리)

		return mav;
		
	}	

	//엑셀처리 (통화관리 : 저장)
	@RequestMapping("/ifrmPrintPage.do")
	public ModelAndView pagePRINT(HttpServletRequest request) 
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("telManager/ifrmPrintPage");
		return mav;
		
	}	

}
