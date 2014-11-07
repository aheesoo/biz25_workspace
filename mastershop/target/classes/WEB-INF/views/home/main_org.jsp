<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:main>

	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	<script type="text/javascript">
	var noticeTotH = ${noticeListCount-1};
		$(document).ready(function() {
		});





		function noticeScroll(type){
			var sc = $('#scrollCount');
			if(type=="up"){
				if(sc.val() < noticeTotH){
					sc.val(Number(sc.val())+1);
				}else{
					sc.val(0);
				}
			}else if(type=="down"){
				if(sc.val() <= 0){
					sc.val(noticeTotH);
				}else{
					sc.val(Number(sc.val())-1);
				}
			}
			$('#noticeDiv').css("top",Number(sc.val())*-22);
			$("#sType").val(type);
		}
	</script>
	</jsp:attribute>
	
	<jsp:body>
	
    <div id="main_r">
    	<div class="mcontnents">
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="/csManager/csManagerMain.do"><img src="/resources/images/shopmaster/main_m1.gif" border="0"></a></div>
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="/telManager/telManagerMain.do"><img src="/resources/images/shopmaster/main_m2.gif" border="0"></a></div>
            <div style="float:left;margin:0px 10px 0px 10px;"><a href="/myInfo/joinInfo.do"><img src="/resources/images/shopmaster/main_m6.gif" border="0"></a></div>
            <div style="float:left;margin:20px 10px 0px 10px;"><a href="/smsManager/smsManagerMain.do"><img src="/resources/images/shopmaster/main_m3.gif" border="0"></a></div>
            <div style="float:left;margin:20px 10px 0px 10px;"><a href="/report/reportMain.do"><img src="/resources/images/shopmaster/main_m4.gif" border="0"></a></div>
            <div style="float:left;margin:20px 10px 0px 10px;"><a href="/csCenter/noticeList.do"><img src="/resources/images/shopmaster/main_m5.gif" border="0"></a></div>
		</div>
     	<div class="mnotice" id="noticeArea">
     		<input type="hidden" id="scrollCount" value="0" />
     		<input type="hidden" id="sType" value="up" />
        	<div style="float:left;width:90px;"><img src="/resources/images/shopmaster/m_notice_tit.gif"></div>
        	<div style="float:left;width:597px; height:22px;position:relative; overflow:hidden">
	        	<div id="noticeDiv" style="position:absolute; padding-top:8px; top:0px;">
					<c:if test="${!empty noticeList}">
						<c:forEach var="item" items="${noticeList}" varStatus="status" >
			        	<div style="float:left;width:500px; height:22px;"><a href="/csCenter/noticeListView.do?seq=${item.pk_seq}" class="mnotice_a">${item.fd_title }</a></div>
			            <div style="float:left;width:97px; height:22px; text-align:center;">
			            	<font color="#bbbbbb">
								<fmt:parseDate value="${item.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
								<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd"/>
			            	</font>
			            </div>
						</c:forEach>
					</c:if>
				</div>
				<c:if test="${empty noticeList}">
		        	<div id="noticeDiv" style="position:absolute; padding-top:8px; top:0px;">
			        	<div style="float:left;width:597px;">등록된 내용이 없습니다.</div>
			        </div>
		        </c:if>
        	</div>

            <div style="float:left;width:24px; padding-top:2px;"><a href="javascript:noticeScroll('down');"><img src="/resources/images/shopmaster/m_notice_up.gif" border="0"></a></div>
            <div style="float:left;width:30px; padding-top:2px;"><a href="javascript:noticeScroll('up');"><img src="/resources/images/shopmaster/m_notice_down.gif" border="0"></a></div>
            <div style="float:left;width:49px; padding-top:2px;"><a href="/csCenter/noticeList.do"><img src="/resources/images/shopmaster/m_notice_go.gif" border="0"></a></div>
        </div>
        <div class="mnotice_line"></div>
    </div>
	    
	</jsp:body>
</layout:main>