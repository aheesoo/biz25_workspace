package com.includesys.sm.util;

import java.util.ArrayList;
import java.util.List;

public class StrUtils 
{

	/**
	 * 숫자형태의 전화번호를 구분자 형태의 전화번호 반환
	 * @param phoneNo 전화번호
	 * @param gubun 구분자
	 * @return
	 */
	public static String formatPhoneNo(String phoneNo, String gubun) {
		
		String retStr = "";
		try{
			if (phoneNo == null || phoneNo.equals("")){	return ""; }
	
			String phoneNo1="";
			String phoneNo2="";
			String phoneNo3="";

			phoneNo = Long.parseLong(phoneNo) == 0 ? "0" : "0"+Long.parseLong(phoneNo);
			int regLength = (phoneNo.startsWith("02"))? 2:3;

			phoneNo1 = phoneNo.substring(0, regLength);
			phoneNo2 = "" + phoneNo.substring(regLength, phoneNo.length()-4);
			phoneNo3 = phoneNo.substring(phoneNo.length()-4, phoneNo.length());

			retStr = phoneNo1+gubun+phoneNo2+gubun+phoneNo3;
			
		}catch(Exception e){
			return phoneNo;
		}
		return retStr;
	}	
	
	/**
	 * \r\n 로 저장된 개행처리를 <br/>로 변경, 스페이스바를 &nbsp;로 변경.
	 * @param \r\n
	 * @return <br/>
	 */
	public static String replaceHTML_Format(String str) {
		if(str != null){
			str = str.replaceAll("\r\n", "<br/>");
			str = str.replaceAll(" ", "&nbsp;");
		}else{
			str = "";
		}
		return str;
	}
}
