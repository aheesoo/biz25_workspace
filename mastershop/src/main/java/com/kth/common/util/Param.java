
package com.kth.common.util;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import com.kth.common.secure.Secure;

public final class Param {
	
	private Secure _secure = null;
	
	
	/**
	 * getParamXSS() : 요청한 파라미터의 값을 clearXSS() 로 필터하여 문자열로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @param avatag
	 * @return
	 */
	public String getParamXSS(HttpServletRequest _request, String rTarget, String defaultValue, String avatag) {		
		
		String result = null;
		String rtn_str = null;
		
		_secure = new Secure();
		
		try {
			
			result = _secure.clearXSS(_request.getParameter(rTarget), avatag);

			if(result != null) {
				String encodeType =  _request.getCharacterEncoding();
				
				if( encodeType != null && encodeType.toUpperCase().equals("UTF-8") ){
				
					rtn_str = URLDecoder.decode(result, "utf-8");
					
				}else {
					
					rtn_str = new String(result.getBytes("8859_1"), "euc-kr");
					
				}
				
			}

		} catch(Exception ex) {

		} finally {

			if(rtn_str == null) {
				rtn_str = defaultValue;

			} else {
				if(rtn_str.trim().length() == 0)
					rtn_str = defaultValue;
			}

		}

		return rtn_str;

	}


	/**
	 * getParamXSS() : 요청한 파라미터의 값을 clearXSS() 로 필터하여 정수로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @param avatag
	 * @return
	 */
	public int getParamXSS(HttpServletRequest _request, String rTarget, int defaultValue, String avatag) {		
		
		int result = 0;
		
		_secure = new Secure();

		try {
			result = Integer.parseInt(_secure.clearXSS(_request.getParameter(rTarget), avatag));

		} catch(Exception ex) {
			result = defaultValue;

		}

		return result;

	}
	
	/**
	 * getParamXSS() : 요청한 파라미터의 값을 clearXSS() 로 필터하여 정수로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @param avatag
	 * @return
	 */
	public long getParamXSS(HttpServletRequest _request, String rTarget, long defaultValue, String avatag) {		
		
		long result = 0;
		
		_secure = new Secure();

		try {
			result = Long.parseLong(_secure.clearXSS(_request.getParameter(rTarget), avatag));

		} catch(Exception ex) {
			result = defaultValue;

		}

		return result;

	}


	/**
	 * getParam() : 요청한 파라미터의 값을 문자열로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @return
	 */
	public String getParam(HttpServletRequest _request, String rTarget, String defaultValue) {

		String result = null;
		try {
			result = _request.getParameter(rTarget);

		} catch(Exception ex) {

		} finally {

			if(result == null) {
				result = defaultValue;

			} else {
				if(result.trim().length() == 0)
					result = defaultValue;
			}

		}

		return result;

	}
	
	
	/**
	 * getParam() : 요청한 파라미터의 값을 정수로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @return
	 */
	public int getParam(HttpServletRequest _request, String rTarget, int defaultValue) {

		int result = 0;

		try {
			result = Integer.parseInt(_request.getParameter(rTarget));

		} catch(Exception ex) {
			result = defaultValue;

		}

		return result;

	}
	
	/**
	 * getAttribute() : 요청한 에트리뷰트의 값을 문자열로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @return
	 */
	public String getAttribute(HttpServletRequest _request, String rTarget, String defaultValue) {

		String result = null;
		try {
			result = (String)_request.getAttribute(rTarget);

		} catch(Exception ex) {

		} finally {

			if(result == null) {
				result = defaultValue;

			} else {
				if(result.trim().length() == 0)
					result = defaultValue;
			}

		}

		return result;

	}

	
	/**
	 * getAttribute() : 요청한 에트리뷰트의 값을 정수로 반환
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @return
	 */
	public int getAttribute(HttpServletRequest _request, String rTarget, int defaultValue) {

		int result = 0;

		try {
			result = (Integer)_request.getAttribute(rTarget);
		} catch(Exception ex) {
			result = defaultValue;
		}

		return result;

	}
	
	/**
	 * getParams() : 요청한 파라미터의 값을 문자열로 반환
	 * 
	 * @param _request
	 * @param rTarget
	 * @param defaultValue
	 * @return
	 */
	public String[] getParams(HttpServletRequest _request, String rTarget, String defaultValue) {

		String[] result = null;
		try {
			result = _request.getParameterValues(rTarget);

		} catch (Exception ex) {

		} finally {

			if (result == null) {
				result = new String[1];
				result[0] = defaultValue;
			} else {
				for (int i = 0; i < result.length; i++) {
					if (result[i] == null || result[i].trim().length() == 0)
						result[i] = defaultValue;
				}
			}

		}
		return result;
	}
	
}