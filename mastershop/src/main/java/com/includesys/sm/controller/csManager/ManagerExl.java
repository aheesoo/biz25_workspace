package com.includesys.sm.controller.csManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.includesys.sm.util.JxlSpinglManager;
import com.includesys.sm.util.StrUtils;

import com.includesys.sm.dto.csManager.CsCustomer;
import com.includesys.sm.service.csManager.CsCustomerService;

public class ManagerExl extends AbstractJExcelView {

	@Autowired
	private HttpSession session;

	@Autowired
	private CsCustomerService csCustomerService;

	protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String exl_chk				 = (String) model.get("exl_chk");

		String fileName = createFileName();
		setFileNameToResponse(request, response, fileName);

		JxlSpinglManager jxlmng = new JxlSpinglManager();
		jxlmng.createSheet(workbook.createSheet("sheet", 0));

		if(exl_chk.equals("cs")){											//고객관리
			List<CsCustomer> itemArray	 = (List<CsCustomer>) model.get("itemList");

			String[][] aaStrHeader = { {"이름", "전화번호", "주소", "최근 통화일시", "총 통화건수", "발송건수", "수신거부"} };
			String[][] excelData = new String[itemArray.size()][7];
			for (int i = 0; i < itemArray.size(); i++) {
				excelData[i][0] = (itemArray.get(i).getFd_user_name() == null)		? ""	 : itemArray.get(i).getFd_user_name();
				excelData[i][1] = (itemArray.get(i).getPk_customer_tel() == null)	? ""	 : StrUtils.formatPhoneNo(itemArray.get(i).getPk_customer_tel().toString(), "-");
				excelData[i][2] = (itemArray.get(i).getFd_addr() == null)			? ""	 : itemArray.get(i).getFd_addr();
				excelData[i][3] = (itemArray.get(i).getFd_last_date() == null)		? ""	 : itemArray.get(i).getFd_last_date();
				excelData[i][4] = (itemArray.get(i).getFd_call_cnt() == null)		? "0"	 : itemArray.get(i).getFd_call_cnt();
				excelData[i][5] = (itemArray.get(i).getFd_sms_cnt() == null)		? "0"	 : itemArray.get(i).getFd_sms_cnt();
				excelData[i][6] = (itemArray.get(i).getFd_rev_sms_flag() == null)	? ""	 : (itemArray.get(i).getFd_rev_sms_flag().toString().equals("Y"))?"거부": "-";
			}
			
			jxlmng.addHeader(aaStrHeader); // 여러셀 추가 (Header Type)
			jxlmng.addData(excelData); // 여러셀 추가 (일반)

		}else if(exl_chk.equals("tel")){									// 통화관리

			List<Map<String, Object>> itemArray	 = (List<Map<String, Object>>) model.get("itemList");

			String[][] aaStrHeader = { {"발신번호", "이름", "주소", "수신번호", "통화일시", "비고"} };
			String[][] excelData = new String[itemArray.size()][7];
			for (int i = 0; i < itemArray.size(); i++) {
				String fd_tel				 = (String) ((itemArray.get(i).get("fd_tel"				) == null ) ? "" : (String) (itemArray.get(i).get("fd_tel"				)));
				String fd_user_name			 = (String) ((itemArray.get(i).get("fd_user_name"		) == null ) ? "" : (String) (itemArray.get(i).get("fd_user_name"		)));
				String fd_addr				 = (String) ((itemArray.get(i).get("fd_addr"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_addr"				)));
				String fd_rcv_tel			 = (String) ((itemArray.get(i).get("fd_rcv_tel"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_rcv_tel"			)));
				String fd_reg_yy			 = (String) ((itemArray.get(i).get("fd_reg_yy"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_yy"			)));
				String fd_reg_mm			 = (String) ((itemArray.get(i).get("fd_reg_mm"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_mm"			)));
				String fd_reg_dd			 = (String) ((itemArray.get(i).get("fd_reg_dd"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_dd"			)));
				String fd_reg_hh			 = (String) ((itemArray.get(i).get("fd_reg_hh"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_hh"			)));
				String fd_reg_mi			 = (String) ((itemArray.get(i).get("fd_reg_mi"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_mi"			)));
				String fd_reg_ss			 = (String) ((itemArray.get(i).get("fd_reg_ss"			) == null ) ? "" : (String) (itemArray.get(i).get("fd_reg_ss"			)));
				String fd_openapi_rc_code	 = (String) ((itemArray.get(i).get("fd_openapi_rc_code"	) == null ) ? "" : (String) (itemArray.get(i).get("fd_openapi_rc_code"	)));

				//통화일시
				String tel_time = fd_reg_yy+"."+fd_reg_mm+"."+fd_reg_dd+" "+fd_reg_hh+":"+fd_reg_mi+":"+fd_reg_ss;
				//비고
				String openApiCodeTxt = "";
				switch (fd_openapi_rc_code) {
					case "401":	openApiCodeTxt = "결번"; break;
					case "405":	openApiCodeTxt = "무응답"; break;
					case "701":	openApiCodeTxt = "연결중"; break;
					case "201":	openApiCodeTxt = "통화중"; break;
					case "404":	openApiCodeTxt = "통화중"; break;
					case "700":	openApiCodeTxt = "통화중"; break;
					case "202":	openApiCodeTxt = "부재중"; break;
					case "203":	openApiCodeTxt = "부재중"; break;
					case "407":	openApiCodeTxt = "부재중"; break;
					case "408":	openApiCodeTxt = "부재중"; break;
					case "904":	openApiCodeTxt = "부재중"; break;
					case "905":	openApiCodeTxt = "부재중"; break;
					case "906":	openApiCodeTxt = "부재중"; break;
					case "907":	openApiCodeTxt = "부재중"; break;
					default:break;
				}
				
				excelData[i][0] = fd_tel;
				excelData[i][1] = fd_user_name;
				excelData[i][2] = fd_addr;
				excelData[i][3] = fd_rcv_tel;
				excelData[i][4] = tel_time;
				excelData[i][5] = openApiCodeTxt;
			}
			
			jxlmng.addHeader(aaStrHeader); // 여러셀 추가 (Header Type)
			jxlmng.addData(excelData); // 여러셀 추가 (일반)
			
		}
	}

	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) {
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE 5.5") >= 0) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		}
	}

	private String createFileName() {
		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return new StringBuilder("eventEntry").append("_").append(fileFormat.format(new Date())).append(".xls").toString();
	}
}
