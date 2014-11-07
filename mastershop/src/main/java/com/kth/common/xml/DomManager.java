package com.kth.common.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kth.common.xml.http.UrlManager;

/**
 * @Class XmlProcessor
 * @Description XML 문서를 HashMap에 담아주는 처리를 해준다.
 * 20051115 처음 작성 [최규남(9namy@netville.com)]
 */

public class DomManager 
{ 
    /**
     * 생성자    
     */
    public DomManager()    {    }
    
       
    /**
     * 
     * strUrl(URL)의 XML데이타를 읽어들여, Document로 만든다.
     * 
     * @param strUrl : URL주소
     * @return       : Document(Dom에 쓰임)
     * @throws Exception
     */
    public Document getDocument(String strUrl) throws Exception
    {
    	UrlManager urlManager = new UrlManager(strUrl);
    	String     strXML     = urlManager.getData();
    	
    	Document doc = null;
    	    	
    	if(strXML != null)
    	{
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();

    		doc = db.parse((InputStream)new ByteArrayInputStream(strXML.toString().getBytes()));
    	}
    	
    	return doc;              
    }  


    /**
     * doc에서 strRootNode일치하는 모든 Node의 데이타를, HashMap으로 반환한다.
     * ex) Dom의 내용이
     *  
     *     "<ZIPCODE> <1> hi </1>  <2> hihi </2>  <3> hihihi </3></ZIPCODE>" 
     * 	   <ZIPCODE> <1> ww </1>  <2> wwww </2>  <3> wwwwww </3></ZIPCODE>
     * 
     * 	   일때, HashMap[]의 값은
     * 
     *     HashMap[0].get("1") : hi,  HashMap[0].get("2") : hihi, HashMap[0].get("3") : hihihi
     *     HashMap[1].get("1") : ww,  HashMap[1].get("2") : wwww, HashMap[1].get("3") : wwwwww
     *     
     *     이다
     * 
     * @param nodeName 데이타를 가져올 Node 
     * @return 엔티티목록
     */
    public HashMap<String,String>[] getHashMapData(Document doc, String strRootNode)
    {
    	if(strRootNode == null || "".equals(strRootNode) || doc == null)    return null;
    	 
    	// 1 doc에서 strRootNode와 일치하는 모든 node를 구한다.
        ArrayList<HashMap<String,String>> list     = new ArrayList<HashMap<String,String>>(0);
        NodeList  nodeList = doc.getElementsByTagName(strRootNode);         
        
        // 2 NodeList 있는 내용을 ArrayList에 넣는다. 
        if(nodeList!=null) 
        {        	
            for(int i=0; i< nodeList.getLength(); i++) 
            {
            	// 2.1 NodeList의 Node를 꺼낸다.(item이 null이면 다음 Node를 꺼낸다)
                Node depth1 = nodeList.item(i);
                if(depth1 == null)
                    continue;

                // 2.2 꺼낸 Node의 자식 NodeList를 다시 구한다.
                HashMap<String,String> map = new HashMap<String,String>();
                NodeList depth2 = depth1.getChildNodes();
                
                // 2.3 NodeList(두번째자식)의 데이터(태그이름,값)를 HashMap에 저장한다.  
                for(int j=0; j < depth2.getLength(); j++) 
                {
                	// 2.3.1 NodeList의 모든 Node를 꺼낸다.
                    Node item = depth2.item(j);
                    
                 	// 2.3.2 Node의 태그이름을 구한다.
                    if( item == null )
                        continue;
                    if("#text".equals(item.getNodeName()))
                        continue;
                    
                    String name = item.getNodeName();
                    
                 	// 2.3.3 Node의 태그에 해당하는 값을 구한다.
                    String value = item.hasChildNodes()?item.getChildNodes().item(0).getNodeValue():"";
                    
                    // 2.3.4 Node의 데이타(태그이름, 값)을 HashMap에 저장한다.
                    map.put(name, value);
                }
                
                // 2.4 저장한 HashMap을 List에 저장한다.
                if(map.size()!=0)
                    list.add(map);
            }
        }
        
        // 3 리스트에 들어 있는 객체들을 HashMap으로 캐스팅하여, HashMap[]에 넣는다.    
        HashMap<String,String>[] result = new HashMap[list.size()];
        
        for(int i=0; i < list.size(); i++) 
        {
            result[i] = (HashMap<String,String>)list.get(i);
        }

        return result;

    }  
}




