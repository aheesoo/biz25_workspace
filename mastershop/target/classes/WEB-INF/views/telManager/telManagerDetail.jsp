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
		<script type="text/javascript">
	 		$(document).ready(function(){
	 			$('.bgchange').mouseover(function(){
					$(this).find('td').css('background','#e0e6ec');
				}).mouseout(function(){
					$(this).find('td').css('background','');
				});
	 			
	 			var tel = "${infoMap.pk_customer_tel}";
	 	 		var telArr = tel.split('-');
	 			$('#csTel1').val(telArr[0]);
	 			$('#csTel2').val(telArr[1]);
	 			$('#csTel3').val(telArr[2]);
	 			
	 			//기간 개월

	 			switch('${radioMonth}'){
					case "1":	$('#radioMonth1').prop("checked", true); break;
					case "3":	$('#radioMonth3').prop("checked", true); break;
					case "6":	$('#radioMonth6').prop("checked", true); break;
					default :	$('#radioMonth6').prop("checked", true);
				}
	 			
	 			
	 			$("#radioMonth1").click(function(){ 
					document.goPageForm.radioMonth.value = '1';
				});
	 			
	 			$("#radioMonth3").click(function(){ 
					document.goPageForm.radioMonth.value = '3';
				});
	 			
	 			$("#radioMonth6").click(function(){ 
					document.goPageForm.radioMonth.value = '6';
				});
	 		});
	 		
	 		$(function(){
				$('#sms_ifrm').load(function(){
					   $('#sms_ifrm').attr("height",sms_ifrm.document.body.scrollHeight);
				});
			});
	
		
			function goPage(pageType){
				var frm = document.goPageForm;
				if(pageType=="list"){
					frm.action = "/csManager/csManagerMain.do";
				}else if(pageType=="mod"){
					frm.goType.value = pageType;
					frm.action = "/csManager/csManagerPro.do";
				}
				frm.submit();
			}
	
			function customerMod(){
				var frm = document.goPageForm;
				frm.action = "/csManager/csManagerPro.do";
				frm.goType.value = 'mod';
				frm.submit();
			}
			
			function divOnOff(dName){
				$('#'+dName).toggle();
			}
			
			function goSearch(){
				var frm = document.goPageForm;
				frm.action = "/telManager/telManagerDetail.do";
				frm.submit();
			}
			
			function divOnOff(dName){
				$('#'+dName).toggle();
			}
			
			function goDetail(seq, fk_tel, receive_number){
				sms_ifrm.location.href = "/csManager/ifrmManagerSmsDetail.do?&fk_seq=" + seq + "&fk_tel=" + fk_tel + "&receive_number=" + receive_number;
				divOnOff('smsDiv');
			}
	
		</script>
	</jsp:attribute>
	
	<jsp:body>
		<div id="smsDiv" style="display:none;position:absolute;text-align:center;width:100%;height:1px;">
		<!-- 팝업 mod -->
			<div id="popup" style="width:460px;height:350px;">
				<div class="pop_top">
					<div style="position:absolute; margin:12px 0px 0px 420px;"><a href="javascript:divOnOff('smsDiv')"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">문구 발송 상세보기</div>
			    </div>
			    <iframe name="sms_ifrm" id="sms_ifrm" width="100%" height="270" frameborder="0" scrolling="no"  maginwidth="0" marginheight="0" frameborder="0" src=""></iframe>
			    <div style="text-align:center; padding:10px;">
			    	<a href="javascript:divOnOff('smsDiv')"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>
			    </div>
			</div>
		<!-- 팝업 mod -->
		</div>

		<div id="popup2" style="height:660px;">
		<form id="goPageForm" name="goPageForm" method="post">
			<input type="hidden" id="orderColumn" 	name="orderColumn" value="${param.orderColumn}"/>				<!-- 타이틀 정렬컬럼명 -->
			<input type="hidden" id="orderTyp" 		name="orderTyp" value="${param.orderTyp}"/>						<!-- 타이틀 정렬방법 -->
			<input type="hidden" id="page" 			name="page" value="${param.page}">								<!-- 페이지번호 -->
			<input type="hidden" id="fk_member_id" 	name="fk_member_id" value="${param.fk_member_id}">				<!-- 아이디 -->
			<input type="hidden" id="pk_customer_tel" 	name="pk_customer_tel" value="${infoMap.pk_customer_tel_org}">		<!-- 전화번호 -->
			<input type="hidden" id="fd_rev_sms_flag" 	name="fd_rev_sms_flag" value="${param.fd_rev_sms_flag}">		<!-- 수신옵션 -->
			<input type="hidden" id="searchColumn" 	name="searchColumn" value="${param.searchColumn}">				<!-- 검색컬럼 -->
			<input type="hidden" id="searchString" 	name="searchString" value="${param.searchString}">				<!-- 검색값 -->
			<input type="hidden" id="goType" 		name="goType"/>
		
			<div class="pop_top">
				<div class="pop_x3"><a href="javascript:parent.divOnOff('detailDiv');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
		    	<div class="pop_tit">고객정보</div>
		    </div>
		    <div class="pop_txt3">
		    	<div style="height:22px;"><font style="font-size:14px; color:#222222; font-weight:bold">기본정보</font></div>
		        <div class="tb_top"></div>
		        <div>
		            <table width="930" border="0" cellspacing="0" cellpadding="0">
                      <tr>
						<td width="120" class="tb2_tit_l">전화번호</td>
						<td width="345" class="tb2_nor_l">
							<b>${infoMap.pk_customer_tel}</b>
							<c:if test="${infoMap.fd_rev_sms_flag=='Y'}"><span style="float:right">수신거부</span></c:if>
						</td>
						<td width="120" class="tb2_tit_l">이름</td>
						<%-- <td width="345" class="tb2_nor_l">${infoMap.fd_user_name}</td> --%>
						<td width="345" class="tb2_nor_l"><c:if test="${view_name_flag == 0}">${infoMap.fd_user_name}</c:if></td>
                      </tr>
                      <tr>
                        <td class="tb2_tit_l">주소</td>
                        <td colspan="3" class="tb2_nor_l"><b>${infoMap.fd_addr}</b></td>
                      </tr>
                      <tr>
                        <td class="tb2_tit_l">메모</td>
                        <td colspan="3" class="tb2_nor_l">${infoMap.fd_memo}</td>
                      </tr>
		            </table>
		        </div>
		        <div style="width:930px; margin-top:35px; height:24px;">
                	<div style="float:left;"><input name="radioMonth" id="radioMonth1" type="radio" value="1" /></div>
                	<div style="float:left; margin-top:3px;">1개월</div>
                	<div style="float:left; margin-left:10px;"><input name="radioMonth" id="radioMonth3" type="radio" value="3" /></div>
                	<div style="float:left; margin-top:3px;">3개월</div>
                	<div style="float:left; margin-left:10px;"><input name="radioMonth" id="radioMonth6" type="radio" value="6" /></div>
                	<div style="float:left; margin-top:3px;">6개월</div>
                	<div style="float:left; margin-left:12px;"><a href="javascript:goSearch()"><img src="/resources/images/shopmaster/btn_check.gif"  border="0"/></a></div>
		        </div>
		        <div class="tb_top"></div>
		        <div>
		            <table width="930" border="0" cellspacing="0" cellpadding="0">
		              <tr>
                        <td width="120" class="tb2_tit_l">최근 통화일시</td>
                        <td width="345" class="tb2_nor_l">
                        	<fmt:parseDate value="${latelyCallDate}" var="dateFmt" pattern="yyyyMMddHHmmss"/>
							<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd HH:mm:ss"/>
                        </td>
                        <td width="120" class="tb2_tit_l">총 통화 건수</td>
                        <td width="345" class="tb2_nor_l">${weekSmsReport.week_total_cnt}건</td>
		              </tr>
		              <tr>
		                <td class="tb2_tit_l">문자 발송 건수</td>
		                <td colspan="3" class="tb2_nor_l">${sumSmsSendCount}건</td>
		              </tr>
		            </table>
		        </div>
		        <div style="margin-top:35px; width:930px;">
		            <div class="box280">
		                <table width="280" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
		                    <td height="30" colspan="2">- 요일별 평균 통화 건수</td>
		                  </tr>
		                  <tr>
		                    <td height="2" colspan="2" bgcolor="#444444"></td>
		                  </tr>
		                  <tr>
                            <td width="100" class="tb2_tit_c">월</td>
                            <td width="180" class="tb2_nor_l">
                            	${weekSmsReport.week_2_cnt}건 
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_2_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
		                  </tr>
		                  <tr>
                            <td class="tb2_tit_c">화</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_3_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_3_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">수</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_4_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_4_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">목</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_5_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_5_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                           	</td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">금</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_6_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_6_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">토</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_7_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_3_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">일</td>
                            <td class="tb2_nor_l">
                            	${weekSmsReport.week_1_cnt}건
                            	<c:choose>
									<c:when test="${weekSmsReport.week_total_cnt > 0}">
											(<fmt:formatNumber value="${weekSmsReport.week_1_cnt/(weekSmsReport.week_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
		                </table>
		            </div>
		            <div class="box280" style="margin-left:45px;">
		                <table width="280" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
                            <td height="30" colspan="2">- 시간대별 평균 통화 건수</td>
                          </tr>
                          <tr>
                            <td height="2" colspan="2" bgcolor="#444444"></td>
                          </tr>
                          <tr>
                            <td width="120" class="tb2_tit_c">새벽 (00~07)</td>
                            <td width="160" class="tb2_nor_l">
                            	${timeSmsReport.time_a_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_a_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">아침 (07~09)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_b_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_b_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">오전 (09~11)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_c_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_c_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">점심 (11~14)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_d_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_d_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">오후 (14~17)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_e_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_e_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">저녁 (17~21)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_f_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_f_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
                          <tr>
                            <td class="tb2_tit_c">야간 (21~24)</td>
                            <td class="tb2_nor_l">
                            	${timeSmsReport.time_g_cnt}건
                            	<c:choose>
									<c:when test="${timeSmsReport.time_total_cnt > 0}">
											(<fmt:formatNumber value="${timeSmsReport.time_g_cnt/(timeSmsReport.time_total_cnt*0.01)}" maxFractionDigits="2" minFractionDigits="2"/>%)
									</c:when>
									<c:otherwise>
											(0%)
									</c:otherwise>
								</c:choose>
                            </td>
                          </tr>
		                </table>
		            </div>
		            <div class="box280" style="margin-left:45px;">
		                <table width="280" border="0" cellspacing="0" cellpadding="0">
		                  <tr>
		                    <td height="30" colspan="2">- 문구 발송 내역</td>
		                  </tr>
		                  <tr>
		                    <td height="2" colspan="2" bgcolor="#444444"></td>
		                  </tr>
		                  <c:if test="${!empty smsSendDate}">
						  		<c:forEach var="item" items="${smsSendDate}" varStatus="status" >
									<tr class="bgchange" style="cursor:pointer;" onclick="goDetail('${item.fk_seq}','${item.fk_tel}','${item.receive_number}')"> 
										<td class="tb2_tit_c"><div style="text-align:center">${status.count}</div></td>
										<td class="tb2_nor_l">
											<div style="text-align:left">
												<!-- <a href="#"> -->
													<fmt:parseDate value="${item.reserve_date}" var="dateFmt" pattern="yyyyMMddHHmm"/>
													<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd"/>
												<!-- </a> -->
											</div>
										</td>
									</tr>
								</c:forEach>
						   </c:if>
						   <c:if test="${empty smsSendDate}">
								<tr> 	
									<td class="tb2_nor_l" colspan="2"><div style="text-align:center">문구 발송 내역이 없습니다.</div></td>
								</tr>
						    </c:if>
		                </table>
		            </div>
		        </div>
		    </div>
		</form>
		</div>

	</jsp:body>
</layout:noFrame>