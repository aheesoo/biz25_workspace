package com.includesys.sm.controller.smsManager;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.includesys.sm.dto.PageHelper;
import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.myinfo.Coin;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.dto.smsManager.BoardEvent;
import com.includesys.sm.service.smsManager.SmsManagerService;
import com.includesys.sm.service.smsManager.SmsManagerServiceImpl;
import com.includesys.sm.util.StrUtils;


@Controller
@RequestMapping("/smsManager")
public class SmsManagerControllerJCY {

	private static Logger logger = LoggerFactory.getLogger(SmsManagerControllerJCY.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SmsManagerService smsManagerService;
	
	/**
	 * 문자관리 메인(달력)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/smsManagerMain.do" )
	public ModelAndView smsManagerMain(HttpServletRequest request)	{
		
		System.out.println("\n[CALL Controller] SmsManagerController.smsManagerMain()");


		String sCalYear			= request.getParameter("sCalYear")		!= null ? request.getParameter("sCalYear").trim() : "";
		String sCalMonth		= request.getParameter("sCalMonth")		!= null ? request.getParameter("sCalMonth").trim() : "";
		String searchColumn		= request.getParameter("searchColumn")	!= null ? request.getParameter("searchColumn").trim() : "view_date";
		String searchString		= request.getParameter("searchString")	!= null ? request.getParameter("searchString").trim() : "";
		String searchTel		= request.getParameter("searchTel")		!= null ? request.getParameter("searchTel").trim() : "";

		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");

		//아이피 가져오기
		String fd_user_ip	= request.getRemoteAddr();

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

		//선택달 날짜
		for(int i=1; i<=today_last_date; i++){
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

//DB 에서 가져오는 부분 - start
// tbl_board_event
			searchString = year+""+month+""+day;

			if(!searchString.equals("")){
				searchColumn = "view_date";
			}

			//PageHelper pageHelper 	= new PageHelper(0, 0, 0, searchColumn, searchString);
			Map<String, Object> cal_map = new HashMap<String, Object>();
			cal_map.put("searchColumn", searchColumn);
			cal_map.put("year", year);
			cal_map.put("month", month);
			cal_map.put("day", day);
			List<BoardEvent> txtArray = smsManagerService.getListCal(cal_map);
			
// tbl_sms_group

			Map<String,String> smsGroupMap = new HashMap<String,String>();
			smsGroupMap.put("fk_tel", searchTel.replace("-", ""));
			smsGroupMap.put("fk_member_id", loginInfo.getPk_member_id());
			smsGroupMap.put("reserve_time_s", searchString+"0000");
			smsGroupMap.put("reserve_time_e", searchString+"5959");
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
					String searchCallRcvCntTypeTxt = "";

					Map<String, String> itemMap = new HashMap<String, String>();

					//발송요청시간
					reserveTime = item.getReserve_time() == null || item.getReserve_time().equals("") ? "" : item.getReserve_time();
					reserveTimeTxt = reserveTime.substring(8, 10)+":"+reserveTime.substring(10, 12);

					//즉시,예약
					if(item.getReserve_type() != null){
						reserveTypeTxt = item.getReserve_type().equals("1") ? "즉시발송" : "예약발송";
					}

					//수신건수
					String searchCallRcvCnt = item.getSearch_call_rcv_cnt() == null || item.getSearch_call_rcv_cnt().equals("") ? "0": item.getSearch_call_rcv_cnt();
/*
		String search_week;						//동보전송 추출조건 1~7 (월~일) 구분자:^
		String search_time;						//동보전송 추출조건 A~G (새벽~야간) 구분자:^
		String search_req_cnt;					//발송 요청 건수 (원하는건수)
		String search_month;					//동보전송 추출조건 추출기간 (월)
		String search_call_rcv_cnt_type;		//추출조건 수신횟수 -1:이하 , 1:이상
		String search_call_rcv_cnt;				//추출조건 수신 건수
		String fk_tel;							//회신 번호 (보내는번호)
		String reserve_time;					//발송요청 시간 (년월일시분)
*/

					itemMap.put("search_call_rcv_cnt_type"		,searchCallRcvCntTypeTxt		);		//추출조건 수신횟수 -1:이하 , 1:이상
	
					itemMap.put("search_req_cnt"				,item.getSearch_req_cnt()		);		//발송 요청 건수 (원하는건수)
					itemMap.put("search_month"					,item.getSearch_month()			);		//동보전송 추출조건 추출기간 (월)
					itemMap.put("search_call_rcv_cnt"			,searchCallRcvCnt				);		//추출조건 수신 건수
					itemMap.put("fk_member_id"					,item.getFk_member_id()			);		//로그인ID
					itemMap.put("fk_tel"						,item.getFk_tel()				);		//회신 번호 (보내는번호)
					itemMap.put("reserve_time"					,reserveTimeTxt					);		//발송요청 시간 (년월일시분)
					itemMap.put("reserve_type"					,reserveTypeTxt					);		//발송요청 시간 (년월일시분)
					itemMap.put("pk_group_code"					,item.getPk_group_code()		);		//그룹 코드 (생성)
					itemMap.put("req_count"						,item.getReq_count()			);		//동보 요청 건수
					smsGroupList.add(itemMap);
				}
			}

//DB 에서 가져오는 부분 - end

			calMap.put("txtArray",txtArray);
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



		ModelAndView mav = new ModelAndView();
		mav.setViewName("smsManager/smsManagerMain");
		mav.addObject("calList",calList);
		mav.addObject("year",year);
		mav.addObject("month",month);

		mav.addObject("nowYear",i_year);
		mav.addObject("nowMonth",i_month);
		mav.addObject("memList",memList);
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
						reserveTypeTxt = item.getReserve_type().equals("1") ? "즉시발송" : "예약발송";
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

					itemMap.put("search_req_cnt"				,item.getSearch_req_cnt()		);		//발송 요청 건수 (원하는건수)
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

}
