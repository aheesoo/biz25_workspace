<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>

<layout:common>
    <jsp:attribute name="stylesheet">
	    <style type="text/css">
	    	.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
	    	.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
			.ui-datepicker{ font-size: 13px; width: 230px; }
	 		.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 11px; }
			.ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 11px; }    	
	    </style>
    </jsp:attribute>

	<jsp:attribute name="javascript">
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	
		<script src="/resources/js/jquery.ui.datepicker-ko.js"></script>
		<script language="javascript">
			var searchColumn = '${param.searchColumn}';
			var search_cms_main_menu = '${param.search_cms_main_menu}';
			var search_cms_sub_menu = '${param.search_cms_sub_menu}';
			
			$(document).ready
			(
				function()
				{
					$('#search_cms_main_menu').val(search_cms_main_menu);
					$('#search_cms_sub_menu').val(search_cms_sub_menu);
					if(searchColumn != '') $('#searchColumn').val(searchColumn);
 					$('#search_start, #search_finish').datepicker();
 					
 					$('#search_cms_main_menu').change(initSubMenu);
					$('#btnSearch').click(search); 
				}
			);
			
			function initSubMenu()
			{
				$('#search_cms_sub_menu option').each
				(
					function()
					{
						if($(this).val() != '')
						{
							$(this).remove();
						}
					}
				);
				
				var fd_group_code = $('#search_cms_main_menu').val();
				if(fd_group_code != '')
				{				
					$.getJSON('/manager/initSubMenuList.do', {'fd_group_code' : fd_group_code})
					.done
					(
						function(result)
						{
							$.each
							(
								result, 
								function(i, item)
								{
									$('#search_cms_sub_menu').append('<option value="' + item.pk_url_code + '">' + item.fd_name + '</option>');	
								}
							);
						}
					)
					.fail
					(
						function( jqxhr, textStatus, error ) 
						{
						    var err = textStatus + ", " + error;
						    alert( "서브메뉴 초기화 실패 : " + err );
						}							
					);
				}
			}
			
			function search()
			{
				var form = document.form;
				form.action = location.pathname;
				form.submit();
			}
			
			function view(pk_cms_log)
			{
				var form = document.form;
				form.pk_cms_log.value = pk_cms_log;
				form.action = '/manager/logView.do';
				form.submit();
			}			
		</script>
		
		${pageNavi.script }
	</jsp:attribute>
	
	<jsp:body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="form" name="form" method="post">	
			<input type="hidden" id="pk_cms_log" name="pk_cms_log" />
			<input type="hidden" id="page" name="page" value="${param.page }" />			
			<tr height="0">
				<td width="15%"></td>
				<td width="35%"></td>
				<td width="15%"></td>
				<td width="35%"></td>				
			</tr>
			<tr height="30">
				<td class="item_title_border">메인메뉴</td>
				<td class="item_input">
					<select id="search_cms_main_menu" name="search_cms_main_menu" style="width:160px">
						<option value="">전체</option>					
					<c:forEach var="mainMenu" items="${mainMenuList }">
						<option value="${mainMenu.pk_url_code }">${mainMenu.fd_name }</option>
					</c:forEach>						
					</select>
				</td>				
				<td class="item_title_border">서브메뉴</td>
				<td class="item_input">
					<select id="search_cms_sub_menu" name="search_cms_sub_menu" style="width:160px">
						<option value="">전체</option>				
					<c:forEach var="subMenu" items="${subMenuList }">
						<option value="${subMenu.pk_url_code }">${subMenu.fd_name }</option>
					</c:forEach>													
					</select>	
				</td>					
			</tr>
			<tr><td height="1" colspan="4" bgcolor="#dfdfdf"></td></tr>	
			<tr height="30">
				<td class="item_title_border">검색대상</td>
				<td class="item_input">
					<select id="searchColumn" name="searchColumn" style="width:128px;">
						<option value="fd_admin_id">아이디</option>
						<option value="fd_class_name">클래스 명</option>									
						<option value="fd_method_name">메서드 명</option>										
					</select>		
					<input type="text" id="searchString" name="searchString" value="${param.searchString }">
				</td>				
				<td class="item_title_border">기간</td>
				<td class="item_input">
					<input type="text" id="search_start" name="search_start" require="true" ispattern="date" hname="검색 시작일" value="${param.search_start }" class="ipt_tx" style="width:80px">
					~ 
					<input type="text" id="search_finish" name="search_finish" require="true" ispattern="date" hname="검색 종료일" value="${param.search_finish }" class="ipt_tx" style="width:80px">
					&nbsp;&nbsp;
					<input type="button" id="btnSearch" class="button" value="검색" style="width:50px" />			
				</td>					
			</tr>			
			</form>															
		</table>				
		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<tr height="0">
				<td width="5%"></td>
				<td width="12%"></td>
				<td width="14%"></td>
				<td width="34%"></td>
				<td width="9%"></td>
				<td width="7%"></td>
				<td width="9%"></td>
				<td width="10%"></td>
			</tr>
			<tr height="25">
				<td class="item_title_border">번호</td>
				<td class="item_title_border">메인메뉴</td>
				<td class="item_title_border">서브메뉴</td>
				<td class="item_title_border">클래스 명</td>			
				<td class="item_title_border">메서드 명</td>			
				<td class="item_title_border">아이디</td>
				<td class="item_title_border">접속 IP</td>			
				<td class="item_title_border">접속 시간</td>			
			</tr>	
				
			<!-- DYNAMIC AREA 'list' -->
			<c:if test="${!empty logList}">
				<c:forEach var="log" items="${logList}" varStatus="status" >
				<tr height="1"><td colspan="8" bgcolor="#dfdfdf"></td></tr>
				<tr height="25" onmouseover="this.style.backgroundColor='#f7f7f7'" onmouseout="this.style.backgroundColor=''" bgcolor="white" class="action"> 	
					<td class="item_input_border" align="center">${pageNavi.totalCount - ((pageNavi.nowPage - 1) * pageNavi.pageSize + status.index) }</td>
					<td class="item_input_border" align="center">${log.fd_cms_main_menu_name }</td>
					<td class="item_input_border" align="center">${log.fd_cms_sub_menu_name }</td>
					<td class="item_input_border">
						<a onclick="view(${log.pk_cms_log });" style="cursor:pointer;">${log.fd_class_name }</a>
					</td>					
					<td class="item_input_border">
						<a onclick="view(${log.pk_cms_log });" style="cursor:pointer;">${log.fd_method_name }</a>
					</td>
					<td class="item_input_border" align="center">${log.fd_admin_id }</td>
					<td class="item_input_border" align="center">${log.fd_access_ip }</td>								
					<td class="item_input_border" align="center">
						<fmt:parseDate value="${log.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
						<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm:ss"/>						
					</td>			
				</tr>
				</c:forEach>
			</c:if>			

			<c:if test="${empty logList}">
				<tr height="1"><td colspan="8" bgcolor="#dfdfdf"></td></tr>
				<tr height="60" bgcolor="white"> 	
					<td class="item_input" align="center" colspan="10">&nbsp;검색된 로그이력이 없습니다</td>
				</tr>
			</c:if>				
			<tr height="1"><td colspan="8" bgcolor="#dfdfdf"></td></tr>
			<tr height="30"> 	
				<td align="center" colspan="8">
					${pageNavi.navi}
				</td>
			</tr>			
		</table>           	
	</jsp:body>
		
</layout:common>