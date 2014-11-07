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
				//----------------------------- 월간조회 설정 -----------------------------
				
				
				var search_date = '${search_date}';
				var search_tel =  '${search_tel}';
				var since_Y 	= 2014; 
				var since_m 	= 8; 
				var sin_val		= since_Y+(since_m<10 ? "0"+since_m : ""+since_m);
					
				var today 		= new Date();
				var today_Y	= today.getFullYear();
				var today_m	= (today.getMonth()+1)>9 ? ''+(today.getMonth()+1) : '0'+(today.getMonth()+1);
				
				var today_val	= today_Y+(today_m<10 ? today_m : ""+today_m);

				for(var i=since_Y; i<=today_Y; i++){
					for(var j=1; j<=12; j++){
						opt_val		= i+ 		(j<10 ? "0"+j : ""+j);
						opt_txt		= i+"년 "+ 	(j<10 ? "0"+j : ""+j)+"월";
			
						if( (sin_val <= opt_val) && (opt_val <= today_val) ){//since 이후 && 이번달 이하이면 추가
							$('#search_date').append('<option value="'+opt_val+'">'+opt_txt+'</option>');
						}
					}
				}		

			//	$("#search_date > option[value='']").attr("selected", "true");

				$("#search_date > option[value="+search_date+"]").attr("selected", "true");
				$("#search_tel > option[value="+search_tel+"]").attr("selected", "true");

				$("#search_date").change(function(){					
					$("#pointForm").submit();
					
				});
				$("#search_tel").change(function(){
					$("#pointForm").submit();
				});
				
			});
		</script>
		
	</jsp:attribute>
	
	<jsp:body>
	<div id="main_r">
		<div class="contents_area">
			<div class="sub_tit">
				<div style="float:left;"><img src="/resources/images/shopmaster/tit_my_info.gif" border="0"/></div>
				<div class="sub_now">홈 &gt; 내정보 &gt; <font class="sub_now_t">코인 사용내역</font></div>
			</div>
			<div class="sub_tit_dot"></div>
			<div class="sub_contents">
				<form id="pointForm" name="pointForm" method="post" action="/myInfo/myPointUse.do">
				
					<div style="height:24px;">
						<div style="float:left; margin-top:2px;"><font style="font-size:14px; color:#222222; font-weight:bold">코인 사용내역</font></div>
						<div style="float:right;">
							<select name="search_date"  id="search_date"class="listmenu_st" style="width:100px;">
							</select>
							&nbsp;
							<select name="search_tel"  id="search_tel"class="listmenu_st" style="width:100px;">
								<c:if test="${!empty coinUserTelList}">
									<c:forEach var="item" items="${coinUserTelList}" varStatus="status" >
										<option value="${item.pk_tel }">${item.pk_tel }</option>
									</c:forEach>
								</c:if>
							</select>						
						</div>
					</div>
					<div class="tb_top"></div>
					<div>
						<table width="930px" border="0" cellspacing="0" cellpadding="0">
							<tr>								
								<td width="200" class="tb_tit_c"><a href="#" class="tbt_a" style="text-decoration:none;">발송일</a></td>
								<td width="200" class="tb_tit_c"><a href="#" class="tbt_a" style="text-decoration:none;">타입</a></td>								
								<td width="200" class="tb_tit_c"><a href="#" class="tbt_a" style="text-decoration:none;">발송건수</a></td>
								<td width="200" class="tb_tit_c">성공건수</td>								
								<td class="tb_tit_c"><a href="#" class="tbt_a" style="text-decoration:none;">소진 코인</a></td>
							</tr>
							<c:if test="${!empty coinUseLogList}">
								<c:set var="total_send_cnt" value="0"/>
								<c:set var="total_send_success_cnt" value="0"/>
								<c:set var="total_send_fail_cnt" value="0"/>
								<c:set var="total_use_point" value="0"/>
								<c:forEach var="item" items="${coinUseLogList}" varStatus="status" >
									<c:set var="total_send_fail_cnt" value="${total_send_fail_cnt+item.mcs_res_result_fail}"/>
									<tr>										
										<td class="tb_nor_c">	
											<%-- 
											<fmt:parseDate value="${item.reserve_time}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
											<fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/>
											--%>
											<fmt:parseDate value="${item.reserve_time}" var="dateFmt" pattern="yyyyMMddHHmm"/>
											<fmt:formatDate value="${dateFmt}"  pattern="yyyy-MM-dd"/>
										</td>
										<td class="tb_nor_c">
											<c:choose>
												<c:when test="${item.msg_type == '1' }">SMS</c:when>
												<c:when test="${item.msg_type == '4' }">
													<c:choose>
														<c:when test="${item.msg_sub_type == '1' }">
															LMS
														</c:when>
														<c:otherwise>MMS</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose>

										</td>									
										<td class="tb_nor_c">
											<fmt:formatNumber value="${item.req_count}" type="number"  groupingUsed="true"/>
											<c:set var="total_send_cnt" value="${total_send_cnt+item.req_count}"/>
										</td>
										<td class="tb_nor_c">
											<fmt:formatNumber value="${item.mcs_res_result_success}" type="number"  groupingUsed="true"/>
											<c:set var="total_send_success_cnt" value="${total_send_success_cnt+item.mcs_res_result_success}"/>
										</td>									
										<td class="tb_nor_c">
											<fmt:formatNumber value="${item.fd_total_coin}" type="number"  groupingUsed="true"/>
											<c:set var="total_use_point" value="${total_use_point+item.fd_total_coin}"/>
										</td>
									</tr>	
								</c:forEach>
							</c:if>
							<c:if test="${empty coinUseLogList}">
								<tr>
									<td class="tb_nor_c" colspan="5">코인 사용 이력이 없습니다.</td>
									
								</tr>
							</c:if>
						</table>
					</div>
					<div class="tb_top" style="margin-top:35px;"></div>
					<div>
						<table width="930" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="110" class="tb2_tit_c">총 발송건수</td>
								<td width="110" class="tb_nor_c">
									<fmt:formatNumber value="${total_send_cnt}" type="number"  groupingUsed="true"/>
								</td>
								<td width="110" class="tb2_tit_c">총 성공건수</td>
								<td width="110" class="tb_nor_c">
									<fmt:formatNumber value="${total_send_success_cnt}" type="number"  groupingUsed="true"/>
								</td>
								<td width="110" class="tb2_tit_c">총 실패건수</td>
								<td width="110" class="tb_nor_c">
									<fmt:formatNumber value="${total_send_fail_cnt}" type="number"  groupingUsed="true"/>
								</td>
								<td width="120" class="tb2_tit_c">총 소진 코인</td>
								<td width="120" class="tb_nor_c">
									<fmt:formatNumber value="${total_use_point}" type="number"  groupingUsed="true"/>									
								</td>
							</tr>
						</table>
					</div>
					<div style="width:930px; border-bottom: 1px solid #555555;"></div>
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	</div>
</jsp:body>
</layout:common>