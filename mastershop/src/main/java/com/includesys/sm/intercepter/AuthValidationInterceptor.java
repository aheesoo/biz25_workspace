package com.includesys.sm.intercepter;

import java.math.BigInteger;
import java.security.Key;
import java.util.List;

import javax.crypto.Cipher;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.includesys.sm.dto.manager.CMSMenu;
import com.includesys.sm.dto.manager.LoginInfo;
import com.includesys.sm.util.ByteUtils;
import com.includesys.sm.util.KeyMaker;


/**
 * 세션 쿠키 체크
 * @author Administrator
 *
 */
@Order(1)
public class AuthValidationInterceptor extends HandlerInterceptorAdapter {
	
	//@Autowired
	//private HttpSession session;

	@Autowired
	private ServletContext servletContext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		
		System.out.println("[CALL Interceptor] AuthValidationInterceptor.preHandle() ");
		
		boolean result = false;

		String requestURI = request.getRequestURI().replaceAll("//", "/");
		
		System.out.println("requestURI="+requestURI);
		
		if(requestURI.equals("/manager/login.do") || requestURI.equals("/manager/logout.do") || 
				requestURI.equals("/manager/searchId.do") || requestURI.equals("/manager/searchPw.do") || 
				requestURI.equals("/callLog/take_log.do") || 
				requestURI.indexOf("/openapi/")>-1 ){

			System.out.println("[CALL] authValidationHandler result.true");
			result = true;
		
		}else {
			
			System.out.println("[CALL] authValidationHandler result.false");
			String encryptAuth = "";

			if(request.getCookies() != null){
				for(Cookie cookie : request.getCookies()){
					if(cookie.getName().equals("ck_smw")){
						encryptAuth = cookie.getValue();
					}
				}
			}
			
			if(!encryptAuth.equals(""))
			{
				//String encryptKey = request.getSession().getServletContext().getInitParameter("encryptKey");
				String encryptKey = servletContext.getInitParameter("encryptKey");
				
				Key key = KeyMaker.generateKey("AES", ByteUtils.toBytes(encryptKey, 16));
			    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				
				byte[] encrypt = new BigInteger(encryptAuth, 16).toByteArray();
			    cipher.init(Cipher.DECRYPT_MODE, key);
			    byte[] decrypt = cipher.doFinal(encrypt);	

			    String auth = new String(decrypt, "UTF-8");
			    String[] authInfo = auth.split("\\[\\{\\|\\}\\]");

			    if(authInfo[4].equals(request.getRemoteAddr()))
			    {
			    	LoginInfo loginInfo = new LoginInfo(authInfo[0], authInfo[1], authInfo[2], authInfo[3], authInfo[4], authInfo[5], authInfo[6], authInfo[7], authInfo[8]);
			    	
			    	System.out.println("authInfo[0]"+authInfo[0]);
			    	System.out.println("authInfo[1]"+authInfo[1]);
			    	System.out.println("authInfo[2]"+authInfo[2]);
			    	System.out.println("authInfo[3]"+authInfo[3]);
			    	System.out.println("authInfo[4]"+authInfo[4]);
			    	System.out.println("authInfo[5]"+authInfo[5]);
			    	System.out.println("authInfo[6]"+authInfo[6]);
			    	System.out.println("authInfo[7]"+authInfo[7]);
			    	System.out.println("authInfo[8]"+authInfo[8]);
			    	
			    	request.setAttribute("loginInfo", loginInfo);
			    	//session.setAttribute("loginInfo", loginInfo);

			    	result = true;
			    }
			}else{
				System.out.println("#2");
				result = false;
				//System.out.println("[CALL] authValidationHandler CookieSession END");
			}
			
			//세션 체크
			//if( session.getAttribute("aclList") == null ){
			//	result = false; //세션이 끊긴 상태
			//}
		}
		System.out.println("Auth #8");
		if( result == true ) {	
			System.out.println("Auth #9");
			return true;
		}else {
			System.out.println("Auth #9-1");
			ModelAndView mav = new ModelAndView();		
			mav.setViewName("message/authorization");
			throw new ModelAndViewDefiningException(mav);
		}
	}
}