package com.includesys.sm.controller.callLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.includesys.sm.dto.common.CallLog;
import com.includesys.sm.service.common.CommonService;

@Controller
@RequestMapping("/callLog")
public class CallLogController {
	private static Logger logger = LoggerFactory.getLogger(CallLogController.class);
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
    private CommonService commonService;

	/**
	 * 콜로그 단건 입력
	 * @param request
	 * @return
	 */
	@RequestMapping(value="take_log.do" )
	public void takeLog(HttpServletRequest request, HttpServletResponse response)	{
		System.out.println("[CallLog Controller] CallLogController.take_log()");
		
		try{
			//내부 사용 변수
			System.out.println( request.getRequestURL() );
			response.setContentType("text/html;charset=UTF-8"); 
			java.io.PrintWriter out		= null;
			out 						= response.getWriter();
			
	//		JSONObject obj 			= new JSONObject();
	//		JSONArray array 		= new JSONArray();
			boolean skipProcess		= true;
	//		String now_time 		= timeStampUtil.longToStr(System.currentTimeMillis(), "yyyyMMddHHmmss");
			String allow_ip			= "111.222.333.444"; //허용 IP
			String error_log		= ""; //DB 입력에러시 로그용
			
			//출력 파라메타
			String rtn_code			= "200";		// 응답코드
			String rtn_msg			= "success";	// 에러메시지
	
			//입력 파라메타
			String sCaller			= ""; //발신번호
			String sCallee			= ""; //수신번호
			String sResult			= ""; //결과코드
			String sClSeqno			= ""; //통화내역 일련번호
			String sMiSeqno			= ""; //발신자 일련번호
			String sDate			= ""; //수신시간 전체
			String sBand			= ""; //시간대역
			String yyyy				= ""; //수신시간 yyyy
			String mm				= ""; //수신시간 mm
			String dd				= ""; //수신시간 dd
			String hh				= ""; //수신시간 hh
			String ii				= ""; //수신시간 ii
			String ss				= ""; //수신시간 ss
			String client_ip		= request.getRemoteAddr(); //클라이언트 IP
			
			//리턴할 json
			JSONObject jsonObj = new JSONObject();	//object
		
			//입력 파라메타 유효성 체크
			try{
				if(client_ip.equals(allow_ip)){
					skipProcess = true;
					rtn_code 	= "201";
					rtn_msg		= client_ip + "is allow";
				}
			}catch(Exception e) {
				skipProcess 	= true;
				rtn_code 		= "301";
				rtn_msg			= client_ip + "is not allow";
			}
			
			if(skipProcess){//입력값 정상

				sCaller			= request.getParameter("sCaller"  	);
				sCallee			= request.getParameter("sCallee"  	);
				sResult			= request.getParameter("sResult"  	);
				sClSeqno		= request.getParameter("sClSeqno" 	);
				sMiSeqno		= request.getParameter("sMiSeqno" 	);
				sBand			= request.getParameter("sBand"  	);
				yyyy			= request.getParameter("yyyy"  		);
				mm				= request.getParameter("mm"  		);
				dd				= request.getParameter("dd"  		);
				hh				= request.getParameter("hh"  		);
				ii				= request.getParameter("ii"  		);
				ss				= request.getParameter("ss"  		);
				sDate			= request.getParameter("sDate"  	);
				
				CallLog callLog = new CallLog();
				
				callLog.setPk_seqno(sClSeqno);
				callLog.setFd_tel(sCallee);
				callLog.setFd_rcv_tel(sCaller);
				callLog.setFd_reg_yy(yyyy);
				callLog.setFd_reg_mm(mm);
				callLog.setFd_reg_dd(dd);
				callLog.setFd_reg_hh(hh);
				callLog.setFd_reg_week(yyyy+mm+dd);
				callLog.setFd_reg_mi(ii);
				callLog.setFd_reg_ss(ss);
				callLog.setFd_openapi_rc_code(sResult);
				callLog.setFd_search_time(sBand);
				callLog.setFd_reg_date(sDate);
				
				try{
					commonService.callLogReg(callLog);
					System.out.println("call 전화번호 등록 완료");

					rtn_code 	= "200";
					rtn_msg		= "DB insert_ok";
				}catch(Exception e) {	

					rtn_code 	= "302";
					rtn_msg		= "DB insert_fail";

					error_log	= sCaller +"^"+ sCallee +"^"+ sResult +"^"+ sClSeqno +"^"+ sMiSeqno +"^"+ sDate +"^"+ sBand;
					System.out.println("【"+ error_log +"】" + rtn_msg);				
					System.out.println(e);				
				}
			}
			
			jsonObj.put("rtn_code", rtn_code);
			out.print(  rtn_code );
		
		}catch (Exception e){
			System.out.println("CallLog Exception - " + e);			
		}
	}

}
