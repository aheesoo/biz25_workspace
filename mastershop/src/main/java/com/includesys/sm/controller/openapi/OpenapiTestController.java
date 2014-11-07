package com.includesys.sm.controller.openapi;

import java.math.BigInteger;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

import com.includesys.sm.dto.member.Member;
import com.includesys.sm.service.manager.LoginService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.util.ByteUtils;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.util.KeyMaker;
import com.kt.openplatform.sdk.KTOpenApiHandler;

@RequestMapping("/openapi")
@Controller
public class OpenapiTestController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MemberService memberService; 

	//@Autowired
	//private HttpSession session;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping(value="/callback1.do")
	public ModelAndView callback1(){
		System.out.println("\n[CALL Controller ] OpenapiTestController.callback1()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home/main");
		return mav;
		
	}

	
	@RequestMapping(value="/callback2.do")
	public ModelAndView callback2(){
		System.out.println("\n[CALL Controller ] OpenapiTestController.callback2()");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home/main");
		return mav;
		
	}

	
	@RequestMapping("/clickToCall.do")
	public void clickToCall(HttpServletResponse response)
	{
		System.out.println("\n[CALL Controller ] OpenapiTestController.clickToCall");
		
		// 발급 받은 개발자 Key와 Api_Secret를 입력
		String auth_key = "s9Ozc4YEdEeRpxf0YEzPFmKh1noN8HaSJEnFg7YYVjWUfqYr4a";
		String auth_secret = "c9jsZgFhvmnbcYFstsR6BRlhSV4mxOBWEuxXXcycbWs5YF3AVG";
		// api id 설정
		String apiId = "1.0.CALL.CLICK2CALL.MAKE";
		
		// https 이용 여부 설정 false로 기본 설정.  
		boolean bSSL = false;
		
		// Make Parameters
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("caller", "0226962520");
		params.put("callee", "01075500007");
		params.put("cb_method", "url"); 		// callback으로 url만 현재 지원함
		params.put("cb_trgt_info", "http://211.42.137.203/openapi/callback2.do"); 	// callback받을 url입력
		params.put("cb_format", ""); // 비운 값으로 입력함
				
		// make xauth params
		HashMap<String, String> xauth_params = new HashMap<String,String>();
		xauth_params.put("username", "includesys");
		xauth_params.put("password", "dlszmffnem12#"); 
		
		System.out.println("\n[CALL before handler ]");
		KTOpenApiHandler handler = KTOpenApiHandler.createHandler(auth_key, auth_secret);
		System.out.println("\n[CALL after handler ]");
		
		System.out.println("\n[CALL before call ]");
		HashMap<?,?> r = handler.call(apiId, params, xauth_params, bSSL);
		System.out.println("\n[CALL after call ]");
		
		System.out.println("RESULT==>" + r);
		
	}

	
	@RequestMapping("/cidNoti.do")
	public void cidNoti(HttpServletResponse response)
	{
		System.out.println("\n[CALL Controller ] OpenapiTestController.cidNoti");
			
		// 발급 받은 개발자 Key와 Api_Secret를 입력
		String auth_key = "s9Ozc4YEdEeRpxf0YEzPFmKh1noN8HaSJEnFg7YYVjWUfqYr4a";
		String auth_secret = "c9jsZgFhvmnbcYFstsR6BRlhSV4mxOBWEuxXXcycbWs5YF3AVG";
		// api id 설정
		String apiId = "1.0.CALL.CID.SETNOTI";
		// https 이용 여부 설정 false로 기본 설정.

		boolean bSSL = false;
		
		// Make Parameters
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("subscriber", "0226962520");
		params.put("cb_method", "url"); // callback으로 url만 현재 지원함
		params.put("cb_trgt_info", "http://211.42.137.203/openapi/callback1.do"); // callback받을 url입력
		params.put("cb_format", ""); // 비운 값으로 입력함
		
		// make xauth params
		HashMap<String, String> xauth_params = new HashMap<String,String>();
		xauth_params.put("username", "includesys");
		xauth_params.put("password", "dlszmffnem12#"); 
		
		KTOpenApiHandler handler = KTOpenApiHandler.createHandler(auth_key, auth_secret);
		
		HashMap<?,?> r = handler.call(apiId, params, xauth_params, bSSL);
		
		System.out.println("RESULT==>" + r);
	}

}
