<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<layout:common>
    <jsp:attribute name="stylesheet">    	
    </jsp:attribute>
    
    <jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   
    	<script langauge="javascript">
    		${script}
    		var fd_menu_yn = '${cmsMenu.fd_menu_yn}';
    		$(document).ready
    		(
    			function()
    			{   	
					if(fd_menu_yn != '')
					{
						$('#fd_use_un option').each
						(
							function()
							{
								if(this.value == fd_menu_yn)
									$(this).attr('selected', true);
							}
						);
					}
					
					$('#btnRegister').click(function(){ btnAction('register'); });
					$('#btnModify').click(function(){ btnAction('modify'); });
					
					$('#btnInit').click
					(
						function()
						{
							var form = document.form;
							form.pk_url_code.value = '';
							form.fd_name.value = '';
							form.fd_url.value = '';
							form.fd_menu_yn.value = 'Y';
							form.fd_sort_no.value = '';
							
							$('#btnRegister').show();
							$('#btnModify').hide();
						}
					);
					
					initBtns();										
    			}	
    		);
    		
    		function initBtns()
    		{
    			if(fd_menu_yn != '')
   				{
		   			$('#btnRegister').hide();
		   			$('#btnModify').show();
   				}
    			else
    			{
		   			$('#btnRegister').show();
		   			$('#btnModify').hide();    				
    			}
    		}
    		
    		function btnAction(event)
    		{
    			var form = document.form;
    			if(validate(form))
   				{
        			form.event.value = event;
        			form.action = '/manager/mainMenu.do';
        			form.submit();   				
   				}    			
    		}
    		
    		function listAction(pk_url_code, event)
    		{
    			var result = true;
    			if(event == "remove") { result = confirm("정말로 삭제하시겠습니까?\n하위 메뉴또한 같이 삭제됩니다."); }
    			
    			var form = document.listForm;
				form.event.value = event;
				
				if(event == "subMenu")
				{
					form.fd_group_code.value = pk_url_code;
					form.action = '/manager/subMenu.do';
				}
				else
				{
					form.pk_url_code.value = pk_url_code;
					form.action = '/manager/mainMenu.do';
				}
								
				if(result) form.submit();
    		}
    	</script>    	
    </jsp:attribute>
    
    <jsp:body>    
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="form" name="form" method="post">	
			<input type="hidden" name="event" />					
			<input type="hidden" name="org_pk_url_code" value="${cmsMenu.pk_url_code }" />					
			<tr height="0">
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="15%"></td>
				<td width="35%"></td>				
			</tr>
			<tr height="30">
				<td class="item_title_border">코드</td>
				<td class="item_input">
					<input type="text" id="pk_url_code" name="pk_url_code" maxlength="4" minbytes="4" msg="코드는 4자여야 합니다." class="numOnly" value="${cmsMenu.pk_url_code }">							
				</td>				
				<td class="item_title_border">이름</td>
				<td class="item_input">
					<input type="text" id="fd_name" name="fd_name" style="width:200px" minbytes="2" msg="이름은 2자이상 입력하셔야 합니다." value="${cmsMenu.fd_name}">								
				</td>					
			</tr>
			<tr><td height="1" colspan="4" bgcolor="#dfdfdf"></td></tr>	
			<tr height="30">
				<td class="item_title_border">경로</td>
				<td class="item_input">
					<input type="text" id="fd_url" name="fd_url" style="width:400px" value="${cmsMenu.fd_url}">							
				</td>				
				<td class="item_title_border">사용여부</td>
				<td class="item_input"> 
					<select id="fd_menu_yn" name="fd_menu_yn" style="width:100px">
						<option value="Y">사용</option>
						<option value="N">미사용</option>						
					</select>								
				</td>					
			</tr>			
			<tr><td height="1" colspan="4" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">순서</td>
				<td class="item_input" colspan="3">
					<input type="text" id="fd_sort_no" name="fd_sort_no" minbytes="1" msg="순서를 입력하셔야 합니다." class="numOnly" style="width:50px" value="${cmsMenu.fd_sort_no}">							
				</td>						
			</tr>	
			</form>															
		</table>	
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:0 0 0 0">				
			<tr height="30">
				<td align="right">
					<input id="btnRegister" type="Button" class="button" value="등록">
					<input id="btnModify" type="Button" class="button" value="수정">
					<input id="btnInit" type="Button" class="button" value="초기화">
				</td>
			</tr>
		</table>				
		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="listForm" name="listForm" method="post">
			<input type="hidden" name="event" />
			<input type="hidden" name="pk_url_code" />
			<input type="hidden" name="fd_group_code" />
			</form>
			<tr height="0">
				<td width="5%"></td>
				<td width="5%"></td>
				<td width="14%"></td>
				<td width="42%"></td>
				<td width="10%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
				<td width="8%"></td>
			</tr>
			<tr height="25">
				<td class="item_title_border">순서</td>
				<td class="item_title_border">코드</td>
				<td class="item_title_border">이름</td>
				<td class="item_title_border">경로</td>
				<td class="item_title_border">사용여부</td>			
				<td class="item_title_border">수정</td>			
				<td class="item_title_border">삭제</td>			
				<td class="item_title_border">하위메뉴</td>			
			</tr>	
				
			<!-- DYNAMIC AREA 'list' -->
			<c:if test="${!empty mainMList}">
				<c:forEach var="item" items="${mainMList}" varStatus="status" >
					<tr height="1"><td colspan="8" bgcolor="#dfdfdf"></td></tr>
					<tr height="25" onmouseover="this.style.backgroundColor='#f7f7f7'" onmouseout="this.style.backgroundColor=''" bgcolor="white" class="action"> 	
						<td class="item_input_border" align="center">&nbsp;${item.fd_sort_no }</td>
						<td class="item_input_border" align="center">&nbsp;${item.pk_url_code }</td>
						<td class="item_input_border" align="center">&nbsp;${item.fd_name }</td>
						<td class="item_input_border">&nbsp;${item.fd_url }</td>
						<td class="item_input_border" align="center">&nbsp;
							<c:if test="${item.fd_menu_yn.trim() == 'Y' }">사용</c:if>
							<c:if test="${item.fd_menu_yn.trim() == 'N' }">미사용</c:if>
						</td>
						<td class="item_input_border" align="center">&nbsp;<a onclick="listAction('${item.pk_url_code }', 'modifyForm');" style="cursor:pointer;">Click</a></td>			
						<td class="item_input_border" align="center">&nbsp;<a onclick="listAction('${item.pk_url_code }', 'remove');" style="cursor:pointer;">Click</a></td>			
						<td class="item_input_border" align="center">&nbsp;<a onclick="listAction('${item.pk_url_code }', 'subMenu');" style="cursor:pointer;">Click</a></td>			
					</tr>
				</c:forEach>
			</c:if>			

			<c:if test="${empty mainMList}">
				<tr height="1"><td colspan="8" bgcolor="#dfdfdf"></td></tr>
				<tr height="60" bgcolor="white"> 	
					<td class="item_input" align="center" colspan="8">&nbsp;등록된 메인메뉴가 없습니다</td>
				</tr>
			</c:if>				
		</table>           
    </jsp:body>
    
</layout:common>    