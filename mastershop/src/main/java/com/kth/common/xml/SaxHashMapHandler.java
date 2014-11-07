package com.kth.common.xml;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Sax를 실행하면서, XML데이타를 HashMap[]의 형태로 반환한다.
 * parse(SaxHashMapHandler)를 실행했을때,  SaxHashMapHandler클레스의 함수가 콜백되는 순서다음과 같다.
 * startDocument() -> startElement() -> characters() -> endElement() -> endDocument()
 * @author Administrator
 */
public class SaxHashMapHandler extends DefaultHandler
{
	//*** private 변수 **** Start
	//
	private ArrayList<HashMap<String,String>> arrayList    = null;
	private HashMap<String,String>            tempHm       = null;
	private String                            strNodeName  = "";   // strNodeName의 모든 자식 Node들은 HashMap에 들어간다.(바로 아래있는 자식들만 들어간다)
	private String                            strTempData  = "";
	private StringBuffer                      stringBuffer = null;
	//
	//*** private 변수 **** End
	
	
	//*** 생성자 **** Start
	//	
	public SaxHashMapHandler()	
	{	
		stringBuffer = new StringBuffer();
		arrayList = new ArrayList<HashMap<String,String>>(0);	
	}
	
	/**	 
	 * @param strStartNodeName : 데이타를 가져올 Node (단 바로 아래 자식의 데이타만 가져온다)
	 */
	public SaxHashMapHandler(String strStartNodeName)
	{			
		stringBuffer = new StringBuffer();
		arrayList = new ArrayList<HashMap<String,String>>(0);	
		this.strNodeName =  strStartNodeName;
	}
	//
	//*** 생성자 **** End

	
	//*** 변수 Get, Set **** Start
	//	
	public void   setNodeName(String strNodeName)	{		if(strNodeName!=null) this.strNodeName = strNodeName; 	}	
	public String setNodeName()	                    {		return this.strNodeName;	        }
	//	
	//*** 변수 Get, Set **** End

	
	
	
	//*** XML Parse에서 실행되는 메소드  **** Start
	//
	
	/**
	 *  문서 파싱 시작
	 */	
	public void startDocument()	throws SAXException
	{ 
		
	}	
	
	
	/**
	 *  태그를 만나면 호출된다.
	 *  qName에 TagName이 들어온다.
	 */
	public void startElement(String namespaceURI, String localName,	String qName, Attributes attributes) throws SAXException
	{			
		// 지정한  strNodeName이 localName과 일치하면(Node읽기 시작), tempHm에 해쉬를 생성한다.
		if(qName.equals(strNodeName))		
			tempHm    = new HashMap<String,String>();				
	}	
	
	/**
	 * 태그 안의 데이타(String)을 만나면 호출된다.
	 */
	public void characters(char buf[], int start, int length)	throws SAXException
	{
		stringBuffer.setLength(0);
		// 태그 안의 데이타를 저장한다.(tempHm가 생성돼었을때)
		if(tempHm != null)
		{
			strTempData = (stringBuffer.append(buf, start, length)).toString();
		}			
	}
	

	/**
	 *  태그의 끝부분(</Tag>)를 만나면 호출된다.
	 *  qName에 TagName이 들어온다.
	 */
	public void endElement(String namespaceURI,	String localName, String qName)	throws SAXException	
	{
		// 지정한  strNodeName이 localName과 일치하면(Node읽기 중지), ArrayList에 HashMap을 추가하고 HashMap을 초기화한다.
		if(qName.equals(strNodeName))
		{
			arrayList.add(tempHm);
			tempHm = null;
		}		
		
		// tempHm에 해쉬가 생성돼어 있으면, tempHm에 데이타를 집어 넣는다.
		else if(tempHm != null)
		{
			tempHm.put(qName, strTempData);			
		}
		
		
		strTempData = "";
	}
	
	
	/**
	 *  문서 파싱 종료
	 */	
	public void endDocument() throws SAXException
	{	
	}
	
	//
	//*** XML Parse에서 실행되는 메소드  **** End
	
		
	/**
	 * 데이타가 들어있는 HashMap[]을 리턴한다.
	 * @return  지정한 Node의 바로 밑에 있는 자식들의 값. (HashMap[])
	 */
	public HashMap<String,String>[]  getHashMapData()
	{		
		 // 3 리스트에 들어 있는 객체들을 HashMap으로 캐스팅하여, HashMap[]에 넣는다.    
        HashMap<String,String>[] aHmData = new HashMap[arrayList.size()];
        
        for(int i=0; i < arrayList.size(); i++) 
        {
        	aHmData[i] = (HashMap<String,String>)arrayList.get(i);
        }

        return aHmData;
		
	}

		

	//*** Error Handler(등록해 주면 ,Error가 발생했을때 호출된다)  **** Start
	//
	public void warning(SAXParseException e)	throws SAXException
	{
		System.out.println("[Warning] line" + e.getLineNumber() + ": " + e.getMessage());
	}
	
	public void error(SAXParseException e)	throws SAXException
	{
		System.out.println("[Error] line" + e.getLineNumber() + ": " + e.getMessage());
	}
	
	public void fatalError(SAXParseException e)	throws SAXException
	{
		System.out.println("[Fatal] line" + e.getLineNumber() + ": " + e.getMessage());
	}
	//
	//*** Error Handler(등록해 주면 ,Error가 발생했을때 호출된다)  **** End
}