<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common> 

	<jsp:attribute name="stylesheet">
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
		<script langauge="text/javascript">
			$(document).ready(function(){
				$('.bgchange').mouseover(function(){
					$(this).find('td').css('background','#e0e6ec');
				}).mouseout(function(){
					$(this).find('td').css('background','');
				});
			});
			
			function goSmsRegister() {
				var frm = document.searchForm;
				frm.action = "/smsManager/smsManagerRegister.do";
				frm.submit();
			}
			
			function cancelProc(){
				if($("input[name=check]:checked").length > 0){
					if(confirm($("input[name=check]:checked").length+"개의 문자발송을 삭제 하시겠습니까?")){
						document.searchForm.proc.value = "cancel";
						$.ajax({   
		    				url : '/smsManager/smsManagerMainList.do',
		    				type : 'post',
		    				cache : false,
		    				data : $("#searchForm").serialize(),
		    				dataType : 'json',
		    				success: function (data) {
		    					alert('문자 발송이 삭제 되었습니다.');
		    					location.href = '/smsManager/smsManagerMainList.do?'+ '&sCalYear=' + '${year}' + '&sCalMonth=' + '${month}';;
		    					////?" + "&sCalYear=" + '${year}' + "&sCalMonth=" + '${month}';
		    					//var frm = document.searchForm;
		    					//frm.action = "/smsManager/smsManagerMainList.do";
					      	},
					      	error: function (data) {
					      		alert('문자 발송을 삭제하는 도중 오류가 발생하였습니다. 다시 시도해주세요.');
					      	}
		    			});
					}
				}else{
					alert("선택된 항목이 없습니다.");
				}
			}

			function searchCal(type,pk_group_code,resever_statusTxt){
				frm = document.searchForm;
				frm.action = "/smsManager/smsManagerMainList.do";
				frm.pk_group_code.value = pk_group_code;
				if(type=="v"){//수정페이지(뷰)

					//frm.action = "/smsManager/smsManagerModify.do";
					//frm.submit();
					if(resever_statusTxt == '발송완료'){
						//closePop(calDayNum);
						sms_ifrm.location.href = "/smsManager/ifrmSmsDetail.do?&group_code=" + pk_group_code;
						popShow('popDetailList');
					}else{
						frm.action = "/smsManager/smsManagerModify.do";
						frm.submit();
					}
					//alert("type="+type+", pk_group_code="+pk_group_code+", form.action = "+frm.action);
				}else if(type=="d"){//삭제
					frm.action = "/smsManager/MainList.do";
				}else if(type!="t"){//전화선택이 아니면
					if(type=="a"){//월 증가
						if($('#sCalMonth').val()==12){
							$('#sCalYear').val($('#sCalYear').val()*1+1);
							$('#sCalMonth').val('0');
						}
						$('#sCalMonth').val($('#sCalMonth').val()*1+1);
					}else if(type=="m"){//월 감소
						if($('#sCalMonth').val()==1){
							$('#sCalYear').val($('#sCalYear').val()*1-1);
							$('#sCalMonth').val('13');
						}
						$('#sCalMonth').val($('#sCalMonth').val()*1-1);
					}
					if($('#sCalMonth').val() < 10){
						$('#sCalMonth').val("0"+$('#sCalMonth').val());
					}
				}
				if(resever_statusTxt != '발송완료'){
					frm.submit();	
				}
			}
			
			$(function(){
				$('#sms_ifrm').load(function(){
					   $('#sms_ifrm').attr("height",sms_ifrm.document.body.scrollHeight);
				});
			});
			
			//레이어팝업 숨김
			function popHide(dName) {
				$('#'+dName).hide();
			}
			
			//레이어팝업 돌출
			function popShow(dName) {
				$('#'+dName).show();
			}

		</script>
	</jsp:attribute>
	
	<jsp:body>
		<!-- SMS 상세페이지 -->
	    <div id="popDetailList" name="popDetailList" style="display:none;position:absolute;top:200px;left:20px;width:95%;height:530px;z-index:99999;">
			<div style="width:615px; height:510px; border: 1px solid #cbcbcb; padding:1px; margin:0 auto; background:#fff">
				<div class="pop_top">
					<div class="pop_x4"><a href="javascript:popHide('popDetailList');"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">상세정보</div>
			    </div>
			    <iframe name="sms_ifrm" id="sms_ifrm" width="100%" height="100%" frameborder="0" scrolling="no"  maginwidth="0" marginheight="0" frameborder="0" src=""></iframe>
		    </div>
		</div>
		
		<div id="main_r">
		<div class="contents_area">
	       	<div class="sub_tit">
		       	<div style="float:left;"><img src="/resources/images/shopmaster/tit_message_admin.gif" border="0"/></div>
		       	<div class="sub_now">홈 &gt; <font class="sub_now_t">문자관리</font></div>
			</div>
			<div class="sub_tit_dot"></div>
           	<div class="sub_contents">
				<form id="searchForm" name="searchForm" method="get" action="smsManagerMainList.do">
				<input type="hidden" id="proc" name="proc" value="" />
					<div style="height:31px;">
						<div style="float:left;"><a href="javascript:searchCal('m')"><img src="/resources/images/shopmaster/list_priv.gif" border="0"/></a></div>
						<div style="float:left; width:90px; margin-top:6px; text-align:center;">${year}년 ${month}월</div>
						<div style="float:left;"><a href="javascript:searchCal('a')"><img src="/resources/images/shopmaster/list_next.gif" border="0"/></a></div>
						<div style="float:left; width:140px; margin-top:2px; text-align:center;">
							<input type="hidden" id="sCalYear" name="sCalYear" value="${year}"/>
							<input type="hidden" id="sCalMonth" name="sCalMonth" value="${month}"/>
							<input type="hidden" id="pk_group_code" name="pk_group_code" />
							<input type="hidden" id="type" name="type" />
							<input type="hidden" id="sPageType" name="sPageType" value="List"/>
							<select id="searchTel" name="searchTel" class="listmenu_st" onchange="searchCal('t')" style="width:100px;">
								<option value="">전체</option>
								<c:forEach var="meml" items="${memList}" varStatus="stat">
									<option value="${meml.pk_tel}" <c:if test="${meml.pk_tel==param.searchTel }">selected='selected'</c:if>>${meml.pk_tel}</option>
								</c:forEach>
							</select>
						</div>

						<div style="float:left; margin-left:10px;">
							<div style="float:left;"><a href="/smsManager/smsManagerMainList.do"><img src="/resources/images/shopmaster/view_list_gray.gif" border="0"/></a></div>
							<div style="float:left;"><a href="/smsManager/smsManagerMain.do"><img src="/resources/images/shopmaster/view_calendar_white.gif" border="0"/></a></div>
						</div>

						<div style="float:right;"><a href="javascript:goSmsRegister();"><img src="/resources/images/shopmaster/btn_sms_register.gif" border="0"/></a></div>
					</div>
	
					<div class="tb_top"></div>
					<div>
	
						<table width="930" border="0" cellspacing="0" cellpadding="0">
						<!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
							<tr>
								<td width="35" class="tb_tit_c"><input type="checkbox" onclick="$('input[id^=check]').attr('checked',this.checked);" /></td>
								<td width="80" class="tb_tit_c">발송일</td>
								<td width="110" class="tb_tit_c">요청일</td>
								<td width="80" class="tb_tit_c">타입</td>
								<td width="100" class="tb_tit_c">발송 건수</td>
								<td class="tb_tit_c">발송 대상</td>
								<td width="100" class="tb_tit_c">상태</td>
							</tr>
							<c:if test="${!empty smsGroupList}">
								<c:forEach var="item" items="${smsGroupList}" varStatus="stat">
									<c:set var="clickEv" value="onclick=\"searchCal('v','${item.pk_group_code}','${item.resever_statusTxt}')\" style=\"cursor:pointer;\""/>
									<tr style="cursor:pointer;" class="bgchange">
										<td class="tb_nor_c">
											<c:if test="${item.resever_statusTxt == '발송예약'}">
												<input type="checkbox" name="check" id="check_${status.index}" value="${item.custom_msg_id}" />
											</c:if>
											<c:if test="${item.resever_statusTxt != '발송예약'}">
												<input type="checkbox" name="check" id="no_check" disabled="disabled" />
											</c:if>
										</td>
										<td class="tb_nor_c" ${clickEv}>${item.reserveDayTxt}일</td>
										<td class="tb_nor_c" ${clickEv}>${item.reg_dateTxt}</td>
										<td class="tb_nor_c" ${clickEv}>${item.msg_type}</td>
										<td class="tb_nor_c" ${clickEv}>${item.req_count}</td>
										<td class="tb_nor_l" ${clickEv}>최근 ${item.search_month}개월 이내의 ${item.search_customerTxt} 고객</td>
										<td class="tb_nor_c" ${clickEv}>${item.resever_statusTxt}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty smsGroupList}">
								<tr>
									<td class="tb_nor_c" colspan="7">&nbsp;문자관리 내역이 없습니다.</td>
								</tr>
							</c:if>
						</table>
	
	
	                </div>
	                <br>
	                <div id="list_area">
	                	<a href="javascript:cancelProc()"><img src="/resources/images/shopmaster/btn_delete.gif" border="0"/></a>
	                </div>
				</form>
			</div>
        </div>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
	    
	</jsp:body>
</layout:common>