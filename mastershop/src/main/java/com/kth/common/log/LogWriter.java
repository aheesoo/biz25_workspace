
package com.kth.common.log;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public final class LogWriter {		
	
	public 	static final int DEBUG	= 10000;
	public 	static final int INFO	= 20000;
	public 	static final int WARN	= 30000;
	public 	static final int ERROR	= 40000;
	public 	static final int FATAL	= 50000;	
	
	/**
	 * LogWriter()
	 */
	public LogWriter() {	
		
	}
	

	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rPage
	 * @param rIP
	 * @param rAction
	 * @param rMsg
	 */
	public void WriteLoggerJockey( String rLoggerName, String rMsg) {			

		Logger rootLogger = Logger.getRootLogger();		
		
		String tempLogMsg = rMsg;
		
		try {
		
			if( rLoggerName != null && rLoggerName.length() > 0 ) {
				
				Logger logger = Logger.getLogger(rLoggerName);
				logger.debug(tempLogMsg);
				
			}else {
				rootLogger.error("LoggerName " + rLoggerName + " is null.");
			}
			
		} catch(Exception ex) {		
			rootLogger.error("LoggerName " + rLoggerName + " ex-" + ex.toString());
			ex.printStackTrace();
		}
			
	}	
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rMsg
	 * @param args
	 * @param t
	 */
	public void WriteLogger(	String 				rLoggerName,
								int 				rLogLevel,
								String 				rMsg,
								Map<String, Object> args, 
								Throwable 			t ) {	
					
		WriteLogger(rLoggerName, rLogLevel, null, null, null, rMsg, args, t, true);
	
	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rMsg
	 * @param t
	 */
	public void WriteLogger(	String 		rLoggerName,
								int 		rLogLevel,
								String 		rMsg,
								Throwable 	t ) {	

		WriteLogger(rLoggerName, rLogLevel, null, null, null, rMsg, null, t, true);

	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rMsg
	 */
	public void WriteLogger(	String 	rLoggerName,
								int 	rLogLevel,
								String 	rMsg ) {	

		WriteLogger(rLoggerName, rLogLevel, null, null, null, rMsg, null, null, true);
		
	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rPage
	 * @param rIP
	 * @param rAction
	 * @param rMsg
	 */
	public void WriteLogger(	String 	rLoggerName,
								int 	rLogLevel,
								String 	rPage, 
								String 	rIP,
								String 	rAction, 
								String 	rMsg ) {	
		
		WriteLogger(rLoggerName, rLogLevel, rPage, rIP, rAction, rMsg, null, null, true);
		
	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rPage
	 * @param rIP
	 * @param rAction
	 * @param rMsg
	 */
	public void WriteLogger(	String 				rLoggerName,
								int 				rLogLevel,
								String 				rPage, 
								String 				rIP,
								String 				rAction, 
								String 				rMsg,
								Map<String, Object> args ) {	
		
		WriteLogger(rLoggerName, rLogLevel, rPage, rIP, rAction, rMsg, args, null, true);
		
	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rPage
	 * @param rIP
	 * @param rAction
	 * @param rMsg
	 */
	public void WriteLogger(	String 		rLoggerName,
								int 		rLogLevel,
								String 		rPage, 
								String 		rIP,
								String 		rAction, 
								String 		rMsg,
								Throwable 	t) {	
		
		WriteLogger(rLoggerName, rLogLevel, rPage, rIP, rAction, rMsg, null, t, true);
		
	}
	
	
	public void WriteLogger(	String 				rLoggerName,
			int 				rLogLevel,
			String 				rPage, 
			String 				rIP,
			String 				rAction, 
			String 				rMsg,
			Map<String, Object> args,
			Throwable 			t) {	
		
		WriteLogger(rLoggerName, rLogLevel, rPage, rIP, rAction, rMsg, args, t, true);
		
	}
	
	
	/**
	 * 
	 * @param rLoggerName
	 * @param rLogLevel
	 * @param rPage
	 * @param rIP
	 * @param rAction
	 * @param rMsg
	 */
	private void WriteLogger(	String 				rLoggerName,
								int 				rLogLevel,
								String 				rPage, 
								String 				rIP,
								String 				rAction, 
								String 				rMsg,
								Map<String, Object> args,
								Throwable 			t,
								boolean 		    privateFlag) {			
		
		Logger rootLogger = Logger.getRootLogger();		
		
		String tempLogMsg = "";
		
		// Method Log
		String methodName = "";
		try {			
			methodName = this.getLogInfo(t);
		} catch (Exception e) {		
			methodName = "";		
		}	
		methodName = "[" + methodName + "]";
		tempLogMsg = methodName + " ";	
		
		// Page Log
		if(rPage != null && rPage.length() > 0) {			
			rPage = "[" + rPage + "]";
			tempLogMsg = rPage + " ";			
		} 
		
		// IP Log
		if(rIP != null && rIP.length() > 0) {			
			rIP = "[" + rIP + "]";
			tempLogMsg += rIP + " ";			
		}					
		
		// Action Log
		if(rAction != null && rAction.length() > 0) {			
			rAction = "[" + rAction + "]";
			tempLogMsg += rAction + " ";			
		}
		
		// Message Log
		if(rMsg != null && rMsg.length() > 0) {			
			rMsg = "[" + rMsg + "]";
			tempLogMsg += rMsg + " ";			
		}
		
		// Args Log
		String tmpGetArgs = getArgs(args);
		if( tmpGetArgs.length() > 0 ){
			tempLogMsg += "[" + tmpGetArgs + "]";
		}
		
		try {
		
			if( rLoggerName != null && rLoggerName.length() > 0 ) {
				
				Logger logger = Logger.getLogger(rLoggerName);
				
				if( t != null ) {
					
					switch(rLogLevel) {
					
						case FATAL : 
							logger.fatal(tempLogMsg, t);
							break;
							
						case ERROR :
							logger.error(tempLogMsg, t);
							break;
							
						case WARN : 
							logger.warn(tempLogMsg, t);
							break;
							
						case INFO :
							logger.info(tempLogMsg, t);
							
							break;
							
						case DEBUG :
							logger.debug(tempLogMsg, t);
							break;
						
					}
					
				}else {
					
					switch(rLogLevel) {
					
						case FATAL : 
							logger.fatal(tempLogMsg);
							break;
							
						case ERROR :
							logger.error(tempLogMsg);
							break;
							
						case WARN : 
							logger.warn(tempLogMsg);
							break;
							
						case INFO :
							logger.info(tempLogMsg);
							break;
							
						case DEBUG :
							logger.debug(tempLogMsg);
							break;
							
					}
					
				}
				
			}else {
				
				rootLogger.error("LoggerName " + rLoggerName + " is null.");
				
			}
			
		} catch(Exception ex) {		
						
			rootLogger.error("LoggerName " + rLoggerName + " ex-" + ex.toString());
			ex.printStackTrace();
			
		}
			
	}	
	
	
	/**
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	private String getLogInfo(Throwable t) throws Exception {
		
		String method = "";		
		
		
		if( t != null) {
			
			try {
				
				// stack trace 를 얻기 위한 의미 없는 Exception ..			
				throw new Exception ();
			
			}catch( Exception e ) {
				

				StackTraceElement[]  stackTraceElementList = e.getStackTrace();		
			
				if( stackTraceElementList != null && stackTraceElementList.length >= 4 ) {					
					
					String simpleClassName = this.getSimpleClassName(stackTraceElementList[3].getClassName());				
					int    line			   = stackTraceElementList[3].getLineNumber();
					method = simpleClassName + "." + stackTraceElementList[3].getMethodName() + ":" + line;			
					
				} 
				
			}
			
		}else {
			
			try {
				
				// stack trace 를 얻기 위한 의미 없는 Exception ..			
				throw new Exception ();
			
			}catch( Exception e ) {				
				
				StackTraceElement[]  stackTraceElementList = e.getStackTrace();			
				
				if( stackTraceElementList != null && stackTraceElementList.length >= 5 ) {					
				
					String simpleClassName = this.getSimpleClassName(stackTraceElementList[4].getClassName());				
					int    line			   = stackTraceElementList[4].getLineNumber();
					method = simpleClassName + "." + stackTraceElementList[4].getMethodName() + ":" + line;			
					
				}
				
			}
			
		}
		
		return method;
		
	}
	
	
	/**
	 * 
	 * @param className
	 * @return
	 * @throws Exception
	 */
	private String getSimpleClassName(String className) throws Exception{
		
		int index = className.lastIndexOf('.');		
		
		if( index != -1 ) {
			
			className = className.substring(index+1, className.length());
			
		}
		
		return className;
		
	}
	
	
	/**
	 * 
	 * @param className
	 * @return
	 * @throws Exception
	 */
	private String getArgs(Map<String, Object> args) {
		
		String strArgs = "";
		
		try {
			String tmpKey 	= "";
			Object tmpValue = "";
			if( args != null ) {
				
				Iterator iter = args.keySet().iterator();
	
				while (iter.hasNext()) {
	
					tmpKey = (String) iter.next();
					tmpValue = args.get(tmpKey);
					
					strArgs = strArgs + tmpKey + "(" + tmpValue + "), ";  
	
				}
				
			}else {
				
				strArgs = "";
				
			}
			
		}catch(Exception ex) {
			
			strArgs = "Args Print Error";
			
		}
		
		return strArgs;
		
	}	
	
		
}



