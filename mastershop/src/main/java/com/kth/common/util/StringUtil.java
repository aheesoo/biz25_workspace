package com.kth.common.util;

import java.text.*;		     // SimpleDateFormat
import java.util.*;		     // Calendar, StringTokenizer
import java.nio.*;		     // ByteBuffer

public final class StringUtil {
	

	/**
	 * idNum (YYYYMMDDF)
	 * @param idNum
	 * @return
	 */
	public static String getMyAge(String idNum) {

		String year = "";
		String month = "";
		String day = "";
		String myYear = ""; // 생일
		int myAge = 0; // 만 나이

		try {

			SimpleDateFormat formatY = new SimpleDateFormat("yyyy",Locale.KOREA);
			SimpleDateFormat formatM = new SimpleDateFormat("MM", Locale.KOREA);
			SimpleDateFormat formatD = new SimpleDateFormat("dd", Locale.KOREA);
			year = formatY.format(new Date());
			month = formatM.format(new Date());
			day = formatD.format(new Date());

			if (idNum.charAt(6) == '1' || idNum.charAt(6) == '2') {
				myYear = "19" + idNum.substring(0, 2);
			}else {
				myYear = "20" + idNum.substring(0, 2);
			}

			if (Integer.parseInt(month) > Integer.parseInt(idNum.substring(2, 4))) {
				myAge = Integer.parseInt(year) - Integer.parseInt(myYear);
			}else if (Integer.parseInt(month) == Integer.parseInt(idNum.substring(2, 4))) {
				
				if (Integer.parseInt(day) > Integer.parseInt(idNum.substring(4, 6))) {
					myAge = Integer.parseInt(year) - Integer.parseInt(myYear);
				}else {
					myAge = Integer.parseInt(year) - (Integer.parseInt(myYear) + 1);
				}

			}else {				
				myAge = Integer.parseInt(year) - (Integer.parseInt(myYear) + 1);
			}

		} catch (Exception ex) {

			ex.printStackTrace();
			myAge = 0;

		}

		return String.valueOf(myAge);

	}
	
	
	/**
	 * 한글체크 : 깨지는 한글 입력 안되도록 [라이브러리]
	 * @param str
	 * @return
	 */
	public boolean chkString(String str) {
		
		//System.out.println(str);
		
		boolean rCode = true;
		
		for(int i=0; i < str.length(); i++) {
		
			//System.out.println("=======> " + str.substring(i, i+1).compareTo(":"));
		
			//System.out.println((int)str.substring(i, i+1).charAt(0));

			if ( ( (int)str.substring(i, i+1).charAt(0) == 63 ||
				   (int)str.substring(i, i+1).charAt(0) == 65533 ) ) { //&&
     			   //str.substring(i, i+1).charAt(0) != '?' ) {

				rCode = false;
				break;

			} else if ((str.substring(i, i+1).compareTo("가") >= 0) && (str.substring(i, i+1).compareTo("힝") <= 0)) {

			} else if ((str.substring(i, i+1).toUpperCase().compareTo("A") >= 0) && (str.substring(i, i+1).toUpperCase().compareTo("Z") <= 0)) {
				
			} else if ((str.substring(i, i+1).compareTo("0") >= 0) && (str.substring(i, i+1).compareTo("9") <= 0)) {
			
			}
			
			else if ( (str.substring(i, i+1).compareTo(":") == 0) ) { 
				
				rCode = false;
				break;
					
			} 

			else if ( (str.substring(i, i+1).compareTo(",") == 0) ) { 
				
				rCode = false;
				break;
					
			} 
			
			else if ( (str.substring(i, i+1).compareTo("%") == 0) ) { 
				
				rCode = false;
				break;
					
			} 
			
			else if ( (str.substring(i, i+1).compareTo("#") == 0) ) { 
				
				rCode = false;
				break;
					
			} 
			
			else if ( (str.substring(i, i+1).compareTo("&") == 0) ) { 
								
				rCode = false;
				break;
					
			} 
			
			else if ( (str.substring(i, i+1).compareTo("!") == 0) ) { } 			
			else if ( (str.substring(i, i+1).compareTo("@") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("#") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("$") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("%") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("^") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("&") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("*") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("(") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo(")") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("-") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("_") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("+") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("=") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("|") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("[") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("]") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("{") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("}") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("<") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo(">") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo(",") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo(".") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("/") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("?") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("~") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo("`") == 0) ) { } 
			else if ( (str.substring(i, i+1).compareTo(" ") == 0) ) { } 
				
			else {
			
				/*
				int tempCnt = 0;

				for(int j=0; j<spChar.length; j++) {

					if( str.substring(i, i+1).compareTo(spChar[j]) == 0 ) {
					
						tempCnt += 1;
						break;

					} 

				}

				if(tempCnt == 0) {
					rCode = false;
					break;
				}
				*/

				rCode = false;
				break;
				
			}
				
		}
		
		return rCode;
	 	
	}

	/**
	 * 넘어온 문자열의 인코딩 형식을 ISO8859_1에서 KSC5601로 변경후 리턴<br>
	 * <font color=red><b>[Deprecated] EncodingKor(String)를 사용하세요!</b></font>
	 * @param rStr : 인코딩을 변경할 문자열
	 * @return     : 인코딩이 변경된 문자열
	 */
	public String toKorean(String rStr) {	
		return EncodingKor(rStr);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 넘어온 문자열의 인코딩 형식을 ISO8859_1에서 KSC5601로 변경후 리턴
	 * @param rStr : 인코딩을 변경할 문자열
	 * @return     : 인코딩이 변경된 문자열
	 */
	public String EncodingKor(String rStr) {
	
		String result = "";
		try {			
			if (rStr != null)
				result = new String(rStr.getBytes("8859_1"), "KSC5601");		
		
		} catch (java.io.UnsupportedEncodingException ex) {
			result = rStr;
			
		} catch (Exception ex) {
			result = rStr;
		}
	
		return result;
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 넘어온 문자열의 인코딩 형식을 fromType에서 toType로 변경후 리턴<br>
	 * <font color=red><b>[Deprecated] changeEncoding(String, String, String)을 사용하세요!</b></font>
	 * @param str   : 인코딩을 변경할 문자열
	 * @param fromType : 변경될 인코딩 타입
	 * @param toType   : 변경할 인코딩 타입
	 * @return
	 */
	public String toKorean(String rStr, String fromType, String toType) {
		return changeEncoding(rStr, fromType, toType);
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 넘어온 문자열의 인코딩 형식을 fromType에서 toType로 변경후 리턴
	 * @param str   : 인코딩을 변경할 문자열
	 * @param fromType : 변경될 인코딩 타입
	 * @param toType   : 변경할 인코딩 타입
	 * @return
	 */
	public String changeEncoding(String rStr, String fromType, String toType) {
	
		String result = "";
	
		try {
			if (rStr != null)
				result = new String(rStr.getBytes(fromType), toType);
			
		} catch (java.io.UnsupportedEncodingException ex) {
			result = rStr;
			
		} catch (Exception ex) {
			result = rStr;
		}
	
		return result;
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * Left Padding (한글,영문,숫자 모두 1글자 단위로 계산합니다.)
	 * @param inStr : 대상 문자열
	 * @param pad   : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param len   : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return      : 처리된 문자열
	 */
	public String LPad(String inStr, char pad, int len) {
	
		StringBuffer rCode = new StringBuffer("");
		String       str   = null;
		
		int strLen = 0;
		int gbnPos = 0;
	
		if (inStr == null) 
			return null;
		
		if (len < 1) 
			return null;
		
		if (strLen(pad) > 1) 
			return null;
		
		str     = inStr.trim();
		strLen  = str.length();
		
		if (len < strLen) 
			return null;
		
		gbnPos = len - strLen;
	
		for (int i=0; i<gbnPos; i++) 
			rCode.append(pad);	
		rCode.append(str);
	
		return rCode.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * Left Padding (바이트 개념 처리 - 한글1자를 2바이트로 처리하여 계산)<br>
	 * <font color=red><b>[Deprecated] LPadByte(String, char, int)를 사용하세요!</b></font>
	 * @param inStr   : 대상 문자열
	 * @param pad     : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param lenByte : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return        : 처리된 문자열
	 */
	public String LPadKor(String inStr, char pad, int lenByte) {
		return LPadByte(inStr, pad, lenByte);
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * Left Padding (바이트 개념 처리 - 한글1자를 2바이트로 처리하여 계산)
	 * @param inStr   : 대상 문자열
	 * @param pad     : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param lenByte : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return        : 처리된 문자열
	 */
	public String LPadByte(String inStr, char pad, int lenByte) {
			
		StringBuffer rCode = new StringBuffer("");
		String       str   = null;
		
		int strLen     = 0;
		int strLenByte = 0;
		int gbnPos     = 0;
	
		if (inStr == null) 
			return null;
		
		if (lenByte < 1) 
			return null;
		
		if (strLen(pad) > 1) 
			return null;
		
		str        = inStr.trim();
		strLen     = str.length();
		strLenByte = strLen(str);
		
		if (lenByte < strLenByte) 
			return null;
		
		gbnPos = lenByte - strLenByte;
	
		int hanCount = strLenByte - strLen;	
		for (int i=0; i<gbnPos; i++) 
			rCode.append(pad);	
		rCode.append(str);
	
		return rCode.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * Right Padding (한글,영문,숫자 모두 1글자 단위로 계산합니다.)
	 * @param inStr : 대상 문자열
	 * @param pad   : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param len   : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return      : 처리된 문자열
	 */
	public String RPad(String inStr, char pad, int len) {
			
		StringBuffer rCode = new StringBuffer("");
		String       str   = null;
		
		int strLen = 0;
		int gbnPos = 0;
	
		if (inStr == null) 
			return null;
		
		if (len < 1) 
			return null;
		
		if (strLen(pad) > 1) 
			return null;
		
		str     = inStr.trim();
		strLen  = str.length();
	
		if (len < strLen) 
			return null;
	
		rCode.append(inStr);	
		for (int i=strLen; i<len; i++)
			rCode.append(pad);
	
		return rCode.toString();
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * Right Padding (한글,영문,숫자 모두 1글자 단위로 계산합니다.)<br>
	 * <font color=red><b>[Deprecated] RPadByte(String, char, int)를 사용하세요!</b></font>
	 * @param inStr : 대상 문자열
	 * @param pad   : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param len   : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return      : 처리된 문자열
	 */
	public String RPadKor(String inStr, char pad, int lenByte) {
		return RPadByte(inStr, pad, lenByte);
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * Right Padding (바이트 개념 처리 - 한글1자를 2바이트로 처리하여 계산)
	 * @param inStr   : 대상 문자열
	 * @param pad     : 1byte 캐릭터 (2byte 캐릭터는 제외됨)
	 * @param lenByte : 글자수 (패딩후의 글자수를 의미합니다.)
	 * @return        : 처리된 문자열
	 */
	public String RPadByte(String inStr, char pad, int lenByte) {
			
		StringBuffer rCode = new StringBuffer("");
		String       str   = null;
		
		int strLen     = 0;
		int strLenByte = 0;
		int gbnPos     = 0;
	
		if (inStr == null) 
			return null;
		
		if (lenByte < 1) 
			return null;
		
		if (strLen(pad) > 1) 
			return null;
	
		str        = inStr.trim();
		strLen     = str.length();
		strLenByte = strLen(str);
	
		if (lenByte < strLenByte) 
			return null;
	
		rCode.append(inStr);
		int hanCount = strLenByte - strLen;
		for (int i=strLen; i<(lenByte-hanCount); i++) 
			rCode.append(pad);
	
		return rCode.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 바이트수 리턴, 글자수 제대로 체크 (한글 포함)
	 * @param str : 대상 문자열
	 * @return : 바이트 수 반환 (한글등은 한글자라 하더라도 2가 리턴)
	 */
	public int strLen(String str) {
		
		if (str == null) 
			return 0;
		
		if (str.length() < 1) 
			return 0;
	
		byte[] by = str.getBytes();
		
		return by.length;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 바이트수 리턴, 글자수 제대로 체크(한글 포함)
	 * @param str : 대상 문자(char에 2바이트 크기의 한글변환 특수문자등이 들어올 수 있음)
	 * @return : 바이트 수 반환 (한글등은 한글자라 하더라도 2가 리턴)
	 */
	public int strLen(char str) {
	
		String str1 = Character.toString(str);

		return strLen(str1);
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * String을 ByteBuffer로 변환
	 * @param str : 대상 문자열
	 * @return : ByteBuffer 반환
	 */
	public ByteBuffer str2byte(String str) 	{
		
		//버퍼에 문자열을 담기 위해 바이트 배열로 바꾼다.
		byte[] by = str.getBytes();
		
		//버퍼 생성
		ByteBuffer buf = ByteBuffer.allocate(by.length);
		
		//바이트배열을 버퍼에 넣는다.
		buf.put(by);
		
		//버퍼의 위치(position)는 0으로 limit와 capacity값과 같게 설정한다.
		buf.clear();  
	
		return buf;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 문자열 찾기 (앞에서 부터)
	 * @param srcStr  : 원본 문자열
	 * @param findStr : 찾을 문자열
	 * @return : 찾아진 문자열의 시작 위치
	 */
	public int find(String srcStr, String findStr) {
		
		if (srcStr == null) 
			return -96;
		
		if (srcStr.trim().length() < 1)/*equals(""))*/ 
			return -97;
		
		if (findStr == null) 
			return -98;
		
		if (findStr.trim().length() < 1)/*equals(""))*/ 
			return -99;
		
		return srcStr.indexOf(findStr, 0);
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 넘어온 밀리초가 몇일 몇시 몇분 몇.몇초로 환산되는지 알려준다.
	 * @param msec
	 * @return
	 */
	public String[] msec2time (long msec) {
	
		int sec  = 1000;
		int min  = 60000;
		int hour = 3600000;
		int day  = 86400000;
		int tmp  = 0;
		
		String rCode[] = new String[4];
		for(int i=0; i<rCode.length; i++) 
			rCode[i] = "0";
		
		if (msec <= 0) 
			return rCode;
	
		rCode[0] = Integer.toString((int)(msec/day));
		tmp      = (int)(msec%day);
	
		rCode[1] = Integer.toString(tmp/hour);
		tmp      = tmp%hour;
	
		rCode[2] = Integer.toString(tmp/min);
		tmp      = tmp%min;
	
		rCode[3] = Integer.toString(tmp);
		if (tmp >= sec) {
			if (rCode[3].length() == 4)      
				rCode[3] = rCode[3].substring(0,1) + "." + rCode[3].substring(1,4);
			
			else if (rCode[3].length() == 5) 
				rCode[3] = rCode[3].substring(0,2) + "." + rCode[3].substring(2,5);
		
		} else if (tmp > 0) {
			rCode[3] = "0." + Integer.toString(tmp); 	
		
		} else	{
			rCode[3] = "0.0"; 	
		}
	
		return rCode;
		
	}
	
	//----------------------------------------------------------------------------------------------------//

	/**
	 * 'yyyy-MM-dd HH:mm:ss' 등의 패턴을 던지면 현재 날짜 값을 해당 패턴으로 적용해서 문자열로 리턴<br>
	 * <font color=red><b>[Deprecated] DateTime 클래스의 getDateTime(String)을 이용하세요!</b></font>  
	 * @param pattern
	 * @return
	 */
	public String getDateTime(String pattern) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);

		//dateHeader.applyPattern (pattern);
		return dateHeader.format(cal.getTime());
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 문자열 치환 - 최초로 일치하는 String을 변경합니다.
	 * @param str         : 대상 문자열
	 * @param find        : 찾을 문자열
	 * @param replaceWith : 바꿀 문자열
	 * @return
	 */
	public String replace(String str, String find, String replaceWith)	{
		return replaceProc(str, find, replaceWith, false);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 문자열 치환 - 일치되는 모든 String을 변경합니다.
	 * 변경일자    - 2005.03.02
	 * @param str
	 * @param find
	 * @param replaceWith
	 * @return
	 */
	public String replaceAll(String str, String find, String replaceWith) {
		return replaceProc(str, find, replaceWith, true);
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 문자열 치환
	 * @param str         : 대상 문자열
	 * @param find        : 찾을 문자열
	 * @param replaceWith : 바꿀 문자열
	 * @param loopValue   : 반복 여부
	 * @return
	 */
	private String replaceProc(String str, String find, String replaceWith, boolean loopValue) {
			
		if (str == null) 
			return null;
		
		if (find == null) 
			return null;
		
		if (replaceWith == null) 
			return null;
	
		int findLen = find.length();
		int strLen  = str.length();
		
		StringBuffer sb = new StringBuffer("");
		
		int startPos = 0;
		int findPos  = 0;
		
		boolean swLoop = true;
		
		//System.out.println("str["+str+"] find["+find+"] resplaceWith ["+replaceWith+"]"); 
		//System.out.println("loopValue["+loopValue+"]");
		
		int oo = 0;
		while(swLoop) {	
			
			swLoop = loopValue;
			//System.out.println("swLoop["+swLoop+"]"+(++oo)+"번째*************");
			
			findPos = str.substring(startPos, strLen).indexOf(find); // 일치하는 문장을 찾는다. pos1을 더한것은 첫번째 찾기시도는 pos1=0 이지만 다음 찾을땐 0보다 크다..substring을 했기때문에 찾은 위치가 왜곡되지요.
		
			if (findPos < 0) {
				//1회차 루프시 : 일치하는 문장이 발견되지 않았다. 
				//2회차 이상   : 더이상 일치하는 문장이 없다.
				//System.out.println("startPos["+Integer.toString(startPos)+"] strLen["+Integer.toString(strLen)+"] break["+str.substring(startPos, strLen)+"]");
				sb.append(str.substring(startPos, strLen));
				break;
			
			} else	{
				// 문장이 일치한다. 변경하자.
				findPos = findPos + startPos;
				
				//System.out.println("startPos["+Integer.toString(startPos)+"] strLen["+Integer.toString(strLen)+"] find당["+str.substring(startPos, findPos)+"]");
				sb.append(str.substring(startPos, findPos)); //일치하기 전 문장까지 저장한다.
				sb.append(replaceWith);		   				 //일치하는 부분을 이걸로 대체한다.
				startPos = findPos +  findLen;		 		 //다음 찾을 위치를 계산한다. 찾은 문장 이후로 시작포인트를 설정한다.
			}
			
			//System.out.println("--startPos["+Integer.toString(startPos)+"] > strLen-1["+Integer.toString(strLen-1)+"]");
			if (startPos > (strLen-1)) 
				break;
		
			//나머지 문장을 붙인다.
			if (!swLoop) 
				sb.append(str.substring(startPos, strLen)); 
			
		}
		
		return sb.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 대상 문자열을 구분자로 쪼개어 1차원 문자 배열로 리턴 (JDK 1.4 부터는 String.split()를 지원)
	 * @param str   : 대상 문자열
	 * @param delim : 구분자
	 * @return
	 */
	public String[] split(String str, String delim) {
	
		if (str	== null) 
			return null;
		
		if (str.trim().length() < 1)/*.equals(""))*/ 
			return null;
		
		if (delim == null) 
			return null;
		
		if (delim.length() < 1) /*equals(""))*/ 
			return null;		
	
		StringTokenizer st = new StringTokenizer(str, delim);
		String[] rCode     = new String[st.countTokens()];
	
		try {			
			for (int i = 0; st.hasMoreTokens(); i++)
				rCode[i] = st.nextToken();
		
		} catch (Exception ex) {
			//log.logWrite("Error", "[Conv][split][Exception]" + ex.toString(), log.ERR);
			ex.printStackTrace();
			rCode = null;
		}
	
		return rCode;
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 대상문자열을 로우/컬럼 구분자로 분리후 2차원 문자 배열로 리턴(입력되는 로우별 컬럼의 개수는 동일해야 함)
	 * @param str      : 대상 문자열
	 * @param colDelim : 컬럼 구분자
	 * @param rowDelim : 로우 구분자
	 * @return
	 */
	public String[][] split(String str, String colDelim, String rowDelim) {
			
		if (str	== null) 
			return null;
		
		if (str.trim().length() < 1) 
			return null;
		
		if (colDelim == null) 
			return null;
		
		if (colDelim.length() < 1)  
			return null;
		
		if (rowDelim == null) 
			return null;
		
		if (rowDelim.length() < 1)  
			return null;
		
		String[][] rCode = null;
		String[] rowData = split(str, rowDelim); //로우 구분
	
		if (rowData == null) {
			//log.logWrite("Public", "[Conv][split][Exception]rowData.len:" + rowData.length, log.DEBUG);
			return null;
		}
							
		try {
			rCode = new String[rowData.length][];
			
			//컬럼 구분
			for (int i = 0; i<rowData.length; i++)	
				rCode[i] = split(rowData[i], colDelim);
			
		} catch (Exception ex)	{
			//log.logWrite("Error", "[Conv][split][Exception]" + ex.toString(), log.ERR);	
			ex.printStackTrace();
			rCode = null;
		}
	
		return rCode;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 문자열을 지정한 위치에서 자른후 지정된 말줄임표로 마무리한다. 
	 * @param str     : 대상 문자열
	 * @param cutLine : 제한 길이
	 * @param mark    : 제한 길이 이후에 붙일 표식
	 * @return
	 */
	public String stringCutter(String str, int cutLine, String mark) {
	
		String result = "";
	
		if(str != null) {
			
			if (str.trim().length() > cutLine) {
				result = str.substring(0, cutLine).trim() + "" + mark;
				
			} else {
				result = str;
			}
			
		} else {
			result = str;
		}
	
		return result;
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * 숫자값만 추출
	 */
	public String convertNum(String str)	{
			
		String result = "";
		
		for(int i=0; i < str.length(); i++) {
			
			if ((str.substring(i, i+1).compareTo("0") >= 0) && (str.substring(i, i+1).compareTo("9") <= 0)) {
			
				result += str.substring(i, i+1);
					
			}
			
		}
		
		return result;
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 숫자로 구성된 문자열인지 체크하여, 전부 숫자이면 true
	 * @param str : 대상 문자열
	 * @return
	 */
	public boolean chkNum(String str)	{
			
		boolean rCode = true;
	
		for(int i=0; i < str.length(); i++) {
			
			if ((str.substring(i, i+1).compareTo("0") >= 0) && (str.substring(i, i+1).compareTo("9") <= 0)) {
			
			} else	{
				rCode = false;
				break;
			}
			
		}
		
		return rCode;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 영문자로 구성된 문자열인지 체크하여, 전부 영문자이면 true
	 * @param str : 대상 문자열
	 * @return
	 */
	public boolean chkEng(String str)	{
	
		boolean rCode = true;
	
		for(int i=0; i < str.length(); i++) {
		
			if ((str.substring(i, i+1).toUpperCase().compareTo("A") >= 0) && (str.substring(i, i+1).toUpperCase().compareTo("Z") <= 0)) {
		
			} else	{
				rCode = false;
				break;
			}
			
		}
		
		return rCode;
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 한글로 구성된 문자열인지 체크하여, 전부 한글이면 true 
	 * @param str
	 * @return
	 */
	public boolean chkKor(String str)	{
		
		boolean rCode = true;
	
		for(int i=0; i < str.length(); i++) {
			
			if ((str.substring(i, i+1).compareTo("가") >= 0) && (str.substring(i, i+1).compareTo("힝") <= 0))	{
		
			} else {
				rCode = false;
				break;
			}
		
		}
		
		return rCode;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 특수문자로 구성된 문자열인지 체크하여, 전부 특수문자이면 true
	 * @param str
	 * @return
	 */
	public boolean chkSpecial(String str)	{
	
		boolean rCode = true;
	
		for(int i=0; i < str.length(); i++) {
			
			if (chkNum(str) || chkEng(str) || chkKor(str)) {
				rCode = false;
				break;
			}
			
		}
		
		return rCode;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 세 자리마다 콤마찍기
	 * @param num
	 * @return
	 */
	public String convertMoney(long num) {
	
		DecimalFormat df = new DecimalFormat("#,##0");
		
		return df.format(num);
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 세 자리마다 콤마찍기
	 * @param num
	 * @return
	 */
	public String convertMoney(int num) {
		
		DecimalFormat df = new DecimalFormat("#,##0");
		
		return df.format(num);
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 세 자리마다 콤마찍기
	 * @param str
	 * @return
	 */ 	
	public String convertMoney(String str) {
	
		if (str == null) 
			return null;
		
		if (!chkNum(str)) 
			return str;

		DecimalFormat df = new DecimalFormat("#,##0");
	
		return df.format(Long.parseLong(str));
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 숫자값을 지정된 패턴 포맷으로 변환 후 리턴
	 * @param num
	 * @param pattern
	 * @return
	 */	
	public String numFormat(int num, String pattern) {
	
		DecimalFormat df = new DecimalFormat(pattern);
	
		return df.format(num);
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 값이 전부 숫자인 문자값을 지정된 패턴 포맷으로 변환 후 리턴
	 * @param num
	 * @param pattern
	 * @return
	 */
	public String numFormat(String num, String pattern) {
	
		DecimalFormat df = new DecimalFormat(pattern);
	
		return df.format(Integer.parseInt(num));
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 일자시간 값을 지정된 패턴 포맷으로 변환 후 리턴
	 * @param datetime
	 * @param pattern
	 * @return
	 */
	public String dtFormat(Date datetime, String pattern) {
	
		SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
	
		return dateHeader.format(datetime);
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 구분자 없는 전화번호에 구분자 넣어 리턴
	 * @param inStr : 대상 문자열
	 * @param delim : 구분자
	 * @return
	 */
	public String telFormat(String inStr, char delim) {
		
		return telFormat(inStr, delim, false);
		
	}

	/**
	 * 구분자 없는 전화번호에 구분자 넣어 리턴
	 * @param inStr : 대상 문자열
	 * @param delim : 구분자
	 * @param starEnable : "*010"으로 시작하는 번호를 허용한다.
	 * @return
	 */
	public String telFormat(String inStr, char delim, boolean starEnable) {
	
		StringBuffer sb  = new StringBuffer("");
		String       str = null;
	
		if (inStr == null) 
			return "";
		
		if (inStr.equals("")) 
			return "";
		
		str = inStr.trim();
	
		if (str.length() < 1) 
			return "";

		if (str.length() < 8) 
			return inStr;		

		//005411112222, 000211112222
		if(str.length()==12){

			String strTemp1 = str.substring(0, 4);
			String strTemp2 = str.substring(4, 8);
			String strTemp3 = str.substring(8, 12);

			// 국번의 3자리
			if( strTemp1.startsWith("0002") ){

				strTemp1 = strTemp1.substring(2, strTemp1.length());

			}else{

				strTemp1 = strTemp1.substring(1, strTemp1.length());

			}

			// 지역번호에 0, 00로 시작할 경우 앞의 0을 제거한다
			if( strTemp2.startsWith("00") ) {

				strTemp2 = strTemp2.substring(2, strTemp2.length());

			}else if(strTemp2.startsWith("0") ) {

				strTemp2 = strTemp2.substring(1, strTemp2.length());

			}

			str = strTemp1 + strTemp2 + strTemp3;

		}
		
		// "*010"으로 시작하는 번호는 "*"을 빼고 숫자 유효성 검사 
		if( starEnable && inStr.startsWith("*010") ) {
			
			if ( !chkNum(str.substring(1)) ) 
				return str;
			
		} else {
			
			if ( !chkNum(str) ) 
				return str;
			
		}
	
		//지역번호  2자리
		if (str.substring(0,2).equals("02")) {
			
			if (str.length() == 9) {
				sb.append(str.substring(0, 2));
				sb.append(delim);
				sb.append(str.substring(2, 5));
				sb.append(delim);
				sb.append(str.substring(5, 9));
		
			} else if (str.length() == 10) {
				sb.append(str.substring(0, 2));
				sb.append(delim);
				sb.append(str.substring(2, 6));
				sb.append(delim);
				sb.append(str.substring(6, 10));
		
			} else	{
				sb.append(str);
			}
			
		}else if ( starEnable && str.startsWith("*010") ) {			// *010으로 시작하는 번호
					
			if (str.length() == 11) {
				sb.append(str.substring(0, 4));
				sb.append(delim);
				sb.append(str.substring(4, 7));
				sb.append(delim);
				sb.append(str.substring(7, 11));
			
			}
			else if (str.length() == 12) {
				sb.append(str.substring(0, 4));
				sb.append(delim);
				sb.append(str.substring(4, 8));
				sb.append(delim);
				sb.append(str.substring(8, 12));
			
			}			
			else {
				sb.append(str);
			}			
			
		} else { //이통사 및 지역번호 3자리, 1588-8000
			
			if (str.length() == 8) {
				sb.append(str.substring(0, 4));
				sb.append(delim);
				sb.append(str.substring(4, 8));
			}
			else if (str.length() == 10) {
				sb.append(str.substring(0, 3));
				sb.append(delim);
				sb.append(str.substring(3, 6));
				sb.append(delim);
				sb.append(str.substring(6, 10));
			
			}
			else if (str.length() == 11) {
				sb.append(str.substring(0, 3));
				sb.append(delim);
				sb.append(str.substring(3, 7));
				sb.append(delim);
				sb.append(str.substring(7, 11));
			
			}			
			else {
				sb.append(str);
			}
			
		}
		
		return sb.toString();
		
	}

	public String telFormat12(String inStr) {

		String tel1 = "";
		String tel2 = "";
		String tel3 = "";

		String tail = "";

		//-- 지역번호 -- //
		//
		if( inStr != null && inStr.length() > 2) {

			// 서울 02  경기 031  인천 032  강원 033  충남 041  대전 042  충북 043 부산 051 
			// 울산 052 대구 053  경북 054  경남 055  전남 061  광주 062  전북 063  제주 064 		

			if( inStr.startsWith("02") ) {

				tel1 = "00" + inStr.substring(0, 2);
				tail = inStr.substring(2, inStr.length() );

			}else if(  inStr.startsWith("031") || inStr.startsWith("032") || inStr.startsWith("033")
					|| inStr.startsWith("041") || inStr.startsWith("042") || inStr.startsWith("043")
					|| inStr.startsWith("051") || inStr.startsWith("052") || inStr.startsWith("053") 
				    || inStr.startsWith("054") || inStr.startsWith("055") || inStr.startsWith("061") 
					|| inStr.startsWith("062") || inStr.startsWith("063") || inStr.startsWith("064") 
					|| inStr.startsWith("070") ) {

				tel1 = "0" + inStr.substring(0, 3);				
				tail = inStr.substring(3, inStr.length() );

			}else{

				return inStr;

			}

		}

		//-- 앞의 4자리-- //
		//
		// "070-215-9865" 같이 070을 제외한 번호가 7자일일때
		if( tail != null && tail.length() == 7 ) {

			tail = "0" + tail;

		// "070-2152-9865" 같이 070을 제외한 번호가 8자일일때
		}else if( tail != null && tail.length() == 8 ) {

			tail = tail;

		}else{

			return inStr;

		}

		tel2 = tail.substring(0, 4);

		//-- 뒤의 4자리 --//
		//
		tel3 = tail.substring(4, 8);

		//-- 결과 --//
		//
		return tel1 + tel2 + tel3;

	}

	//----------------------------------------------------------------------------------------------------//
		
	/**
	 * 구분자 없는 전화번호에 구분자 넣어 리턴 (지역번호 제외한 나머지 '*' 처리)
	 * @param inStr
	 * @param delim
	 * @param hiddenStr
	 * @return
	 */
	public String telHiddenFormat(String inStr, char delim, char hiddenStr) {
	
		StringBuffer sb  = new StringBuffer("");
		String       str = null;

		if (inStr == null) 
			return "-";
		
		str = inStr.trim();
	
		if (str.length() < 1) 
			return null;
		
		if (!chkNum(str)) 
			return str;
	
		//지역번호  2자리
		if (str.substring(0,2).equals("02")) {
			
			if (str.length() == 9) {
				sb.append(str.substring(0, 2));
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
			
			} else if (str.length() == 10)	{
				sb.append(str.substring(0, 2));
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				
			} else	{
				sb.append(str);
			}
			
		}
		
		//이통사 및 지역번호 3자리
		else {
		
			if (str.length() == 10)	{
				sb.append(str.substring(0, 3));
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
			
			} else if (str.length() == 11)	{
				sb.append(str.substring(0, 3));
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(delim);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				sb.append(hiddenStr);
				
			} else	{
				sb.append(hiddenStr);
			}
			
		}
		
		return sb.toString();
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 전화번호를 3등분 하여 1차원 문자 배열로 리턴 
	 * @param inStr : 대상 전화번호  
	 * @return
	 */
	public String[] telSplit(String inStr) {
		
		return telSplit(inStr, false); 	
		
	}
	
	/**
	 * 전화번호를 3등분 하여 1차원 문자 배열로 리턴 
	 * @param inStr : 대상 전화번호
	 * @param starEnable : "*010"으로 시작하는 번호 허용
	 * @return
	 */
	public String[] telSplit(String inStr, boolean starEnable) {

		//StringBuffer sb  = new StringBuffer("");
		String         str = null;
		
		String rCode[] = new String[3];
	
		if (inStr == null) 
			return null;
		
		//구분자 제거
		str = replaceAll(inStr.trim(), "-", "");
				
		if (str.length() < 1) 
			return null;

		// "*010"로 시작하는 번호 허용
		if( starEnable && inStr.startsWith("*010") ) {
			
			if (!chkNum(str.substring(1))) {
				
				rCode[0] = str;
				rCode[1] = "";
				rCode[2] = "";
				
				return rCode;
			}
			
		} else {
			
			if (!chkNum(str)) {
				
				rCode[0] = str;
				rCode[1] = "";
				rCode[2] = "";
				
				return rCode;
			}
			
		}
	
		//지역번호  2자리
		if (str.substring(0,2).equals("02")) {
		
			if (str.length() == 9) {
				rCode[0] = str.substring(0, 2);
				rCode[1] = str.substring(2, 5);
				rCode[2] = str.substring(5, 9);			
			
			} else if (str.length() == 10)	{
				rCode[0] = str.substring(0, 2);
				rCode[1] = str.substring(2, 6);
				rCode[2] = str.substring(6, 10);;
		
			} else	{
				rCode = null;
			}
			
		} 
		
		// *010으로 시작하는 번호
		else if( starEnable && inStr.startsWith("*010") ) {
			
			if (str.length() == 11) {
				rCode[0] = str.substring(0, 4);
				rCode[1] = str.substring(4, 7);
				rCode[2] = str.substring(7, 11);
			
			} else if (str.length() == 12)	{
				rCode[0] = str.substring(0, 4);
				rCode[1] = str.substring(4, 8);
				rCode[2] = str.substring(8, 12);			
				
			} else	{
				rCode = null;
			}
			
		}
		
		//이통사 및 지역번호 3자리
		else {
		
			if (str.length() == 10) {
				rCode[0] = str.substring(0, 3);
				rCode[1] = str.substring(3, 6);
				rCode[2] = str.substring(6, 10);
			
			} else if (str.length() == 11)	{
				rCode[0] = str.substring(0, 3);
				rCode[1] = str.substring(3, 7);
				rCode[2] = str.substring(7, 11);			
				
			} else	{
				rCode = null;
			}
			
		}
		
		return rCode;		
		
	}
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * NULL 및 공백문자를 체크하여 NULL 및 공백시 원하는 문자를 리턴 
	 * @param str
	 * @param pad
	 * @return
	 */
	public String nullSpcChk(String str, String pad) {
	
		String returnStr = null;
	
		if (str == null)
			returnStr = pad;
	
		else if (str.trim().length() < 1) 
			returnStr = pad;
	
		else 
			returnStr = str.trim();
		
		return returnStr;
		
	}
	
	//----------------------------------------------------------------------------------------------------//

	/**
	 * <br>
	 * HTML과 Trouble이 발생할 수 있는 특정 문자를 HTML 태그로 변경함<br>
	 * ', ", &, <, >, \n 을 \', quot, amp, lt, gt, br 태그로 변경함<br>
	 * DB등에서 값을 추출하여 HTML TextArea를 사용하지 않고<br>
	 * Table TD등에 직접 값을 보여주는 경우 사용함<br>
	 * <font color=red><b>[Deprecated] text2web(String)을 사용하세요!</b></font>
	 * @param str
	 * @return
	 */
	public String convertHtmlToText(String str) {
		return text2html(str);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * <br>
	 * HTML과 Trouble이 발생할 수 있는 특정 문자를 HTML 태그로 변경함<br>
	 * ', ", &, <, >, \n 을 \', quot, amp, lt, gt, br 태그로 변경함<br>
	 * DB등에서 값을 추출하여 HTML TextArea를 사용하지 않고<br>
	 * Table TD등에 직접 값을 보여주는 경우 사용함
	 * @param str
	 * @return
	 */
	public String text2web(String str) {
	
		String result = str;

		result = result.replaceAll("'",  "`");
		result = result.replaceAll("\"", "″");
		result = result.replaceAll("&",  "&amp;");
		result = result.replaceAll("<",  "&lt;");
		result = result.replaceAll(">",  "&gt;");
		result = result.replaceAll("\n", "<br>");
	
		return result.trim();
		
	}
	
	//----------------------------------------------------------------------------------------------------//

	/**
	 * <br>
	 * HTML과 Trouble이 발생할 수 있는 특정 문자를 Wap용 WML 태그로 변경함<br>
	 * &, <, >, 공백문자, \n 을 amp, lt, gt, nbsp, br 태그로 변경함<br>
	 * DB등에서 추출된 값을 Wap용 WML 페이지의 p 태그 안에서 보여주는 경우등에 사용함<br>
	 * <font color=red><b>[Deprecated] text2wap(String)을 사용하세요!</b></font>
	 * @param str
	 * @return
	 */
	public String convertHtmlToText2(String str) {
		return text2wap(str);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * <br>
	 * HTML과 Trouble이 발생할 수 있는 특정 문자를 Wap용 WML 태그로 변경함<br>
	 * &, <, >, 공백문자, \n 을 amp, lt, gt, nbsp, br 태그로 변경함<br>
	 * DB등에서 추출된 값을 Wap용 WML 페이지의 p 태그 안에서 보여주는 경우등에 사용함
	 * @param str
	 * @return
	 */
	public String text2wap(String str) {
		String result = str;

		result = result.replaceAll("&",  "&amp;");
		result = result.replaceAll("<",  "&lt;");
		result = result.replaceAll(">",  "&gt;");
		result = result.replaceAll(" ",  "&nbsp;");
		result = result.replaceAll("\n", "<br/>");
		
		return result.trim();
	}
	
	//----------------------------------------------------------------------------------------------------//

	/**
	 * DB Table에 Field값을 입력/수정할 때, ' 를 '' 로 변경함<br>
	 * <font color=red><b>[Deprecated] text2db(String)를 사용하세요!</b></font>
	 * @param str
	 * @return
	 */
	public String html2text(String str) {
		return text2db(str);
	}
	
	//----------------------------------------------------------------------------------------------------//	
	
	/**
	 * DB Table에 Field값을 입력/수정할 때, ' 를 '' 로 변경함
	 * @param str
	 * @return
	 */
	public String text2db(String str) {
	
		String rStr = null;
	
		if (str==null) 
			return null;
	
		rStr = replaceAll(str, "'", "''");
		
		return rStr;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 자바-스크립트에서 매개변수의 ', " 처리 
	 * @param str
	 * @return
	 */
	public String text2script(String str) {
	
		String rStr = null;
	
		if (str==null)
			return null;
		
		rStr = replaceAll(str,  "'",  "\\'");
		rStr = replaceAll(rStr, "\"", "&quot;");
	
		return rStr;
		
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * TextBox [value] Attribute 내용의 " 처리<br>
	 * <font color=red><b>[Deprecated] text2textbox(String)를 사용하세요!</b></font>
	 * @param str
	 * @return
	 */
	public String component2script(String str) {
		return text2textbox(str);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * TextBox [value] Attribute 내용의 " 처리
	 * @param str
	 * @return
	 */
	public String text2textbox(String str) {
	
		String rStr = null;
	
		if (str==null)
			return null;
		
		rStr = replaceAll(str, "\"", "&quot;");
	
		return rStr;
		
	}

	//----------------------------------------------------------------------------------------------------//

	/**
	 * TextArea 에서 br (대소문자 구분없이) 태그의 줄바꿈 처리<br>
	 * <font color=red><b>[Deprecated] tag2textarea(String)를 사용하세요!</b></font>
	 * @param str
	 * @return
	 */
	public String text2html(String str) {
		return tag2textarea(str);
	}

	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * TextArea 에서 br (대소문자 구분없이) 태그의 줄바꿈 처리
	 * @param str
	 * @return
	 */
	public String tag2textarea(String str) {
	
		String rStr = null;
	
		if (str==null) 
			return null;
		
		rStr = replaceAll(str,  "<br>", "\n");
		rStr = replaceAll(rStr, "<bR>", "\n");
		rStr = replaceAll(rStr, "<Br>", "\n");
		rStr = replaceAll(rStr, "<BR>", "\n");
		
		return rStr;
		
	}
	
	/**
	 * 문자열을 주어진 길이만 큼 잘라서 fixStr을 마지막에 추가해서 반환
	 * @param inputStr - 자를 문자열
	 * @param limit    - 자를 길이
	 * @param fixStr   - 문자열 마지막에 붙일 문자열
	 * @return
	 */
	public String cutStringLength(String inputStr, int limit, String fixStr) {
		
		if (inputStr == null)
            return "";

        if (limit <= 0)
            return inputStr;

        byte[] strbyte = null;

        strbyte = inputStr.getBytes();


        if (strbyte.length <= limit) {
            return inputStr;
        }

        char[] charArray = inputStr.toCharArray();

        int checkLimit = limit;
        for ( int i = 0 ; i < charArray.length ; i++ ) {
            if (charArray[i] < 256) {
                checkLimit -= 1;
            }
            else {
                checkLimit -= 2;
            }

            if (checkLimit <= 0) {
                break;
            }
        }

        //대상 문자열 마지막 자리가 2바이트의 중간일 경우 제거함
        byte[] newByte = new byte[limit + checkLimit];

        for ( int i = 0 ; i < newByte.length ; i++ ) {
            newByte[i] = strbyte[i];
        }

        if (fixStr == null) {
            return new String(newByte);
        }
        else {
            return new String(newByte) + fixStr;
        }
        
	}
	

	public static String nullToStr(String str){
		return nullToStr(str, "");
	}
	
	public static String nullToStr(String str, String def){
		String rtnvalue = str;
		if (str == null) {
			rtnvalue = def;
		} else if (str.trim().length() < 1) {
			rtnvalue = def;
		}
		
		//주소록 데이터때문에 씀
		if(rtnvalue.equals("00000000000")){
			rtnvalue = "";
		}
		return rtnvalue;
	}
	
	public static int equlasCount(String str, String ch) {
		int count = 0;
		if (str == null) {
			return count;
		} else if (str.trim().length() < 1) {
			return count;
		} else {
			for(int i = 0; i < str.length(); i++) {
				if ( (str.substring(i, i+1).compareTo(ch) == 0) ) {
					count++;
				}
			}
		}
		return count;
	}
	
	//olleh/src/common.properties property load
	public static String getProperty(String name) {
		
		String value = "";
        ResourceBundle bundle = null;
        
        try {
        	
            bundle = ResourceBundle.getBundle("common");
            value  = (String)bundle.getString(name);
            
		} catch (Exception e) {
			
			value = "";
			
        }
		
		return value;
	}
	
	
	//지정한 문자에 원하는 길이를 넣으면 앞자리 0으로 채워서 반환
	public static String getZeroPlus(String str1, int str_len){
		
		String rtnValue = "0000000000";
		rtnValue = rtnValue + str1;
		rtnValue = rtnValue.substring( rtnValue.length()-str_len, rtnValue.length() );
		
		return rtnValue;
	
	}

	public static String getNowDateTime(){
		
		String rtnValue = "";
		
		Calendar calendar	= new GregorianCalendar();
		Date date2			= new Date();
		
		String year		= new Integer(calendar.get(Calendar.YEAR)).toString();
		String month	= new Integer(calendar.get(Calendar.MONTH)+1).toString();//달은 0부터 시작
		String date		= new Integer(calendar.get(Calendar.DATE)).toString();
		String hour		= ""+date2.getHours();
		String minute	= ""+date2.getMinutes();
		String second	= ""+date2.getSeconds();
		
		month = getZeroPlus(month, 2);
		date = getZeroPlus(date, 2);
		hour = getZeroPlus(hour, 2);
		minute = getZeroPlus(minute, 2);
		second = getZeroPlus(second, 2);
		
		rtnValue = year+month+date+hour+minute+second;
		
		return rtnValue;
	}
	
	
	//모델 타입 반환 1(영상), 2(음성), 3(영상 유무선) 한 자리, 0:정보없음
	public static int getModelType(String MODEL_NAME){
		
		int type = 0;

		/*
		olleh단말 모델명 생성 rule은 다음과 같습니다.
		- MF: 제조사 코드 두 자리 (예:AT: 아프로텍 SM: 삼성전자 LN: 엘지노텔)
		- T : 1(영상), 2(음성), 3(영상 유무선) 한 자리
		- SN: 순서 번호 두 자리 (01 부터 순서대로 할당)
		“-“
		- Y: 제조년 마지막 한자리(예: 2008년일 경우 8)
		- MM : 출시 월 두자리 (예: 3월이면 03)
		- R: D/W (DCP/WiFi)
		 
		아프로텍 음성단말 		: AT201-803D  (O)
		LG 음성단말 			: LN201-805D  (O)
		삼성 음성단말 			: SS201-804DW (O)
		W10 					: RC201-807W
		LG 영상단말(무선포함) 	: LN301-806D
		LG 영상단말 			: LN101-806D
		삼성 영상단말(무선포함) : SS301-806DW
		삼성 영상단말 			: SS101-806DW
		*/
		
		//if(MODEL_NAME.equals("AT201-803D")){			//O
		if(MODEL_NAME.indexOf("AT201-803D") >= 0){		//O
			//아프로텍 AT201-803D
			//model_explan = "4.5mm(1.8형), 65k 컬러 LCD<br>64화음 벨소리, 착신전환 등 부가서비스";
			//model_image = "at201-803d.gif";
			type = 2;

		//}else if(MODEL_NAME.equals("LN201-805D")){		//O
		}else if(MODEL_NAME.indexOf("LN201-805D") >= 0){		//O
			//엘지노텔 LN201-805D
			//model_explan = "1.7GHz의 Clean Band 활용 음성전용 디지털폰<br>폰북 간편검색, 모닝콜/알람";
			//model_image = "LN201-805D.jpg";
			type = 2;
			
		//}else if(MODEL_NAME.equals("SS201-804DW")){		//O
		}else if(MODEL_NAME.indexOf("SS201-804DW") >= 0){		//O
			//삼성전자 SS201-804DW
			//model_explan = "LAN 1포트, WAN 1포트 지원, PSTN 포트 지원<br>PSTN과 VoIP Dual 모드 지원";
			//model_image = "SS201-804DW.jpg";
			type = 2;
		
		//}else if(MODEL_NAME.equals("RC201-807W")){		//O
		}else if(MODEL_NAME.indexOf("RC201-807W") >= 0){		//O
			//W10 : RC201-807W
			//model_explan = "라이브 폰 북, 음성 통화, SMS 메시지";
			//model_image = "W10.jpg";
			type = 2;
			
		//}else if(MODEL_NAME.equals("LN301-806D")){		//O
		}else if(MODEL_NAME.indexOf("LN301-806D") >= 0){		//O
			//LG 영상단말 : LN301-806D
			//model_explan = "영상통화(화면분할 및 대체영상)<br>CID, SMS 등 부가서비스";
			//model_image = "LN301-806D.jpg";
			type = 3;
			
		//}else if(MODEL_NAME.equals("LN101-806D")){		//O
		}else if(MODEL_NAME.indexOf("LN101-806D") >= 0){		//O
			//LG 영상단말 : LN101-806D
			//model_explan = "영상통화(화면분할 및 대체영상)<br>CID, SMS 등 부가서비스";
			//model_image = "LN101-806D.jpg";
			type = 1;
			
		//}else if(MODEL_NAME.equals("SS301-806DW")){		//O
		}else if(MODEL_NAME.indexOf("SS301-806DW") >= 0){		//O
			//삼성 영상단말 : SS301-806DW
			//model_explan = "멀티미디어 폰 북<br>통화 중 영상 Capture";
			//model_image = "SS301-806DW.jpg";
			type = 3;
			
		//}else if(MODEL_NAME.equals("SS101-806DW")){		//O
		}else if(MODEL_NAME.indexOf("SS101-806DW") >= 0){		//O
			//삼성 영상단말 : SS101-806DW
			//model_explan = "멀티미디어 폰 북<br>통화 중 영상 Capture";
			//model_image = "SS101-806DW.jpg";
			type = 1;
		
		}
		
		return type;
	
	}
	
	
	/**
	 * JSP에서 자바스크립트로 배열을 넘기는 함수
	 * @param strArrayName      : 생성될 자바스크립트 코드의 배열 이름
	 * @param alStrJspArrayList : ArrayList 
	 * @return                  : 1차원 자바배열을 자바스크립트 코드로 변환해서 반환한다.
	 */
	public String getArrayJsCode(String strArrayName, ArrayList alStrJspArrayList)
	{		
		
		if(alStrJspArrayList == null || strArrayName == null) 
			return null;		
		
		StringBuffer sbScArrayData = new StringBuffer("");
		
		sbScArrayData.append("var "+ strArrayName +  "= new Array();");	
			
		for(int i=0; i<alStrJspArrayList.size() ; i++)		
			sbScArrayData.append(strArrayName + "[" + i + "] = '" + alStrJspArrayList.get(i) +"';");				
		
		return sbScArrayData.toString();
		
	}
	
	
	/**
	 * JSP에서 자바스크립트로 배열을 넘기는 함수
	 * @param strArrayName      : 생성될 자바스크립트 코드의 배열 이름
	 * @param aStrJspArrayData  : 1차원 자바 배열 
	 * @return                  : 1차원 자바배열을 자바스크립트 코드로 변환해서 반환한다.
	 */
	public String getArrayJsCode(String strArrayName, String[] aStrJspArrayData)
	{		
		
		if(aStrJspArrayData == null || strArrayName == null) 
			return null;		
		
		StringBuffer sbScArrayData = new StringBuffer("");
		
		sbScArrayData.append("var "+ strArrayName +  "= new Array();");	
			
		for(int i=0; i<aStrJspArrayData.length; i++)		
			sbScArrayData.append(strArrayName + "[" + i + "] = '" + aStrJspArrayData[i] +"';");				
		
		return sbScArrayData.toString();
		
	}
	
	
	/**
	 * JSP에서 자바스크립트로 배열을 넘기는 함수
	 * @param strArrayName      : 생성될 자바스크립트 코드의 배열 이름
	 * @param aaStrJspArrayData : 2차원 자바 배열 
	 * @return                  : 2차원 자바배열을 자바스크립트 코드로 변환해서 반환한다.
	 */
	public String getArrayJsCode(String strArrayName, String[][] aaStrJspArrayData)
	{			
		if(aaStrJspArrayData == null || strArrayName == null) 
			return null;		
		
		StringBuffer sbScArrayData = new StringBuffer("");
		sbScArrayData.append("var "+ strArrayName +  "= new Array();");	
		
		for(int i=0; i<aaStrJspArrayData.length; i++)
		{
			//이차원 Array 생성
			sbScArrayData.append(strArrayName +"[" + i + "] = new Array(); ");
			
			for(int j=0; j<aaStrJspArrayData[i].length; j++)
			{
				sbScArrayData.append(strArrayName + "[" + i + "][" + j + "] = '" + aaStrJspArrayData[i][j]  +"';");
			}
		}
		
		return sbScArrayData.toString();
	}
	
	
	/**
	 * tiniDecode() : tini로 부터 읽어들인 데이타를 HTML형식에 맞게 디코딩한다.
	 * @param str
	 * @return
	 */
	public String tiniDecode(String str)
	{
		
		return tiniDecode(str, true);
		
	}
	
	
	/**
	 * tiniDecode(): tini로 부터 읽어들인 데이타를 HTML형식에 맞게 디코딩한다.
	 * @param str
	 * @param bUserTag : true이면 사용자 지정 태그까지 인코딩 한다.
	 * @return
	 */
	public String tiniDecode(String str, boolean bUserTag)
	{			
		
		if(str != null || str.length() > 0)
		{
			
			str = str.replace("&quot;", "\"");
			str = str.replace("&amp;", "&");
			str = str.replace("&amp", "&");
			str = str.replace("&lt;", "<");
			str = str.replace("&lt", "<");
			str = str.replace("&gt;", ">");
			str = str.replace("&gt", ">");
			str = str.replace("<br>", "\n");
			
			if( bUserTag )
			{
				
				str = str.replace("userCssStyleStart", "<style type='text/css'>");
				str = str.replace("userCssStyleEnd",   "</style>");
				
			}			
			
			return str;
		}
		else
		{
			
			return "";
			
		}
		
	}
	
	//----------- 2010/11/02 -----------------//
	//----------- 2010/11/02 -----------------//
	//----------- 2010/11/02 -----------------//
	//----------- 2010/11/02 -----------------//
	//----------- 2010/11/02 -----------------//
	//----------- Write      -----------------//
	
	
	public Object nullToObject(String str){
		return nullToStr(str, "");
	}
	
}
