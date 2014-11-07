<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:noFrame>
	<jsp:attribute name="stylesheet">

	</jsp:attribute>

	<jsp:attribute name="stylesheet">

	</jsp:attribute>

	<jsp:attribute name="javascript">
		<script langauge="text/javascript">	
			${script}
			$(document).ready(function() {
				//document.ifrmSmsTelList_form = parent.document.register_form;
			});
		</script>
		${pageNavi.script}
	</jsp:attribute>
	
<jsp:body>
		<table width="470" border="0" cellspacing="0" cellpadding="0">		
			<tr>
	            <td colspan="2" height="2" bgcolor="#222222"></td>
          	</tr>
			<tr>
				<td class="tb2_tit_c">No.</td>
				<td class="tb2_tit_c">전화 번호</td>
			</tr>
			<c:if test="${!empty customer_tel_list}">
				<c:forEach var="item" items="${customer_tel_list}" varStatus="status" >
					<tr> 	
						<td class="tb2_nor_l"><div style="text-align:center">${((pageNavi.nowPage - 1) * pageNavi.pageSize + status.index + 1) }</div></td>
						<td class="tb2_nor_l"><div style="text-align:center">${item.receive_number}</div></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty customer_tel_list}">
				<tr> 	
					<td class="tb2_nor_l" colspan="2">&nbsp;검색된 데이터가 없습니다</td>
				</tr>
			</c:if>
		</table>
		<div  class="pop_btn">${pageNavi.navi}</div>
</jsp:body>
</layout:noFrame>