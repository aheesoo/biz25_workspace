<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common>
	<jsp:attribute name="stylesheet">
	</jsp:attribute>
		
	<jsp:attribute name="javascript">
		<script language="javascript" type="text/javascript">
			${script}
		</script>
	</jsp:attribute>

<jsp:body>
		<div class="contents_area">
		<div class="sub_tit">
			<div style="float:left;"><img src="/resources/images/shopmaster/tit_cs_center.gif" border="0"/></div>
				<div class="sub_now">홈 > 고객센터 > <font class="sub_now_t">공지사항</font></div>
			</div>
			<div class="sub_tit_dot"></div>
			<div class="sub_contents"></div>
		</div>
</jsp:body>
</layout:common>