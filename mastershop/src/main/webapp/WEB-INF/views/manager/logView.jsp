<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>

<layout:common>

    <jsp:attribute name="stylesheet">
	    <style type="text/css">
	    	.btnArea{width:255px;margin:0 auto;text-align:center;padding:5px 0}
	    	.btnArea a{display:inline-block; padding:5px 13px 3px 13px}
	    </style>
    </jsp:attribute>
        
    <jsp:attribute name="javascript">.
		<script src="/resources/js/commonLibrary.js"></script>
		<script src="/resources/js/validate.js"></script>   	
    	<script langauge="text/javascript">
    		$(document).ready
    		(
    			function()
    			{
    				$('#btnList').click
    				(
    					function() 
    					{ 
    						var form = document.transForm;
    						form.action = '/manager/logList.do';
    						form.submit();
    					} 
    				);
    			}
    		);
   		</script>
   	</jsp:attribute>

	<jsp:body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #dcdcdc 1px solid; padding:0 0 0 0">
			<form id="transForm" name="transForm" method="post">
			<input type="hidden" id="page" name="page" value="${param.page }" />			
			<input type="hidden" id="search_cms_main_menu" name="search_cms_main_menu" value="${param.search_cms_main_menu }" />
			<input type="hidden" id="search_cms_sub_menu" name="search_cms_sub_menu" value="${param.search_cms_sub_menu }" />
			<input type="hidden" id="searchColumn" name="searchColumn" value="${param.searchColumn }" />
			<input type="hidden" id="searchString" name="searchString" value="${param.searchString }" />
			<input type="hidden" id="search_start" name="search_start" value="${param.search_start }" />
			<input type="hidden" id="search_finish" name="search_finish" value="${param.search_finish }" />					
			</form>			
			<tr height="0">
				<th width="160px"></th> 
				<tH width="*"></th>
			</tr>
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">아이디 / 접속 IP</td>
				<td class="item_input_border">&nbsp;${log.fd_admin_id } / ${log.fd_access_ip }</td>				
			</tr>			
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">접속 시간</td>
				<td class="item_input_border">
					<fmt:parseDate value="${log.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
					<fmt:formatDate value="${dateFmt}" pattern="yyyy-MM-dd HH:mm:ss"/>									
				</td>				
			</tr>
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">메뉴</td>
				<td class="item_input_border">&nbsp;${log.fd_cms_main_menu_name } > ${log.fd_cms_sub_menu_name }</td>				
			</tr>				
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">클래스 명</td>
				<td class="item_input_border">&nbsp;${log.fd_class_name }</td>				
			</tr>				
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">메서드 명</td>
				<td class="item_input_border">&nbsp;${log.fd_method_name }</td>				
			</tr>				
			<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>
			<tr height="30">
				<td class="item_title_border">파라메터</td>
				<td class="item_input_border">
				<c:if test="${!empty logParamList}">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="padding:0 0 0 0">
						<tr height="30px">
							<td style="width:150px" align="center" class="item_title_border">Name</td>
							<td align="center" class="item_title_border">Value</td>
						</tr>
					<c:forEach var="logParam" items="${logParamList }">
						<tr height="1"><td colspan="2" bgcolor="#dfdfdf"></td></tr>					
						<tr height="30px">
							<td style="width:150px" class="item_input_border">
								${logParam.fd_param_name } <c:if test="${logParam.fd_param_type == 2 }"><br />[배열]</c:if>
							</td>
							<td class="item_input_border">${logParam.fd_param_value }</td>
						</tr>
					</c:forEach>
					</table>
				</c:if>					
				</td>				
			</tr>			
			</form>
		</table>
		<div class="btnArea">
			<a href="#none" id="btnList" class="button">목록보기</a>
		</div>		
	</jsp:body>
</layout:common>