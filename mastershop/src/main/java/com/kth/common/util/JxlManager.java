package com.kth.common.util;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

/**
 * jxl을 컨트롤 하는 클래스(특정 디자인을 위한 메소드가 많다)
 * @author 송영석
 * 
 */
 public class JxlManager 
 {  
	private int nRowCellCount       = 0;     // 현재 열의 위치
	
	private String strFilePath      = "";   
	private String strExcelFileName = "";
	private String strFileFullname  = "";
	
	private WritableWorkbook wwb            = null;
	private WritableSheet ws                = null;
	private WritableCellFormat  formatTitle = null;
	
	private File fSaveFile                  = null; 
	
	 
	//====================== Get Methode ========================================
	//
	public int getRowCellCount()	{		return nRowCellCount;	}	
	public String getFileFullName()	{		return strFileFullname;	}
	//
	//====================== Get Methode ========================================
	
	/**
	 * 생성자
	 * 
	 * @param _strFilePath      : 저장되는 Path
	 * @param _strExcelFileName : 저장되는 엑셀 파일 이름
	 * @param _strSheetName     : Sheet이름
	 * @throws Exception
	 */
	public JxlManager(String _strFilePath, String _strExcelFileName, String _strSheetName) throws Exception 
	{
		// 1.Create : Excel File Path
		strFilePath      = _strFilePath;
		strExcelFileName = _strExcelFileName;
		strFileFullname  =  strFilePath + strExcelFileName;
				
		// 2.Create : Workbook, Sheet
		wwb = Workbook.createWorkbook(new File(strFileFullname));		
		ws  = wwb.createSheet(_strSheetName, 0);
		
		
		// 3.Create : Header Format
		formatTitle= new WritableCellFormat();		
		formatTitle.setBackground(jxl.format.Colour.GRAY_25 );
		formatTitle.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN );
		formatTitle.setAlignment(jxl.format.Alignment.CENTRE);
	}
	
	/**
	 * 2차원 배열의 데이타를 표처럼 엑셀 파일에 쓴다.
	 * 
	 * @param aStrData  : 2차원 배열을의 데이타
	 * @throws Exception
	 */
	public void addData(String[][] aStrData) throws Exception 
	{
		if(aStrData == null) return;				
 
		for(int i=0; i<aStrData.length; i++, nRowCellCount++)
		{			   				 
			for(int j=0; j<aStrData[i].length; j++)
			{
				ws.addCell( new Label( j, nRowCellCount, aStrData[i][j]) ); 
			}
		}	
	}
	
	/**
	 * 셀 한개를 삽입한다.
	 * @param lData      : Label (Cell)
	 * @throws Exception
	 */
	public void addData(Label lData) throws Exception 
	{
		ws.addCell(lData);	
	}
	
	/**
	 * 셀 한개를 삽입한다.(셀 스타일을 설정할수 있다.)
	 *  
	 * @param lData   : 셀
	 * @param strFormatType :  들어오는 값에 따라, Cell의 스타일을 설정한다.(현재 'HEADER'만 된다.)
	 * @throws Exception
	 */
	public void addData(Label lData, String strFormatType) throws Exception 
	{
		if(strFormatType!=null && strFormatType.equals("HEADER"))
		{
			lData.setCellFormat(formatTitle);			
		}		
		 
		ws.addCell(lData);
	}
	
	/**
	 * 
	 * 2차원 배열의 데이타를 저장한다. (셀 스타일은 'Header'이다)
	 * 
	 * @param aStrHeaderData
	 * @throws Exception
	 */
	public void addHeader(String[][] aStrHeaderData) throws Exception 
	{
		if(aStrHeaderData == null) return;				
 
		for(int i=0; i<aStrHeaderData.length; i++, nRowCellCount++)
		{			   				 
			for(int j=0; j<aStrHeaderData[i].length; j++)
			{
				ws.addCell( new Label( j, nRowCellCount, aStrHeaderData[i][j], formatTitle) ); 
			}
		}	
	}
	
	/**
	 *  닫기전에 wirte를 꼭 해야지 저장이 된다.! 
	 */
	public void write() 
	{
		try 
		{
			wwb.write();
			wwb.close();
			wwb= null;
		} 
		catch (IOException e)	 {		}		
		catch (WriteException e) {		}
		finally 
		{
			if(wwb != null)
				try 
				{
					wwb.close();
					
				} 
				catch (WriteException e) {	} 
				catch (IOException e)    {	}			
		}
		
		wwb= null;		
	}	
	
	/**
	 *  현재 열에 +1을 해준다.
	 */
	public void addRowCellCount()
	{
		nRowCellCount++;
	}
	
	/**
	 * 현재 열에 addCount만큼 더해준다. 
	 * @param addCount 
	 */
	public void addRowCellCount(int addCount)
	{
		nRowCellCount += addCount;
	}
	
	/**
	 * 선택한 열의 사이즈를 조절한다.
	 * @param addCount 
	 */
	public void setColumnView(int nColum, int nSize)
	{
		ws.setColumnView(nColum, nSize); 
	}
}