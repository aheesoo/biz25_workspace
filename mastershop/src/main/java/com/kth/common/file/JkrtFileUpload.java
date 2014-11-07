
package com.kth.common.file;

import java.util.*;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;

/**	
 * <table cellspacing="1" cellpadding="0" bgcolor="skyblue" border="0">
 * <tr bgcolor="#FFFFFF">
 * <td bgcolor="beige"><pre><font style="font-family:굴림체;font-size:10pt;">  
 *   타이틀       : 자카르타 파일 업로드
 *   클래스       : JkrtFileUpload
 *   작성자       : 송영석  
 *   작성일자     : 2009-03-30  
 *   수정일자     : 2009-03-30  
 *   전자우편     : virusno33@pointi.com</font></pre></td>
 *   라이프러리   : commons-fileupload-1.2.1.jar, commons-io-1.4.jar
 * </tr>
 * </table>	
*/

public final class JkrtFileUpload {
	
	private boolean isMultipart     = false;
	private int     nSizeThreshold  = 1024 * 50;
	private int 	nMaxSize	    = 0; 
	private String  basePath	    = "";
	
	private String  fileFullName	= "";
	private String  fileName	    = "";	
	
	private List<FileItem>  items  	   = null;
	HashMap<String, String> paramsList = new HashMap<String, String>();
	
	
	/**
	 * JkrtFileUpload() : 생성자
	 * @param request  : 
	 * @param nMaxSize : 업로드 되는 파일의 제한 사이즈
	 * @param basePath : 업로드되는 곳의 폴더
	 * @throws Exception
	 */
	public JkrtFileUpload( HttpServletRequest request, int nMaxSize, String basePath ) throws Exception {		
		
		// 폴더 끝에 '/'가 들어가면 제외시킨다.
		if( basePath.lastIndexOf('/') == basePath.length() ) {
			
			basePath = basePath.substring(0, basePath.length() - 1 );
			
		}
		
		// 로컬 변수 셋팅
		this.basePath = basePath;
		this.nMaxSize = nMaxSize;
		
		// 멀티파트 컨텐츠 객체 가져오기
		isMultipart   = ServletFileUpload.isMultipartContent(request);
		
		// 파일 업로드 설정및 파라미터 설정
		if ( isMultipart ) {
		
			// Create a factory for disk-based file items			
			DiskFileItemFactory factory = new DiskFileItemFactory();
	
			// Set factory constraints
			factory.setRepository( new File(basePath) );			
			factory.setSizeThreshold(nSizeThreshold);			 
	
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
	
			// Set overall request size constraint
			upload.setSizeMax(nMaxSize);
			upload.setFileSizeMax(nMaxSize);
	
			try {
				
				// Parse the request (LIST TYPE : FileItem)
				items = upload.parseRequest(request);
				setParams();
				
			}catch(Exception ex) {			
					
				System.out.println("Class : com.kth.common.file.JkrtFileUpload.uploadFile() 	" );			
				System.out.println("Msg   : " + ex.getMessage()									  );
				throw ex;
				
			}			
			
		}
		
	}
	 

	/**
	 * uploadFile : 파일업로드	
	 * @return         : 업로드된 파일 개수를 리턴한다 (파일사이즈 초과시는 "-1"을 반환한다.
	 * @throws Exception
	 */
	synchronized public int uploadFile() throws Exception {
		
		return uploadFile(null);
		
	}
	
	
	/**
	 * uploadFile : 파일업로드
	 * @param fileName : 저장시키는 파일이름
	 * @return         : 업로드된 파일 개수를 리턴한다 (파일사이즈 초과시는 "-1"을 반환한다.,  -2 : 파일 확장자 에러
	 * @throws Exception
	 */
	synchronized public int uploadFile( String fileName ) throws Exception {
		
		int nUploadFileCount = 0;
		
		boolean bWrite = false;
		
		try {
			
			if ( isMultipart ) {			
				
				// Process the uploaded items
				Iterator iter = items.iterator();
				
				while ( iter.hasNext() ) {
					
				    FileItem item = (FileItem) iter.next();
	
				    if( !item.isFormField() ) {
				        
				    	// String fieldName = item.getFieldName();
					    // String fileName = item.getName();			    	
					    // String contentType = item.getContentType();
					    
					    // boolean isInMemory = item.isInMemory();			    	
					    // long sizeInBytes = item.getSize();					    
					    // System.out.println("contentType : " + contentType);
					    // System.out.println("getFieldName : " + item.getFieldName());				    	
				    	
				    	if( this.checkFiletype(item.getName() )) {
				    		
				    		bWrite = true;
				    		
				    	}
				    	
					    // 파일이름이 설정이 안되어 있다면
				    	if ( fileName == null || fileName.equals("") ) {
				    		
				    		// 업로드된 파일이름을 가져온다.
				    		fileName = item.getName();
				    		
				    		if ( fileName != null && fileName.length() > 0 ) {
					    		
					    		// 파일의 이름의 경로까지 전달됨으로 경로는 제외한다.
					    		int nStartIndex = fileName.lastIndexOf("/");	
					    		
					    		if( nStartIndex == -1 ) {
					    			
					    			nStartIndex = fileName.lastIndexOf("\\");
					    			
					    		}
					    		
					    		// 경로가 있는 파일이면 경로를 제외하고 파일 이름만 가져온다.
					    		if( nStartIndex >= 0 ) {
					    		
					    			fileName = fileName.substring(nStartIndex + 1 , fileName.length() );
					    			
					    		}	
					    		
				    		}
				    	
				    	// 파일이름이 설정이 되어 있다면 (파일이름 + 확장자)
				    	} else {
				    		
				    		String tempFileName = item.getName();	// 업로드된 파일이름을 가져온다.			    		
				    		String lastFileName = ""; 				// 확장자	
				    		
				    		// 파일의 확장자만 가져온다.
				    		if( tempFileName != null && tempFileName.length() > 0 ) {
				    			
				    			int nTempIndex = tempFileName.lastIndexOf('.');
				    			
				    			if( nTempIndex != -1 ) {
				    				
				    				lastFileName = tempFileName.substring( nTempIndex + 1, tempFileName.length() );
				    				
				    			}				    				
				    			
				    		}
				    		
				    		fileName = fileName + "." + lastFileName;
				    		
				    	}
				    	
				    	// 파일을 저장한다.
				    	File uploadedFile = new File(basePath + "/" + fileName);
				    	
				    	if( bWrite ){
				    		
				    		item.write(uploadedFile);
				    		
				    	}else {
				    		
				    		System.out.println("false");
				    		
				    	}
						
						this.fileFullName =  basePath + "/" + fileName;
						this.fileName     =  fileName;
						
						nUploadFileCount++; // 저장된 파일개수	
						
				    }
				     
				}
				
			}		
			
		// 파일 사이즈 초과시 발생하는 익셉션
		} catch(SizeLimitExceededException ex) {			
			
			System.out.println("Class : com.kth.common.file.JkrtFileUpload.uploadFile() 	" );
			System.out.println("Error : 파일 사이즈가 " + nMaxSize + " 보다 더 초과되었습니다.	" );
			System.out.println("Msg   : " + ex.getMessage()									  );
			
			throw ex;
			
		// 업로드시 예기치 못한 오류가 발생
		} catch(Exception ex) {			
			
			System.out.println("Class : com.kth.common.file.JkrtFileUpload.uploadFile() 	" );
			System.out.println("Error : 파일 업로드시 예기치 못한 에러가 발생하였습니다.			" );
			System.out.println("Msg   : " + ex.getMessage()									  );
			
			throw ex;
			
		}			
			
		if( bWrite == false ) {		
						
			return -2;
			
		}else {
			
			return nUploadFileCount;
			
		}
		
	}
	
	
	/**
	 * getParam() : 문자형 파라미터값을 가져온다
	 * @param key : 파라미터 key값
	 * @param nDefault : 파라미터값이 없으면 기본값으로 셋팅된다
	 * @return
	 */
	public String getParam(String key, String strDefault) {
		
		String value = paramsList.get(key);
		
		if( value != null && value.length() > 0 ) {
			
			return value;
			
		} else {
			
			return strDefault;
			
		}
		
	}
	
	
	/**
	 * getParam() : 정수형 파라미터값을 가져온다
	 * @param key : 파라미터 key값
	 * @param nDefault : 파라미터값이 없으면 기본값으로 셋팅된다
	 * @return
	 */
	public int getParam(String key, int nDefault) {
		
		String value = paramsList.get(key);
		
		try {
			
			return Integer.parseInt(value);			
			
		} catch(Exception ex) {
			
			return nDefault;
			
		}
		
	}
		
	
	/**
	 * getParams() : 파라미터들을 반환한다 
	 * @return
	 */
	public HashMap<String, String> getParams() {
		
		return paramsList;
		
	}
	

	/**
	 * getFileName() : 저장된 파일이름(파일명)을 반환한다.
	 * @return
	 */
	public String getFileName() {
		
		return fileName;
		
	}
	
	
	/**
	 * getFileFullName() : 저장된 파일이름(폴더 + 파일명)을 반환한다.
	 * @return 
	 */
	public String getFileFullName() {
		
		return fileFullName;
		
	}
	
	
	/**
	 * checkFiletype : 허용된 파일 확장자를 체크한다
	 * @param filename : 파일명
	 * @return
	 */
	public boolean checkFiletype(String filename){
		
		boolean result = false;		
		
		String[] allow = {
				
				"jpg",
				"gif",
				"bmp",
				"ppt",
				"pptx",
				"pdf",
				"xls",
				"xlsx",
				"doc",
				"docx",
				"gul",
				"txt",
				"hwp",
				"tif"				
		};
		
		if( filename != null && filename.length() > 0) {
			
			filename = filename.toLowerCase();
			
			String type = filename.substring( filename.lastIndexOf('.') + 1, filename.length() );
			
			for( int i=0; i < allow.length; i++){
				
				if( type.equals(allow[i]) ) {
					
					result = true;
					
				}
				
			}
			
		}		
		
		if( result == false) {
			
			System.out.println("Class : com.kth.common.file.JkrtFileUpload.checkFiletype() 	" );
			System.out.println("Error : 허용되지 않은 확장자 파일입니다.						" );
			System.out.println("filename : "+ filename 										  );			
			
		}
		
		return result;		
		
	}
	
	
	/**
	 * getParams() : 전달된 파라미터 값을 가져온다.  
	 * @return	   : HashMap으로된 파라미터 값을 가져온다.
	 * @throws Exception
	 */
	private void setParams() throws Exception {
		
		// Process the uploaded items
		Iterator iter = items.iterator();
		
		// Set Prams
		while ( iter.hasNext() ) {
			
		    FileItem item = (FileItem) iter.next();

		    if (item.isFormField()) {		    	
		    			    	
		    	paramsList.put( item.getFieldName(), item.getString() );
		    	
		    }
		    
		}		
		
	}	 
	
}