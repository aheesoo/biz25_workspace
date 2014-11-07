package com.kth.common.db;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DBHandler {
	
	/**
	 * singleton
	 */ 
	static private DBHandler instance = null;  
 
	/**
	 * [true]일때 "sqlMapConfig.xml"을 읽어들임 
	 */
	public static boolean reload = false; 
	
	/**
	 * IBATIS DB Handler
	 */
	public static SqlMapClient mapper = null;	
	
	
	/**
	 * 생성자
	 */
	private DBHandler() {	
		
		instance   = null;
				 
		reload     = false;
		
		mapper     = null;						
		
	}	

	//-- --//
	
	static synchronized public DBHandler getInstance() {
		
		// Create Instance
		if(instance == null) {		

			instance = new DBHandler();	
		
		} 
		
		return instance;
		
	}
	
	static synchronized public void clearMapper() {
		
		DBHandler.mapper     = null;	
		 
		
	}
	
	//-- --//
	
	
	/**
	 * createMapper() : reload변수값이 true 이면,
	 * 					sqlMapConfig.xml 을 새로 읽어서 mapper 를 다시 할당
	 * 					(이렇게하면 외부 Sql XML 파일을 새로 읽게된다)
	 * @throws Exception 
	 */
    private void createMapper()  throws Exception { 
    	
    	// 1. reload : [true]
    	//   => "sqlMapConfig.xml" 파일을 읽어들인다.
    	//     ( reload값을 true로 변경하면, WAS restart 및 reload 없이 읽어 들일 수 있다.)
    	//
    	// 2. reload : [false] & mapper : [null] 
    	//   => 최초 1회 "sqlMapConfig.xml" 파일을 읽어들인다.
    	// 
    	// 3. reload : false
        //   => 기존에 생성한 mapper를 사용한다.
    	//if(      reload == true 
    	//	|| ( reload == false && mapper == null) ) {
 	
    	if( mapper == null ) {
    		
    		try {    			
    			
    			 //sqlMapConfig.xml 문서를 로드하기 위한 스트림 준비 (아직 로드 아님)
	            Reader reader = Resources.getResourceAsReader("com/kth/sqlMapConfig.xml");
	
	            // Reader를 통해 sqlMapConfig.xml을 로드하여 sqlMap들을
	            // 선별하여 객체로 만들어 지는 것이 바로 SqlMapClient가 된다.
	            mapper = SqlMapClientBuilder.buildSqlMapClient(reader);
	            
	            if(reader != null) {
	            	
	            	reader.close();
	            	
	            }
	            
	            // 한번 읽어들이면 reload의 값을 true로 변경하여 현재 생성된 maaper를 사용하도록 한다.
	            //reload = false;   
	            	
	        } catch(Exception ex) {
	
	        	throw ex;	            
	
	        }	     
	  
    	} 
    	
    }      
   
    
    /**
     * 
     * @param mapName
     * @return
     * @throws Exception 
     * @throws Exception 
     */
    public Object queryForObject(String mapName) throws Exception  {		
    	    	
		createMapper();			
		return mapper.queryForObject(mapName);		
		
	}  
    
    
    /**
     * 
     * @param mapName
     * @return
     * @throws Exception 
     */
	public Object queryForObject(String mapName, Map<String,Object> inParam) throws Exception {

		createMapper();
		return mapper.queryForObject(mapName, inParam);

	}
	
	public Object queryForObject(String mapName, Object inParam) throws Exception {

		createMapper();
		return mapper.queryForObject(mapName, inParam);

	}
    
    
    /**
     * 
     * @param mapName
     * @return
     * @throws Exception 
     */
    public List queryForList(String mapName) throws Exception {
		
		createMapper();			
		return mapper.queryForList(mapName);
		
	}    
    
    public List<Map<String,Object>> queryForList(String mapName, Map<String,Object> inParam) throws Exception {		
    	    	
		createMapper();			
		return mapper.queryForList(mapName, inParam);					
		
	} 
    
    public List queryForList(String mapName, Object inParam) throws Exception {		
    	
		createMapper();			
		return mapper.queryForList(mapName, inParam);					
		
	} 
    
    
    public Object insert(String mapName) throws Exception {
			
		createMapper();			
		return mapper.insert(mapName);			
		
	} 
    
    
    public Object insert(String mapName, Map<String,Object> inParam) throws Exception {		
    	
    	
		createMapper();			
		return mapper.insert(mapName, inParam);
		
	} 
    
    public Object insert(String mapName, Object inParam) throws Exception {		
    	
    	
		createMapper();			
		return mapper.insert(mapName, inParam);
		
	} 
    
    public int update(String mapName) throws Exception {		
    	
		createMapper();			
		return mapper.update(mapName);
				
	} 

    
    public int update(String mapName, Map<String,Object> inParam) throws Exception  {		
    	    	
		createMapper();			
		return mapper.update(mapName, inParam);		
		
		
	} 
    
    public int update(String mapName, Object inParam) throws Exception  {		
    	
		createMapper();			
		return mapper.update(mapName, inParam);		
		
		
	} 
    
    
    public int delete(String mapName) throws Exception  {    	
    	
		createMapper();			
		return mapper.delete(mapName);						
		
	} 

    public int delete(String mapName, Map<String,Object> inParam) throws Exception  {    	
    	
		createMapper();			
		return mapper.delete(mapName, inParam);						
		
	} 
    
    public int delete(String mapName, Object inParam) throws Exception  {    	
    	
		createMapper();			
		return mapper.delete(mapName, inParam);						
		
	} 
    
    
    /**
     * 
     * @param method
     * @param mapName
     * @param inparam
     * @return
     */
    private String createLogMsg(String method, String mapName, Object inparam) {
    	
    	String LogMsg  = "\n------------------------------";    	
    		   LogMsg += "ERROR LOG INFO";
    		   LogMsg += "------------------------------\n";
    		    		   
    	LogMsg += "Method  : " + method  			 + "\n";
    	LogMsg += "mapName : " + mapName 			 + "\n";
    	
    	if( inparam != null ) {
    		
    		LogMsg += " inParam : " + inparam.toString() + "\n";
    		
    	}
    	
    	return LogMsg;
    	
    }
    
    public void startBatch()  throws Exception {
    	        
    	createMapper();		
		mapper.startBatch();
		
    }   
    
    
    public void executeBatch()  throws Exception {  
    	      
    	createMapper();		
		mapper.executeBatch();			
		
    }  
    
    
    // 트랜젝션 시작   
    public void startTransaction()  throws Exception {   

    	createMapper();		
		mapper.startTransaction();			
		
    }  
    
    
    // 트랜젝션 커밋   
    public void commitTransaction()  throws Exception  { 
    	                	
        createMapper();		
		mapper.commitTransaction();
		
    }   
    
    
    // 트랜젝션 종료(commit를 호출하지 않으면 적용하지 않음)   
    public void endTransaction()  throws Exception {   
    	
        createMapper();		
		mapper.endTransaction();
		
    } 
    
    
    public boolean batchInsert(String mapName, List<Object> voList) throws Exception  {    	
    	
		boolean result = false;
		
		if( voList != null && voList.size() > 0 ) {
			
			try {
				
				startTransaction();
				startBatch();
				
				for( int i=0; i < voList.size(); i++ ) {
					
					this.mapper.insert(mapName, voList.get(i));
					
				}				

				commitTransaction();
				
				result = true;
				
			} catch (SQLException e) {			
				
				throw e;
				
			}finally{
				
				try{
					
					endTransaction();
					
				}catch (SQLException e) {					
					
					throw e;
					
				}				
				
			}
			
		}else {
		
			result = false;
			
		}	 
		
		return result;
    	
    }
    
    public boolean batchUpdate(String mapName, List<Object> voList) throws Exception  {    	
    	
		boolean result = false;
		
		if( voList != null && voList.size() > 0 ) {
			
			try {
				
				startTransaction();
				startBatch();
				
				for( int i=0; i < voList.size(); i++ ) {
					
					this.mapper.update(mapName, voList.get(i));
					
				}				

				commitTransaction();
				
				result = true;
				
			} catch (SQLException e) {			
				
				throw e;
				
			}finally{
				
				try{
					
					endTransaction();
					
				}catch (SQLException e) {					
					
					throw e;
					
				}				
				
			}
			
		}else {
		
			result = false;
			
		}	 
		
		return result;
    	
    }
    
    
    public boolean batchDelete(String mapName, List<Object> voList) throws Exception  {    	
    	
		boolean result = false;
		
		if( voList != null && voList.size() > 0 ) {
			
			try {
				
				startTransaction();
				startBatch();
				
				for( int i=0; i < voList.size(); i++ ) {
					
					this.mapper.delete(mapName, voList.get(i));
					
				}				

				commitTransaction();
				
				result = true;
				
			} catch (SQLException e) {			
				
				throw e;
				
			}finally{
				
				try{
					
					endTransaction();
					
				}catch (SQLException e) {					
					
					throw e;
					
				}				
				
			}
			
		}else {
		
			result = false;
			
		}	 
		
		return result;
    	
    }  
    
	public boolean batchDelInsert(String delMapName, Object delParam, String insMapName, List<Object> voList) throws Exception  {    	
    	
		boolean result = false;
		
		if( voList != null && voList.size() > 0 ) {
			
			try {
				
				startTransaction();
				startBatch();				
			
				this.mapper.delete(delMapName, delParam);
			
				for( int i=0; i < voList.size(); i++ ) {
					
					this.mapper.insert(insMapName, voList.get(i));
					
				}				

				commitTransaction();
				
				result = true;
				
			} catch (SQLException e) {			
				
				throw e;
				
			}finally{
				
				try{
					
					endTransaction();
					
				}catch (SQLException e) {					
					
					throw e;
					
				}				
				
			}
			
		}else {
		
			result = false;
			
		}	 
		
		return result;
    	
    }
    
    
    public Object statement(String mapName) throws Exception {
    	
    	createMapper();			
		return mapper.queryForObject(mapName);		
    	
    }
    
}  
