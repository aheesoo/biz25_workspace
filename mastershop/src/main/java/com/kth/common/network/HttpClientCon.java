
package com.kth.common.network;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.Reader;
import java.io.InputStreamReader;

/*
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

*/

public final class HttpClientCon {
	
	//-- Global Fields --//
	//

	//
	//-- Global Fields --//
	
	
	/**
	 * 구성자
	 */
	public HttpClientCon() {
		
	}
	
	
	/**
	 * read() - 원격지의 파일을 다운로드하고 그 이름을 반환
	 * @param fileName - 원격지 파일 이름
	 * @return
	 * @throws Exception
	 */
	public String read(String fileName) throws Exception {
		
		return read(fileName, 3000); 
		
	}
	
	
	/**
	 * read() - 원격지의 파일을 다운로드하고 그 이름을 반환
	 * @param fileName - 원격지 파일 이름
	 * @param rTimeOut - 타임아웃 대기시간 (ex : 3000 - 3초)
	 * @return - 저장된 파일 이름
	 * @throws Exception
	 */
	public String read(String fileName, int rTimeOut) throws Exception {
		
		String result = "";
		
		// URL 경로의 파일 가져오기
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setSoTimeout(rTimeOut); //5초
		client.getHttpConnectionManager().getParams().setConnectionTimeout(rTimeOut); //5초

		HttpMethod method = new GetMethod(new URI(fileName, false).toString());
		method.setFollowRedirects(true);

		try {
			
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {

				System.out.println(">> Method failed:\n" + method.getStatusLine());
				
			} else {
				
				result = method.getResponseBodyAsString();

				//System.out.println(">> Result Msg : [" + result + "]");
				
			}			
		
		} catch (HttpException ex) {
			
			System.out.println(">> Fatal protocol violation :\n" + ex.getMessage());				

			throw ex;
			
		} catch (java.io.IOException ex) {
			
			System.out.println(">> Fatal transport error:\n" + ex.getMessage());
			
			throw ex;

		} catch (Exception ex) {
			
			System.out.println(">> UnKnown error:\n" + ex.getMessage());
			
			throw ex;
			
		} finally {
			
			method.releaseConnection();
			
		}

		return result;
		
	}
	
}
