package com.kth.common.xml;

import java.util.HashMap;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.kth.common.xml.http.UrlManager;

public class SaxManager 
{		
	/**
	 * 
	 * @param strUrl          : 접속한 URL
	 * @param strRootNodeName : 데이타를 가져올 Node들.(바로 밑에 자식들의 값만 가져온다.) 
	 * @return                : 해당 strRootNodeName의 값들을 HashMap[]로 반환한다.
	 * @throws Exception
	 */
	public HashMap<String,String>[] getHashMapData(String strUrl, String strRootNodeName) throws Exception	
	{			
		SaxHashMapHandler saxHashMapHandler = new SaxHashMapHandler(strRootNodeName);
		UrlManager urlManager = new UrlManager(strUrl);
		
		// Create a builder factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        
        // Create the builder and parse the file
        factory.newSAXParser().parse(urlManager.getInputStream(), saxHashMapHandler);
        
        // saxHashMapHandler에서 HashMap[]데이터를 가져온다.
        return saxHashMapHandler.getHashMapData();					
	}
}
