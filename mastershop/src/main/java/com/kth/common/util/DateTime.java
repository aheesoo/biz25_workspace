
package com.kth.common.util;

import java.text.*;		     // SimpleDateFormat
import java.util.*;		     // Calendar, StringTokenizer
//import java.nio.*;		  // ByteBuffer
//import java.nio.channels.*; // ByteBuffer

public final class DateTime {
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 년도 1부터 시작 (dateGbn 값에 사용)
	 */
	public final String YEAR   =   "YEAR";
	/**
	 * 월 1월 부터 (dateGbn 값에 사용)
	 */
	public final String MONTH  =  "MONTH";
	/**
	 * 일 1일 부터 (dateGbn 값에 사용)
	 */
	public final String DAY    =    "DAY";
	/**
	 * 시간 00시부터 (dateGbn 값에 사용)
	 */
	public final String HOUR    =  "HOUR";
	/**
	 * 분 00분부터 (dateGbn 값에 사용)
	 */
	public final String MINUTE = "MINUTE";
	/**
	 * 초 00초부터 (dateGbn 값에 사용)
	 */
	public final String SECOND = "SECOND";
	/**
	 * 밀리초 000초부터 (dateGbn 값에 사용)
	 */
	public final String MILLISECOND = "MILLISECOND";
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 생성자
	 */
	public DateTime() {
		
	}
	
	//----------------------------------------------------------------------------------------------------//

	/**
	 * 'yyyy-MM-dd HH:mm:ss' 등의 패턴을 던지면 현재 날짜 값을 해당 패턴으로 적용해서 문자열로 리턴<br> 
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
	 * 입력되는 시간은 24시간제(0-23시 기준) (1월 1일 24:00시면 1월 2일 00:00시가됨 주의!)
	 * @param str
	 * @return
	 */
	public Date str2date(String str) {
		
		if (str == null) 
			return null;
		
		String strDate = str.trim();
		if (strDate.length() < 1) 
			return null;
		
		Calendar cal = Calendar.getInstance();
		
		if (strDate.length() >=4)  
			cal.set(Calendar.YEAR, Integer.parseInt(strDate.substring(0,4)));
		else			   
			cal.set(Calendar.YEAR, 0);
		
		if (strDate.length() >=6)  
			cal.set(Calendar.MONTH, Integer.parseInt(strDate.substring(4,6))-1);
		else			   
			cal.set(Calendar.MONTH, 0);
		
		if (strDate.length() >=8)  
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(strDate.substring(6,8)));
		else			   
			cal.set(Calendar.DAY_OF_MONTH, 1);
		
		if (strDate.length() >=10) 
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strDate.substring(8,10)));
		else			   
			cal.set(Calendar.HOUR_OF_DAY, 0);
		
		if (strDate.length() >=12) 
			cal.set(Calendar.MINUTE, Integer.parseInt(strDate.substring(10,12)));
		else			   
			cal.set(Calendar.MINUTE, 0);
		
		if (strDate.length() >=14) 
			cal.set(Calendar.SECOND, Integer.parseInt(strDate.substring(12,14)));
		else			   
			cal.set(Calendar.SECOND, 0);
		
		if (strDate.length() >=17) 
			cal.set(Calendar.MILLISECOND, Integer.parseInt(strDate.substring(14,17)));
		else			   
			cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
		
	} 
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 입력된 날짜값을 "yyyyMMddHHmmss"의 년월일시분초 형태로 리턴
	 * @param dt
	 * @return
	 */
	public String date2str(Date dt) {
		return date2str(dt, "yyyyMMddHHmmss");
	} 
	
	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * 입력된 날짜값을 패턴에 맞는 형태로 리턴
	 * @param dt
	 * @param pattern : "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss" 등
	 * @return
	 */
	public String date2str(Date dt, String pattern) {
		
		Calendar cal = Calendar.getInstance();
		String   ptn = null;
		
		if (dt == null) 
			return null;
		
		if (pattern == null) 
			return null;
		
		ptn = pattern.trim();
		if (ptn.length() < 1) 
			return null;
		
		SimpleDateFormat dateHeader = new SimpleDateFormat(ptn);
		
		return dateHeader.format(cal.getTime());
		
	} 
	
	//----------------------------------------------------------------------------------------------------//
	
  	/**
  	 * 입력된 Date를 년,월,일,시,분,초,밀리초의 구분값에 따라<br>
  	 * 입력된 value만큼 더하거나 빼어 그 결과를 Date 타입으로 리턴<br>
  	 * @param strDateTime1 : 기준시간
  	 * @param dateGbn      : 년,월,일,시..등으로 연산<br>
  	 *                        객체.YEAR/MONTH/DAY/HOUR/MINUTE/SECOND/MILLISECOND)
  	 * @param value
  	 * @return
  	 */
  	public Date computeDateTime(Date strDateTime1, String dateGbn, int value) {
  		
  		Calendar cal    = Calendar.getInstance();  		
  		String   tmpGbn = null;
  		
		if (strDateTime1 == null) 
			return null;
		
		if (dateGbn == null) 
			return null;
		
		tmpGbn = dateGbn.trim();
		if (tmpGbn.length() < 1) 
			return null;
		
  		cal.setTime(strDateTime1);
		
    	if (tmpGbn.equals(this.YEAR))			
    		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + value);
    	else if (tmpGbn.equals(this.MONTH)) 		
    		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + value);
    	else if (tmpGbn.equals(this.DAY))		
    		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + value);
    	else if (tmpGbn.equals(this.HOUR))	        
    		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + value);
    	else if (tmpGbn.equals(this.MINUTE))		
    		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + value);
    	else if (tmpGbn.equals(this.SECOND))		
    		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + value);
    	else if (tmpGbn.equals(this.MILLISECOND))	
    		cal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND) + value);
    	else					
    		return null;
    	
    	return cal.getTime();
    	
  	}
  	
	//----------------------------------------------------------------------------------------------------//
  	
	/**
	 * 입력된 String을 년,월,일,시,분,초,밀리초의 구분값에 따라<br>
	 * 입력된 value만큼 더하거나 빼어 그 결과를 "yyyyMMddHHmmss" 타입으로 리턴
  	 * @param strDateTime1 : 기준시간
  	 * @param dateGbn      : 년,월,일,시..등으로 연산<br>
  	 *                        객체.YEAR/MONTH/DAY/HOUR/MINUTE/SECOND/MILLISECOND)
	 * @param value
	 * @return
	 */
  	public String computeDateTime(String strDateTime1, String dateGbn, int value) {
  		return computeDateTime(strDateTime1, dateGbn, value, "yyyyMMddHHmmss");
  	}
  	
	//----------------------------------------------------------------------------------------------------//
  	
  	/**
  	 * 입력된 String을 년,월,일,시,분,초,밀리초의 구분값에 따라<br>
  	 * 입력된 value만큼 더하거나 빼어 그 결과를 패턴 타입으로 리턴
  	 * @param strDateTime1 : 기준시간
  	 * @param dateGbn      : 년,월,일,시..등으로 연산<br>
  	 *                        객체.YEAR/MONTH/DAY/HOUR/MINUTE/SECOND/MILLISECOND)
  	 * @param value
  	 * @param pattern
  	 * @return
  	 */
  	public String computeDateTime(String strDateTime1, String dateGbn, int value, String pattern) {
  		
  		if (strDateTime1 == null) 
  			return null;
  		
  		String strDateTime = strDateTime1.trim();
  		
  		int rLength = strDateTime.length();
  		if (rLength < 1) 
  			return null;
  		
  		if (pattern == null)      
  			return null;
  		
  		int pLength = pattern.length();
  		if (pLength < 1) 
  			return null;
		
  		SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
  		
  		return dateHeader.format(computeDateTime(str2date(strDateTime1), dateGbn, value)).substring(0,rLength>pLength?pLength:rLength);
  		
  	}
  	
	//----------------------------------------------------------------------------------------------------//
  	
	/**
	 * 날짜형식 변환
	 * @param str
	 * @param pattern
	 * @return
	 */
  	public String dateFormatString(String str, String pattern) {
	
  		String temp = "";
	
  		switch(str.length()) {
  		case 6:
  			temp = str.substring(0, 4) + pattern + str.substring(4, 6);
  			break;
	
  		case 8:
  			temp = str.substring(0, 4) + pattern + str.substring(4, 6) + pattern +
			str.substring(6, 8);
  			break;
	
  		default :
  			temp = str;
  			break;
  		
  		}
	
  		return temp;
  		
  	}

	//----------------------------------------------------------------------------------------------------//
  	
	/**
	 * 시간형식 변환
	 * @param str
	 * @param pattern
	 * @return
	 */
  	public String timeFormatString(String str, String pattern) {
 
  		String temp = "";

  		switch(str.length()) {
  		case 4:
  			temp = str.substring(0, 2) + pattern + str.substring(2, 4);
  			break;

  		case 6:
  			temp = str.substring(0, 2) + pattern + str.substring(2, 4) + pattern +
			str.substring(4, 6);
  			break;

  		default :
  			temp = str;
  			break;      
  		}

  		return temp;
  		
  	}
  	
  	//----------------------------------------------------------------------------------------------------//
  	
  	/**
  	 * getPreWeekDate : XX주 전 날짜 구하기 (ex) 1주전 ==> 1
  	 * @param week
  	 * @return
  	 */
	public String getPreWeekDate(int week) {
		
		Calendar cal = new GregorianCalendar();
		cal.add ( cal.DATE, -7 * week );

		String yyyy = ""+ (cal.get( cal.YEAR ));
		String mm	= ""+ (cal.get( cal.MONTH ) + 1) ;
		String dd	= ""+ (cal.get( cal.DATE ));

		mm = mm.length() == 1 ? "0"+mm : mm;
		dd = dd.length() == 1 ? "0"+dd : dd;

		return yyyy+mm+dd;

    }

  	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * getWeekOfDate()
	 * 해당 날짜가 몇째주 인지 구하기 (ex) 1주전 ==> 1
	 * @param yyyymmdd
	 * @return
	 */
	public int getWeekOfDate(String yyyymmdd) {

        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm   = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
        int dd   = Integer.parseInt(yyyymmdd.substring(6, 8));

        Calendar calendar = new GregorianCalendar();
        calendar.set(yyyy, mm, dd);

		return calendar.get(Calendar.WEEK_OF_MONTH);

    }

  	//----------------------------------------------------------------------------------------------------//
	
	/**
	 * getWeekChangeToKor()
	 * 몇째주인지(첫째주, 두째주..) 한글로 변환 (ex) 1주전 ==> 1)
	 * @param week
	 * @return
	 */
	public String getWeekChangeToKor(int week) {

		String rtnStr = "";

		switch(week) {
			case 1:
				rtnStr = "첫째주";
				break;
			case 2:
				rtnStr = "둘째주";
				break;
			case 3:
				rtnStr = "셋째주";
				break;
			case 4:
				rtnStr = "넷째주";
				break;
			case 5:
				rtnStr = "다섯째주";
				break;
			case 6:
				rtnStr = "여섯째주";
				break;
			default:
				rtnStr = "";
				break;
		}
		return rtnStr;
    }

  	//----------------------------------------------------------------------------------------------------//

	/**
	 * getSetForDOW() : 요일 반환
	 * @param yyyymmdd
	 * @return
	 */
    public int getSetForDOW(String yyyymmdd) {
    	
        int yyyy = Integer.parseInt(yyyymmdd.substring(0, 4));
        int mm   = Integer.parseInt(yyyymmdd.substring(4, 6)) - 1;
        int dd   = Integer.parseInt(yyyymmdd.substring(6, 8));

        Calendar calendar = new GregorianCalendar();
        calendar.set(yyyy, mm, dd);

        return calendar.get(Calendar.DAY_OF_WEEK);
        
    }
    
  	//----------------------------------------------------------------------------------------------------//
    
    /**
     * getSetForDOW() : 요일 반환
     * @param yyyy
     * @return
     */
    public int getSetForDOW(int yyyy) {

        Calendar calendar = new GregorianCalendar();
        calendar.set(yyyy, 0, 1);

        return calendar.get(Calendar.DAY_OF_WEEK);

    }
	
  	//----------------------------------------------------------------------------------------------------//

    /**
     * getSetForYYYYMMDD_WEEK()
     * 요청한 일자가 속한 주의 일요일~토요일까지의 yyyymmdd 값을 배열로 반환
     * @param yyyymmdd
     * @return
     */
	public String[] getSetForYYYYMMDD_WEEK(String yyyymmdd) {
            	
    	String[] week = new String[7];
    	
    	if(yyyymmdd != null && yyyymmdd.trim().length() == 8) {
    	
    		int _year  = Integer.parseInt(yyyymmdd.trim().substring(0, 4).trim());
    		int _month = Integer.parseInt(yyyymmdd.trim().substring(4, 6).trim()) - 1;
    		int _day   = Integer.parseInt(yyyymmdd.trim().substring(6, 8).trim()) - getSetForDOW(yyyymmdd);
    		
        	Calendar StartDate = Calendar.getInstance();
            StartDate.set(Calendar.YEAR,  _year);
            StartDate.set(Calendar.MONTH, _month);
            StartDate.set(Calendar.DATE,  _day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            
        	for(int i=0; i<7; i++) {
        		
        		//java.util.Date startday = StartDate.getTime();
        		
        		StartDate.add(Calendar.DATE, 1);

        		week[i] = sdf.format(StartDate.getTime()).trim();
        		
        	}   
        	
    	}   
    	
    	return week;
    	
    }

	//----------------------------------------------------------------------------------------------------//
  	
	/**
	 * Now클래스의 인스턴스를 얻는다.
	 */
  	public Now Now() {
  		
  		Now now = new Now();
  		
  		return now;
  		
	}
  	
	//----------------------------------------------------------------------------------------------------//
  	
  	/**
  	 * 현재 시스템 시간을 돌려준다.
  	 */
  	public class Now {
  		
  		//Calendar cal = null;
  		
  		public Now() {
  			//'yyyy-MM-dd HH:mm:ss'
  			//cal = Calendar.getInstance();
  		}
  		
  		//----------------------------------------------------------------------------------------------------//
  		
  		/**
  		 * 현재 시스템 시간을 "yyyyMMddHHmmss" 패턴으로 리턴
  		 * @return
  		 */
  		public String getDateTimeStr() 	{
	  		
  			Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("yyyyMMddHHmmss");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
  		
  		//----------------------------------------------------------------------------------------------------//
	  	
  		/**
  		 * 현재 시스템 시간을 입력 패턴에 맞게 리턴
  		 * @param pattern
  		 * @return
  		 */
	  	public String getDateTimeStr(String pattern) {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
        		
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 시간을 리턴
	  	 * @return
	  	 */
	  	public Date getDateTime() {
	  		
	  		Calendar cal = Calendar.getInstance();
        		
	  		return cal.getTime();
	  		
	  	}
  		
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 년도를 리턴
	  	 * @return
	  	 */
  		public String getNowYearStr() {
	  		
  			Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("yyyy");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
  		//----------------------------------------------------------------------------------------------------//
  		
  		/**
  		 * 현재 월을 리턴
  		 * @return
  		 */
	  	public String getNowMonthStr()	{
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("MM");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 일을 리턴
	  	 * @return
	  	 */
	  	public String getNowDayStr() {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("dd");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 시간을 리턴
	  	 * @return
	  	 */
	  	public String getNowHourStr() {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("HH");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 분을 리턴
	  	 * @return
	  	 */
	  	public String getNowMinuteStr() {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("mm");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 초를 리턴
	  	 * @return
	  	 */
	  	public String getNowSecondStr() {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("ss");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
		//----------------------------------------------------------------------------------------------------//
	  	
	  	/**
	  	 * 현재 밀리초를 리턴
	  	 * @return
	  	 */
	  	public String getNowMilliSecondStr() {
	  		
	  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat("SSS");
        	
        	return dateHeader.format(cal.getTime());
        	
	  	}
	  	
  	}  	

	//----------------------------------------------------------------------------------------------------//
  	
  	/*
  	public String getDateTime(String pattern)
  	{
  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
        	//dateHeader.applyPattern (pattern);
        	return dateHeader.format(cal.getTime()); // 스트링버퍼?
  	}
  	
  	//현재 날짜값얻기, 'yyyy-MM-dd HH:mm:ss'
  	public String getnow(String pattern)
  	{
  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
        	//dateHeader.applyPattern (pattern);
        	return dateHeader.format(cal.getTime()); // 스트링버퍼?
  	}
  	
  	//현재 날짜값얻기, 'yyyy-MM-dd HH:mm:ss'
  	public String getDateTime(String pattern)
  	{
  		Calendar cal = Calendar.getInstance();
        	SimpleDateFormat dateHeader = new SimpleDateFormat(pattern);
        	//dateHeader.applyPattern (pattern);
        	return dateHeader.format(cal.getTime()); // 스트링버퍼?
  	}
  	
  	//입력날짜 2개의 차이를 dateGbn으로 계산하여 날짜수를 리턴합니다.
  	//dateTime1 - dateTime2 의 결과
  	public int computeDateTime(Date dateTime1, Date dateTime2, String dateGbn)
  	{
  		Calendar cal1 = Calendar.getInstance();
  		Calendar cal2 = Calendar.getInstance();
  		long Gbn = 0;
  		if (dateTime1 == null) return 0;
  		if (dateTime2 == null) return 0;
  		if (dateGbn == null) return 0;
  		String tmpGbn = dateGbn.trim();
  		if (tmpGbn.length() < 1) return 0;
  		
  		cal1.setTime(dateTime1);
  		cal2.setTime(dateTime2);
  		long cha = cal1.getTimeInMillis() - cal2.getTimeInMillis();
  		
  		//24L*60L*60L*1000L
  		
  		
  		int sec  = 1000;
  		int min  = 60000;
  		int hour = 3600000;
  		int day  = 86400000;
  		int month  = 86400000;
  		int year  = 86400000;
  		
  		if (tmpGbn.equals(this.YEAR))			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + value);
        	else if (tmpGbn.equals(this.MONTH)) 		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + value);
        	else if (tmpGbn.equals(this.DAY))		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + value);
        	else if (tmpGbn.equals(this.HOUR))	        cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + value);
        	else if (tmpGbn.equals(this.MINUTE))		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + value);
        	else if (tmpGbn.equals(this.SECOND))		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + value);
        	else if (tmpGbn.equals(this.MILLISECOND))	cal.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND) + value);
        	else					return null;
        	
        	
		return cal1.getTimeInMillis() - cal2.getTimeInMillis();
  	}
  	*/
  	
	//----------------------------------------------------------------------------------------------------//
  	
  	public boolean isValidDate(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (date == null)
			return false;
		String format = null;
		try {
			format = sdf.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.equals(format);
	}
  	
  	public String getDateYear(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (dateStr == null || dateStr.trim().length() == 0)
			return "";
		String format = "";
		try {
			format = sdf.format(sdf.parse(dateStr)).substring(0, 4);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format;
	}
  	
  	public String getDateMonth(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (dateStr == null || dateStr.trim().length() == 0)
			return "";
		String format = "";
		try {
			format = sdf.format(sdf.parse(dateStr)).substring(4, 6);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format;
	}
  	
  	public String getDateDay(String dateStr, String pattern) {
  		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
  		if (dateStr == null || dateStr.trim().length() == 0)
  			return "";
  		String format = "";
  		try {
  			format = sdf.format(sdf.parse(dateStr)).substring(6, 8);
  		} catch (ParseException e) {
  			e.printStackTrace();
  		}
  		return format;
  	}
  	
  	/**
  	 * milliSeconds 동안 딜레이를 시킨다.
  	 * @param milliSeconds 1/1000 초
  	 */
  	public void delayTime(int milliSeconds) {
  		long saveTime = System.currentTimeMillis();
  		long currTime = 0;
  		while ( currTime - saveTime < milliSeconds) {
  			currTime = System.currentTimeMillis();
  		}
  	}
  	
  	
	/**
  	 * 해당 년/월의 마지막 날짜를 구한다. 
  	 * @param year : 년
  	 * @param month : 월
  	 * @return
  	 */
  	public int getLastDay(int year, int month)
  	{
	  	
	  	GregorianCalendar cld = new GregorianCalendar ( year, month - 1, 1 );	  	
	  	return cld.getActualMaximum ( Calendar.DAY_OF_MONTH );	
	  	
  	}
  	
}
