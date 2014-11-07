package com.kth.apilog;

import java.io.*;
//import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

//import org.json.simple.*;

import com.kth.common.db.*;
//import com.kth.common.secure.*;
//import com.kth.common.util.*;
import com.kth.model.*;

public class Logging extends HttpServlet {
    
	private static final long serialVersionUID 	= 1L;
	final boolean 	bDebugMode					= false;	

	static final int DEBUG	= 10000;
	static final int INFO	= 20000;
	static final int WARN	= 30000;
	static final int ERROR	= 40000;
	static final int FATAL	= 50000;	
	
	private DBHandler daoTb 			= DBHandler.getInstance();
//	private TimeStampUtil timeStampUtil	= new TimeStampUtil();
	
	protected synchronized void process(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, NumberFormatException, UnsupportedEncodingException {

		//출력 파라메타
		String rtn_code			= "200";		// 응답코드
		String rtn_msg			= "success";	// 에러메시지
		
		try{		
			//내부 사용 변수
			System.out.println( request.getRequestURL() );
			response.setContentType("text/html;charset=UTF-8"); 
			java.io.PrintWriter out		= null;
			out 						= response.getWriter();
			
//			JSONObject obj 				= new JSONObject();
//			JSONArray array 			= new JSONArray();
			boolean skipProcess			= true;
//			String now_time 			= timeStampUtil.longToStr(System.currentTimeMillis(), "yyyyMMddHHmmss");
			String allow_ip				= "111.222.333.444"; //허용 IP
			String error_log			= ""; //DB 입력에러시 로그용
			
			//입력 파라메타
			String sCaller				= ""; //발신번호
			String sCallee				= ""; //수신번호
			String sResult				= ""; //결과코드
			String sClSeqno				= ""; //통화내역 일련번호
			String sMiSeqno				= ""; //발신자 일련번호
			String sDate				= ""; //수신시간 전체
			String sBand				= ""; //시간대역
			String yyyy					= ""; //수신시간 yyyy
			String mm					= ""; //수신시간 mm
			String dd					= ""; //수신시간 dd
			String hh					= ""; //수신시간 hh
			String ii					= ""; //수신시간 ii
			String ss					= ""; //수신시간 ss
			String client_ip			= request.getRemoteAddr(); //클라이언트 IP

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
				ApiLog inParam 	= new ApiLog();
				
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
				
				inParam.setPk_seqno(sClSeqno);
//				inParam.setFd_member_id(fd_member_id);
				inParam.setFd_tel(sCallee);
				inParam.setFd_rcv_tel(sCaller);
				inParam.setFd_reg_yy(yyyy);
				inParam.setFd_reg_mm(mm);
				inParam.setFd_reg_dd(dd);
				inParam.setFd_reg_hh(hh);
				inParam.setFd_reg_week(yyyy+mm+dd);
				inParam.setFd_reg_mi(ii);
				inParam.setFd_reg_ss(ss);
				inParam.setFd_openapi_rc_code(sResult);
//				inParam.setFd_openapi_skind(fd_openapi_skind);
				inParam.setFd_search_time(sBand);
				inParam.setFd_reg_date(sDate);
				
				try{
					daoTb.insert("ApiLog.insert", inParam);

					rtn_code 	= "200";
					rtn_msg		= "DB insert_ok";

					System.out.println( rtn_msg );	
					out.print(  rtn_code );
				}catch(Exception e) {	

					rtn_code 	= "302";
					rtn_msg		= "DB insert_fail";

					error_log	= sCaller +"^"+ sCallee +"^"+ sResult +"^"+ sClSeqno +"^"+ sMiSeqno +"^"+ sDate +"^"+ sBand;
					System.out.println("【"+ error_log +"】" + rtn_msg);				
					System.out.println(e);				
					out.print(  rtn_code );
				}
			}
			
		}catch(Exception e) {			
			System.out.println("openAPI Log Exception - " + e);			
		}
	}

	protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, NumberFormatException{
		try {
			process(request, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, NumberFormatException{
		try {
			process(request, response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}		

}
