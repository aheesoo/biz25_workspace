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
		</script>
	</jsp:attribute>

<jsp:body>
	<div id="content">

		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="10%"></th>
				<th width="*%"></th>
			</tr>

			<tr height="30">
				<td class="item_input alignC">명세서 선택</td>
				<td class="item_input alignC">
					<select>
						<option value="">02-5555-0000</option>
					</select>
				</td>
				<td class="item_input alignC">
					<select>
						<option value="">2014년 06월</option>
					</select>
				</td>
				<td class="item_input alignC"><input type="button" value="조회"/></td>
				<td></td>
			</tr>
			<tr height="1"><td colspan="5" bgcolor="#F2DAB4"></td></tr>
		</table>

		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="50%"></th>
				<th width="*%"></th>
				<th width="10%"></th>
			</tr>
			<tr>
				<td>2014년 06월 요금 상세내역</td>
				<td></td>
				<td>
					<input type="button" value="인쇄"/>
					<input type="button" value="엑셀저장"/>
				</td>
			</tr>
		</table>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="50%"></th>
				<th width="50%"></th>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">구분</td>
				<td class="item_title_border">금액</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">월정액</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">선불권</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">광고의뢰</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">추가 문자발송</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">당월요금계</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">미납요금계</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">총청구금액</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>
			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
		</table>
<br>
		&nbsp;&nbsp;포인트 내역
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="0">
				<th width="50%"></th>
				<th width="50%"></th>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">구분</td>
				<td class="item_title_border">포인트</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">이월 포인트</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">구매 포인트</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">사용 포인트</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">잔여 포인트</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border">다음달 이월 포인트</td>
				<td class="item_input_border alignR">10,000 원</td>
			</tr>
			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
		</table>

<br>


	</div>
</jsp:body>
</layout:noFrame>