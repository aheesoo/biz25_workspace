<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>

<layout:common>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	
		<script language="javascript">
			$(document).ready
			(
				function()
				{
					$('#btnRegister').click(function(){location.href = '/manager/ipRegister.do';});
				}
			);
			
			function modify(pk_ip_acl)
			{
				var form = document.form;
				form.pk_ip_acl.value = pk_ip_acl;
				form.action = '/manager/ipModify.do';
				form.submit();
			}
		</script>
	</jsp:attribute>
	
	<jsp:body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="form" name="form" method="post">	
			<input type="hidden" name="pk_ip_acl" />
			</form>		
			<tr height="0">
				<td width="8%"></td>
				<td width="15%"></td>
				<td width="43%"></td>
				<td width="10%"></td>
				<td width="13%"></td>
				<td width="11%"></td>
			</tr>
			<tr height="25">
				<td class="item_title_border">번호</td>
				<td class="item_title_border">IP</td>
				<td class="item_title_border">설명</td>
				<td class="item_title_border">사용여부</td>			
				<td class="item_title_border">등록자 ID</td>			
				<td class="item_title_border">등록일</td>			
			</tr>	
				
			<!-- DYNAMIC AREA 'list' -->
			<c:if test="${!empty ipList}">
				<c:forEach var="ipACL" items="${ipList}" varStatus="status" >
				<tr height="1"><td colspan="6" bgcolor="#dfdfdf"></td></tr>
				<tr height="25" onmouseover="this.style.backgroundColor='#f7f7f7'" onmouseout="this.style.backgroundColor=''" bgcolor="white" class="action"> 	
					<td class="item_input_border" align="center">${totalCount - status.index }</td>
					<td class="item_input_border" align="center">&nbsp;
						${ipACL.fd_ip_1 }.
						${ipACL.fd_ip_2 }.
						<c:if test="${ipACL.fd_ip_3 == null}">*</c:if>
						<c:if test="${ipACL.fd_ip_3 != null}">${ipACL.fd_ip_3 }</c:if>.
						<c:if test="${ipACL.fd_ip_4 == null}">*</c:if>
						<c:if test="${ipACL.fd_ip_4 != null}">${ipACL.fd_ip_4 }</c:if>						
					</td>		
					<td class="item_input_border">
						&nbsp;<a onclick="modify(${ipACL.pk_ip_acl });" style="cursor:pointer;">${ipACL.fd_explain }</a>
					</td>		
					<td class="item_input_border" align="center">
						<c:if test="${ipACL.fd_menu_yn.trim() == 'Y' }">사용</c:if>
						<c:if test="${ipACL.fd_menu_yn.trim() == 'N' }">미사용</c:if>					
					</td>			
					<td class="item_input_border" align="center">${ipACL.fd_admin_id }</td>			
					<td class="item_input_border" align="center">
						<fmt:parseDate value="${ipACL.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
						<fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/>					
					</td>			
				</tr>
				</c:forEach>
			</c:if>			

			<c:if test="${empty ipList}">
				<tr height="1"><td colspan="6" bgcolor="#dfdfdf"></td></tr>
				<tr height="200" bgcolor="white"> 	
					<td class="item_input" align="center" colspan="6" width="200px"">&nbsp;등록된 IP 리스트가 없습니다</td>
				</tr>
			</c:if>				
		</table>           	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr height="25">
				<td style="text-align:center;padding:10px" >
					<input type="button" id="btnRegister" class="button" value="등록" style="width:50px" />
				</td>
			</tr>
		</table>		
	</jsp:body>

</layout:common>