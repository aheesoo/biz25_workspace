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
		</style>
	</jsp:attribute>

	<jsp:attribute name="stylesheet">
		<style>
			label.rintiantta {color:Red;}
			.alignC{text-align:center;}
			.alignR{text-align:right;}
			.send_area{
				position:absolute;
				top:10px;
				width:90%;
				padding:10px;
				background-Color:#b4b4b4;
				z-index:1000;
			 	display:none;
			}
			.send_area > div{
				background-Color:#fff;
			}
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
					pop_background("off");
					$('#'+area).hide();
				}else{
					$('#'+area).show();
					pop_background("on");
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


		</script>
	</jsp:attribute>

<jsp:body>
	<div id="content">

		<table width="100%">
			<tr>
				<td width="50%">
					<select>
						<option value="2014-06">2014년 06월</option>
					</select>
				</td>
				<td class="alignR" width="50%">
					<input type="button" class="button" onclick="area_view('send_area')" value="문자 제작"/>
				</td>
			</tr>
		</table>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">등록일</td>
				<td class="item_title_border">발송(예약)일</td>
				<td class="item_title_border">타입</td>
				<td class="item_title_border">구분</td>
				<td class="item_title_border">발송건수</td>
				<td class="item_title_border">성공건수</td>
				<td class="item_title_border">실패건수</td>
				<td class="item_title_border">소진 포인트</td>
				<td class="item_title">문자발송</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>


			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>


			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">2014.06.02</td>
				<td class="item_input_border alignC">2014.06.18</td>
				<td class="item_input_border alignC">SMS</td>
				<td class="item_input_border alignC">예약</td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">45</span></td>
				<td class="item_input_border alignC"><span width="85%">145</span></td>
				<td class="item_input_border alignC"><span width="85%">15</span></td>
				<td class="item_input alignC">취소하기</td>
			</tr>
			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
		</table>

<br>

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>

			<tr height="1"><td colspan="8" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">총 발송건수</td>
				<td class="item_input_border alignC">648</td>
				<td class="item_title_border">총 성공건수</td>
				<td class="item_input_border alignC">644</td>
				<td class="item_title_border">총 실패건수</td>
				<td class="item_input_border alignC">4</td>
				<td class="item_title_border">총 소진 포인트</td>
				<td class="item_input alignC">5,794</td>
			</tr>
			<tr height="1"><td colspan="8" bgcolor="#F2DAB4"></td></tr>
		</table>

<br>
<br>

<!--  POPUP 영역 - 시작  -->
<div id="send_area" class="send_area">
	<div>


		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="130px"></th>
				<th width="*%"></th>
				<th width="130px"></th>
				<th width="*%"></th>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">발송날짜</td>
				<td class="item_input" colspan="3">
					<select>
						<option value="2014">2014년</option>
					</select>
					<select>
						<option value="07">07월</option>
					</select>
					<select>
						<option value="01">01일</option>
					</select>
				</td>
				<td class="item_input" colspan='3'>
					<label><input type="checkbox" />반복 &nbsp; &nbsp; 매월 <span>01</span>일</label>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border" rowspan="15">대상추출</td>
				<td class="item_input">추출기간</td>
				<td class="item_input" colspan="5">
					<label><input type="radio"> 1개월</label>&nbsp;&nbsp;&nbsp;
					<label><input type="radio"> 3개월</label>&nbsp;&nbsp;&nbsp;
					<label><input type="radio"> 6개월</label>
				</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input" rowspan="11">추출조건</td>
				<td class="item_input" rowspan="3">요일</td>
				<td class="item_input" colspan="4" colspan="4">
					<label><input type="checkbox" />전체</label>
					<label><input type="checkbox" />주중</label>
					<label><input type="checkbox" />주말</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input" colspan="4">
					<label><input type="checkbox" />월요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />화요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />수요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />목요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />금요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />토요일</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />일요일</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">시간대</td>
				<td class="item_input" colspan="5">
					<label><input type="checkbox" />전체</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />새벽</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />아침</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />점심</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />저녁</label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" />밤</label>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">수신횟수</td>
				<td class="item_input" colspan="4">
					<input type="text" />건 &nbsp;&nbsp;&nbsp;
					<select>
						<option>이하</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">발송횟수</td>
				<td class="item_input" colspan="4">
					<input type="text" />건 &nbsp;&nbsp;&nbsp;
					<select>
						<option>이하</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="5" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_input">발송건수</td>
				<td class="item_input" colspan="4"><input type="text" />건</td>
			</tr>

			<tr height="1"><td colspan="6" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td></td>
				<td>
				145건
				</td>
				<td class="item_input" colspan="4"><input type="button" class="button" value="인쇄"/>&nbsp;&nbsp;<input type="button" class="button" value="엑셀저장"/></td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">상담 메모</td>
				<td class="item_input" colspan="6">
					상담 말머리 &nbsp;&nbsp;<input type="text"/><br/>
					<textarea rows="5" cols="60"></textarea>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="30">
				<td class="item_title_border">발신번호</td>
				<td class="item_input" colspan="6">
					<select>
						<option>02-561-1511</option>
					</select>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>

			<tr height="30">
				<td class="item_title_border">타입</td>
				<td class="item_input" colspan="2">
					<select>
						<option>SMS</option>
					</select>
				</td>
				<td colspan="4">
					<textarea rows="5" cols="60"></textarea> &nbsp;&nbsp;<input type="button" class="button" value="추천문구"/>
				</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>
			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>

			<tr height="30">
				<td class="item_title_border">포인트</td>
				<td class="item_input" colspan="6">286Coin(예정)</td>
			</tr>

			<tr height="1"><td colspan="7" bgcolor="#EDCE9B"></td></tr>
			<tr height="10px"><td colspan="7"></td></tr>

			<tr height="30">
				<td class="item_input alignR" width="10px"colspan="7">
					<input type="button" class="button" value="등록하기"/>
					<input type="button" class="button" onclick="area_view('send_area');" value="취소"/>
				</td>
			</tr>
		</table>

	</div>
</div>
<!--  POPUP 영역 - 끝  -->

	</div>
</jsp:body>
</layout:noFrame>