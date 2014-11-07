<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<script src="/resources/js/jquery-1.11.0.js"></script>
	<script langauge="javascript">
	 $(document).ready(function(){

	 }); 
	 
	 function goModify() {
		var form = document.form;
		form.action = '/member/memberJoinInfo.do'; 
		form.method = "post";
		form.event.value = "modify";
		form.submit();
	 }
	</script>
</head>
<body>
	<table width="100%" style="BORDER: none;">
		<form id="form" name="form" method="get">
			<input type="hidden" id="event" name="event" value=""/>
		</form>
		<tr>
			<td style="text-align:left">&nbsp;고객정보</td>
		</tr>
		<tr>
			<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B  1px solid; padding:0 0 0 0">
						<tr height="0">
							<td width="15%"></td>
							<td width="35%"></td>				
							<td width="15%"></td>
							<td width="35%"></td>
						</tr>	
						<tr height="25">
							<td class="item_title_border">&nbsp;상호 (성명)</td>
							<td class="item_input_border" align="left">&nbsp;${contents.fd_user_name}</td>
							<td class="item_title_border">&nbsp;업종</td>
							<td class="item_input" align="left">&nbsp;${contents.fd_business_type}</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border">&nbsp;사업자번호</td>
							<td class="item_input_border" align="left">&nbsp;${contents.fd_corp_regist_num}</td>
							<td class="item_title_border">&nbsp;주민(법인)번호</td>
							<td class="item_input" align="left">&nbsp;${contents.fd_regist_num}</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border" rowspan="2">&nbsp;일반전화번호</td>
							<td class="item_input_border" rowspan="2" align="left">&nbsp;${contents.pk_tel}</td>
							<td class="item_title_border" rowspan="2">&nbsp;휴대전화번호</td>
							<td class="item_input" align="left">&nbsp;${contents.fd_mobile}</td>
						</tr>
						<tr height="30">
							<td>&nbsp;* 예약 설정된 발송내역은 수정하기 이전 번호로 적용이 됩니다.</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border" rowspan="2">&nbsp;주소</td>
							<td class="item_input" colspan="3">&nbsp;${contents.fd_post_num}</td>
						</tr>
						<tr height="30">
							<td class="item_input" colspan="4">${contents.fd_addr}&nbsp;${contents.fd_addr_detail}</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border">&nbsp;아이디</td>
							<td class="item_input_border" align="left">&nbsp;${contents.pk_member_id}</td>
							<td class="item_title_border">&nbsp;비밀번호</td>
							<td class="item_input" align="left"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan="3" style="height:20px"></td></tr>
			<tr>
				<td style="text-align:right">
					<input type="button" id="okBtn" class="button" value="수정" onclick="goModify();" style="width:75px" />
				</td>
			</tr>
		</table>
	</body>
</html>