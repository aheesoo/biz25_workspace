package com.kth.common.util;

public final class TinyFilter {
	
	
	/**
	 * tiny에 사용되는 작은 따옴표, 큰따옴표를 특수문자 처리한다.
	 * replaceQm() :  작은따옴표, 큰따옴표를 특수문자 따옴표로 변환
	 * @param str
	 * @return
	 */
	public String replaceQm(String str) 
	{
		
		if( str != null && str.length() > 0 )
		{
			
			char[] ch = str.toCharArray();
			
			int sCount = 1;
			int mCount = 1;
			for(int i = 0; i < ch.length; i++)
			{
				if(ch[i] == '\'')
				{	
					
					//홀수 일때(앞에 작은 따옴표)
					if( (sCount % 2) == 1 )					
						ch[i] = '‘';
					
					//짝수 일때 (뒤에 작은 따옴표)
					else
						ch[i] = '’';
					
					sCount++;
					
				}
				
				else if(ch[i] == '\"')
				{	
					
					//홀수 일때(앞에 큰 따옴표)
					if( (mCount % 2) == 1 )					
						ch[i] = '“';
					
					//짝수 일때 (뒤에 큰따옴표)
					else
						ch[i] = '”';
					
					mCount++;
					
				}
				
			}
			
			String strResult = new String(ch);			
			
			return strResult;
			
		}
		else
			return "";
		
	}	
	
	

	/**
	 * tinyFilter() : tiny 필터
	 * @param str
	 * @param bSpace
	 * @return
	 */
	public String tinyFilterEncoding(String str, boolean allowSpace) 
	{		
		
		str = replaceQm(str);
		// 특수문자 '；' 로 변경한다
		str = str.replaceAll(";","；");
		// 특수문자 '＃' 로 변경한다
		str = str.replaceAll("#","＃");
		// 특수문자 '――' 로 변경한다
		str = str.replaceAll("--","――");
		
		if(!allowSpace)
			str = str.replaceAll(" ","");

		return (str);
		
	}	
	
	
	/**
	 * tinyFilterDecode(): 필터 디코드
	 * @param str
	 * @return
	 */
	public String tinyFilterDecode(String str) 
	{
		   
		str = str.replaceAll("“", "\"");
		str = str.replaceAll("”", "\"");
		str = str.replaceAll("‘", "\'");
		str = str.replaceAll("’", "\'");
		str = str.replaceAll("；", ";");
		str = str.replaceAll("＃", "#");
		str = str.replaceAll("――", "--");				

		return (str);
		
	}	
	

	/**
	 * sqlFilter() : 기존소스를 변경하지 않기 위하여 같은 이름으로 위임시킨다.
	 * @param str
	 * @return
	 */
	public String sqlFilter(String str) 
	{
		
		return tinyFilterEncoding(str, false); 
		
	}
 
	
	/**
	 * sqlFilter() : 기존소스를 변경하지 않기 위하여 같은 이름으로 위임시킨다.
	 * @param str
	 * @return
	 */
	public String sqlFilter(String str, boolean allowSpace) 
	{
		
		return tinyFilterEncoding(str, allowSpace);
		
	}

	
}