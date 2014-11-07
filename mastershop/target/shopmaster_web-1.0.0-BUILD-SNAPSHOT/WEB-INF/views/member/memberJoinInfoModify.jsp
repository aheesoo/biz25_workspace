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
	${script}
	var zipcode = '${contents.fd_post_num}';
	 $(document).ready(function(){
		 $('#zipcode1').val(zipcode.substring(0, 3));
		 $('#zipcode2').val(zipcode.substring(4, zipcode.length));
	 }); 
	 
	 function modifySubmit() {
		/* var form = document.form;
		form.action = '/member/memberJoinInfo.do'; 
		form.method = "post";
		form.submit(); */
		 location.href = '/member/memberJoinInfo.do'; 
	 }
	 
	 function goMemberJoinInfo() {
		 location.href = '/member/memberJoinInfo.do'; 
	 }
	</script>
</head>
<body>
	<table width="100%" style="BORDER: none;">
		<form id="form" name="form" method="get">
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
							<td class="item_input" align="left">
								<input TYPE="text" id="mobile" name="mobile" value="${contents.fd_mobile}" size="15"/>
							</td>
						</tr>
						<tr height="30">
							<td>&nbsp;* 예약 설정된 발송내역은 수정하기 이전 번호로 적용이 됩니다.</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border" rowspan="2">&nbsp;주소</td>
							<td class="item_input" colspan="3">
								<input TYPE="text" id="zipcode1" name="zipcode1" value="" size="5"/>
								&nbsp;-&nbsp;
								<input TYPE="text" id="zipcode2" name="zipcode2" value="" size="5"/>
								<input type="button" id="zipcodeBtn" class="button" value="우편번호검색" style="width:100px" />
							</td>
						</tr>
						<tr height="30">
							<td class="item_input" colspan="4">
								<input TYPE="text" id="add1" name="add1" value="${contents.fd_addr}" size="30"/>
								<input TYPE="text" id="add2" name="add2" value="${contents.fd_addr_detail}" size="15"/>
							</td>
						</tr>
						<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
						<tr height="30">
							<td class="item_title_border">&nbsp;아이디</td>
							<td class="item_input_border" align="left">&nbsp;${contents.pk_member_id}</td>
							<td class="item_title_border">&nbsp;비밀번호</td>
							<td class="item_input" align="left">
								&nbsp;************&nbsp;&nbsp;
								<input type="button" id="pwModify" class="button" value="비밀번호변경" style="width:100px" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan="3" style="height:20px"></td></tr>
			<tr>
				<td style="text-align:right" colspan="2">
					<input type="button" id="okBtn" class="button" value="확인" onclick="modifySubmit();" style="width:75px" />
					<input type="button" id="cancelBtn" class="button" value="취소" onclick="goMemberJoinInfo();" style="width:75px" />
				</td>
			</tr>
			</form>
		</table>
	</body>
</html>