<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
    <jsp:attribute name="stylesheet">
	    <style type="text/css">
	    	.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
	    	.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
	    </style>
    </jsp:attribute>
    
    <jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	
    	<script langauge="text/javascript">
			${script}
			
			var fd_menu_yn = '${ipACL.fd_menu_yn}';
			
	 		$(document).ready
	 		(
	 			function()
	 			{
	 				$('#fd_menu_yn').val(fd_menu_yn);
	 				
	 				$('#btnModify').click(function() { modify('modify'); } );
	 				$('#btnRemove').click(function() { modify('remove'); } );
	 				$('#btnList').click(function() { location.href="/manager/ipList.do"; } );
	 			}
	 		);
	 		
	 		function modify(event)
	 		{
	 			var form = document.form;
	 			
	 			if(validate(form))
 				{
	 				form.event.value = event;
 					form.submit();
 				}
	 		}
	 		
   		</script>
   	</jsp:attribute>

<jsp:body>
    <div>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="form" name="form" method="post" action="/manager/ipModify.do">
			<input type="hidden" name="event"/>
			<input type="hidden" name="pk_ip_acl" value="${ipACL.pk_ip_acl }"/>
			<tr height="0">
				<th width="150px"></th>
				<th width="*"></th>
			</tr>
			<tr height="30">
				<td colspan="2" class="item_input_border">
					<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:10px 10px 0 10px">
						<tr height="0">
							<th width="25px"></th>
							<th width="*"></th>
						</tr>
						<tr>
							<td><span class="ui-icon ui-icon-info"></span></td>
							<td>첫번째, 두번째 Octet은 필수 입력 값입니다.</td>
						<tr>
						<tr>
							<td><span class="ui-icon ui-icon-info"></span></td>
							<td>Octet에 입력 값이 없을 경우 0~255까지 모두 접근이 가능합니다.(세번째, 네번째만 설정 가능)</td>
						<tr>
						<tr>
							<td></td>
							<td>예) 211.155.4.공백 : IP 211.155.4.0~255 접근가능</td>
						<tr>
						<tr>
							<td><span class="ui-icon ui-icon-info"></span></td>
							<td>Octet은 32비트 IP 주소의 네 부분을 이루는 8비트의 숫자입니다.</td>
						<tr>																
						<tr height="10px"><td colspan="2"></td></tr>								
					</table>
				</td>				
			</tr>				
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>			
			<tr height="30">
				<td class="item_title_border">IP</td>
				<td class="item_input_border">
					<input type="text" id="fd_ip_1" name="fd_ip_1" maxlength="3" require="true" minlength="1" hname="IP 주소 첫번째 자리" value="${ipACL.fd_ip_1 }" style="width:70px" class="numOnly ipt_tx" /> - 
					<input type="text" id="fd_ip_2" name="fd_ip_2" maxlength="3" require="true" minlength="1" hname="IP 주소 두번째 자리" value="${ipACL.fd_ip_2 }" style="width:70px" class="numOnly ipt_tx" /> - 
					<input type="text" id="fd_ip_3" name="fd_ip_3" maxlength="3" hname="IP 주소 세번째 자리" value="${ipACL.fd_ip_3 }" style="width:70px" class="numOnly ipt_tx" /> - 
					<input type="text" id="fd_ip_4" name="fd_ip_4" maxlength="3" hname="IP 주소 네번째 자리" value="${ipACL.fd_ip_4 }" style="width:70px" class="numOnly ipt_tx" />  
				</td>				
			</tr>
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">설명</td>
				<td class="item_input_border">
					<input type="text" id="fd_explain" name="fd_explain" require="true" minlength="3" maxlength="100" hname="설명" value="${ipACL.fd_explain }" style="width:500px" class="ipt_tx" />
				</td>				
			</tr>				
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">사용여부</td>
				<td class="item_input_border">
					<select id="fd_menu_yn" name="fd_menu_yn" style="width:80px">
						<option value="Y">사용</option>
						<option value="N">미사용</option>
					</select>
				</td>				
			</tr>				
			</form>
		</table>
		<div class="btnArea">
			<a href="#none" id="btnModify" class="button">수정하기</a>
			<a href="#none" id="btnRemove" class="button">삭제하기</a>
			<a href="#none" id="btnList" class="button">목록보기</a>
		</div>
	</div>
</jsp:body>
</layout:common>