package com.includesys.sm.controller.smsManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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

import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.dto.smsManager.Emoticon;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.service.report.ReportService;
import com.includesys.sm.service.smsManager.EmoticonService;
import com.includesys.sm.service.smsManager.SmsManagerService;
import com.includesys.sm.util.StrUtils;
import com.includesys.sm.webUtil.PageNavi;

@Controller
@RequestMapping("/smsManager")
public class SmsManagerController {

	private static Logger logger = LoggerFactory.getLogger(SmsManagerController.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	@Autowired
	private EmoticonService emoticonService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ReportService reportService;

	@Autowired
	private HttpServletRequest request;
	
	
	/**
	 * 
	 * @param map, request
	 * @return
	 */
	@RequestMapping("/ifrmSmsDetail.do")
	public ModelAndView ifrmSmsDetail(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("map : " + map);
		
		String group_code 					= map.get("group_code") == null ? "" : map.get("group_code").toString().trim();
		int call_count = 0;
		SmsManager smsManager = new SmsManager();
		List<SmsManager> reportDetailList = new ArrayList<SmsManager>();
		Map<String, Object> report_map = new HashMap<String, Object>();
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		Member member = memberService.get(loginInfo.getPk_member_id());
		
		report_map.put("group_code", group_code);
		report_map.put("member_id", loginInfo.getPk_member_id());
		
		try {
			smsManager = reportService.getReportDetailGroup(report_map);
			smsManager.setFk_tel(StrUtils.formatPhoneNo(smsManager.getFk_tel(), "-"));
			//smsManager.setSend_content(smsManager.getSend_content().replace("\r\n", " "));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			//reportDetailList = reportService.getReportDetailList(report_map);
			reportDetailList = smsManagerService.getSmsCustomerTelList(report_map);
			for(SmsManager hyphen : reportDetailList){
				//hyphen.setPk_customer_tel(StrUtils.formatPhoneNo(hyphen.getPk_customer_tel(), "-"));
				hyphen.setReceive_number(StrUtils.formatPhoneNo(hyphen.getReceive_number(), "-"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			call_count = smsManagerService.getCallCount(group_code);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("smsManager", smsManager);
		mav.addObject("reportDetailList", reportDetailList);
		mav.addObject("call_count", call_count);
		mav.addObject("view_name_flag", member.getFd_view_name());
		mav.setViewName("/smsManager/ifrmSmsDetail");

		return mav;		
	}
	
	/**
	 * 문자 등록,수정 -> 발송500건이상 제한 유무
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/checkSendLimit.do")
	public JSONObject checkSendLimitProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		JSONObject jsonObj = new JSONObject();
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		String rtn_code = "200";
		String limitYN = "N";
		
		try {
			limitYN = smsManagerService.getSendLimit(loginInfo.getPk_member_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rtn_code = "301";
		}
		
		jsonObj.put("limitYN", limitYN);
		jsonObj.put("rtn_code", rtn_code);
		
		return jsonObj;
	}
	
	/**
	 * 문자발송 수정
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/smsManagerModify.do" )
	public ModelAndView smsManagerModify(@RequestParam Map<String, Object> map){
		System.out.println("map : " + map);
		System.out.println("\n[CALL Controller] SmsManagerController.smsManagerModify()");
		
		String pk_group_code = map.get("pk_group_code") == null ? "" : map.get("pk_group_code").toString();
		
		String sCalYear = map.get("sCalYear") == null ? "" : map.get("sCalYear").toString();
		String sCalMonth = map.get("sCalMonth") == null ? "" : map.get("sCalMonth").toString();
		String searchTel = map.get("searchTel") == null ? "" : map.get("searchTel").toString();
		String sPageType = map.get("sPageType") == null ? "" : map.get("sPageType").toString();
		
		
		List <Map<String, Object>> product_tel_list = null;
		SmsManager smsManager = new SmsManager();
		LoginInfo loginInfo = null;
		List<Emoticon> emoticon_list = null; 
		boolean skip_process = true;
		
		HashMap<String, Object> temp_map = new HashMap<String, Object>();
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmm");
		String nowDate = ft.format(new Date());		
		String modifyYN = "";
		
		try {
			smsManager = smsManagerService.getSmsManager(pk_group_code);
			smsManager.setFk_tel(StrUtils.formatPhoneNo(smsManager.getFk_tel(), "-"));
			//smsManager.setSend_content(smsManager.getSend_content().replace("\r\n", " "));
			
			//현재시간보다 발송시간이 작을 경우 발송으로 간주 수정버튼 Hide 처리.
			if(Long.parseLong(nowDate) >= Long.parseLong(smsManager.getReserve_time())){
				modifyYN = "N";
			}else{
				modifyYN = "Y";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		
		try {
			loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		temp_map.put("pk_member_id", loginInfo.getPk_member_id());
		
		switch(smsManager.getMsg_type()){
			case "1":	temp_map.put("fd_sms_type", "S"); break;
			case "4":	if(smsManager.getMsg_sub_type().equals("1")){
								temp_map.put("fd_sms_type", "L");
							}else{
								temp_map.put("fd_sms_type", "M");
							}
							break;
			default :
		}
		/*temp_map.put("pageStart", 0);
		temp_map.put("pageSize", 3);*/
		try {	
			//emoticon_list = emoticonService.getRecommendMsgList(temp_map);
			emoticon_list = emoticonService.getRecommendContentList(temp_map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		
		if(skip_process){
			try {
				product_tel_list = smsManagerService.getProductTel(loginInfo.getPk_member_id());
				for(Map<String, Object> hyphen : product_tel_list){
					hyphen.put("fk_tel", StrUtils.formatPhoneNo((String)hyphen.get("fk_tel"), "-"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
			}
		}
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("smsManager", smsManager);
		mav.setViewName("smsManager/smsManagerModify");
		mav.addObject("product_tel_list", product_tel_list);
		mav.addObject("emoticon_list", emoticon_list);
		mav.addObject("modifyYN", modifyYN);
		
		mav.addObject("sCalYear", sCalYear);
		mav.addObject("sCalMonth", sCalMonth);
		mav.addObject("searchTel", searchTel);
		mav.addObject("sPageType", sPageType);
	
		return mav;
	}
	
	/**
	 * 문자 관리 > 문자 발송 등록
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/smsManagerModify.do", params="proc=modify")
	public JSONObject smsManagerModifyProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		System.out.println("map : " + map);
		System.out.println("[CALL Sms Controller ] smsManagerModifyProc");
		//String use_coin = map.get("use_coin") == null ? "" : map.get("use_coin").toString();
		String search_req_cnt 			= map.get("search_req_cnt") == null ? "" : map.get("search_req_cnt").toString();
		String req_count 			= map.get("req_count") == null ? "" : map.get("req_count").toString();
		String send_type 				= map.get("send_type") == null ? "" : map.get("send_type").toString();
		String yearColumn 			= map.get("yearColumn") == null ? "" : map.get("yearColumn").toString();
		String monthColumn 			= map.get("monthColumn") == null ? "" : map.get("monthColumn").toString();
		String dayColumn 			= map.get("dayColumn") == null ? "" : map.get("dayColumn").toString();
		String hourColumn 			= map.get("hourColumn") == null ? "" : map.get("hourColumn").toString();
		String minuteColumn 		= map.get("minuteColumn") == null ? "" : map.get("minuteColumn").toString();
		String org_reserve_time		= map.get("org_reserve_time") == null ? "" : map.get("org_reserve_time").toString().trim();
		//String req_count 				= map.get("req_count") == null ? "" : map.get("req_count").toString();
		String sms_type 				= map.get("sms_type") == null ? "" : map.get("sms_type").toString();
		String radioMonth 			= map.get("radioMonth") == null ? "" : map.get("radioMonth").toString();
		String customer_type 		= map.get("customer_type") == null ? "" : map.get("customer_type").toString();
		String[] check_day 			= request.getParameterValues("check_day");
		String check_time_yn 		= map.get("check_time_yn") == null ? "" : map.get("check_time_yn").toString();
		String[] check_time 			= request.getParameterValues("check_time");
		//String check_time = map.get("check_time") == null ? "" : map.get("check_time").toString();
		//String start_hourColumn 	= map.get("start_hourColumn") == null ? "" : map.get("start_hourColumn").toString();
		//String end_hourColumn 		= map.get("end_hourColumn") == null ? "" : map.get("end_hourColumn").toString();
		String send_count_yn 		= map.get("send_count_yn") == null ? "" : map.get("send_count_yn").toString();
		String search_call_rcv_cnt 		= map.get("search_call_rcv_cnt") == null ? "" : map.get("search_call_rcv_cnt").toString();
		String search_call_rcv_cnt_type = map.get("search_call_rcv_cnt_type") == null ? "" : map.get("search_call_rcv_cnt_type").toString();
		String fk_tel 					= map.get("fk_tel") == null ? "" : map.get("fk_tel").toString();
		String send_content 		= map.get("send_content") == null ? "" : map.get("send_content").toString();
		//List<SmsManager> customer_tel_list = request.getParameterValues("customer_tel_list");
		String attachment_path 		= map.get("attachment_path") == null ? "" : map.get("attachment_path").toString();
		String attachment_file_size 		= map.get("attachment_file_size") == null ? "" : map.get("attachment_file_size").toString();
		String reg_date 		= map.get("reg_date") == null ? "" : map.get("reg_date").toString();
		String group_code 		= map.get("group_code") == null ? "" : map.get("group_code").toString();
		String send_subject 		= map.get("send_subject") == null ? "" : map.get("send_subject").toString();
		String send_mms_subject 		= map.get("send_mms_subject") == null ? "" : map.get("send_mms_subject").toString();
		
		String group_code_temp = "";
		String search_week = "";
		String search_time = "";
		String search_year;
		String search_month;
		String search_date;
		String rtn_code = "200";
		String result = "";
		String fd_memo = "";
		boolean skip_process = true;
		int i;
		
		Map<String, Object> unvailDay_map = new HashMap<String, Object>();
		
		Coin coin = new Coin();
		JSONObject jsonObj = new JSONObject();
		SmsManager smsManager_temp = new SmsManager();
		SimpleDateFormat ft = new SimpleDateFormat ();//"yyyyMMddHHmmss");
		
		ft.applyPattern("yyyyMMddHHmmss");
		String modify_date =  ft.format(new Date( ));
		
		ft.applyPattern("yyyyMMddHHmm");
		String reserve_time = ft.format(new Date( ));
		
		ft.applyPattern("yyyy-MM-dd");
		String nowDate = ft.format(new Date());
		
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		//그룹코드는 나노세컨드로 
		try {
			group_code_temp = Long.toString(System.nanoTime());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		
		//코인 정보를 갖고옴.
		try {
			coin = smsManagerService.getCoin(loginInfo.getPk_member_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
			rtn_code = "306";
		}
		
		//결제되는 코인과 비교하여 부족시 "305" error 처리
		switch(sms_type){
			case "1": 	if(Integer.parseInt(req_count) * 2 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
			case "2": 	if(Integer.parseInt(req_count) * 5 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
			case "3": 	if(Integer.parseInt(req_count) * 20 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
		}
		
		//예약은 Max 3달까지만 설정이 가능(3달뒤 날짜와 비교하여 에러처리)
		if(skip_process){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String dateTemp = dateFormat.format(new Date());
			Calendar calendarTemp = new java.util.GregorianCalendar();
			Date tempNow;
			String setDate = "";
			String set3MonthDate = "";
			try 
			{
				/*3달후 날짜 선택*/
				tempNow = dateFormat.parse(dateTemp);
				calendarTemp.setTime(tempNow);
				calendarTemp.add(Calendar.MONTH, +3);
				set3MonthDate = dateFormat.format(calendarTemp.getTime());
			} 
			catch (ParseException e1){
				e1.printStackTrace();
				skip_process = false;
			}
			//설정한 날짜 갖고오기
			setDate = yearColumn + ((monthColumn.length() == 1) ? "0"+monthColumn : monthColumn) + ((dayColumn.length() == 1) ? "0"+dayColumn : dayColumn); 
			
			if(Long.parseLong(set3MonthDate) < Long.parseLong(setDate)){
				rtn_code = "308";
				skip_process = false;
			}
			//System.out.println("set3MonthDate= " + set3MonthDate);
			//System.out.println("setDate = " + setDate);
		}
		
		//문자예약시 21 ~ 8시 까지는 예약이 안되게 처리
		/*if(skip_process){
			if(send_type.equals("2")){
				String timeTemp = ((hourColumn.length() == 1) ? "0"+hourColumn : hourColumn) + ((minuteColumn.length() == 1) ? "0"+minuteColumn : minuteColumn);
				if( (Integer.parseInt(timeTemp) > 2059) || (Integer.parseInt(timeTemp) <= 800)){
					skip_process = false;
					rtn_code = "309";
				}
			}
		}*/
		
		//예약불가일인지 체크
		if(skip_process){
			try {
				unvailDay_map.put("year", yearColumn);
				unvailDay_map.put("month", (monthColumn.length() == 1) ? "0"+monthColumn : monthColumn);
				unvailDay_map.put("day", (dayColumn.length() == 1) ? "0"+dayColumn : dayColumn);
				
				fd_memo = smsManagerService.getUnvailDay(unvailDay_map);
				if(fd_memo != null){
					rtn_code = "307";
					skip_process = false;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
			}
		}
		
		if(skip_process) {
			//로그인 아이디
			smsManager_temp.setFk_member_id(loginInfo.getPk_member_id());
			
			//발신번호
			String[] tel_temp = fk_tel.split("-");
			fk_tel = "";
			for ( i=0 ; i<tel_temp.length ; i++){
				fk_tel += tel_temp[i];
			}
			smsManager_temp.setFk_tel(fk_tel);
			
			//그룹코드 (나노세컨드)
			smsManager_temp.setGroup_code(group_code);
			
			//Custom_msg_id는 "아이디_그룹코드"
			//smsManager_temp.setCustom_msg_id(loginInfo.getPk_member_id() + "_" + group_code_temp);
			
			//SMS: 1,1 LMS: 4,1 MMS: 4,2~4
			switch(sms_type){
				case "1" : 	//SMS
								smsManager_temp.setMsg_type("1");
								smsManager_temp.setMsg_sub_type("1");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 2));
								smsManager_temp.setSend_subject(send_subject);
								break;
				case "2" : 	//LMS
								smsManager_temp.setMsg_type("4");
								smsManager_temp.setMsg_sub_type("1");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 5));
								smsManager_temp.setSend_subject(send_subject);
								break;
				case "3" :	//MMS  
								smsManager_temp.setMsg_type("4");
								smsManager_temp.setMsg_sub_type("3");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 20));
								smsManager_temp.setAttachment_file_size(attachment_file_size);
								smsManager_temp.setAttachment_path(attachment_path);
								smsManager_temp.setSend_subject(send_mms_subject);
								break;
				default : 
			}
			
			//발송건수
			smsManager_temp.setReq_count(req_count);
			
			//발송가능건수
			smsManager_temp.setSearch_req_cnt(search_req_cnt);
			
			//등록일
			smsManager_temp.setReg_date(reg_date);
			
			//수정일
			smsManager_temp.setModify_date(modify_date);
			
			//문자 제목
			//smsManager_temp.setSend_subject("");
			
			//문자 내용
			smsManager_temp.setSend_content(send_content);
			
			//발송 날짜 세팅 (10 이하일때 앞에 0붙히기)
			monthColumn = (monthColumn.length() == 1) ? "0"+monthColumn : monthColumn;
			dayColumn = (dayColumn.length() == 1) ? "0"+dayColumn : dayColumn;
			hourColumn = (hourColumn.length() == 1) ? "0"+hourColumn : hourColumn;
			minuteColumn = (minuteColumn.length() == 1) ? "0"+minuteColumn : minuteColumn;
				
			
			//즉시일때 : 1 예약일때 : 2
			switch(send_type) {
				case "1" : smsManager_temp.setReserve_time(reserve_time); break;
				case "2" : smsManager_temp.setReserve_time(yearColumn + monthColumn + dayColumn + hourColumn + minuteColumn); break;
				default :
			}
			
			//발송대상 기간설정 1개월, 3개월, 6개월
			smsManager_temp.setSearch_month(radioMonth);
			
			//대상 조회 기간 설정.
			try 
			{
				temp_now = ft.parse(nowDate);
				calendar.setTime(temp_now );
				calendar.add(Calendar.MONTH, - Integer.parseInt(radioMonth));	
				search_date = ft.format(calendar.getTime());
				String[] date_arry = search_date.split("-");
				search_year = date_arry[0];
				search_month = date_arry[1];
				
				smsManager_temp.setExtraction_year(search_year);
				smsManager_temp.setExtraction_month(search_month);
			} 
			catch (ParseException e1){ skip_process = false;}
			
			//전체 :1 ,단골 : 2 , 신규 : 3 미사용 : -1
			switch(customer_type){
				case "1" :  smsManager_temp.setSearch_customer("1"); break;
				case "2" :  smsManager_temp.setSearch_customer("2"); break;
				case "3" :  smsManager_temp.setSearch_customer("3"); break;
				case "4" :  smsManager_temp.setSearch_customer("4"); break;
				default : smsManager_temp.setSearch_customer("-1");
			}
			
			//ex)일월화수목금토 = 1^2^3^4^5^6^7
			if(check_day != null){
				for(i=0 ; i<check_day.length ; i++){
					if(i == (check_day.length-1))
						search_week += check_day[i];
					else
						search_week += check_day[i] + "^";
				}
				smsManager_temp.setSearch_week(search_week);
				
				//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
				if(check_day.length < 7) {
					for(i=0 ; i<check_day.length ; i++){
						switch(check_day[i]){
							case "1" : smsManager_temp.setExtraction_day1("1");	break; //일요일
							case "2" : smsManager_temp.setExtraction_day2("2");	break; //월요일
							case "3" : smsManager_temp.setExtraction_day3("3");	break; //화요일
							case "4" : smsManager_temp.setExtraction_day4("4");	break; //수요일
							case "5" : smsManager_temp.setExtraction_day5("5");	break; //목요일
							case "6" : smsManager_temp.setExtraction_day6("6");	break; //금요일
							case "7" : smsManager_temp.setExtraction_day7("7");	break; //토요일
							default : 
						}
					}
				}
			}else{
				search_week = "1^2^3^4^5^6^7";
				smsManager_temp.setSearch_week(search_week);
			}
			
			//시간대 사용 체크시
			if(check_time_yn.equals("1")){
				if(check_time != null){
					for(i=0 ; i<check_time.length ; i++){
						if(i == (check_time.length-1))
							search_time += check_time[i];
						else
							search_time += check_time[i] + "^";
					}
					smsManager_temp.setSearch_time(search_time);
					
					//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
					if(check_time.length < 7) {
						for(i=0 ; i<check_time.length ; i++){
							switch(check_time[i]){
								case "A" : smsManager_temp.setExtraction_time1("A"); break; //새벽(00~07)
								case "B" : smsManager_temp.setExtraction_time2("B"); break; //아침(07~09)
								case "C" : smsManager_temp.setExtraction_time3("C"); break; //오전(09~11)
								case "D" : smsManager_temp.setExtraction_time4("D"); break; //점심(11~14)
								case "E" : smsManager_temp.setExtraction_time5("E"); break; //오후(14~17)
								case "F" : smsManager_temp.setExtraction_time6("F"); break; //저녁(17~21)
								case "G" : smsManager_temp.setExtraction_time7("G"); break; //야간(21~24)
								default : 
							}
						}
					}
				}else{
					search_time = "A^B^C^D^E^F^G";
					smsManager_temp.setSearch_time(search_time);
				}
			}else{
				search_time = "A^B^C^D^E^F^G";
				smsManager_temp.setSearch_time(search_time);
			}
			
			//통화건수 사용 체크시
			if(send_count_yn.equals("1")){
				smsManager_temp.setSearch_call_rcv_cnt(search_call_rcv_cnt);
				smsManager_temp.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
			}
			
			//발송여부 즉시 : 1 예약 : 2
			smsManager_temp.setReserve_type(send_type);
			
			//사용자 아이피
			//smsManager_temp.setClient_ip(request.getRemoteAddr());
			
			try {
				//수정되기전 시간과 현재시간을 비교하여 현재시간보다 이후일경우에만 수정.
				System.out.println(Long.parseLong(org_reserve_time));
				System.out.println(reserve_time);
				if(Long.parseLong(org_reserve_time) > Long.parseLong(reserve_time)){
					rtn_code = smsManagerService.modifySms(smsManager_temp);
				}else{
					rtn_code = "303";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		jsonObj.put("fd_memo", fd_memo);
		jsonObj.put("rtn_code", rtn_code);
		
		return jsonObj;
	}
	
	/**
	 * 문자발송 등록
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/smsManagerRegister.do" )
	public ModelAndView smsManagerRegister(@RequestParam Map<String, Object> map){
		System.out.println("\n[CALL Controller] SmsManagerController.smsManagerRegister()");
		System.out.println("map : " + map);
		boolean skip_process = true;
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd");		
		List <Map<String, Object>> product_tel_list = null;
		List<Emoticon> emoticon_list = null; 
		HashMap<String, Object> temp_map = new HashMap<String, Object>();
		
		String sCalYear = map.get("sCalYear") == null ? "" : map.get("sCalYear").toString();
		String sCalMonth = map.get("sCalMonth") == null ? "" : map.get("sCalMonth").toString();
		String searchTel = map.get("searchTel") == null ? "" : map.get("searchTel").toString();
		String sPageType = map.get("sPageType") == null ? "" : map.get("sPageType").toString();
		
		temp_map.put("fd_sms_type", "L");
		temp_map.put("pageStart", 0);
		temp_map.put("pageSize", 3);
		
		LoginInfo loginInfo = null;
		
		String reg_date =  ft.format(new Date( ));
		String script = "";
		
		try {
			loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
			script = "alert('로그인 정보를 가지고 오는 도중 오류가 발생하였습니다.');";
		}
		
		try {	
			emoticon_list = emoticonService.getRecommendMsgList(temp_map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
			script = "alert('추천 문구 리스트를 가지고 오는 도중 오류가 발생하였습니다.');";
		}
		
		/*if(skip_process){
			try {
				total_coin = smsManagerService.getCoin(loginInfo.getPk_member_id());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
				script = "alert('사용 가능한 코인을 가지고 오는 도중 오류가 발생하였습니다.');";
			}
		}*/

		if(skip_process){
			try {
				product_tel_list = smsManagerService.getProductTel(loginInfo.getPk_member_id());
				for(Map<String, Object> hyphen : product_tel_list){
					//StrUtils.formatPhoneNo((String)hyphen.get("fk_tel"), "-");
					hyphen.put("fk_tel", StrUtils.formatPhoneNo((String)hyphen.get("fk_tel"), "-"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
				script = "alert('발신번호를 가지고 오는 도중 오류가 발생하였습니다.');"; 
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("reg_date", reg_date);
		mav.addObject("product_tel_list", product_tel_list);
		mav.addObject("emoticon_list", emoticon_list);
		mav.addObject("script", script);
		
		mav.addObject("sCalYear", sCalYear);
		mav.addObject("sCalMonth", sCalMonth);
		mav.addObject("searchTel", searchTel);
		mav.addObject("sPageType", sPageType);
		
		mav.setViewName("smsManager/smsManagerRegister");
	
		return mav;		
	}
	
	/**
	 * 
	 * @param map, request
	 * @return
	 */
	@RequestMapping("/ifrmSmsTelList.do")
	public ModelAndView ifrmSmsTelList(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("map : " + map);
		System.out.println("\n[CALL Controller] MemberController.ifrmSmsTelList()");
		int pageSize = 5;
		
		String search_date;
		String search_year;
		String search_month;
		
		int i;
		int customer_count = 0;
		boolean skip_customer = false;
		boolean skip_process = true;
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = ft.format(new Date());
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		
		SmsManager search_customer = new SmsManager();
		List<SmsManager> customer_tel_list = null;
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		int page										= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		String customer_type 					= map.get("customer_type") == null ? "" : map.get("customer_type").toString();
		String radioMonth 						= map.get("radioMonth") == null ? "" : map.get("radioMonth").toString();
		String[] check_day 						= request.getParameterValues("check_day");
		String check_time_yn 					= map.get("check_time_yn") == null ? "" : map.get("check_time_yn").toString();
		String[] check_time 						= request.getParameterValues("check_time");
		String send_count_yn 					= map.get("send_count_yn") == null ? "" : map.get("send_count_yn").toString();
		String search_call_rcv_cnt 			= map.get("search_call_rcv_cnt") == null ? "" : map.get("search_call_rcv_cnt").toString();
		String search_call_rcv_cnt_type 	= map.get("search_call_rcv_cnt_type") == null ? "" : map.get("search_call_rcv_cnt_type").toString();
		
		String extraction_day1 	= map.get("extraction_day1") == null ? "" : map.get("extraction_day1").toString();
		String extraction_day2 	= map.get("extraction_day2") == null ? "" : map.get("extraction_day2").toString();
		String extraction_day3 	= map.get("extraction_day3") == null ? "" : map.get("extraction_day3").toString();
		String extraction_day4 	= map.get("extraction_day4") == null ? "" : map.get("extraction_day4").toString();
		String extraction_day5 	= map.get("extraction_day5") == null ? "" : map.get("extraction_day5").toString();
		String extraction_day6 	= map.get("extraction_day6") == null ? "" : map.get("extraction_day6").toString();
		String extraction_day7 	= map.get("extraction_day7") == null ? "" : map.get("extraction_day7").toString();
		
		String extraction_time1 	= map.get("extraction_time1") == null ? "" : map.get("extraction_time1").toString();
		String extraction_time2 	= map.get("extraction_time2") == null ? "" : map.get("extraction_time2").toString();
		String extraction_time3 	= map.get("extraction_time3") == null ? "" : map.get("extraction_time3").toString();
		String extraction_time4 	= map.get("extraction_time4") == null ? "" : map.get("extraction_time4").toString();
		String extraction_time5 	= map.get("extraction_time5") == null ? "" : map.get("extraction_time5").toString();
		String extraction_time6 	= map.get("extraction_time6") == null ? "" : map.get("extraction_time6").toString();
		String extraction_time7 	= map.get("extraction_time7") == null ? "" : map.get("extraction_time7").toString();
		
		//로그인된 아이디
		search_customer.setFk_member_id(loginInfo.getPk_member_id());
		
		//1: 전체고객 2: 단골고객 3: 신규고객
		search_customer.setSearch_customer(customer_type);
		switch(customer_type){
			case "1" : 	skip_customer = true;
							break;
			case "2" : 	skip_customer = true;
							search_call_rcv_cnt = "5";
							search_call_rcv_cnt_type = "1";
							search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
							search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
							break;
			case "3" : 	skip_customer = true;
							search_call_rcv_cnt = "1";
							search_call_rcv_cnt_type = "-1";
							search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
							search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
							break;
			default :
		}
		
		//대상 조회 기간 설정.
		try 
		{
			temp_now = ft.parse(nowDate);
			calendar.setTime(temp_now );
			calendar.add(Calendar.MONTH, - Integer.parseInt(radioMonth));	
			search_date = ft.format(calendar.getTime());
			String[] date_arry = search_date.split("-");
			search_year = date_arry[0];
			search_month = date_arry[1];
			
			search_customer.setExtraction_year(search_year);
			search_customer.setExtraction_month(search_month);
		} 
		catch (ParseException e1){ skip_process = false;}
		
		//전체고객, 단골고객, 신규고객 이 아닌 사용자가 임의로 설정한 값일 때
		if(skip_customer == false){
			//요일 처리
			if(check_day != null){
				//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
				if(check_day.length < 7) {
					for(i=0 ; i<check_day.length ; i++){
						switch(check_day[i]){
							case "1" : search_customer.setExtraction_day1("1");	break; //일요일
							case "2" : search_customer.setExtraction_day2("2");	break; //월요일
							case "3" : search_customer.setExtraction_day3("3");	break; //화요일
							case "4" : search_customer.setExtraction_day4("4");	break; //수요일
							case "5" : search_customer.setExtraction_day5("5");	break; //목요일
							case "6" : search_customer.setExtraction_day6("6");	break; //금요일
							case "7" : search_customer.setExtraction_day7("7");	break; //토요일
							default : 
						}
					}
				}
			}else{
				search_customer.setExtraction_day1(extraction_day1);
				search_customer.setExtraction_day2(extraction_day2);
				search_customer.setExtraction_day3(extraction_day3);
				search_customer.setExtraction_day4(extraction_day4);
				search_customer.setExtraction_day5(extraction_day5);
				search_customer.setExtraction_day6(extraction_day6);
				search_customer.setExtraction_day7(extraction_day7);
			}
			
			//시간 처리
			if(check_time_yn.equals("1")){
				if(check_time != null){
					//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
					if(check_time.length < 7) {
						for(i=0 ; i<check_time.length ; i++){
							switch(check_time[i]){
								case "A" : search_customer.setExtraction_time1("A"); break; //새벽(00~07)
								case "B" : search_customer.setExtraction_time2("B"); break; //아침(07~09)
								case "C" : search_customer.setExtraction_time3("C"); break; //오전(09~11)
								case "D" : search_customer.setExtraction_time4("D"); break; //점심(11~14)
								case "E" : search_customer.setExtraction_time5("E"); break; //오후(14~17)
								case "F" : search_customer.setExtraction_time6("F"); break; //저녁(17~21)
								case "G" : search_customer.setExtraction_time7("G"); break; //야간(21~24)
								default : 
							}
						}
					}
				}else{
					search_customer.setExtraction_time1(extraction_time1);
					search_customer.setExtraction_time2(extraction_time2);
					search_customer.setExtraction_time3(extraction_time3);
					search_customer.setExtraction_time4(extraction_time4);
					search_customer.setExtraction_time5(extraction_time5);
					search_customer.setExtraction_time6(extraction_time6);
					search_customer.setExtraction_time7(extraction_time7);
				}
			}
			
			//통화횟수
			if(send_count_yn.equals("1")){
				search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
				search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
			}
			
		}
		
		search_customer.setPageStart((page-1)*pageSize);
		search_customer.setPageSize(pageSize);
		
		try {
			customer_count = smsManagerService.getCustomerCount(search_customer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		
		if(skip_process){
			try {
				customer_tel_list = smsManagerService.getCustomerTelList(search_customer);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
			}
		}
		
		for(SmsManager item : customer_tel_list){
			item.setReceive_number(StrUtils.formatPhoneNo(item.getReceive_number(), "-"));
		}
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/smsManager/ifrmSmsTelList.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(customer_count);
		pageNavi.setMethod("post");
		pageNavi.setPageSize(pageSize);
		
		pageNavi.setParameters("customer_type", customer_type);
		pageNavi.setParameters("radioMonth", radioMonth);
		pageNavi.setParameters("check_time_yn", check_time_yn);
		pageNavi.setParameters("send_count_yn", send_count_yn);
		pageNavi.setParameters("search_call_rcv_cnt", search_call_rcv_cnt);
		pageNavi.setParameters("search_call_rcv_cnt_type", search_call_rcv_cnt_type);
		
		if(check_day != null){
			if(check_day.length > 0){
				for(i=0 ; i<check_day.length ; i++){
					switch(check_day[i]){
						case "1" : pageNavi.setParameters("extraction_day1", check_day[i]); break;
						case "2" : pageNavi.setParameters("extraction_day2", check_day[i]); break;
						case "3" : pageNavi.setParameters("extraction_day3", check_day[i]); break;
						case "4" : pageNavi.setParameters("extraction_day4", check_day[i]); break;
						case "5" : pageNavi.setParameters("extraction_day5", check_day[i]); break;
						case "6" : pageNavi.setParameters("extraction_day6", check_day[i]); break;
						case "7" : pageNavi.setParameters("extraction_day7", check_day[i]); break;
						default : 
					}
				}
			}
		}else{
			pageNavi.setParameters("extraction_day1", extraction_day1);
			pageNavi.setParameters("extraction_day2", extraction_day2);
			pageNavi.setParameters("extraction_day3", extraction_day3);
			pageNavi.setParameters("extraction_day4", extraction_day4);
			pageNavi.setParameters("extraction_day5", extraction_day5);
			pageNavi.setParameters("extraction_day6", extraction_day6);
			pageNavi.setParameters("extraction_day7", extraction_day7);
		}
		
		if(check_time_yn.equals("1")){
			if(check_time != null){
				if(check_time.length > 0){
					for(i=0 ; i<check_time.length ; i++){
						switch(check_time[i]){
							case "A" : pageNavi.setParameters("extraction_time1", check_time[i]); break;
							case "B" : pageNavi.setParameters("extraction_time2", check_time[i]); break;
							case "C" : pageNavi.setParameters("extraction_time3", check_time[i]); break;
							case "D" : pageNavi.setParameters("extraction_time4", check_time[i]); break;
							case "E" : pageNavi.setParameters("extraction_time5", check_time[i]); break;
							case "F" : pageNavi.setParameters("extraction_time6", check_time[i]); break;
							case "G" : pageNavi.setParameters("extraction_time7", check_time[i]); break;
							default : 
						}
					}
				}
			}else{
				pageNavi.setParameters("extraction_time1", extraction_time1);
				pageNavi.setParameters("extraction_time2", extraction_time2);
				pageNavi.setParameters("extraction_time3", extraction_time3);
				pageNavi.setParameters("extraction_time4", extraction_time4);
				pageNavi.setParameters("extraction_time5", extraction_time5);
				pageNavi.setParameters("extraction_time6", extraction_time6);
				pageNavi.setParameters("extraction_time7", extraction_time7);
			}
		}
		pageNavi.make();

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("customer_count", customer_count);
		mav.addObject("customer_tel_list", customer_tel_list);
		mav.setViewName("/smsManager/ifrmSmsTelList");

		return mav;		
	}
	
	/**
	 * 파일업로드 아이프레임 첫화면
	 * @param request
	 * @return
	 */
	@RequestMapping("/ifrmUploadMMS.do")
	public ModelAndView ifrmUploadMMS(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("map : " + map);
		System.out.println("\n[CALL Controller] MemberController.ifrmUploadMMS()");
		String filename = "";
		String[] arry;
		
		String attachment_path = map.get("attachment_path") == null ? "" : map.get("attachment_path").toString();
		String attachment_file_size = map.get("attachment_file_size") == null ? "" : map.get("attachment_file_size").toString();
		String mms_send_content = map.get("send_content") == null ? "" : map.get("send_content").toString();
		
		arry = attachment_path.split("/");
		
		filename = arry[arry.length-1];
		
		//System.out.println("attachment_path : " + attachment_path);
		//System.out.println("filename" + filename);
		//System.out.println("mms_send_content" + mms_send_content);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("attachment_path", attachment_path);
		mav.addObject("attachment_file_size", attachment_file_size);
		mav.addObject("filename", filename);
		mav.addObject("mms_send_content", mms_send_content);
		mav.setViewName("/smsManager/ifrmUploadMMS");

		return mav;		
	}
	
	/**
	 * MMS 이미지 파일 업로드
	 * @param request
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/uploadMMS.do", method=RequestMethod.POST)
    @ResponseBody
    //public Object uploadMMS(MultipartHttpServletRequest mRequest)
    public ModelAndView uploadMMS(MultipartHttpServletRequest mRequest)
	{
		//boolean os_pattern = true; //window
		boolean os_pattern = false; //linux

		
		String rtn_code = "200"; 
		String savefilename = "";
		String orgfilename = "";
		String savePath = "/resources";
		String osPath = "";
		
		if(os_pattern) osPath = "\\resources";
		else osPath = "/resources";
		
		String path = request.getSession().getServletContext().getRealPath(osPath);
		String attachment_path = "";
		String datefolder = "";
		MultipartFile mpf;
		File upload_dir, mcs_dir, year_dir, month_dir;
		int img_size = 1024 * 300; //300kb
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/smsManager/ifrmUploadMMS");
		//System.out.println("path : " + path);
		
        Iterator<String> itr =  mRequest.getFileNames();
        if(itr.hasNext()) {
        	mpf = mRequest.getFile(itr.next());
            //System.out.println(mpf.getOriginalFilename() +" uploaded!");
            //System.out.println("mpf : " + mpf.getContentType());
            
            try {
            	// 형식 지정
                Date dt = new Date();
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf = new SimpleDateFormat();
                
              //resources/upload/에 mcs_upload 디렉토리가 없을 경우 생성
                upload_dir = new File(path, "upload");
                if(!upload_dir.exists()){
                	upload_dir.mkdir();
                }
                savePath = savePath + "/upload";
                if(os_pattern){
                    path = path + "\\upload";
               }else{
                    path = path + "/upload";
               }
                
                //resources/upload/에 mcs_upload 디렉토리가 없을 경우 생성
                mcs_dir = new File(path, "mcs_upload");
                if(!mcs_dir.exists()){
                	mcs_dir.mkdir();
                }
                savePath = savePath + "/mcs_upload";
                if(os_pattern){
                    path = path + "\\mcs_upload";
               }else{
                    path = path + "/mcs_upload";
               }
                
                //resources/upload/에 지금 년도 디렉토리가 없을시 생성
                sdf.applyPattern("yyyy");
                datefolder = sdf.format(dt).toString();
                year_dir = new File(path, datefolder);
                if(!year_dir.exists()){
                	year_dir.mkdir();
                }
                savePath = savePath + "/" + datefolder;
                if(os_pattern){
                     path = path + "\\" + datefolder;
                }else{
                     path = path + "/" + datefolder;
                }
                
                //resources/upload/년도/에 지금 월일 디렉토리가 없을 경우 생성
                sdf.applyPattern("MMdd");
                datefolder = sdf.format(dt).toString();
                month_dir = new File(path, datefolder);
                if(!month_dir.exists()){
                	month_dir.mkdir();
                }
                savePath = savePath + "/" + datefolder;
                if(os_pattern){
                    path = path + "\\" + datefolder;
                }else{
                    path = path + "/" + datefolder;
                }
                
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
            
            
            try {
            	//jpg가 아닐 경우 등록 안함.
            	if(!mpf.getContentType().equals("image/jpeg") && !mpf.getContentType().equals("image/pjpeg")){
            		rtn_code = "303";
            		mav.addObject("rtn_code", rtn_code);
            		return mav;
            	}
                //System.out.println("file length : " + mpf.getBytes().length);
                //System.out.println("file name : " + mpf.getOriginalFilename());
                
            	if(mpf.getBytes().length < img_size){
            		orgfilename = mpf.getOriginalFilename();
                    //확장자 갖고오기. ex)png, jpg, jpeg
             		String ext = orgfilename.substring(orgfilename.lastIndexOf(".")+1,orgfilename.length());
                     
             		//중복되지 않는 파일명 생성하기
             		String randomUUID = ""+UUID.randomUUID();
             		savefilename = randomUUID+"."+ext;
            		
            		
	    			//전송된 파일 서버에 복사하기
	    			InputStream is = mpf.getInputStream();
	    			//서버에 복사(출력)하기 위한 스트림 객체
	    			FileOutputStream fos;
	    			
	    			if(os_pattern) fos = new FileOutputStream(path + "\\"+ savefilename);
	    			else fos = new FileOutputStream(path + "/"+ savefilename);
	    			
	    			//파일 복사하기
	    			FileCopyUtils.copy(is, fos);
	    			
	    			is.close();
	    			fos.close();
	    			//System.out.println(path + "\\"+ savefilename);
	    			//System.out.println("FileUpload1 Success! "+orgfilename+"\\"+savefilename);
	    			
	    			/*if(os_pattern){
		    			attachment_path = savePath + "\\" + savefilename;
		    			attachment_path = attachment_path.replace("\\", "\\\\");
	    			}else{
	    				attachment_path = savePath + "/" + savefilename;
	    			}*/
	    			
	    			attachment_path = savePath + "/" + savefilename; 
	    			
	    			mav.addObject("attachment_path", attachment_path);
	    			mav.addObject("attachment_file_size", mpf.getBytes().length);
	    			mav.addObject("savefilename", savefilename);
	    			mav.addObject("filename", orgfilename);
            	}else{
            		rtn_code = "302";
            	}
    		} catch (Exception e) {
    			e.printStackTrace();
    			//System.out.println("FileUpload1 Fail! "+e.getMessage());
    			rtn_code = "301";
    		}
        } else {
        	rtn_code = "301";
        }
        
		mav.addObject("rtn_code", rtn_code);
		return mav;		
    }
	
	/**
	 * 추천 문구 리스트 조회
	 * @param map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/uploadMMS.do", params="proc=delete")
	public JSONObject deleteUploadMMS(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		JSONObject jsonObj = new JSONObject();
		System.out.println("map : " + map);
		
		String deletePath 				= map.get("deletePath") == null ? "" : map.get("deletePath").toString();
		String rtn_code = "200";
		
		String path = request.getSession().getServletContext().getRealPath(deletePath);
		System.out.println(path);
		
		File file = new File(path);
		 
		 if(file.delete()){
			 //파일삭제 완료
			 rtn_code = "200";
		 }else{
			 //파일삭제 실패
			 rtn_code = "301";
		 }
		 jsonObj.put("rtn_code", rtn_code);
		return jsonObj;
	}

	
	/**
	 * 추천 문구 리스트 조회
	 * @param map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/smsManagerRegister.do", params="proc=recommendmsg")
	public JSONObject getRecommendMsgList(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		String sms_type 				= map.get("sms_type") == null ? "" : map.get("sms_type").toString();
		String rtn_sms_type = "";
		JSONObject jsonObj = new JSONObject();
		
		List<Emoticon> emoticon_list = null; 
		HashMap<String, Object> temp_map = new HashMap<String, Object>();
		boolean skip_process = true;
		String rtn_code = "200";
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		String member_id = loginInfo.getPk_member_id();
		
		temp_map.put("pk_member_id", member_id);
		
		switch(sms_type){
			case "1": temp_map.put("fd_sms_type", "S");	rtn_sms_type = "SMS"; break;
			case "2": temp_map.put("fd_sms_type", "L");	rtn_sms_type = "LMS"; break;
			case "3": temp_map.put("fd_sms_type", "M");	rtn_sms_type = "MMS"; break;
			default : skip_process = false; rtn_code = "301";
		}
		
		if(skip_process) {
			try {
				emoticon_list = emoticonService.getRecommendContentList(temp_map);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				rtn_code = "301";
			}
		}
		jsonObj.put("rtn_sms_type", rtn_sms_type);
		jsonObj.put("emoticon_list", emoticon_list);
		jsonObj.put("rtn_code", rtn_code);
		
		return jsonObj;
	}

	/**
	 * 문자 관리 > 문자 발송 등록
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/smsManagerRegister.do", params="proc=register")
	public JSONObject getSmsManagerRegisterProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		System.out.println("map : " + map);
		System.out.println("[CALL Sms Controller ] getSmsManagerRegisterProc");
		//String use_coin = map.get("use_coin") == null ? "" : map.get("use_coin").toString();
		String search_req_cnt 			= map.get("search_req_cnt") == null ? "" : map.get("search_req_cnt").toString();
		String req_count 			= map.get("req_count") == null ? "" : map.get("req_count").toString();
		String send_type 				= map.get("send_type") == null ? "" : map.get("send_type").toString();
		String yearColumn 			= map.get("yearColumn") == null ? "" : map.get("yearColumn").toString();
		String monthColumn 			= map.get("monthColumn") == null ? "" : map.get("monthColumn").toString();
		String dayColumn 			= map.get("dayColumn") == null ? "" : map.get("dayColumn").toString();
		String hourColumn 			= map.get("hourColumn") == null ? "" : map.get("hourColumn").toString();
		String minuteColumn 		= map.get("minuteColumn") == null ? "" : map.get("minuteColumn").toString();
		//String req_count 				= map.get("req_count") == null ? "" : map.get("req_count").toString();
		String sms_type 				= map.get("sms_type") == null ? "" : map.get("sms_type").toString();
		String radioMonth 			= map.get("radioMonth") == null ? "" : map.get("radioMonth").toString();
		String customer_type 		= map.get("customer_type") == null ? "" : map.get("customer_type").toString();
		String[] check_day 			= request.getParameterValues("check_day");
		String check_time_yn 		= map.get("check_time_yn") == null ? "" : map.get("check_time_yn").toString();
		String check_time_all 		= map.get("check_time_all") == null ? "" : map.get("check_time_all").toString();
		String[] check_time 			= request.getParameterValues("check_time");
		//String check_time = map.get("check_time") == null ? "" : map.get("check_time").toString();
		//String start_hourColumn 	= map.get("start_hourColumn") == null ? "" : map.get("start_hourColumn").toString();
		//String end_hourColumn 		= map.get("end_hourColumn") == null ? "" : map.get("end_hourColumn").toString();
		String send_count_yn 		= map.get("send_count_yn") == null ? "" : map.get("send_count_yn").toString();
		String search_call_rcv_cnt 		= map.get("search_call_rcv_cnt") == null ? "" : map.get("search_call_rcv_cnt").toString();
		String search_call_rcv_cnt_type = map.get("search_call_rcv_cnt_type") == null ? "" : map.get("search_call_rcv_cnt_type").toString();
		String fk_tel 					= map.get("fk_tel") == null ? "" : map.get("fk_tel").toString();
		String send_content 		= map.get("send_content") == null ? "" : map.get("send_content").toString();
		//List<SmsManager> customer_tel_list = request.getParameterValues("customer_tel_list");
		String attachment_path 		= map.get("attachment_path") == null ? "" : map.get("attachment_path").toString();
		String attachment_file_size 		= map.get("attachment_file_size") == null ? "" : map.get("attachment_file_size").toString();
		String send_subject 		= map.get("send_subject") == null ? "" : map.get("send_subject").toString();
		String send_mms_subject 		= map.get("send_mms_subject") == null ? "" : map.get("send_mms_subject").toString();
		
		String group_code_temp = "";
		String search_week = "";
		String search_time = "";
		String search_year;
		String search_month;
		String search_date;
		String rtn_code = "200";
		String result = "";
		String fd_memo = "";
		boolean skip_process = true;
		int i;
		
		Map<String, Object> unvailDay_map = new HashMap<String, Object>();
		
		Coin coin = new Coin();
		JSONObject jsonObj = new JSONObject();
		SmsManager smsManager_temp = new SmsManager();
		SimpleDateFormat ft = new SimpleDateFormat ();//"yyyyMMddHHmmss");
		
		ft.applyPattern("yyyyMMddHHmmss");
		String reg_date =  ft.format(new Date( ));
		
		ft.applyPattern("yyyyMMddHHmm");
		String reserve_time = ft.format(new Date( ));
		
		ft.applyPattern("yyyy-MM-dd");
		String nowDate = ft.format(new Date());
		
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		//그룹코드는 나노세컨드로 
		try {
			group_code_temp = Long.toString(System.nanoTime());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
		}
		
		//코인 정보를 갖고옴.
		try {
			coin = smsManagerService.getCoin(loginInfo.getPk_member_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
			rtn_code = "306";
		}
		
		//결제되는 코인과 비교하여 부족시 "305" error 처리
		switch(sms_type){
			case "1": 	if(Integer.parseInt(req_count) * 2 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
			case "2": 	if(Integer.parseInt(req_count) * 5 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
			case "3": 	if(Integer.parseInt(req_count) * 20 > coin.getFd_total_coin()){
								skip_process = false;
								rtn_code = "305";
							}break;
		}
		
		//예약은 Max 3달까지만 설정이 가능(3달뒤 날짜와 비교하여 에러처리)
		if(skip_process){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String dateTemp = dateFormat.format(new Date());
			Calendar calendarTemp = new java.util.GregorianCalendar();
			Date tempNow;
			String setDate = "";
			String set3MonthDate = "";
			try 
			{
				/*3달후 날짜 선택*/
				tempNow = dateFormat.parse(dateTemp);
				calendarTemp.setTime(tempNow);
				calendarTemp.add(Calendar.MONTH, +3);
				set3MonthDate = dateFormat.format(calendarTemp.getTime());
			} 
			catch (ParseException e1){
				e1.printStackTrace();
				skip_process = false;
			}
			//설정한 날짜 갖고오기
			setDate = yearColumn + ((monthColumn.length() == 1) ? "0"+monthColumn : monthColumn) + ((dayColumn.length() == 1) ? "0"+dayColumn : dayColumn); 
			System.out.println("setDate = " + setDate);
			if(setDate.length() > 0){
				if(Long.parseLong(set3MonthDate) < Long.parseLong(setDate)){
					rtn_code = "308";
					skip_process = false;
				}
			}
			
			//System.out.println("set3MonthDate= " + set3MonthDate);
			//System.out.println("setDate = " + setDate);
		}
		
		//문자예약시 21 ~ 8시 까지는 예약이 안되게 처리
		/*if(skip_process){
			if(send_type.equals("2")){
				String timeTemp = ((hourColumn.length() == 1) ? "0"+hourColumn : hourColumn) + ((minuteColumn.length() == 1) ? "0"+minuteColumn : minuteColumn);
				if( (Integer.parseInt(timeTemp) > 2059) || (Integer.parseInt(timeTemp) <= 800)){
					skip_process = false;
					rtn_code = "309";
				}
			}
		}*/
		
		//예약불가일인지 체크
		if(skip_process){
			try {
				unvailDay_map.put("year", yearColumn);
				unvailDay_map.put("month", (monthColumn.length() == 1) ? "0"+monthColumn : monthColumn);
				unvailDay_map.put("day", (dayColumn.length() == 1) ? "0"+dayColumn : dayColumn);
				
				fd_memo = smsManagerService.getUnvailDay(unvailDay_map);
				if(fd_memo != null){
					rtn_code = "307";
					skip_process = false;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
			}
		}
		
		if(skip_process) {
			//로그인 아이디
			smsManager_temp.setFk_member_id(loginInfo.getPk_member_id());
			
			//발신번호
			String[] tel_temp = fk_tel.split("-");
			fk_tel = "";
			for ( i=0 ; i<tel_temp.length ; i++){
				fk_tel += tel_temp[i];
			}
			smsManager_temp.setFk_tel(fk_tel);
			
			//그룹코드 (나노세컨드)
			smsManager_temp.setGroup_code(group_code_temp);
			
			//Custom_msg_id는 "아이디_그룹코드"
			smsManager_temp.setFk_custom_msg_id(loginInfo.getPk_member_id() + "_" + group_code_temp);
			
			//SMS: 1,1 LMS: 4,1 MMS: 4,2~4
			switch(sms_type){
				case "1" : 	//SMS
								smsManager_temp.setMsg_type("1");
								smsManager_temp.setMsg_sub_type("1");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 2));
								smsManager_temp.setSend_subject(send_subject);
								break;
				case "2" : 	//LMS
								smsManager_temp.setMsg_type("4");
								smsManager_temp.setMsg_sub_type("1");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 5));
								smsManager_temp.setSend_subject(send_subject);
								break;
				case "3" :	//MMS  
								smsManager_temp.setMsg_type("4");
								smsManager_temp.setMsg_sub_type("3");
								smsManager_temp.setUse_coin(Integer.toString(Integer.parseInt(req_count) * 20));
								smsManager_temp.setAttachment_file_size(attachment_file_size);
								smsManager_temp.setAttachment_path(attachment_path);
								smsManager_temp.setSend_subject(send_mms_subject);
								break;
				default : 
			}
			
			//발송건수
			smsManager_temp.setReq_count(req_count);
			
			//발송가능건수
			smsManager_temp.setSearch_req_cnt(search_req_cnt);
			
			//등록일
			smsManager_temp.setReg_date(reg_date);
			
			//문자 제목
			//smsManager_temp.setSend_subject("");
			
			//문자 내용
			smsManager_temp.setSend_content(send_content);
			
			//발송 날짜 세팅 (10 이하일때 앞에 0붙히기)
			monthColumn = (monthColumn.length() == 1) ? "0"+monthColumn : monthColumn;
			dayColumn = (dayColumn.length() == 1) ? "0"+dayColumn : dayColumn;
			hourColumn = (hourColumn.length() == 1) ? "0"+hourColumn : hourColumn;
			minuteColumn = (minuteColumn.length() == 1) ? "0"+minuteColumn : minuteColumn;
				
			
			//즉시일때 : 1 예약일때 : 2
			switch(send_type) {
				case "1" : smsManager_temp.setReserve_time(reserve_time); break;
				case "2" : smsManager_temp.setReserve_time(yearColumn + monthColumn + dayColumn + hourColumn + minuteColumn); break;
				default :
			}
			
			//발송대상 기간설정 1개월, 3개월, 6개월
			smsManager_temp.setSearch_month(radioMonth);
			
			//대상 조회 기간 설정.
			try 
			{
				temp_now = ft.parse(nowDate);
				calendar.setTime(temp_now );
				calendar.add(Calendar.MONTH, - Integer.parseInt(radioMonth));	
				search_date = ft.format(calendar.getTime());
				String[] date_arry = search_date.split("-");
				search_year = date_arry[0];
				search_month = date_arry[1];
				
				smsManager_temp.setExtraction_year(search_year);
				smsManager_temp.setExtraction_month(search_month);
			} 
			catch (ParseException e1){ skip_process = false;}
			
			//전체 :1 ,단골 : 2 , 신규 : 3 미사용 : -1
			switch(customer_type){
				case "1" :  smsManager_temp.setSearch_customer("1"); break;
				case "2" :  smsManager_temp.setSearch_customer("2"); break;
				case "3" :  smsManager_temp.setSearch_customer("3"); break;
				case "4" :  smsManager_temp.setSearch_customer("4"); break;
				default : smsManager_temp.setSearch_customer("-1");
			}
			
			//ex)일월화수목금토 = 1^2^3^4^5^6^7
			if(check_day != null){
				for(i=0 ; i<check_day.length ; i++){
					if(i == (check_day.length-1))
						search_week += check_day[i];
					else
						search_week += check_day[i] + "^";
				}
				smsManager_temp.setSearch_week(search_week);
				
				//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
				if(check_day.length < 7) {
					for(i=0 ; i<check_day.length ; i++){
						switch(check_day[i]){
							case "1" : smsManager_temp.setExtraction_day1("1");	break; //일요일
							case "2" : smsManager_temp.setExtraction_day2("2");	break; //월요일
							case "3" : smsManager_temp.setExtraction_day3("3");	break; //화요일
							case "4" : smsManager_temp.setExtraction_day4("4");	break; //수요일
							case "5" : smsManager_temp.setExtraction_day5("5");	break; //목요일
							case "6" : smsManager_temp.setExtraction_day6("6");	break; //금요일
							case "7" : smsManager_temp.setExtraction_day7("7");	break; //토요일
							default : 
						}
					}
				}
			}else{
				search_week = "1^2^3^4^5^6^7";
				smsManager_temp.setSearch_week(search_week);
			}
			
			//시간대 사용 체크시
			if(check_time_yn.equals("1")){
				if(check_time != null){
					for(i=0 ; i<check_time.length ; i++){
						if(i == (check_time.length-1))
							search_time += check_time[i];
						else
							search_time += check_time[i] + "^";
					}
					smsManager_temp.setSearch_time(search_time);
					
					//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
					if(check_time.length < 7) {
						for(i=0 ; i<check_time.length ; i++){
							switch(check_time[i]){
								case "A" : smsManager_temp.setExtraction_time1("A"); break; //새벽(00~07)
								case "B" : smsManager_temp.setExtraction_time2("B"); break; //아침(07~09)
								case "C" : smsManager_temp.setExtraction_time3("C"); break; //오전(09~11)
								case "D" : smsManager_temp.setExtraction_time4("D"); break; //점심(11~14)
								case "E" : smsManager_temp.setExtraction_time5("E"); break; //오후(14~17)
								case "F" : smsManager_temp.setExtraction_time6("F"); break; //저녁(17~21)
								case "G" : smsManager_temp.setExtraction_time7("G"); break; //야간(21~24)
								default : 
							}
						}
					}
				}else{
					search_time = "A^B^C^D^E^F^G";
					smsManager_temp.setSearch_time(search_time);
				}
			}else{
				search_time = "A^B^C^D^E^F^G";
				smsManager_temp.setSearch_time(search_time);
			}
			
			//통화건수 사용 체크시
			if(send_count_yn.equals("1")){
				smsManager_temp.setSearch_call_rcv_cnt(search_call_rcv_cnt);
				smsManager_temp.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
			}
			
			//발송여부 즉시 : 1 예약 : 2
			smsManager_temp.setReserve_type(send_type);
			
			//사용자 아이피
			//smsManager_temp.setClient_ip(request.getRemoteAddr());
			smsManager_temp.setMember_ip(request.getRemoteAddr());
			
			//등록자 구분 [회원:M, 상담사:C]
			smsManager_temp.setReq_member_type("M");
			
			try {
				rtn_code = smsManagerService.registerSms(smsManager_temp);
				//rtn_code = "301";
				//System.out.println("send_subject : " + smsManager_temp.getSend_subject());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				rtn_code = "301";
			}
			
		}
		
		jsonObj.put("rtn_code", rtn_code);
		jsonObj.put("fd_memo", fd_memo);
		
		return jsonObj;
	}
	
	/**
	 * 문자 관리 > 발송 대상 조회
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/smsManagerRegister.do", params="proc=search")
	public JSONObject getSmsManagerSearchProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		System.out.println("Map : " + map);
		System.out.println("[CALL Sms Controller ] getSmsManagerSearchProc");
		
		String search_date;
		String search_year;
		String search_month;
		String rtn_code = "200";
		String use_coin = "0";
		
		int i;
		int customer_count = 0;
		boolean skip_customer = false;
		boolean skip_process = true;
		
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = ft.format(new Date());
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		
		SmsManager search_customer = new SmsManager();
		List<SmsManager> customer_tel_list = null;
		JSONObject jsonObj = new JSONObject();
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		
		String customer_type 					= map.get("customer_type") == null ? "" : map.get("customer_type").toString();
		String sms_type 							= map.get("sms_type") == null ? "" : map.get("sms_type").toString();
		String radioMonth 						= map.get("radioMonth") == null ? "" : map.get("radioMonth").toString();
		String[] check_day 						= request.getParameterValues("check_day");
		String check_time_yn 					= map.get("check_time_yn") == null ? "" : map.get("check_time_yn").toString();
		String[] check_time 						= request.getParameterValues("check_time");
		String send_count_yn 					= map.get("send_count_yn") == null ? "" : map.get("send_count_yn").toString();
		String search_call_rcv_cnt 			= map.get("search_call_rcv_cnt") == null ? "" : map.get("search_call_rcv_cnt").toString();
		String search_call_rcv_cnt_type 	= map.get("search_call_rcv_cnt_type") == null ? "" : map.get("search_call_rcv_cnt_type").toString();
		
		//로그인된 아이디
		search_customer.setFk_member_id(loginInfo.getPk_member_id());
		
		//1: 전체고객 2: 단골고객 3: 신규고객
		search_customer.setSearch_customer(customer_type);
		switch(customer_type){
			case "1" : 	skip_customer = true;
							break;
			case "2" : 	skip_customer = true;
							search_call_rcv_cnt = "5";
							search_call_rcv_cnt_type = "1";
							search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
							search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
							break;
			case "3" : 	skip_customer = true;
							search_call_rcv_cnt = "1";
							search_call_rcv_cnt_type = "-1";
							search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
							search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
							break;
			default :
		}
		
		//대상 조회 기간 설정.
		try 
		{
			temp_now = ft.parse(nowDate);
			calendar.setTime(temp_now );
			calendar.add(Calendar.MONTH, - Integer.parseInt(radioMonth));	
			search_date = ft.format(calendar.getTime());
			String[] date_arry = search_date.split("-");
			search_year = date_arry[0];
			search_month = date_arry[1];
			
			search_customer.setExtraction_year(search_year);
			search_customer.setExtraction_month(search_month);
		} 
		catch (ParseException e1){ skip_process = false; rtn_code = "301";}
		
		//전체고객, 단골고객, 신규고객 이 아닌 사용자가 임의로 설정한 값일 때
		if(skip_customer == false){
			//요일 처리
			if(check_day != null){
				//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
				if(check_day.length < 7) {
					for(i=0 ; i<check_day.length ; i++){
						switch(check_day[i]){
							case "1" : search_customer.setExtraction_day1("1");	break; //일요일
							case "2" : search_customer.setExtraction_day2("2");	break; //월요일
							case "3" : search_customer.setExtraction_day3("3");	break; //화요일
							case "4" : search_customer.setExtraction_day4("4");	break; //수요일
							case "5" : search_customer.setExtraction_day5("5");	break; //목요일
							case "6" : search_customer.setExtraction_day6("6");	break; //금요일
							case "7" : search_customer.setExtraction_day7("7");	break; //토요일
							default : 
						}
					}
				}
			}
			
			//시간 처리
			if(check_time_yn.equals("1")){
				if(check_time != null){
					//전부 체크시(length가 7) where 조건에서 결과값이 전부 체크를 안한것과 같으므로 안넣음.
					if(check_time.length < 7) {
						for(i=0 ; i<check_time.length ; i++){
							switch(check_time[i]){
								case "A" : search_customer.setExtraction_time1("A"); break; //새벽(00~07)
								case "B" : search_customer.setExtraction_time2("B"); break; //아침(07~09)
								case "C" : search_customer.setExtraction_time3("C"); break; //오전(09~11)
								case "D" : search_customer.setExtraction_time4("D"); break; //점심(11~14)
								case "E" : search_customer.setExtraction_time5("E"); break; //오후(14~17)
								case "F" : search_customer.setExtraction_time6("F"); break; //저녁(17~21)
								case "G" : search_customer.setExtraction_time7("G"); break; //야간(21~24)
								default : 
							}
						}
					}
				}
			}
			
			//통화횟수
			if(send_count_yn.equals("1")){
				search_customer.setSearch_call_rcv_cnt(search_call_rcv_cnt);
				search_customer.setSearch_call_rcv_cnt_type(search_call_rcv_cnt_type);
			}
			
		}
		
		System.out.println("search_customer : " + search_customer.getSearch_customer());
		
		try {
			customer_count = smsManagerService.getCustomerCount(search_customer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			skip_process = false;
			rtn_code = "302";
		}
		
		if(skip_process){
			try {
				customer_tel_list = smsManagerService.getCustomerTelList(search_customer);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				skip_process = false;
				rtn_code = "303";
			}
		}
		
		if(skip_process){
			switch(sms_type){
			//SMS 1건당 2 Coin
			case "1": use_coin = Integer.toString(customer_count * 2); break;
			//LMS 1건당 5 Coin
			case "2": use_coin = Integer.toString(customer_count * 5); break;
			//MMS 1건당 20 Coin
			case "3": use_coin = Integer.toString(customer_count * 20); break;
			}
		}
		
		jsonObj.put("customer_tel_list", customer_tel_list);
		jsonObj.put("customer_count", customer_count);
		jsonObj.put("use_coin", use_coin);
		jsonObj.put("rtn_code", rtn_code);
		
		
		return jsonObj;
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
	
	
//JCY
	/**
	 * 문자관리 메인(달력)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/smsManagerMain.do" )
	public ModelAndView smsManagerMain(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] SmsManagerController.smsManagerMain()");

		//달력 년/월
		String sCalYear			= request.getParameter("sCalYear")		!= null ? request.getParameter("sCalYear").trim() : "";
		String sCalMonth		= request.getParameter("sCalMonth")		!= null ? request.getParameter("sCalMonth").trim() : "";
		
		//검색
		String searchColumn		= request.getParameter("searchColumn")	!= null ? request.getParameter("searchColumn").trim() : "view_date";
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";
		String searchTel		= request.getParameter("searchTel")		!= null ? request.getParameter("searchTel").trim() : "";

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/smsManagerMain");
		
		
		//요금 납부 확인
		Map<String, Object> result_map = checkPrice(loginInfo.getPk_member_id());
	    String sum_fd_price		= ""+ result_map.get("sum_fd_price");
	    String sum_pay_price	= ""+ result_map.get("sum_pay_price");
	    int cnt					= Integer.parseInt(""+result_map.get("cnt"));
	
	    String script = "";
	    
	    if(cnt >= 2){
	    	//2개월 이상 체납된 사용자 (접근제한 처리)
	    	
	    	script = "payMsg("+sum_fd_price+","+sum_pay_price+");";
			
			mav.addObject("script",script);
			mav.setViewName("message/returnScript");	
			
	    }else{
	    	 if(cnt >= 1){
	 	    	//2개월 이상 체납된 사용자 (접근제한 처리)
	 	    	
	 	    	script = "payMsg("+sum_fd_price+","+sum_pay_price+");";
	 			
	 			
	 			
	 	    }
				
			//회원이 사용하는 전화 리스트
			Map<String, String> memberMap = new HashMap<String, String>();
			memberMap.put("fk_member_id", loginInfo.getPk_member_id());
			memberMap.put("fd_use_yn", "Y");
					
			List<BoardEvent> memListGet = smsManagerService.getMemberTelList(memberMap);
			ArrayList<Map<String, String>> memList = new ArrayList<Map<String, String>>();
			
			for(BoardEvent item:memListGet){
				Map<String, String> itemMap = new HashMap<String, String>();
				itemMap.put("fk_member_id",		item.getFk_member_id());
				itemMap.put("pk_tel",			StrUtils.formatPhoneNo(item.getPk_tel().toString(), "-"));
				itemMap.put("fd_use_yn",		item.getFd_use_yn());
				itemMap.put("fd_reg_date",		item.getFd_reg_date());
				itemMap.put("fd_del_date",		item.getFd_del_date());
				memList.add(itemMap);
			}
			//회원이 사용하는 전화 리스트
			
			///// 달력
			//날짜 저장 리스트
			Object[] calList = new Object[42];
			//리스트 번호
			int dayNum = 0;
	
					
			Calendar cal_today = Calendar.getInstance();
			Calendar cal_before = Calendar.getInstance();
			Calendar cal_after = Calendar.getInstance();
			Calendar cal_now = Calendar.getInstance();
	
			String year = sCalYear;
			String month = sCalMonth;
	
			//1일 가져오기 (년,월)
			cal_today.set(Calendar.DATE, 1);
	
			int i_year = cal_today.get(Calendar.YEAR);
			int i_month = cal_today.get(Calendar.MONTH) + 1;
			int i_day = cal_now.get(Calendar.DATE);
	/*
			Date d = new Date();
			int i_day = d.getDate();
	*/		
	
			String nowYear = (String.valueOf(i_year).length() < 2)?"0"+String.valueOf(i_year):String.valueOf(i_year);
			String nowMonth = (String.valueOf(i_month).length() < 2)?"0"+String.valueOf(i_month):String.valueOf(i_month);
			String nowDay = (String.valueOf(i_day).length() < 2)?"0"+String.valueOf(i_day):String.valueOf(i_day);
			
			//년 월이 없을때 기본 오늘로 세팅
			if(year != null && month != null && !"".equals(year) && !"".equals(month)) {
				cal_today.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
			} else {
				year = (String.valueOf(i_year).length() < 2)?"0"+String.valueOf(i_year):String.valueOf(i_year);
				month = (String.valueOf(i_month).length() < 2)?"0"+String.valueOf(i_month):String.valueOf(i_month);
			}
	
			//요일계산
			cal_before.setTime(cal_today.getTime());
			cal_before.add(Calendar.MONTH, -1);
	
			cal_after.setTime(cal_today.getTime());
			cal_after.add(Calendar.MONTH, 1);
	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
			String strToday = sdf.format(cal_today.getTime());
			String strBefore = sdf.format(cal_before.getTime());
			String strAfter = sdf.format(cal_after.getTime());
	
			int today_last_date = cal_today.getActualMaximum(Calendar.DATE);
			int before_last_date = cal_before.getActualMaximum(Calendar.DATE);
	
			String[] str_week_list = new String[]{"", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
			int today_first_week_idx = cal_today.get(Calendar.DAY_OF_WEEK);
			String today_first_week = str_week_list[today_first_week_idx];
	
				int gap = today_first_week_idx - 1;
	
				if(gap == 0){
					gap = 7;
				}
	
			//전달 날짜
			for(int i=gap; i>0; i--) {
				cal_before.set(Calendar.DATE, (before_last_date - i + 1));
	
				String yearTxt = String.valueOf(cal_before.get(Calendar.YEAR));
	
				String monthTxt = String.valueOf(cal_before.get(Calendar.MONTH) + 1);
				if(monthTxt.length() < 2) monthTxt = "0" + monthTxt;
	
				String day = String.valueOf(cal_before.get(Calendar.DATE));
				int dow = cal_before.get(Calendar.DAY_OF_WEEK);
				if(day.length() < 2) day = "0" + day;
	
				String classTxt = "";
		
				if(dow == 7) classTxt = " last";
	
				//날짜별 class(classTxt), text(txts)저장
				Map<String, Object> calMap = new HashMap<String, Object>();
				ArrayList<Map<String, Object>> txtArray = new ArrayList<Map<String, Object>>();
	
				calMap.put("txtArray",txtArray);
				calMap.put("classTxt", classTxt);
				calMap.put("yearTxt", yearTxt);
				calMap.put("monthTxt", monthTxt);
				calMap.put("dayTxt", "<font color=\"#aaaaaa\">"+day+"</font>");
				calMap.put("dayNum", dayNum);
	
				calList[dayNum] = calMap;
	
				dayNum++;
			}
	
	
	// DB에서 가져오는 부분 - S
	
	// tbl_board_event
				searchString = year+""+month;
	
				if(!searchString.equals("")){
					searchColumn = "view_date";
				}
				String lastDate = ((""+today_last_date).length() < 2)? "0"+today_last_date : ""+today_last_date;
	
//				String lastDate = (today_last_date < 10)? "0"+today_last_date : ""+today_last_date;
				
	
				//PageHelper pageHelper 	= new PageHelper(0, 0, 0, searchColumn, searchString);
				Map<String, Object> cal_map = new HashMap<String, Object>();
				cal_map.put("searchColumn", searchColumn);
				cal_map.put("year", year);
				cal_map.put("month", month);
				
				List<BoardEvent> boardEvArray = smsManagerService.getListCal(cal_map);
	
				ArrayList<Map<String, ArrayList<Map<String, Object>>>> boardEvList = new ArrayList<Map<String, ArrayList<Map<String, Object>>>>();
				int boardEvListNum = (boardEvArray != null) ? boardEvArray.size() : 0;
	
	
				for(int i=0 ; i < today_last_date ; i++){			// 날짜만큼 반복
					Map<String, ArrayList<Map<String, Object>>> boardEvMap = new HashMap<String, ArrayList<Map<String, Object>>>();
					ArrayList<Map<String, Object>> boardEvInList = new ArrayList<Map<String, Object>>();
					String iNum = (i < 10)? "0"+i : ""+i;
					
					if(boardEvArray != null){
						for(BoardEvent item : boardEvArray){
							if(iNum.equals(item.getFd_dd())){
								Map<String, Object> boardEvInMap = new HashMap<String, Object>();
								boardEvInMap.put("pk_seq", item.getPk_seq());
								boardEvInMap.put("fd_title", item.getFd_title());
								boardEvInMap.put("fd_yy", item.getFd_yy());
								boardEvInMap.put("fd_mm", item.getFd_mm());
								boardEvInMap.put("fd_dd", item.getFd_dd());
								boardEvInList.add(boardEvInMap);
							}
						}
					}
					boardEvMap.put("dayList", boardEvInList);							//날짜별 내용(배열)
					boardEvList.add(boardEvMap);
				}
	// tbl_sms_group
	
				Map<String,String> smsGMap = new HashMap<String,String>();
				smsGMap.put("fk_tel", searchTel.replace("-", ""));
				smsGMap.put("fk_member_id", loginInfo.getPk_member_id());
				smsGMap.put("reserve_time_s", searchString+"010000");
				smsGMap.put("reserve_time_e", searchString+""+lastDate+"5959");
				smsGMap.put("cancel_yn", "N");
				
				List<BoardEvent> smsGroupArray = smsManagerService.getSmsGroupList(smsGMap);
	
				ArrayList<Map<String, ArrayList<Map<String, Object>>>> smsGroupList = new ArrayList<Map<String, ArrayList<Map<String, Object>>>>();
	 
				int smsGroupListNum = (smsGroupArray != null) ? smsGroupArray.size() : 0;
	
	
				for(int i=0 ; i <= today_last_date ; i++){			// 날짜만큼 반복
	
					Map<String, ArrayList<Map<String, Object>>> smsGroupMap = new HashMap<String, ArrayList<Map<String, Object>>>();
					ArrayList<Map<String, Object>> smsGroupInList = new ArrayList<Map<String, Object>>();
					String iNum = (i < 10)? "0"+i : ""+i;
	
					if(smsGroupArray != null){
						if(smsGroupArray.size() > 0){
							for(BoardEvent item : smsGroupArray){
								String reserveTypeTxt = "";
								String reserveTime = "";
								String reserveTimeTxt = "";
								String searchCallRcvCntTypeTxt = "";
								String fd_yy, fd_mm, fd_dd;
	
								//발송요청시간
								reserveTime = item.getReserve_time() == null || item.getReserve_time().equals("") ? "" : item.getReserve_time();
								fd_yy = reserveTime.substring(0,4);
								fd_mm = reserveTime.substring(4,6);
								fd_dd = reserveTime.substring(6,8);
								reserveTimeTxt = reserveTime.substring(8, 10)+":"+reserveTime.substring(10, 12);
	
								if(iNum.equals(fd_dd)){
		
				
									Map<String, Object> itemMap = new HashMap<String, Object>();
				
									//즉시,예약
									if(item.getReserve_type() != null){
										//reserveTypeTxt = item.getReserve_type().equals("1") ? "즉시발송" : "예약발송";
										//reserveTypeTxt = item.getReserve_type().equals("1") ? "발송완료" : "발송예약";
										if(item.getMsg_send_finish().equals("Y")){
											reserveTypeTxt = "발송완료";
										}else{
											reserveTypeTxt = "발송예약";
										}
									}
				
									//수신건수
									String searchCallRcvCnt = item.getSearch_call_rcv_cnt() == null || item.getSearch_call_rcv_cnt().equals("") ? "0": item.getSearch_call_rcv_cnt();
				
									itemMap.put("search_call_rcv_cnt_type"		,searchCallRcvCntTypeTxt		);		//추출조건 수신횟수 -1:이하 , 1:이상
					
									itemMap.put("search_req_cnt"				,item.getSearch_req_cnt()		);		//발송 요청 건수 (원하는건수)
									itemMap.put("search_month"					,item.getSearch_month()			);		//동보전송 추출조건 추출기간 (월)
									itemMap.put("search_call_rcv_cnt"			,searchCallRcvCnt				);		//추출조건 수신 건수
									itemMap.put("fk_member_id"					,item.getFk_member_id()			);		//로그인ID
									itemMap.put("fk_tel"						,item.getFk_tel()				);		//회신 번호 (보내는번호)
									itemMap.put("reserve_time"					,reserveTimeTxt					);		//발송요청 시간 (년월일시분)
									itemMap.put("reserve_type"					,reserveTypeTxt					);		//발송요청 타입
									itemMap.put("fd_yy"							,fd_yy							);		//발송요청 년
									itemMap.put("fd_mm"							,fd_mm							);		//발송요청 월
									itemMap.put("fd_dd"							,fd_dd							);		//발송요청 일
									itemMap.put("pk_group_code"					,item.getPk_group_code()		);		//그룹 코드 (생성)
									itemMap.put("req_count"						,item.getReq_count()			);		//동보 요청 건수
									smsGroupInList.add(itemMap);
								}
							}
						}
					}
					smsGroupMap.put("dayList", smsGroupInList);							//날짜별 내용(배열)
					smsGroupList.add(smsGroupMap);
				}
				
		System.out.println("lastDate = "+lastDate);
		System.out.println("smsGroupList = "+smsGroupList);
		System.out.println("boardEvList = "+boardEvList);
	// DB에서 가져오는 부분 - E
	
		int chkDay = 0;  //현재 날짜 구분을 위하여
	
			//선택달 날짜
			for(int i=1; i<=today_last_date; i++){
				if(i==1){
					chkDay=dayNum;							//현재 날짜 시작 지점(전달 보여줄 날짜수)
				}
				cal_today.set(Calendar.DATE, i);
				String day = String.valueOf(cal_today.get(Calendar.DATE));
				if(day.length() < 2) day = "0" + day;
				
				int dow = cal_today.get(Calendar.DAY_OF_WEEK);
				
				String classTxt = "";
	
				if(dow == 7) classTxt = " last";
	
				//날짜별 class(classTxt), text(txts)저장
				Map<String, Object> calMap = new HashMap<String, Object>();
	
				//지금 날짜에 Class 적용
				if(year.equals(nowYear) && month.equals(nowMonth) && day.equals(nowDay)){
					classTxt+="_n";
				}
	
				calMap.put("txtArray",boardEvList);
				calMap.put("classTxt", classTxt);
				calMap.put("yearTxt", year);
				calMap.put("monthTxt", month);
				calMap.put("dayTxt", day);
				calMap.put("dayNum", dayNum);
				calMap.put("smsGroupList", smsGroupList);
	
				calList[dayNum] = calMap;
	
				dayNum++;
			}
			//선택달 날짜
	
			//다음달 날짜
			
			int today_last_week_idx = cal_today.get(Calendar.DAY_OF_WEEK);
			if(dayNum < 35){
				gap = 14 - today_last_week_idx;
			}else if(dayNum > 35){
				gap = 7 - today_last_week_idx;
			}else{
				gap = today_last_week_idx;
			}
	
			for(int i=0; i<gap; i++) {
				cal_after.set(Calendar.DATE, i+1);
				String yearTxt = String.valueOf(cal_before.get(Calendar.YEAR));
	
				String monthTxt = String.valueOf(cal_before.get(Calendar.MONTH) + 1);
				if(monthTxt.length() < 2) monthTxt = "0" + monthTxt;
	
				String day = String.valueOf(cal_after.get(Calendar.DATE));
				if(day.length() < 2) day = "0" + day;
	
	
				int dow = cal_after.get(Calendar.DAY_OF_WEEK);
	
				String classTxt = "";
	
				if(dow == 7) classTxt = " last";
	
				//날짜별 class(classTxt), text(txts)저장
				Map<String, Object> calMap = new HashMap<String, Object>();
				ArrayList<Map<String, Object>> txtArray = new ArrayList<Map<String, Object>>();
	
	/*
				if(year.equals(nowYear) && month.equals(nowMonth) && day.equals(nowDay)){
					classTxt+=" on";
				}
				if(year.equals(nowYear) && month.equals(nowMonth) && day.equals(nowDay)){
					for(int inum=0;inum < 2; inum++){
						Map<String, Object> txts = new HashMap<String, Object>();
						txts.put("num", inum);
						txts.put("txt", "test"+inum);
						txtArray.add(txts);
					}
				}
	*/
				calMap.put("txtArray",txtArray);
				calMap.put("classTxt", classTxt);
				calMap.put("yearTxt", yearTxt);
				calMap.put("monthTxt", monthTxt);
				calMap.put("dayTxt", "<font color=\"#aaaaaa\">"+day+"</font>");
				calMap.put("dayNum", dayNum);
	
				calList[dayNum] = calMap;
	
				dayNum++;
			}
			//다음달 날짜
			///// 달력
			
			mav.addObject("calList",calList);
			mav.addObject("year",year);
			mav.addObject("month",month);

			mav.addObject("nowYear",i_year);
			mav.addObject("nowMonth",i_month);
			mav.addObject("memList",memList);
			mav.addObject("chkDay",chkDay);
			mav.addObject("script",script);
	    }
		//System.out.println("calList = "+calList);

		return mav;
	}


	/**
	 * 문자관리 메인(리스트)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/smsManagerMainList.do" )
	public ModelAndView smsManagerMainList(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] SmsManagerController.smsManagerMainList()");
		System.out.println(request);

		String sCalYear			= request.getParameter("sCalYear")		!= null ? request.getParameter("sCalYear").trim() : "";				//년
		String sCalMonth		= request.getParameter("sCalMonth")		!= null ? request.getParameter("sCalMonth").trim() : "";			//월
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";			//검색용
		String searchTel		= request.getParameter("searchTel")		!= null ? request.getParameter("searchTel").trim() : "";			//회원전화번호

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

		//회원이 사용하는 전화 리스트
		Map<String, String> memberMap = new HashMap<String,String>();
		memberMap.put("fk_member_id", loginInfo.getPk_member_id());
		memberMap.put("fd_use_yn", "Y");
				
		List<BoardEvent> memListGet = smsManagerService.getMemberTelList(memberMap);
		ArrayList<Map<String, String>> memList = new ArrayList<Map<String, String>>();
		
		for(BoardEvent item:memListGet){
			Map<String, String> itemMap = new HashMap<String, String>();
			itemMap.put("fk_member_id",		item.getFk_member_id());
			itemMap.put("pk_tel",			StrUtils.formatPhoneNo(item.getPk_tel().toString(), "-"));
			itemMap.put("fd_use_yn",		item.getFd_use_yn());
			itemMap.put("fd_reg_date",		item.getFd_reg_date());
			itemMap.put("fd_del_date",		item.getFd_del_date());
			memList.add(itemMap);
		}
		//회원이 사용하는 전화 리스트
		
		Calendar cal_today = Calendar.getInstance();

		String year = sCalYear;
		String month = sCalMonth;
		String day_s="";
		String day_e="";

		//1일 가져오기 (년,월)
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
		day_s = (String.valueOf(i_minday).length() < 2)?"0"+String.valueOf(i_minday):String.valueOf(i_minday);
		day_e = (String.valueOf(i_maxday).length() < 2)?"0"+String.valueOf(i_maxday):String.valueOf(i_maxday);
		searchString = year+""+month;

//DB 에서 가져오는 부분 - start
// tbl_sms_group
			Map<String,String> smsGroupMap = new HashMap<String,String>();
			smsGroupMap.put("fk_tel", searchTel.replace("-", ""));
			smsGroupMap.put("fk_member_id", loginInfo.getPk_member_id());
			smsGroupMap.put("reserve_time_s", searchString+""+day_s+"0000");
			smsGroupMap.put("reserve_time_e", searchString+""+day_e+"5959");
			smsGroupMap.put("cancel_yn", "N");
			
			List<BoardEvent> smsGroupArray = smsManagerService.getSmsGroupList(smsGroupMap);
			
			ArrayList<Map<String, String>> smsGroupList = new ArrayList<Map<String, String>>();
			System.out.println("smsGroupArray.size() = "+smsGroupArray.size());

			if(smsGroupArray.size() > 0){
				System.out.println("smsGroupArray.size() = "+smsGroupArray.get(0).getSearch_week());

				for(BoardEvent item : smsGroupArray){
					String reserveTypeTxt = "";
					String reserveTime = "";
					String reserveTimeTxt = "";
					String reserveDayTxt = "";
					String reg_dateTxt = "";
					String msgTypeTxt="";
					String search_customerTxt="";
					String resever_statusTxt="";
					String searchCallRcvCntTypeTxt = "";

					Map<String, String> itemMap = new HashMap<String, String>();

					//발송요청시간
					reserveTime = item.getReserve_time() == null || item.getReserve_time().equals("") ? "" : item.getReserve_time();
					reserveTimeTxt = reserveTime.substring(8, 10)+":"+reserveTime.substring(10, 12);
					
					//발송일
					reserveDayTxt = reserveTime.substring(6, 8);
					
					//요청일
					reg_dateTxt = item.getReg_date().split(" ")[0].replace("-",".");

					//즉시,예약
					if(item.getReserve_type() != null){
						//reserveTypeTxt = item.getReserve_type().equals("1") ? "즉시발송" : "예약발송";
						reserveTypeTxt = item.getReserve_type().equals("1") ? "발송완료" : "발송예약";
					}

					//sms 타입
					if(item.getMsg_type() != null && item.getMsg_sub_type() != null){
						if(item.getMsg_type().equals("1") && item.getMsg_sub_type().equals("1")){
							msgTypeTxt = "SMS";
						}else if(item.getMsg_type().equals("4") && item.getMsg_sub_type().equals("1")){
							msgTypeTxt = "LMS";
						}else if(item.getMsg_type().equals("4") && (Integer.parseInt(item.getMsg_sub_type()) > 1 && Integer.parseInt(item.getMsg_sub_type()) < 5)){
							msgTypeTxt = "MMS";
						}
					}
					
					//고객타입
					if(item.getSearch_customer() != null){
						if(item.getSearch_customer().equals("1")){
							search_customerTxt = "전체";
						}else if(item.getSearch_customer().equals("2")){
							search_customerTxt = "단골";
						}else if(item.getSearch_customer().equals("3")){
							search_customerTxt = "신규";
						}else if(item.getSearch_customer().equals("-1")){
							search_customerTxt = "선택";
						}
					}
					
					//발송상태
					if(item.getMsg_send_finish() != null && item.getCancel_yn() != null){
						if(item.getMsg_send_finish().equals("Y")){
							resever_statusTxt="발송완료";
						}else if(item.getMsg_send_finish().equals("N")){
							if(item.getReserve_type().equals("2")){
								resever_statusTxt="발송예약";
							}else if(item.getReq_count() != item.getRes_count() && item.getReserve_type().equals("1")){
								resever_statusTxt="처리중";
							}
						}
					}
					//수신건수
					String searchCallRcvCnt = item.getSearch_call_rcv_cnt() == null || item.getSearch_call_rcv_cnt().equals("") ? "0": item.getSearch_call_rcv_cnt();

					itemMap.put("reserveDayTxt"					,reserveDayTxt					);		//발송일
					itemMap.put("reg_dateTxt"					,reg_dateTxt					);		//작성시간
					itemMap.put("reserve_time"					,reserveTimeTxt					);		//발송요청 시간 (년월일시분)
					itemMap.put("reserve_type"					,reserveTypeTxt					);		//발송타입 1:즉시, 2:예약
					itemMap.put("search_call_rcv_cnt_type"		,searchCallRcvCntTypeTxt		);		//추출조건 수신횟수 -1:이하 , 1:이상
					itemMap.put("msg_type"						,msgTypeTxt						);		//SMS 타입
					itemMap.put("search_customerTxt"			,search_customerTxt				);		//동보전송 추출조건 고객타입 1:단골고객, 2:신규고객  -1:미사용  구분자:^
					itemMap.put("resever_statusTxt"				,resever_statusTxt				);		//발송상태

					//itemMap.put("search_req_cnt"				,item.getSearch_req_cnt()		);		//발송 요청 건수 (원하는건수)
					itemMap.put("req_count"						,item.getReq_count()	);		//동보 요청 건수
					itemMap.put("search_month"					,item.getSearch_month()			);		//동보전송 추출조건 추출기간 (월)
					itemMap.put("search_call_rcv_cnt"			,searchCallRcvCnt				);		//추출조건 수신 건수
					itemMap.put("fk_member_id"					,item.getFk_member_id()			);		//로그인ID
					itemMap.put("fk_tel"						,item.getFk_tel()				);		//회신 번호 (보내는번호)
					itemMap.put("pk_group_code"					,item.getPk_group_code()		);		//그룹 코드 (생성)
					itemMap.put("custom_msg_id"					,item.getFk_custom_msg_id()		);		//메시지 ID
					itemMap.put("req_count"						,item.getReq_count()			);		//동보 요청 건수

					smsGroupList.add(itemMap);
				}
			}

//DB 에서 가져오는 부분 - end

		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/smsManagerMainList");
		mav.addObject("smsGroupList",smsGroupList);
		mav.addObject("year",year);
		mav.addObject("month",month);
		mav.addObject("memList",memList);

		return mav;		
	}
	
	/**
	 * 문자 관리 > 리스트 > 삭제 처리
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/smsManagerMainList.do", params="proc=cancel")
	public JSONObject smsManagerMainListProc(@RequestParam Map<String, String> map, HttpServletRequest request)
	{
		System.out.println("map : " + map);
		JSONObject jsonObj = new JSONObject();
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		SmsManager delManager = new SmsManager();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
		String nowDate = ft.format(new Date());		
		String rtn_code = "";
		
		String[] check = request.getParameterValues("check");
		String event = map.get("event") == null ? "" : map.get("event").toString();
		String member_id = loginInfo.getPk_member_id();
		
		String[] group_code = new String[check.length];
		
		delManager.setFk_member_id(member_id);
		//delManager.setCustom_msg_id_arry(check);
		delManager.setCancel_time(nowDate);
		delManager.setCancel_yn("Y");
		
		if(event.equals("modify")){
			delManager.setGroup_code_arry(check);
		}else{
			for(int i=0 ; i<check.length ; i++){
				//System.out.println("check[" + i + "] = " + check[i]);
				String[] check_arry = check[i].split("_");
				group_code[i] = check_arry[1];
				//System.out.println("group_code[" + i + "] = " + group_code[i]);
			}
			delManager.setGroup_code_arry(group_code);
		}
		
		
		
		
		try {
			rtn_code = smsManagerService.cancelationSms(delManager);
		} catch (Exception e) {
			// TODO: handle exception
			rtn_code = "301";
			e.printStackTrace();
		}
		
		if(rtn_code.equals("200")){
			try {
				rtn_code = smsManagerService.gotoLog(member_id);
			} catch (Exception e) {
				// TODO: handle exception
				rtn_code = "302";
				e.printStackTrace();
			}
		}
		
		jsonObj.put("rtn_code", rtn_code);
		
		return jsonObj;
	}
//JCY
}
