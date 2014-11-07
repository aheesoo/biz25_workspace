<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
	<jsp:attribute name="stylesheet">
		<style type="text/css">
			.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
			.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
			.ui-datepicker{ font-size: 13px; width: 230px; }
			.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 11px; }
			.ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 11px; }

			label.rintiantta {color:Red;}
			.alignC{text-align:center;}
			.alignC{text-align:center;}
			.alignR{text-align:right;}
			.report_area{
				position:absolute;
				top:10px;
				width:90%;
				padding:10px;
				background-Color:#b4b4b4;
				z-index:1000;
			 	display:none;
			}
			.report_area > div{
				background-Color:#fff;
			}
			.sendDay{color:red;}
		</style>
	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>
		<script src="/resources/js/jquery.ui.datepicker-ko.js"></script>
		<script langauge="text/javascript">	
			${script}

			function openTeb(tabName){
				
			}

			function area_view(area){
				if($('#'+area).css("display") != "none"){
					if(area!="result_area"){
						pop_background("off");
					}
					$('#'+area).hide();
				}else{
					if(area!="result_area"){
						pop_background("on");
					}
					$('#'+area).show();
				}
			}

			function pop_background(type){
				var $div = $('<div id="progressDiv_z" style="position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>');
 				if(type=="on"){
					$('#content').append($div);
 				}else{
 					$('#progressDiv_z').remove();﻿﻿ 
 				}
 			}
			
			$(document).ready(function() {
				 //parent.autoResize();
			});
				
		</script>
	</jsp:attribute>

<jsp:body>
	<div id="content">
	
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr height="0">
				<th width="130px"></th>
				<th width="*"></th>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_title_border">조회기간</td>
				<td class="item_input">
					<input type="button" width="60px" value="전일" />
					<input type="button" width="60px" value="당일" />
					<input type="button" width="60px" value="3일" />
					<input type="button" width="60px" value="1주일" />
					<input type="button" width="60px" value="1개월" />
					<input type="button" width="60px" value="3개월" /><br>
					<select id="start_y" name="start_m" style="width:55px;"></select>
					<select id="start_m" name="start_m" style="width:55px;"></select>
					<select id="start_d" name="start_d" style="width:55px;"></select>
					&nbsp;~&nbsp;
					<select id="finish_y" name="finish_m" style="width:55px;"></select>
					<select id="finish_m" name="finish_m" style="width:55px;"></select>
					<select id="finish_d" name="finish_d" style="width:55px;"></select>
				</td>
			</tr>
			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_title_border">월별조회</td>
				<td class="item_input">
					<select id="select_y" name="select_m" style="width:55px;"></select>
					<select id="select_m" name="select_m" style="width:55px;"></select>
					&nbsp;&nbsp;&nbsp;&nbsp; 특정 연월 선택 조회
				</td>
			</tr>
			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
		</table>
		<table width="100%">
			<tr>
				<td width="*"></td>
				<td width="10px"><input type="button" class="button" onclick="area_view('report_area')" value="리포트 조회"></td>
			</tr>
		</table>


<!--  POPUP 영역 - 시작  -->
<div id="report_area" class="report_area">
	<div>
		<h4>상세 결과 리포트</h4>
		<select>
			<option value="2014">2014년</option>
		</select>
		<select>
			<option value="06">06월</option>
		</select>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr height="0">
				<th width="100px"></th>
				<th width="40px"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
			</tr>
			
			<tr height="1"><td colspan="11" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30px">
				<td class="item_title" rowspan="3" colspan="2">일자</td>
				<td class="item_title" colspan="3">통화 수신</td>
				<td class="item_title" colspan="3">문자 발송</td>
				<td class="item_title" colspan="3">효과 분석(CALL 응답률)</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30px">
				<td class="item_title">오전</td>
				<td class="item_title">오후</td>
				<td class="item_title">합계</td>
				<td class="item_title">오전</td>
				<td class="item_title">오후</td>
				<td class="item_title">합계</td>
				<td class="item_title">미발송 CID</td>
				<td class="item_title">발송 CID</td>
				<td class="item_title">응답률</td>
			</tr>

			<tr height="1"><td colspan="11" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input" rowspan="9">2014년 6월</td>
				<td class="item_input sendDay">1일</td>
				<td class="item_input sendDay">10</td>
				<td class="item_input sendDay">22</td>
				<td class="item_input sendDay">32</td>
				<td class="item_input sendDay">0</td>
				<td class="item_input sendDay">200</td>
				<td class="item_input sendDay">200</td>
				<td class="item_input sendDay">15</td>
				<td class="item_input sendDay">17</td>
				<td class="item_input sendDay">8.5%</td>
			</tr>

			<tr height="1"><td colspan="10" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">2일</td>
				<td class="item_input">10</td>
				<td class="item_input">22</td>
				<td class="item_input">32</td>
				<td class="item_input">0</td>
				<td class="item_input">200</td>
				<td class="item_input">200</td>
				<td class="item_input">15</td>
				<td class="item_input">17</td>
				<td class="item_input">8.5%</td>
			</tr>

			<tr height="1"><td colspan="10" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input" colspan="10">...</td>
			</tr>

			<tr height="1"><td colspan="10" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">29일</td>
				<td class="item_input">10</td>
				<td class="item_input">22</td>
				<td class="item_input">32</td>
				<td class="item_input">0</td>
				<td class="item_input">200</td>
				<td class="item_input">200</td>
				<td class="item_input">15</td>
				<td class="item_input">17</td>
				<td class="item_input">8.5%</td>
			</tr>

			<tr height="1"><td colspan="10" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">30일</td>
				<td class="item_input">10</td>
				<td class="item_input">22</td>
				<td class="item_input">32</td>
				<td class="item_input">0</td>
				<td class="item_input">200</td>
				<td class="item_input">200</td>
				<td class="item_input">15</td>
				<td class="item_input">17</td>
				<td class="item_input">8.5%</td>
			</tr>
			<tr height="1"><td colspan="11" bgcolor="#F2DAB4"></td><td></td></tr>
		</table>

		<h4>문자 발송 내역</h4>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr height="0">
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
				<th width="*"></th>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_title">발송 일자</td>
				<td class="item_title">SMS</td>
				<td class="item_title">LMS</td>
				<td class="item_title">MMS</td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">2014-06-01</td>
				<td class="item_input">[수피자]월드컴 한국의 첫 번째 경기 !!...</td>
				<td class="item_input"></td>
				<td class="item_input"></td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">2014-06-29</td>
				<td class="item_input"></td>
				<td class="item_input">[수피자]월드컴 한국의 첫 번째 경기 !!...</td>
				<td class="item_input"></td>
			</tr>

			<tr height="1"><td colspan="4" bgcolor="#F2DAB4"></td><td></td></tr>
			<tr height="30">
				<td class="item_input">2014-06-30</td>
				<td class="item_input"></td>
				<td class="item_input"></td>
				<td class="item_input">[수피자]월드컴 한국의 첫 번째 경기 !!...</td>
			</tr>
		</table>
		
		<h4 onclick="area_view('result_area')" style="cursor:pointer">전체 리포트 결과 보기 ▼</h4>
		<div id="result_area">
			검색결과 나와요
		</div>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr height="0">
				<td class="item_input alignC" width="*"><input type="button" class="button" onclick="area_view('report_area');" value="확인"/></td>
			</tr>
		</table>
	</div>
</div>
<!--  POPUP 영역 - 끝 (report_area) -->

<br>
<br>
	</div>
</jsp:body>
</layout:noFrame>