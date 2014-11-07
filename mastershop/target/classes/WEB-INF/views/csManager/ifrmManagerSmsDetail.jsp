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
	    	<div style="width: 250px; float: left; margin-top:30px;">
		    	<table width="250" border="0" cellspacing="0" cellpadding="0">
		        	<tr>
	               		<td colspan="4" height="2" bgcolor="#222222"></td>
             		</tr>
             		<tr>
	               		<td class="pop_tb_tit_l" width="65px">발송날짜</td>
	               		<td colspan="3" class="pop_tb_nor_l">
	               			<fmt:parseDate value="${csCustomer.reserve_date}" var="dateFmt" pattern="yyyyMMddHHmm"/>
							<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm"/>
	               		</td>
             		</tr>
             		<tr>
	               		<td class="pop_tb_tit_l">발신번호</td>
	               		<td colspan="3" class="pop_tb_nor_l">${csCustomer.fk_tel}</td>
             		</tr>
             		<tr>
	               		<td class="pop_tb_tit_l">수신번호</td>
	               		<td colspan="3" class="pop_tb_nor_l">${csCustomer.receive_number}</td>
             		</tr>
             		<tr>
	               		<td class="pop_tb_tit_l">문자 타입</td>
	               		<td class="pop_tb_nor_l">
	               			<c:choose>
								<c:when test="${csCustomer.msg_type == '1'}">SMS</c:when>
								<c:when test="${(csCustomer.msg_type == '4') && (csCustomer.msg_sub_type == '1')}">LMS</c:when>
								<c:when test="${(csCustomer.msg_type == '4') && (csCustomer.msg_sub_type != '1')}">MMS</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="pop_tb_tit_l">성공 유무</td>
	               		<td class="pop_tb_nor_l">
	               			<c:choose>
								<c:when test="${csCustomer.res_result == '0'}">성공</c:when>
								<c:otherwise>실패</c:otherwise>
							</c:choose>
	               		</td>
	               	</tr>
		        </table>
		    </div>
		    <div style="width: 160px; margin-left: 260px;">
		    	<c:choose>
					<c:when test="${(csCustomer.msg_type == '4') && (csCustomer.msg_sub_type != '1')}">
						<div style="float:left; width:180px;">
							<div class="message_tbg">
    							<div class="message_top"></div>
								<div style="width:132px; height:200px; margin-left:15px; color:line-height:18px; overflow-x:hidden; overflow-y:auto">
									<img class="img_format" src="${csCustomer.attachment_path}" id="previewImg" width="132" height="180" />
									<textarea class="message_f2" name="mms_content" id="mms_content" rows="" readonly="readonly">${csCustomer.send_content}</textarea>
								</div>
        						<div class="message_bottom"></div>
    						</div>
						</div>
					</c:when>
					<c:otherwise>
						<div style="float:left; width:180px; margin-top:30px;">
							<div class="message_tbg">
		    					<div class="message_top"></div>
								<div class="message_t_lms">
									<textarea class="message_f2" id="smslms_content" name="smslms_content" readonly="readonly" rows="" >${csCustomer.send_content}</textarea>
								</div>
		        				<div class="message_bottom"></div>
		    				</div>
		    			</div>
					</c:otherwise>
				</c:choose>
		    </div>
	    </div>
	</jsp:body>
</layout:noFrame>