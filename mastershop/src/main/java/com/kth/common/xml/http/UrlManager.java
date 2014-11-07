package com.kth.common.xml.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.cookie.CookiePolicy;




public class UrlManager 
{	
	URL url= null;
	URLConnection urlConnnection = null;	
	
	/**
	 * 생성자
	 * @param strUrl : 접속될 곳의 Url
	 * @throws Exception
	 */
	public UrlManager(String strUrl) throws Exception
	{        		
       	this.url = new URL(strUrl);
       	this.urlConnnection =  url.openConnection();    
	}	 
	
	public UrlManager() throws Exception
	{        		
    
	}	 
	
	
	/**
	 * 
	 * URL에 접속된 곳의 데이타를 가져온다.
	 * @return String(ex: XML은 XML데이타를 가져온다)
	 * @throws Exception
	 */
	public String getData() throws Exception
	{	  	   
		// 1. 연결된 URL의 InputStream데이타를 받아서, BufferedReader에 넣는다.
		StringBuffer sbData      = new StringBuffer();
		HttpURLConnection huConn = (HttpURLConnection)this.urlConnnection;
		BufferedReader in        = new BufferedReader(new InputStreamReader(huConn.getInputStream()));

		// 2 BufferedReader를 한줄씩 읽어 들이며, sbData에 넣는다.
		try
		{
			String lineText        = "";
			while((lineText = in.readLine()) != null)
			{
				if (lineText.trim().length() > 0)			
					sbData.append(lineText);			
			}

			in.close();
			huConn.disconnect();
		}
		catch (Exception e) 
		{            
			if(in     != null)   in.close();
			if(huConn != null)   huConn.disconnect();
			
		}
		finally 
		{
			if(in     != null)   in.close();
			if(huConn != null)   huConn.disconnect();
		}
		
		// 3. sbData를 String으로 변환하고 리턴한다.
		return sbData.toString();     	
	}
	
	public InputStream getInputStream() throws Exception
	{	
		String strXmlData = getData();
		return (InputStream)new ByteArrayInputStream(strXmlData.toString().getBytes());
	}
	
	
	
	/**
	 * 
	 * URL에 접속된 곳의 데이타를 가져온다.
	 * @return String(ex: XML은 XML데이타를 가져온다)
	 * @throws Exception
	 */
	public String getUrlData(String str_url, int second) throws Exception {
	
		String rtn_str		= "";
		HttpClient client	= null;
		GetMethod get		= null;
		PostMethod post		= null;
		
		try {
	
			client= new HttpClient();
			client.setTimeout(second);		//TimeOut : 3 Second 3000
			get = new GetMethod(str_url);
			get.setFollowRedirects(true);
			int iGetResultCode = client.executeMethod(get);
			
			rtn_str = get.getResponseBodyAsString();
			//rtn_str = new String(rtn_str.getBytes("8859_1"), "KSC5601");
			rtn_str = new String(rtn_str.getBytes("8859_1"), "KSC5601");
	
			//System.out.println("Return Str = "+rtn_str);
	
		} catch(Exception ex) {
			//System.out.println("RINGO URL Connection Exception : "+ex);
			rtn_str = "";
		} finally {
			get.releaseConnection();
	    }
		
		return rtn_str;
	}
	
	
	/**
	 * 
	 * URL에 접속된 곳의 데이타를 가져온다.
	 * @return String(ex: XML은 XML데이타를 가져온다)
	 * @throws Exception
	 */
	public String getUrlDataPhoneBook1(String str_url, int second, String callCtn, String brType, String ollehNo) throws Exception {
	
		String rtn_str		= "";
		HttpClient client	= null;
		PostMethod post		= null;
		
		
		System.out.println("getUrlDataPhoneBook1");
		System.out.println("str_url="+str_url);
		System.out.println("second="+second);
		System.out.println("callCtn="+callCtn);
		System.out.println("brType="+brType);
		System.out.println("ollehNo="+ollehNo);
		
		
		try {
	
			client= new HttpClient();
			client.setTimeout(second);		//TimeOut : 3 Second 3000
			//get = new GetMethod(str_url);
			post = new PostMethod(str_url);

		    //post.setRequestBody( new NameValuePair[] {	new NameValuePair("blogId", blogId) } );

			// 보낼 파라메터 정의
			NameValuePair[] parametersBody =  new NameValuePair[] {
			  new NameValuePair("CallCtn",	callCtn),
			  new NameValuePair("BrType", 	brType),
			  new NameValuePair("ollehNo", 	ollehNo)
			};

			post.setRequestBody(parametersBody);
    
			int httpResult = client.executeMethod(post);
			
			rtn_str = post.getResponseBodyAsString();

			//rtn_str = new String(rtn_str.getBytes("8859_1"), "KSC5601");
	
			
			if(rtn_str.length() > 1){
				rtn_str = rtn_str.trim();
			}
			System.out.println("getUrlDataPhoneBook1 Return Str = "+rtn_str);
	
		} catch(java.net.SocketTimeoutException ex) {
			
			System.out.println("SocketTimeException");
			
			rtn_str="<?xml version='1.0' encoding='euc-kr'?>";
			rtn_str=rtn_str+"<ABIMPORT><RESULT>";
			rtn_str=rtn_str+"<CODE>E0999</CODE>";
			rtn_str=rtn_str+"<MESSAGE>CONNECTION TIME OVER</MESSAGE>";
			rtn_str=rtn_str+"</RESULT></ABIMPORT>";
			
		} catch(Exception ex) {
			System.out.println("getUrlDataPhoneBook1 Exception : "+ex);

			rtn_str="<?xml version='1.0' encoding='euc-kr'?>";
			rtn_str=rtn_str+"<ABIMPORT><RESULT>";
			rtn_str=rtn_str+"<CODE>E0998</CODE>";
			rtn_str=rtn_str+"<MESSAGE>CONNECTION EXCEPTION</MESSAGE>";
			rtn_str=rtn_str+"</RESULT></ABIMPORT>";
			
		} finally {
			post.releaseConnection();
	    }
		
		return rtn_str;
	}
	
	

	/**
	 * 
	 * URL에 접속된 곳의 데이타를 가져온다.
	 * @return String(ex: XML은 XML데이타를 가져온다)
	 * @throws Exception
	 */
	public String getUrlDataPhoneBook2(
			String str_url, int second, String ssn, String ssn_type, String auth_key, String brType, String sessionkey, String callCtn, String ollehNo) throws Exception {
	
		String rtn_str		= "";
		HttpClient client	= null;
		PostMethod post		= null;
		
		try {
	
			System.out.println("getUrlDataPhoneBook2");
			System.out.println("Ssn="+ssn);
			System.out.println("SsnType="+ssn_type);
			System.out.println("AuthKey="+auth_key);
			System.out.println("BrType="+brType);
			//System.out.println("SessionKey="+sessionkey);
			System.out.println("SessKey="+sessionkey);
			System.out.println("CallCtn="+callCtn);
			System.out.println("ollehNo="+ollehNo);
			
			client= new HttpClient();
			client.setTimeout(second);		//TimeOut : 3 Second 3000
			//get = new GetMethod(str_url);
			post = new PostMethod(str_url);

		    //post.setRequestBody( new NameValuePair[] {	new NameValuePair("blogId", blogId) } );

			// 보낼 파라메터 정의
			NameValuePair[] parametersBody =  new NameValuePair[] {
			  new NameValuePair("Ssn",			ssn),
			  new NameValuePair("SsnType", 		ssn_type),
			  new NameValuePair("AuthKey", 		auth_key),
			  new NameValuePair("BrType", 		brType),
			  //new NameValuePair("SessionKey", 	sessionkey),
			  new NameValuePair("SessKey", 		sessionkey),
			  new NameValuePair("CallCtn", 		callCtn),
			  new NameValuePair("ollehNo", 		ollehNo),
			};

			post.setRequestBody(parametersBody);
    
			int httpResult = client.executeMethod(post);
			
			rtn_str = post.getResponseBodyAsString();

			//rtn_str = new String(rtn_str.getBytes("8859_1"), "KSC5601");
	
			if(rtn_str.length() > 1){
				rtn_str = rtn_str.trim();
			}
			
			System.out.println("getUrlDataPhoneBook2 Return Str ="+rtn_str);
	
			
		} catch(java.net.SocketTimeoutException ex) {
			System.out.println("SocketTimeException");
		} catch(Exception ex) {
			System.out.println("RINGO URL Connection Exception : "+ex);
			rtn_str = "";
		} finally {
			post.releaseConnection();
	    }
		
		return rtn_str;
	}
	
	
	/**
	 *  HashMap에 저장된 데이타 ( [Param Name, Param Value] )를 가지고 
	 *  Get방식의 URL에 사용되는 파라미터를 만든다.  
	 * 
	 * @param hmParams
	 * @return String( Ex : "?Param1=value1&Param2=value2&Param3=value3  )
	 */
    public static String getGetTypeParam(HashMap hmParams) 
    {
        if (hmParams == null)   return "";        

        StringBuffer sb = new StringBuffer();

        Iterator iterator = hmParams.keySet().iterator();
        
        
        Object key = null;
        sb.append("?");

        while(iterator.hasNext()) 
        {
            key=iterator.next();
            sb.append((String)key);
            sb.append("=");
            sb.append((String)hmParams.get(key));
            sb.append("&");
        }
        
        return sb.toString();       
    }
}
