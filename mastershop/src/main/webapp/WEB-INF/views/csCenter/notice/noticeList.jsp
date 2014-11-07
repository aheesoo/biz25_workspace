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
				var search_Column = "${pageHelper.searchColumn}";
				
				jQuery("#searchColumn > option[value="+search_Column+"]").attr("selected", "true");
				$("#go_searchBtn").click(function(){
					var frm = document.noticeForm;
					
					frm.method = "post";
					frm.action = "/csCenter/noticeList.do";
					frm.submit();
				});
			});
			
			function goView(seq){
				var form = document.noticeForm;
				form.action = '/csCenter/noticeListView.do'; 
				form.method = "post";
				form.seq.value = seq;
				$("#noticeForm").submit();
			}
		</script>
		${pageNavi.script }
	</jsp:attribute>
	
	<jsp:body>
	
		<div id="main_r">
			<div class="contents_area">
				<div class="sub_tit">
					<div style="float:left;"><img src="/resources/images/shopmaster/tit_cs_center.gif" border="0"/></div>
					<div class="sub_now">홈 &gt; 고객센터 &gt; <font class="sub_now_t">공지사항</font></div>
				</div>
				<div class="sub_tit_dot"></div>
				<div class="sub_contents">
					<form id="noticeForm" name="noticeForm" method="post">
					<input type="hidden" id="page" name="page" value="${pageHelper.page}" />
					<input type="hidden" id="seq" name="seq" value="" />
					<div style="height:27px;">
						<div style="float:right;">
							<a id="go_searchBtn"  href="#"><img src="/resources/images/shopmaster/btn2_search.gif" border="0"/></a>
						</div>
						<div style="float:right; width:145px;">
							<input name="searchString" id="searchString" type="text" class="fild_st" style="width:137px;" value="${pageHelper.searchString}">
						</div>
						<div style="float:right; margin-top:4px;">제목&nbsp;&nbsp;</div>                    
					</div>
					<div class="tb_top"></div>		          
					<div>
						<table width="930px" border="0" cellspacing="0" cellpadding="0">
							<!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
							<tr>
								<%--
								<td width="80" class="tb_tit_c">번호</td>
								<td class="tb_tit_c"><a href="#" class="tbt_a">제목</a></td>
								<td width="100" class="tb_tit_c"><a href="#" class="tbt_a">조회수</a></td>
								<td width="150" class="tb_tit_c"><a href="#" class="tbt_a">등록일</a></td>
								 --%>
								<td width="80" class="tb_tit_c">번호</td>
								<td class="tb_tit_c">제목</td>
								<td width="100" class="tb_tit_c">조회수</td>
								<td width="150" class="tb_tit_c">등록일</td>
							</tr>
							<c:if test="${!empty noticeList}">
								<c:forEach var="item" items="${noticeList}" varStatus="status" >
								<tr style="cursor:pointer;" onclick="goView('${item.pk_seq}');">
									<td class="tb_nor_c">&nbsp;${pageNavi.totalCount - ((pageNavi.nowPage - 1) * pageNavi.pageSize + status.index) }</td>
									<td class="tb_nor_l"><font color="#000000">${item.fd_title }</font></td>
									<td class="tb_nor_c">
										<fmt:formatNumber value="${item.fd_hit}" type="number"  groupingUsed="true"/>
									</td>
									<td class="tb_nor_c">
										<fmt:parseDate value="${item.fd_reg_date}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
										<fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty noticeList}">
								<tr>
									<td class="tb_nor_c" colspan="4">
										등록된 내용이 없습니다.
									</td>
								</tr>
							</c:if>
						</table>
						<!-- 메모아이콘 롤오버시 출력 <div class="sub_memo">김치 추가, 고춧가루 추가</div>-->
					</div>
					<div style="width:930px; height:24px; margin-top:15px; text-align:center;">
						${pageNavi.navi}
					</div>
					</form>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		</div>
	</jsp:body>
</layout:common>