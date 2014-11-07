
package com.kth.common.file;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import com.kth.common.util.StringUtil;

public final class FileHandler {
	
	StringUtil format = new StringUtil();
	
	/**
	 * 구성저
	 */
	public FileHandler() {
		
	}
	
	/**
	 * makeDir() : 디렉토리 생성
	 * @param strDirName
	 * @return
	 */
	public synchronized boolean makeDir(String strDirName) {
		
		boolean rCode = true;
		
		File dirName = new File(strDirName);
		
		try {
			
			// 디렉토리가 없으면 생성한다.
			if (!dirName.isDirectory()) {
				
				//상위 디렉토리도 함께 생성합니다.
				if (!dirName.mkdirs()) {
					
					System.out.println ("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeDir] [ERROR] [디렉토리 생성 실패]");	
					
					rCode = false;
					
				}else{
					
					System.out.println ("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeDir] [] [디렉토리 생성 성공]");
					
				}
			}
		
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeDir] [Exception]");	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
			
		} finally {
			
			dirName = null;
		}

		return rCode;	
		
	}
	

	/**
	 * makeFile() : 파일 생성_디렉토리까지 전부 생성함
	 * @param strFileName
	 * @return
	 */
	public synchronized boolean makeFile(String strFileName) {
		
		boolean rCode = true;
		
		File fileName = new File(strFileName);
		
		try {
			
			String nameArr[] = splitPathName(strFileName);
			
			if (!makeDir(nameArr[0])) 
				return false;
			
			// 파일이 없으면 생성한다.
			if (!fileName.exists()) {
				
				if (!fileName.createNewFile()) {
					
					System.out.println ("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeFile] [ERROR] [파일 생성 실패] file : " + strFileName);	
					
					rCode = false;
					
				}
				
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeFile] [Exception]file : " + strFileName);	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
			
		} finally {
			
			fileName = null;
			
		}

		return rCode;
		
	}
	

	/**
	 * splitPathName()
	 * 파일명과 디렉토리를 구분해서 배열로줌<br>
	 * 0번 배열 : 디렉토리명. 끝에 "/"제외한 디렉토리명임.<br>
	 * 1번 배열 : 파일명.
	 * @param strFileName
	 * @return
	 */
	public synchronized String[] splitPathName(String strFileName) {
		
		String str1 = strFileName.trim();
		String nameArr[] = new String[2];
		int gbnPos = str1.lastIndexOf("\\");
		
		if (gbnPos < 1)  
			gbnPos = str1.lastIndexOf("/");
		
		// 디렉토리명 끝에 "/"제외한 디렉토리명임.
		nameArr[0] = str1.substring(0, gbnPos);

		// 파일명
		nameArr[1] = str1.substring(gbnPos+1, str1.length());
		
		return nameArr;
		
	} 

	
	/**
	 * isFileExists()
	 * 파일 존재 하니? 디렉토리면 false
	 * @param strFileName
	 * @return
	 */
	public synchronized boolean isFileExists(String strFileName) {

		boolean rCode = false;

		File fileName = new File(strFileName);
		
		try {
			
			if (fileName.exists()) {
				
				if (fileName.isFile()) { 
					
					rCode = true;
					
				} else {
					
					rCode = false;
					
				}
				
			} else {
				
				rCode = false;
				
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [isFileExists] [Exception] file : " + strFileName);	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
		
		} finally {
			
			fileName = null;
		}

		return rCode;
		
	}
	

	/**
	 * isDirExists()
	 * 폴더 존재 하니? 파일이면 false
	 * @param strFileName
	 * @return
	 */
	public synchronized boolean isDirExists(String strFileName) {
		
		boolean rCode = false;
		
		File fileName = new File(strFileName);
		
		try {
			
			if (fileName.exists()) {
				
				if (fileName.isDirectory()) 
					rCode = true;
				else 
					rCode = false;
				
			} else {
				
				rCode = false;
				
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [isDirExists] [Exception] file : " + strFileName);	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
		
		} finally {
			
			fileName = null;
			
		}

		return rCode;
		
	}
	

	/**
	 * deleteFile() : 파일 삭제
	 * @param strFileName
	 * @return
	 */
	public synchronized boolean deleteFile(String strFileName) {
		
		boolean rCode = true;
		
		File fileName = new File(strFileName);
		
		try {
			
			if (!fileName.delete()) {
				
				System.out.println ("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [makeFile] [ERROR] [파일 삭제 실패] file : " + strFileName);	
				
				rCode = false;
			
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [deleteFile] [Exception] file : " + strFileName);	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
		
		} finally {
			
			fileName = null;
		}
		
		return rCode;
	}
	

	
	/**
	 * copyFile() :  파일 복사, toFileName이 이미 있으면 에러
	 * @param fromFileName
	 * @param toFileName
	 * @return
	 */
	public synchronized int copyFile(String fromFileName, String toFileName) {
		
		return copyFile(fromFileName, toFileName, false);
		
	}


	/**
	 * copyFile() :  파일 복사, toFileName이 이미 있으면 에러
	 * @param fromFileName
	 * @param toFileName
	 * @param forceReWrite
	 * @return
	 */
	public synchronized int copyFile(String fromFileName, String toFileName, boolean forceReWrite) {
		
		int rCode = 0;
		File toFile = null;
		File frFile = null;
		FileInputStream  frFStream = null;
		FileOutputStream toFStream = null;
		byte[] Buffer = new byte[1024*10];
		int readByte = 0;
		
		try {
			
			frFile = new File(fromFileName);
			toFile = new File(toFileName);
		
			// fromFileName이 없는 경우
			if (!isFileExists(fromFileName)) 
				return -1;  
			
			// toFileName이 이미 존재하는 경우. (forceReWrite == true 인 경우는 덮어쓴다.)
			if(! forceReWrite) {
				if (isFileExists(toFileName))  
					return -2;
			}			
			
			// toFileName 생성실패
			if (!makeFile(toFileName)) 
				return -3;      
			
			frFStream = new FileInputStream(frFile);
			toFStream = new FileOutputStream(toFile);
			
			while (true) {
				
				readByte = frFStream.read(Buffer);
				
				if (readByte > 0) 
					toFStream.write(Buffer, 0, readByte);
				else 
					break;
      			
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "][FileHandler] [deleteFile] [Exception] fromFileName : " + fromFileName + ", toFileName : " + toFileName);	
			
			ex.printStackTrace();
			
			rCode = -99;
			
			System.gc();
		
		} finally {
			
			toFile = null;
			frFile = null;
			inStreamClose(frFStream);
			outStreamClose(toFStream);
			
		}
		
		return rCode;
		
	}
	
	
	/**
	 * moveFile() : 파일 이동, toFileName이 이미 있으면 에러
	 * @param fromFileName
	 * @param toFileName
	 * @return
	 */
	public synchronized int moveFile(String fromFileName, String toFileName) {
		
		int rCode = copyFile(fromFileName, toFileName);
		
		if (rCode < 0) 
			return rCode;
		
		if (!deleteFile(fromFileName)) 
			return -10;
		
		return 0;
		
	}
	
	
	/**
	 * renameFile() : 파일명 변경
	 * @param strFileName
	 * @param changeName
	 * @return
	 */
	public synchronized boolean renameFile(String strFileName, String changeName) {
		
		boolean rCode = true;
		File fileName = new File(strFileName);
		File filechangeName = new File(changeName);
		
		try {			
			if(filechangeName.isFile())
			{			
				rCode = false;				
			}
			else 
			{				
				fileName.renameTo(filechangeName); //파일 이름 변경
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [renameFile] [Exception]file : " + strFileName);	
			
			ex.printStackTrace();
			
			rCode = false;
			
			System.gc();
		
		} finally {
			
			fileName = null;
			filechangeName = null;
			
		}
		
		return rCode;
		
	}
	
	
	/**
	 * getFileData()
	 * 지정한 파일의 내용을 읽어서 문자열로 반환함
	 * @param _file
	 * @return
	 */
	public synchronized String getFileData(String _file) {

		StringBuffer sbResult      = null;
		FileInputStream	fiStream   = null;
		InputStreamReader isReader = null;
		BufferedReader bfReader	   = null;

		String strTemp     = null;
		String strReturn   = null;
		String strFileName = null;

		strFileName = _file.trim();

		if(strFileName != null && (new File(strFileName)).exists()) {

			try {
				
				fiStream = new FileInputStream(strFileName);

				//OS Level 에서의 Charset 이 상이함..
                //isReader = new InputStreamReader(fiStream);
                //isReader = new InputStreamReader(fiStream, "utf-8");
                isReader = new InputStreamReader(fiStream, "euc-kr");
                //isReader = new InputStreamReader(fiStream, "KSC5601");
                //isReader = new InputStreamReader(fiStream, "8859_1");

				bfReader = new BufferedReader(isReader);

				sbResult = new StringBuffer ("");

				do {
					
					strTemp = bfReader.readLine();
    	
					if(strTemp != null) {
						
						sbResult.append(strTemp + "\n");
						
					}

				} while(strTemp != null);

				strReturn = sbResult.toString();

			} catch (Exception ex) {
				
				System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [getFileData] [Exception] file : " + strFileName);
				
				ex.printStackTrace();
				
				strReturn = null;

				System.gc();
				
			} finally {

				if(bfReader != null) {
					
					try {
						bfReader.close();
				
					} catch (Exception e1) {
						e1.printStackTrace();

					}

				}

				if(isReader != null) {

					try {
						isReader.close();

					} catch (Exception e1) {
						e1.printStackTrace();

					}

				}

				if(fiStream != null) {

					try {
						fiStream.close();

					} catch (Exception e1) {
						e1.printStackTrace();

					}

				}

			}

		} else {
			
			strReturn = "내용이 없습니다.";

		}

		return strReturn;
		
	}
	
	
	/**
	 * inStreamClose()
	 * @param is
	 */
	private synchronized void inStreamClose(FileInputStream is) {
		
		try {
			
			if (is != null) {
				
				is.close();
				is = null;
				
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [inStreamClose] [Exception]");	
			
			ex.printStackTrace();
			
			is = null;
			
			System.gc();
			
		}
		
	}
	
	/**
	 * outStreamClose()
	 * @param os
	 */
	private synchronized void outStreamClose(FileOutputStream os) {
		
		try {
			
			if (os != null) {
				
				os.close();
				os = null;
				
			}
			
		} catch(Exception ex) {
			
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [outStreamClose] [Exception]");	
			
			ex.printStackTrace();
			
			os = null;
			
			System.gc();
			
		}
		
	}
	
	/**
	 * toDayDeleteFile() : 현재일자와 틀리면 이전 파일은 삭제
	 * @param strFolderName
	 * @return
	 */
	public synchronized void deleteTodayFile(String strFolderName) {
		File folderName = null;
		try {
			folderName = new File(strFolderName);
			if (folderName.isDirectory()) {
				File uploadFiles[] = folderName.listFiles();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				for (File tempFile : uploadFiles) {
					if (tempFile.isFile()) {
						if (!sdf.format(new Date()).equals(sdf.format(new Date(tempFile.lastModified())))) {
							deleteFile(strFolderName + tempFile.getName());
						}
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [toDayDeleteFile] [Exception] file : " + strFolderName);
			ex.printStackTrace();
			System.gc();
		} finally {
			folderName = null;
		}
	}
	
	/**
	 * deleteBeforeFile() : 10시간전 파일 삭제
	 * @param strFolderName
	 * @return
	 */
	public synchronized void deleteBeforeFile(String strFolderName) {
		File folderName = null;
		try {
			folderName = new File(strFolderName);
			if (folderName.isDirectory()) {
				File uploadFiles[] = folderName.listFiles();
				SimpleDateFormat sdf_time = new SimpleDateFormat("yyyyMMddHHmmss");
				for (File tempFile : uploadFiles) {
					if (tempFile.isFile()) {
						
						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();
						
						//24시간전 년월일시분초
						//cal1.add(Calendar.DATE, -1);
						cal1.add(Calendar.HOUR_OF_DAY, -10);	//10시간이전
						
						/*
						t_year	= cal1.get(cal1.YEAR);
						t_month = cal1.get(cal1.MONTH)+1;
						t_day	= cal1.get(cal1.DAY_OF_MONTH);
						t_hh	= cal1.get(cal1.HOUR_OF_DAY);
						t_mm	= cal1.get(cal1.MINUTE);
						t_ss	= cal1.get(cal1.SECOND);
						*/

						cal2.setTime( new Date( tempFile.lastModified() ) );	//수정한 날짜
						
						int compare_cnt = cal1.compareTo(cal2);
						if(compare_cnt > 0){
							deleteFile(strFolderName + tempFile.getName());
						}
						
						/*
						if (!sdf.format(new Date()).equals(sdf.format(new Date(tempFile.lastModified())))) {
							deleteFile(strFolderName + tempFile.getName());
						}
						*/
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("[" + format.getDateTime("yyyy-MM-dd HH:mm:ss") + "] [FileHandler] [toDayDeleteFile] [Exception] file : " + strFolderName);
			ex.printStackTrace();
			System.gc();
		} finally {
			folderName = null;
		}
	}
	
}