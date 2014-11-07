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
		<div class="pop_txt">
	        <div>
	        	<table width="575" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td colspan="6" height="2" bgcolor="#222222"></td>
	              </tr>
	              <tr>
	                <td class="pop_tb_tit_l">발송날짜</td>
	                <td colspan="2" class="pop_tb_nor_l">
	                	<fmt:parseDate value="${smsManager.reserve_time}" var="dateFmt" pattern="yyyyMMddHHmm"/>
						<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm"/>	
	                </td>
	                <td width="85" class="pop_tb_tit_l">발신번호</td>
	                <td colspan="2" class="pop_tb_nor_l">${smsManager.fk_tel}</td>
	              </tr>
	              <tr>
	                <td class="pop_tb_tit_l">발송대상</td>
	                <td colspan="5" class="pop_tb_nor_l">
	                	<c:choose>
							<c:when test="${smsManager.search_month == '1'}">
								<c:if test="${smsManager.search_customer == '2'}">최근 1개월 이내의 단골 고객</c:if>
								<c:if test="${smsManager.search_customer == '3'}">최근 1개월 이내의 신규 고객</c:if>
								<c:if test="${smsManager.search_customer != '2' && smsManager.search_customer != '3'}">최근 1개월 이내의 전체 고객</c:if>
							</c:when>
							<c:when test="${smsManager.search_month == '3'}">
								<c:if test="${smsManager.search_customer == '2'}">최근 3개월 이내의 단골 고객</c:if>
								<c:if test="${smsManager.search_customer == '3'}">최근 3개월 이내의 신규 고객</c:if>
								<c:if test="${smsManager.search_customer != '2' && smsManager.search_customer != '3'}">최근 3개월 이내의 전체 고객</c:if>
							</c:when>
							<c:when test="${smsManager.search_month == '6'}">
								<c:if test="${smsManager.search_customer == '2'}">최근 6개월 이내의 단골 고객</c:if>
								<c:if test="${smsManager.search_customer == '3'}">최근 6개월 이내의 신규 고객</c:if>
								<c:if test="${smsManager.search_customer != '2' && smsManager.search_customer != '3'}">최근 6개월 이내의 전체 고객</c:if>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
	                </td>
	              </tr>
	              <tr>
	                <td class="pop_tb_tit_l">타입</td>
	                <td colspan="5" class="pop_tb_nor_l">
	                	<c:choose>
							<c:when test="${smsManager.msg_type == '1'}">SMS</c:when>
							<c:when test="${smsManager.msg_type == '4'}">
								<c:if test="${smsManager.msg_sub_type == '1'}">LMS</c:if>
								<c:if test="${smsManager.msg_sub_type != '1'}">MMS</c:if>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
	                </td>
	              </tr>
	              <tr>
	                <td class="pop_tb_tit_l">발송건수</td>
	                <td width="85" class="pop_tb_nor_l">${smsManager.req_count} 건</td>
	                <td width="85" class="pop_tb_tit_l">성공건수</td>
	                <td class="pop_tb_nor_l">${smsManager.mcs_res_result_success} 건</td>
	                <td width="85" class="pop_tb_tit_l"><font style="color:#FF0000; font-weight:bold;">응답건수</font></td>
	                <td width="85" class="pop_tb_nor_l"><font style="color:#FF0000; font-weight:bold;">${call_count} 건</font></td>
	              </tr>
	            </table>
	        </div>
	        <div style="margin-top:40px;">
	            <div style="float:left; width:180px;">
	                <div class="message_tbg">
	                    <div class="message_top"></div>
	                    <!-- <div class="message_t_mms"><img src="/resources/images/shopmaster/ex_message.gif" width="132" height="202" /></div> -->
	                    <%-- <div class="message_t_lms">
		            		<textarea class="message_f2" readonly="readonly" rows="" >${smsManager.send_content}</textarea>
		              	</div> --%>
		              	<c:choose>
							<c:when test="${smsManager.msg_sub_type == '1'}">
								<div class="message_t_lms">
		            				<textarea class="message_f2" readonly="readonly" rows="" >${smsManager.send_content}</textarea>
		              			</div>
							</c:when>
							<c:otherwise>
								<div style="width:132px; height:200px; margin-left:15px; color:line-height:18px; overflow-x:hidden; overflow-y:auto">
				        			<img class="img_format" src="${smsManager.attachment_path}" id="previewImg" width="132" height="180" />
				        			<textarea class="message_f2" name="mms_send_content" id="mms_send_content" rows="" readonly="readonly">${smsManager.send_content}</textarea>
				        		</div>
							</c:otherwise>
						</c:choose>
	                    <div class="message_bottom"></div>
	                </div>
	            </div>
	            <div style="float:left; width:395px; height:264px; overflow:auto;">
	            	<table width="377" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td colspan="3" height="2" bgcolor="#222222"></td>
	                  </tr>
	                  <tr>
	                    <td width="150" class="tb_tit_c">발송일시</td>
	                    <td width="90" class="tb_tit_c">이름</td>
	                    <td class="tb_tit_c">전화번호</td>
	                  </tr>
	                  
	                  <c:if test="${!empty reportDetailList}">
							<c:forEach var="item" items="${reportDetailList}" varStatus="status" >
								<tr>
			                    	<td class="tb_nor_c" style="font-size:11px;">
			                    		<fmt:parseDate value="${item.reserve_date}" var="dateFmt" pattern="yyyyMMddHHmm"/>
										<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm"/>	
			                    	</td>
			                    	<%-- <td class="tb_nor_c" style="font-size:11px;">${item.fd_user_name}</td> --%>
			                    	<td class="tb_nor_c" style="font-size:11px;"><c:if test="${view_name_flag == 0}">${item.fd_user_name}</c:if></td>
			                    	<td class="tb_nor_c" style="font-size:11px;">${item.receive_number}</td>
			                    </tr>
							</c:forEach>
					  </c:if>
					  <c:if test="${empty reportDetailList}">
							<tr> 	
								<td class="tb2_nor_l" colspan="3" style="text-align:center">&nbsp;수신한 내역이 없습니다.</td>
							</tr>
					  </c:if>
	                </table>
	            </div>
	        </div>
	    </div>
	</jsp:body>
</layout:noFrame>