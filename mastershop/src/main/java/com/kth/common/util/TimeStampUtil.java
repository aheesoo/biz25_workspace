package com.kth.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeStampUtil {
	
	public String longToStr (long timeMillis) {
		
		return longToStr (timeMillis, "yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param timeMillis
	 * @param dateFormat "yyyy-MM-dd HH:mm:ss:SS"
	 * @return
	 */
	public String longToStr (long timeMillis, String dateFormat) {
		
		String date = "";
		
		if( timeMillis > 0L ) {			
			
			try {
				
				Timestamp timestamp = new Timestamp(timeMillis);
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				date = format.format(timestamp);
				
			}catch(Exception ex) {
				
				System.err.println("TimeStampUtil.longToStr() : " + ex);
				
			}
			
		}
		
		return date;
		
	}
	
	
	public long strToLong(String date) {
		
		return strToLong(date, "yyyyMMddHHmm");		
	}
	
	public long strToLong(String date, String dateFormat) {
		
		if( date != null && date.length() > 0 ) {
			
			date = date.replaceAll("\\.", "");
			date = date.replaceAll("-", "");			
			
		}
		
		if( dateFormat != null && dateFormat.length() > 0 ) {
			
			dateFormat = dateFormat.replaceAll("\\.", "");
			dateFormat = dateFormat.replaceAll("-", "");
			
		}
		
		// 시간 변경
		long timeMillis = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat); 
		try {
			
			timeMillis = simpleDateFormat.parse(date).getTime();			
			
		}catch(Exception ex) {
			
			System.err.println("TimeStampUtil.strToLong() : " + ex);
			
		}
		
		return timeMillis;
		
	}	
	
}
