
package com.kth.common.network;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.Reader;
import java.io.InputStreamReader;

public final class HttpUrlCon {
	
	//-- Global Fields --//
	//

	//
	//-- Global Fields --//
	
	
	/**
	 * 구성자
	 */
	public HttpUrlCon() {
		
	}
	

	
	public String read(String rURL) {
		
		String result = "";
		
		try {
		
			URL _url = new URL(rURL);
			HttpURLConnection uc = (HttpURLConnection) _url.openConnection();
			
			/*
			int code = uc.getResponseCode();

			String response = uc.getResponseMessage();
			
			//System.out.print("code : " + code + ", response : " + response);

			for(int j=1; ; j++) {
				
				String header = uc.getHeaderField(j);
				String key    = uc.getHeaderFieldKey(j);
				
				if(header == null || key == null)
					break;
				
				//System.out.println(uc.getHeaderFieldKey(j) + " : " + header);
				
			}
			*/
			
			InputStream in = new BufferedInputStream(uc.getInputStream());
			
			Reader r = new InputStreamReader(in);
			
			int c;
			while( (c=r.read()) != -1 ) {
				
				result += Character.toString((char)c);
				
				//System.out.print((char)c);
				
			}
			
		} catch(MalformedURLException ex) {
			
			ex.printStackTrace();
		
		} catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		return result;
		
	}
	
}
