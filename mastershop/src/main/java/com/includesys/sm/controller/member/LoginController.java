package com.includesys.sm.controller.member;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.CookieGenerator;

import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.dto.member.Member;
import com.includesys.sm.service.manager.LoginService;
import com.includesys.sm.service.member.MemberService;
import com.includesys.sm.util.ByteUtils;
import com.includesys.sm.util.HashEncrypt;
import com.includesys.sm.util.KeyMaker;
import com.includesys.sm.util.OpenapiAesSecure;

@RequestMapping("/manager")
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MemberService memberService; 

	//@Autowired
	//private HttpSession session;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request){

		System.out.println("\n[CALL Controller ] LoginController.loginForm()");
		
		String id = "";
		String pwd = "";
		String check = "";

		
		
		Device device = DeviceUtils.getCurrentDevice(request);
		
		 if (device != null){
			 if (device.isMobile()) {				 
		        //    deviceType = "mobile";
				 //System.out.println("deviceType : mobile" );
				 //return new ModelAndView("http://m.mastershop.kr");
				 return (ModelAndView)new ModelAndView("redirect:http://m.mastershop.kr");
				 //return d
				 
		     } else if (device.isTablet()) {
		    	 //System.out.println("deviceType : tablet" );
		    	 return (ModelAndView)new ModelAndView("redirect:http://m.mastershop.kr");
		         // deviceType = "tablet";
		      }
	     }
		
		 ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/login");
		
		//autoLogin 여부
		HashEncrypt autoLogin_sha256 = new HashEncrypt("TRUE");
		String authLoginCookie = autoLogin_sha256.getEncryptData();

		System.out.println("[CALL] authValidationHandler result.false");
		String authLoginSavedCookie = "";

		if(request.getCookies() != null){
			for(Cookie cookie : request.getCookies()){
				if(cookie.getName().equals("check_alogin")){
					authLoginSavedCookie = cookie.getValue();
				}
			}
		}
System.out.println("authLoginSavedCookie="+authLoginSavedCookie);	//DFE88090C5ED7AC2F32571F0FC822FDA4D8CD281FC7138C7CD6DB656F6E2D081
System.out.println("authLoginCookie=     "+authLoginCookie);		//DFE88090C5ED7AC2F32571F0FC822FDA4D8CD281FC7138C7CD6DB656F6E2D081

		//autologin 체크된 경우
		if(authLoginSavedCookie.equals(authLoginCookie)){
			
			System.out.println("Auth #1");
			
			String encryptAuth = "";
			if(request.getCookies() != null){
				for(Cookie cookie : request.getCookies()){
					if(cookie.getName().equals("ck_alogin")){
						encryptAuth = cookie.getValue();
					}
				}
			}
			
			//System.out.println("encryptAuth=     "+encryptAuth);		//DFE88090C5ED7AC2F32571F0FC822FDA4D8CD281FC7138C7CD6DB656F6E2D081
			
			//String encryptKey = request.getSession().getServletContext().getInitParameter("encryptKey");
			String encryptKey = servletContext.getInitParameter("encryptKey");
			
			if(encryptAuth.length()>0){

				try {
					
					Key key = KeyMaker.generateKey("AES", ByteUtils.toBytes(encryptKey, 16));
					Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
					
					byte[] encrypt = new BigInteger(encryptAuth, 16).toByteArray();
					cipher.init(Cipher.DECRYPT_MODE, key);
					byte[] decrypt = cipher.doFinal(encrypt);	
					String auth = new String(decrypt, "UTF-8");
					String[] authInfo = auth.split("\\[\\{\\|\\}\\]");
					if(authInfo[2].equals(request.getRemoteAddr()))
					{
						id 		= authInfo[0];
						pwd		= authInfo[1];	
						check	= "TRUE";
						
						mav.addObject("id", id);
						mav.addObject("pwd", pwd);
						mav.addObject("check", check);
					}
					
				} catch (InvalidKeyException | NoSuchAlgorithmException
						| InvalidKeySpecException | IllegalArgumentException
						| NoSuchPaddingException | IllegalBlockSizeException
						| BadPaddingException | UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			}
		}else{
			System.out.println("#2 인증값이 다름");
			//System.out.println("[CALL] authValidationHandler CookieSession END");
		}

		return mav;
	}
	
	
	/**
	 * 쿠키처리 없이 로그인 페이지 접속
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login2.do", method=RequestMethod.GET)
	public ModelAndView loginForm2(HttpServletRequest request){

		System.out.println("\n[CALL Controller ] LoginController.loginForm2()");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/login");

		return mav;
	}
	

	
	@RequestMapping(value="/login.do", params="event=loginProc")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttrebutes) throws Exception
	{
		System.out.println("\n[CALL Controller ] LoginController.login event=loginProc");

		String script = "";
		String pk_member_id			= request.getParameter("id");
		String fd_member_pwd		= request.getParameter("pwd");
		String fd_member_pwd_org	= request.getParameter("pwd");
		String autoLogin			= request.getParameter("autoLogin")!=null ? request.getParameter("autoLogin") : "FALSE";
		
		System.out.println(" .login pk_member_id="+pk_member_id+"/ fd_member_pwd="+fd_member_pwd);
		
		//사용자 정보 취득
		//Member member = memberService.get(pk_member_id);
		Member member = memberService.getJoinInfo(pk_member_id);
		
		
		//패스워드(입력된값)
		HashEncrypt sha256 = new HashEncrypt(fd_member_pwd);
		fd_member_pwd = sha256.getEncryptData();
		System.out.println(" .login sha256.getFd_member_pwd="+fd_member_pwd); //3AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4

		ModelAndView mav = new ModelAndView();
		
		if(member != null && member.getFd_member_pwd().equals(fd_member_pwd))
		{
			System.out.println(" .login getFd_user_name="+member.getFd_user_name());	//조상현
			System.out.println(" .login fd_member_pwd="+member.getFd_member_pwd());	//3AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4
			
			if(member.getFd_openapi_member_id() == null){
				member.setFd_openapi_member_id("");
			}
			if(member.getFd_openapi_member_pwd() == null){
				member.setFd_openapi_member_pwd("");
			}
			
			//====== autoLogin 여부 쿠키저장 (체크선택여부만 저장) START ===== 
			HashEncrypt autoLogin_sha256 = new HashEncrypt(autoLogin);
			String authLoginCookie = autoLogin_sha256.getEncryptData();

			if(autoLogin.equals("TRUE")){
			    CookieGenerator authGer = new CookieGenerator();
			    authGer.setCookiePath("/");
			    authGer.setCookieName("check_alogin");
			    authGer.setCookieMaxAge(2592000); //60초*60분*24시간*30일=한달
			    authGer.addCookie(response, authLoginCookie);  //Added cookie with name [check_alogin] and value [DFE88090C5ED7AC2F32571F0FC822FDA4D8CD281FC7138C7CD6DB656F6E2D081]

			    System.out.println("체크선택 TRUE. authLoginCookie="+authLoginCookie); //DFE88090C5ED7AC2F32571F0FC822FDA4D8CD281FC7138C7CD6DB656F6E2D081
			    
			}else{

				Cookie auth = new Cookie("check_alogin", null);
				auth.setPath("/");
				//auth.setHttpOnly(true); //After Servlet3.0
				auth.setMaxAge(0);
				response.addCookie(auth);	
				
				System.out.println("체크선택  FALSE. ");
			}
			//====== autoLogin 여부 쿠키저장 (체크선택여부만 저장) END ===== 
			
			
			
			String user_access = member.getFd_user_access();	//회원징계상태 비활성:0 활성:1
			String member_state = member.getFd_member_state();	//회원상태 1301:정상
			
			if(!member_state.equals("1301")){
				
				script = "alert('회원님은 접속 권한이 없습니다.');";	//징계상태
				
				mav.setViewName("manager/login");
				mav.addObject("script", script);	
				
			}else if(user_access.equals("0"))	//정상
			{
				SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddHHmmss");
				String nowDate = ft.format(new Date());	
				
				
			    //========================== 약관동의 START ==================================
				int agreeCount = 0;
				try {
					agreeCount = memberService.getAgreeCount(pk_member_id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    //-------------------------- 약관동의 END ------------------------------------
				
				//openapi pwd 복호화 (START)
				String opanAsePwd = "";
				try {
					
					OpenapiAesSecure openAse = new OpenapiAesSecure();
					
					System.out.println("member.getFd_openapi_member_id()= "+member.getFd_openapi_member_id());
					System.out.println("member.getFd_openapi_member_pwd()= "+member.getFd_openapi_member_pwd());
					opanAsePwd = openAse.aesDecode(member.getFd_openapi_member_pwd());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					if(agreeCount>0){
						script = "alert('오픈API 로그인 실패하였습니다.');";
					}
					redirectAttrebutes.addFlashAttribute("script", script);
					
					
					e.printStackTrace();
					opanAsePwd = "";
				}
				//openapi pwd 복호화 (END)
				System.out.println("opanAsePwd= "+opanAsePwd);
				
				
				//int authExpire = Integer.parseInt(request.getSession().getServletContext().getInitParameter("authExpire")) * 60;
				//String encryptKey = request.getSession().getServletContext().getInitParameter("encryptKey");

				int authExpire = Integer.parseInt(servletContext.getInitParameter("authExpire")) * 60;
				String encryptKey = servletContext.getInitParameter("encryptKey");
				
				Key key = KeyMaker.generateKey("AES", ByteUtils.toBytes(encryptKey, 16));
				
			    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			    cipher.init(Cipher.ENCRYPT_MODE, key);

			    
			    //쿠키 문자열 암호화 (LoginInfo 객체순서에 맞게)
			    String encryptStr = String.format("%s[{|}]%s[{|}]%s[{|}]%s[{|}]%s[{|}]%s[{|}]%s[{|}]%s[{|}]%s", 
			    		member.getPk_member_id(), member.getFd_user_name(), member.getFd_tel(), "", request.getRemoteAddr(),
			    		member.getFd_openapi_member_id(), opanAsePwd, member.getFd_openapi_autologin(), member.getFd_view_name());
			    
			    byte[] plain = encryptStr.getBytes("UTF-8");
			    byte[] encrypt = cipher.doFinal(plain);
			    String encryptHexStr = new BigInteger(encrypt).toString(16);
			    
			    //COOKIE
			    CookieGenerator authGer = new CookieGenerator();
			    authGer.setCookiePath("/");
			    authGer.setCookieName("ck_smw");  		//[ck_smw] and value [-17cbcd883d2dff7c4a2aff398e3a5d7dadf22bf93bb848e1e0a31f67a13c8adf225b51a1293450bd338923aadb519495698c0a1345fc8ba10ab0023389841ed3]
			    //authGer.setCookieMaxAge(authExpire);
			    authGer.setCookieMaxAge(36000); //10시간
			    authGer.addCookie(response, encryptHexStr);
			    
			    
			    //========================== Auto Login Cookie START (실제데이터 id/pwd) ==================================
			    if(autoLogin.equals("TRUE")){
			    	
			    	System.out.println("TRUE. Auto Login Cookie START");
			    	
				    String encryptAutoLogin = String.format("%s[{|}]%s[{|}]%s", pk_member_id, fd_member_pwd_org, request.getRemoteAddr());
				    
				    byte[] plain2 = encryptAutoLogin.getBytes("UTF-8");
				    byte[] encrypt2 = cipher.doFinal(plain2);
				    String encryptHexStr2 = new BigInteger(encrypt2).toString(16);
				    
				    //COOKIE
				    CookieGenerator authGer2 = new CookieGenerator();
				    authGer2.setCookiePath("/");
				    authGer2.setCookieName("ck_alogin"); //60*30=1800(초)->
				    authGer2.setCookieMaxAge(36000); //10시간
				    authGer2.addCookie(response, encryptHexStr2);  //[ck_alogin] and value [a748a010fae6d171000a11849b28032faad11f857a5f891e815d9b74c3fd8f0f5e3f0ec306b9d7822a2ff617b93050e]
				    
			    }else{
			    	
			    	System.out.println("FALSE. Auto Login Cookie START");
			    	
					Cookie auth = new Cookie("ck_alogin", null);
					auth.setPath("/");

					//auth.setHttpOnly(true); //After Servlet3.0
					auth.setMaxAge(0);
					response.addCookie(auth);	
					
			    }
			    //-------------------------- Auto Login Cookie END ------------------------------------

			    
			    
			    System.out.println(" ===== 로그인 완료 > view 페이지로 이동 ===== ");
			    System.out.println(" ");


			    script = script + "location.href='/home/main.do';";
			    
			    //mav.setViewName("redirect:"+strViewName);
			    //mav.setViewName("redirect:"+"main.do");
			    mav.addObject("script", script);
			    //mav.setViewName("redirect:"+"/home/main.do");
			    
			    mav.setViewName("message/returnScript");
			    //mav.setViewName("/home/main");
			    
			    


			    //-------------------------- 미납요금 체크 START ------------------------------------
				//사용자 정보 취득
			    try {
				    Map<String, Object> result_map = null;
				    result_map = memberService.checkPay(pk_member_id);
				    String sum_fd_price		= ""+ result_map.get("sum_fd_price");
				    String sum_pay_price	= ""+ result_map.get("sum_pay_price");
				
				    String pay_cnt = ""+ result_map.get("cnt");
				    
				 //  System.out.println("pay_cnt : " + Integer.parseInt(pay_cnt) );
				    
				    
					if(Integer.parseInt(pay_cnt) >=3 && Integer.parseInt(sum_pay_price) == 0){
						script = "alert('고객님 미납요금("+sum_fd_price+"원)이 발생하여 이용 중지되었습니다. \\n납부 후 이용해 주세요. 자세한 사항은 고객센터 (1577-1111)에 문의 하세요.');";	//징계상태
						
						mav.setViewName("manager/login");
						mav.addObject("script", script);	
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("요금 테이블 정보 확인바람"+pk_member_id);
				}
			    //-------------------------- 미납요금 체크   END ------------------------------------
			    
			}
			else
			{
				script = "alert('회원님은 접속 권한이 없습니다.');clearPwd();";	//징계상태
				
				mav.setViewName("manager/login");
				mav.addObject("script", script);				
			}
		}
		else
		{
			Cookie auth = new Cookie("check_alogin", null);
			auth.setPath("/");
			//auth.setHttpOnly(true); //After Servlet3.0
			auth.setMaxAge(0);
			response.addCookie(auth);	
			
			script = "alert('아이디 또는 비번이 잘못되었습니다.');";
			script = script + "location.href='/manager/login.do';";
		    mav.addObject("script", script);
		    mav.setViewName("message/returnScript");
			
			/*script = "alert('아이디 또는 비번이 잘못되었습니다.');clearPwd();";
			mav.setViewName("manager/login");
			mav.addObject("script", script);*/
		}
		
		return mav;
	}
	

	/**
	 * 로그아웃
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletResponse response)
	{
		System.out.println("\n[CALL Controller ] LoginController.logout");
		
		//auth.setHttpOnly(true); //After Servlet3.0
		
		//로그인 정보
		Cookie auth = new Cookie("ck_smw", null);
		auth.setPath("/");
		auth.setMaxAge(0);
		response.addCookie(auth);		

		//자동로그인 체크선택
		Cookie auth1 = new Cookie("check_alogin", null);
		auth1.setPath("/");
		auth1.setMaxAge(0);
		response.addCookie(auth1);
		
		//자동로그인정보
		Cookie auth2 = new Cookie("ck_alogin", null);
		auth2.setPath("/");
		auth2.setMaxAge(0);
		response.addCookie(auth2);
		
		return new ModelAndView("redirect:/manager/login.do");
	}
	

}
