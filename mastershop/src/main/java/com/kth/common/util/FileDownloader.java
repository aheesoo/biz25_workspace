package com.kth.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class FileDownloader 
{
	public FileDownloader(HttpServletRequest request, HttpServletResponse response,JspWriter out, String strSourceFilePath, String strSourceFileName) throws Exception
	{
		//JSP 페이지에서 자동으로 OutputStream을 발생함으로 Clear한 다음에 OutputStream을 생성해야 한다.
		out.clear();
		// 파일 다운 로드 (클라이언트)
		File f = new File(strSourceFilePath + strSourceFileName);		
		
		if(f.exists()) 
		{			
			byte buff[] = new byte[2048];

			int bytesRead;

			//response.sendRedirect(_path+Excel_File);
					
						

			response.setContentType("application/vnd.ms-excel");
			//response.setHeader("Cache-Control","no-store");
			//response.setDateHeader("Expires",0);
			//response.setHeader("Pragma","no-cache");
			response.setHeader("Content-Disposition", "attachment; filename=" + strSourceFileName);
			response.setHeader("Content-Description", "JSP Generated Data");
			

			FileInputStream fin 	= new java.io.FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fin);

			ServletOutputStream fout = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			
			while((bytesRead = bis.read(buff)) != -1) 
			{
				bis.toString();
				System.out.println();
				bos.write(buff, 0, bytesRead);
				bos.flush();
			}
						
			bos.flush();
			
			
			
			bos.close();						
			fout.close();			
			
			bis.close();
			fin.close();			
			
			f.delete(); //화일을 생성과 동시에 byte[]배열에 입력후 화일은 삭제
			
		}
	}

}
