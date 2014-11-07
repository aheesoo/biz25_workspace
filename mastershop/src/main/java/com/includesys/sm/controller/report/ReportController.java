package com.includesys.sm.controller.report;

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

import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.dto.smsManager.SmsManager;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.service.report.ReportService;
import com.includesys.sm.util.StrUtils;
import com.includesys.sm.webUtil.PageNavi;


@Controller
@RequestMapping("/report")
public class ReportController {

	private static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 고객관리 메인
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/reportMain.do" )
	public ModelAndView reportMain(@RequestParam Map<String, Object> map, HttpServletRequest request)	{
		System.out.println("\n[CALL Controller] ReportController.reportMain()");
		int pageSize = 10;
		int totalCount = 0;
		String tel_temp;
		int page					= Integer.parseInt(map.get("page") == null || map.get("page").toString().trim() == "" ? "1" : map.get("page").toString());
		String fk_tel 	= map.get("fk_tel") == null ? "" : map.get("fk_tel").toString().trim();
		String searchDate 	= map.get("searchDate") == null ? "" : map.get("searchDate").toString().trim();
		
		SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM");
		String nowDate = ft1.format(new Date());
		Calendar calendar = new java.util.GregorianCalendar();
		Date temp_now;
		String search_date;
		List<String> date_list = new ArrayList<String>();
		List<Map<String, Object>> tel_list = new ArrayList<Map<String, Object>>();
		LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
		List<SmsManager> report_list = new ArrayList<SmsManager>();
		Map<String, Object> report_map = new HashMap<String, Object>();
		
		//첫 화면 진입시 오늘 날짜로 설정.
		if(searchDate.equals("")){
			SimpleDateFormat ft = new SimpleDateFormat ();//"yyyyMMddHHmmss");
			ft.applyPattern("yyyyMM");
			searchDate =  ft.format(new Date( ));
		}
		
		//넘어오는 fk_tel 하이픈 제거
		tel_temp = fk_tel.replaceAll("-", "");
		
		//오늘부터 6개월전까지만 검색 날짜 설정.
		try 
		{
			temp_now = ft1.parse(nowDate);
			calendar.setTime(temp_now );
			
			calendar.add(Calendar.MONTH, - 0);	
			search_date = ft1.format(calendar.getTime());
			date_list.add(search_date);
			
			for(int i=0 ; i<5 ; i++){
				calendar.add(Calendar.MONTH, - 1);	
				search_date = ft1.format(calendar.getTime());
				date_list.add(search_date);
			}
			
		} 
		catch (ParseException e1){
			e1.printStackTrace();
		}

/*		
		//오늘날짜
		SimpleDateFormat ftnow = new SimpleDateFormat ("yyyy-MM-dd-HH-mm-ss");
		String[] nowDataArray = ftnow.format(new Date( )).split("-");
		String re_now = nowDataArray[0]+"-"+nowDataArray[1]+"-"+nowDataArray[2]+" "+nowDataArray[3]+":"+nowDataArray[4]+":"+nowDataArray[5];

		System.out.println("re_now="+re_now);

		report_map.put("startDate", searchDate + "01000000");
		report_map.put("finishDate", searchDate + nowDataArray[2]+"000000");
*/
		report_map.put("fk_member_id", loginInfo.getPk_member_id());
		report_map.put("startDate", searchDate + "010000");
		report_map.put("finishDate", searchDate + "312359");

		report_map.put("fk_member_id", loginInfo.getPk_member_id());
		report_map.put("fk_tel", tel_temp);
		report_map.put("pageStart", (page-1)*pageSize);
		report_map.put("pageSize", pageSize);
		
		
		
		//청약가입된 회선 목록 갖고옴.
		try {
			tel_list = reportService.getSendTelList(loginInfo.getPk_member_id());
			
			//회선 목록 연락처 하이픈 추가
			for(Map<String, Object> hyphen : tel_list){
				hyphen.put("fk_tel", StrUtils.formatPhoneNo((String)hyphen.get("fk_tel"), "-"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//리포트 리스트 카운트 갖고옴. (tbl_sms_group 사용자의 그룹수)
		try {
			totalCount = reportService.getMonthReportListCount(report_map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//리포트 리스트 갖고옴.
		try {
			report_list = reportService.getMonthReportList(report_map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		List<Map<String, Object>> graphDataList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> Reportgraph = new ArrayList<Map<String, Object>>();
		
		//리포트 그래프 데이터 갖고옴.
		int maxCnt = 0;

		try {
			Reportgraph = reportService.getMonthReportGraph(report_map);
			
			String year = searchDate.substring(0, 4);
			String month = searchDate.substring(4, 6);
			System.out.println("year : " + year + " month : " + month);

			//통화수신통계 - s
			Calendar cal_today = Calendar.getInstance();

			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			String[] dateArray = ft.format(new Date()).split("-");
			String yymmTemp = dateArray[0] + dateArray[1];
			int maxday = 0;
			
			//현재 날짜와 월이 같을경우 오늘날짜까지만 그래프를 표시.
			if(searchDate.equals(yymmTemp)){
				maxday = Integer.parseInt(dateArray[2]);
			}else{
				if(year != null && month != null && !"".equals(year) && !"".equals(month)) {
					cal_today.set(Integer.parseInt(year), Integer.parseInt(month)-1, 1);
				}else{
					cal_today.set(Calendar.DATE, 1);
				}
				maxday = cal_today.getActualMaximum(Calendar.DAY_OF_MONTH);
			}
			

			
			//System.out.println("maxday : " + maxday);
			//데이터 날짜만 넣고 나머지 초기화
			int cCount = 0;
			for(int i=1 ; i < maxday ; i++){
				Map<String, Object> listMap = new HashMap<String, Object>();
				listMap.put("day_number", i);
				//System.out.println("cCount : " + cCount);
				//System.out.println("Reportgraph.size() : " + Reportgraph.size());
				if(Reportgraph.size() > cCount) {
					//System.out.println("Reportgraph.get(cCount).get() : " + Reportgraph.get(cCount).get("day_number") + " i : " + i);
					//if(Reportgraph.get(cCount).get("day_number").equals(Integer.toString(i))){
					if(Integer.parseInt((String) Reportgraph.get(cCount).get("day_number")) == i){
						//System.out.println("check");
						if( (Reportgraph.get(cCount).get("req_count") == "") || (Reportgraph.get(cCount).get("req_count") == null) ){
							listMap.put("req_count", 0);
						}else{
							if(maxCnt < Integer.parseInt(Reportgraph.get(cCount).get("req_count").toString())){
								maxCnt = Integer.parseInt(Reportgraph.get(cCount).get("req_count").toString());
								//System.out.println("1maxCnt : " + maxCnt);
							}
							listMap.put("req_count", Reportgraph.get(cCount).get("req_count"));
						}
						
						if( (Reportgraph.get(cCount).get("call_count") == "") || (Reportgraph.get(cCount).get("call_count") == null) ){
							listMap.put("call_count", 0);
						}else{
							if(maxCnt < Integer.parseInt(Reportgraph.get(cCount).get("call_count").toString())){
								maxCnt = Integer.parseInt(Reportgraph.get(cCount).get("call_count").toString());
								//System.out.println("1maxCnt : " + maxCnt);
							}
							listMap.put("call_count", Reportgraph.get(cCount).get("call_count"));
						}
						cCount++;
					}else{
						listMap.put("req_count", 0);
						listMap.put("call_count", 0);
					}
				}else{
					listMap.put("req_count", 0);
					listMap.put("call_count", 0);
				}
				graphDataList.add(listMap);
				//System.out.println("graphDataList : " + graphDataList);
			}
			
			//그래프 데이터의 날짜 위치에 넣음.
			/*int addr = 0;
			for(Map<String, Object> temp : Reportgraph){
				addr = Integer.parseInt((String) temp.get("day_number")) - 1;
				graphDataList.add(addr, temp);
			}
			
			System.out.println("graphDataList 2" + graphDataList);*/
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		PageNavi pageNavi = new PageNavi();
		pageNavi.setAction("/report/reportMain.do");
		pageNavi.setNowPage(page);
		pageNavi.setTotalCount(totalCount);
		pageNavi.setMethod("post");
		pageNavi.setPageSize(pageSize);
		pageNavi.setParameters("fk_tel", fk_tel);
		pageNavi.setParameters("searchDate", searchDate);
		pageNavi.make();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("searchDate", searchDate);
		mav.addObject("fk_tel", fk_tel);
		mav.addObject("date_list", date_list);
		mav.addObject("report_list", report_list);
		mav.addObject("tel_list", tel_list);
		mav.addObject("graphDataList", graphDataList);
		mav.addObject("pageNavi", pageNavi);
		mav.addObject("maxCnt",maxCnt);
		mav.setViewName("report/reportMain");
		return mav;		
	}
	
	/**
	 * 
	 * @param map, request
	 * @return
	 */
	@RequestMapping("/ifrmReportDetail.do")
	public ModelAndView ifrmSmsTelList(@RequestParam Map<String, String> map, HttpServletRequest request){
		System.out.println("map : " + map);
		
		String group_code 					= map.get("group_code") == null ? "" : map.get("group_code").toString().trim();
		String call_count 						= map.get("call_count") == null ? "" : map.get("call_count").toString().trim();
		
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
			reportDetailList = reportService.getReportDetailList(report_map);
			for(SmsManager hyphen : reportDetailList){
				hyphen.setPk_customer_tel(StrUtils.formatPhoneNo(hyphen.getPk_customer_tel(), "-"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("smsManager", smsManager);
		mav.addObject("reportDetailList", reportDetailList);
		mav.addObject("call_count", call_count);
		mav.addObject("view_name_flag", member.getFd_view_name());
		mav.setViewName("/report/ifrmReportDetail");

		return mav;		
	}
}
