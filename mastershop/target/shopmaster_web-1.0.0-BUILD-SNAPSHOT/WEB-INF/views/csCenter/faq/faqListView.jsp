<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<layout:common> 
	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	<jsp:attribute name="javascript">
		<script type="text/javascript">
			$(document).ready(function(){
			
				$("#go_searchBtn").click(function(){
					$("#faqForm").attr("action", "/csCenter/faqList.do");
					$("#faqForm").submit();
				});
			});
			function goView(seq){
				var form = document.faqForm;
				form.action = '/csCenter/faqListView.do'; 
				form.method = "post";
				form.seq.value = seq;
				form.submit();
			}
			function golist(){
				var form = document.faqForm;
				location.href="/csCenter/faqList.do?page="+form.page.value; 
			}	
		</script>		
	</jsp:attribute>
	
	<jsp:body>
		<div id="main_r">
			<div class="contents_area">
				<div class="sub_tit">
					<div style="float:left;"><img src="/resources/images/shopmaster/tit_cs_center.gif" border="0"/></div>
					<div class="sub_now">홈 &gt; 고객센터 &gt; <font class="sub_now_t">FAQ</font></div>
				</div>
				<div class="sub_tit_dot"></div>
				<div class="sub_contents">
					<div class="tb_top"></div>
					<form name="faqForm" id="faqForm" method="post" action="/csCenter/faqListView.do">
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="seq" name="seq" value="" />
					<div>
						<table width="930" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="100" class="tb_tit_c">제목</td>
								<td colspan="2" class="tb_nor_l">${viewMain.fd_title }</td>
								<td width="100" class="tb_tit_c">분류</td>
								<td width="100" class="tb_nor_c">${viewMain.fd_code_name }</td>
								<td width="100" class="tb_tit_c">작성일</td>
								<td width="120" class="tb_nor_c">
									<%-- 2014.06.20 11:14 --%>							
									${viewMain.fd_reg_date }
								</td>
							</tr>
							<tr>
								<td colspan="7" class="tb_nor_l" style="padding:20px; line-height:18px;">
									
									${viewMain.fd_content }
								</td>
							</tr>
							<!-- 이전,다음 tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
							<c:if test="${!empty viewPre }">
							<tr onclick="javascript:goView('${viewPre.pk_seq}')" style="cursor:pointer;">
								<td colspan="7" class="tb_nor_l">
									<div style="float:left; width:70px; text-align:center;">▲ 이전</div>
									<div style="float:left;">${viewPre.fd_title }</div>
									<div style="float:right; width:140px; text-align:center;">
										${viewPre.fd_reg_date }
										<%--										
										<fmt:parseDate value="${viewPre.fd_reg_date }" var="preDateFmt" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${preDateFmt}"  pattern="yyyy-MM-dd HH:mm" />
										 --%>
									</div>
								</td>
							</tr>
							</c:if>
							<c:if test="${empty viewPre }">
							<tr>
								<td colspan="7" class="tb_nor_l">
									<div style="float:left; width:70px; text-align:center;">▲ 이전</div>
									<div style="float:left;">이전 글이 없습니다. </div>
									<div style="float:right; width:140px; text-align:center;"></div>
								</td>
							</tr>
							</c:if>
							<c:if test="${!empty viewNext }">
							<tr onclick="javascript:goView('${viewNext.pk_seq}')" style="cursor:pointer;">
								<td colspan="7" class="tb_nor_l">
									<div style="float:left; width:70px; text-align:center;">▼ 다음</div>
									<div style="float:left;">${viewNext.fd_title }</div>
									<div style="float:right; width:140px; text-align:center;">
										<%--2014.06.20 15:32 --%>
										${viewNext.fd_reg_date }
										<%--
										<fmt:parseDate value="${viewNext.fd_reg_date }" var="nextDateFmt" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${nextDateFmt}"  pattern="yyyy-MM-dd HH:mm" />
										 --%>
									</div>
								</td>
							</tr>
							</c:if>
							<c:if test="${empty viewNext }">
							<tr>
								<td colspan="7" class="tb_nor_l">
									<div style="float:left; width:70px; text-align:center;">▼ 다음</div>
									<div style="float:left;">다음 글이 없습니다.</div>
									<div style="float:right; width:140px; text-align:center;"></div>
								</td>
							</tr>
							</c:if>
						</table>
					</div> 
					</form>
					<div style="width:930px; height:30px; margin-top:20px;">
						<a href="javascript:golist();"><img src="/resources/images/shopmaster/btn_list.gif" border="0"/></a>
					</div> 
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		</div>
	</jsp:body>
</layout:common>