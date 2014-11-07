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
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_title_border">날짜</td>
				<td class="item_title_border">결제방식</td>
				<td class="item_title_border">충전 포인트</td>
				<td class="item_title_border">보너스 포인트</td>
				<td class="item_title_border">합계 포인트</td>
				<td class="item_title">결제 금액</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">6월 18일</td>
				<td class="item_input_border alignC">휴대폰</td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">0Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input alignC">11,000원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">6월 18일</td>
				<td class="item_input_border alignC">휴대폰</td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">0Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input alignC">11,000원</td>
			</tr>

			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
			<tr height="30">
				<td class="item_input_border alignC">6월 18일</td>
				<td class="item_input_border alignC">휴대폰</td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">0Coin</span></td>
				<td class="item_input_border alignC"><span width="85%">1,000Coin</span></td>
				<td class="item_input alignC">11,000원</td>
			</tr>
			<tr height="1"><td colspan="9" bgcolor="#F2DAB4"></td></tr>
		</table>
		&nbsp;&nbsp;* 최근 6개월 이내의 포인트 충전내역이 표시됩니다.

<br>


	</div>
</jsp:body>
</layout:noFrame>