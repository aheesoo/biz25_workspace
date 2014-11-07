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
			
			$(document).ready(function() {
			
			});

			
			
			function goSmsRegister() {
				//location.href = '';
				var frm = document.searchForm;
				frm.action = "/smsManager/smsManagerRegister.do";
				frm.submit();
			}

			function searchCal(type,pk_group_code,reserve_type,calDayNum){
				//alert(reserve_type);
				var frm = document.searchForm;
				frm.action = "/smsManager/smsManagerMain.do";
				frm.type.value = type;
				frm.pk_group_code.value = pk_group_code;
				if(type=="v"){ //수정페이지로 이동(뷰페이지)
					if(reserve_type == '발송예약'){
						frm.action = "/smsManager/smsManagerModify.do";
					}else if(reserve_type == '발송완료'){
						//closePop(calDayNum);
						sms_ifrm.location.href = "/smsManager/ifrmSmsDetail.do?&group_code=" + pk_group_code;
						popShow('popDetailList');
					}
					//alert("type="+type+", pk_group_code="+pk_group_code+", form.action = "+frm.action);

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
				if(reserve_type != '발송완료'){
					frm.submit();	
				}
			}
			
			
			function openPop(smsId){
				var old_smsId = $('#old_smsId').val();
				$("[id^='smsList_']").hide();
				$('#smsList_'+smsId).show();
				$('#cdNum_'+smsId).attr("href","javascript:closePop('"+smsId+"')");
				
				if(old_smsId != "" && old_smsId != smsId){
					$('#cdNum_'+old_smsId).attr("href","javascript:openPop('"+old_smsId+"')");
				}
				$('#old_smsId').val(smsId);
			}
/* 
			function togglePop(smsId){
				$('#smsList_'+smsId).toggle();
			}
 */
			 $(function(){
				$('#sms_ifrm').load(function(){
					   $('#sms_ifrm').attr("height",sms_ifrm.document.body.scrollHeight);
				});
			});			
 
			function closePop(smsId){
				var old_smsId = $('#old_smsId').val();
				$('#smsList_'+smsId).hide();
				$('#cdNum_'+smsId).attr("href","javascript:openPop('"+smsId+"')");
				$('#old_smsId').val(smsId);
			}
 
			//레이어팝업 숨김
			function popHide(dName) {
				$('#'+dName).hide();
			}
			
			//레이어팝업 돌출
			function popShow(dName) {
				$('#'+dName).show();
			}

			
			//1개월 이상 체납시
			function payMsg(fd_price, pay_price){

				var noPayPrice = fd_price - pay_price;
				priceAlert = "고객님 미납요금("+commaNum(noPayPrice)+"원)이 발생하여 알려드립니다.\n\n더 자세한 사항은 고객센터(1899-1431)으로 문의 하세요";

				alert(priceAlert);
				//alertAgree(500, 500, "", "알림", "테스트");
			}	

			//3자리 콤마
			function commaNum(num) {  
				var len, point, str;  

				num = num + "";  
				point = num.length % 3  
				len = num.length;  

				str = num.substring(0, point);
				while (point < len) {
					if (str != "") str += ",";
					str += num.substring(point, point + 3);
					point += 3;
				}  
				return str;  
			}
		</script>
	</jsp:attribute>
	
	<jsp:body>
	<input type="hidden" id="old_smsId" name="old_smsId"/>
	
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
				<div style="height:31px;">
					<div style="float:left;"><a href="javascript:searchCal('m','')"><img src="/resources/images/shopmaster/list_priv.gif" border="0"/></a></div>
					<div style="float:left; width:90px; margin-top:6px; text-align:center;">${year}년 ${month}월</div>
					<div style="float:left;"><a href="javascript:searchCal('a','')"><img src="/resources/images/shopmaster/list_next.gif" border="0"/></a></div>
					<div style="float:left; width:140px; margin-top:2px; text-align:center;">
						<form id="searchForm" name="searchForm" method="get" action="smsManagerMain.do">
							<input type="hidden" id="sCalYear" name="sCalYear" value="${year}"/>
							<input type="hidden" id="sCalMonth" name="sCalMonth" value="${month}"/>
							<input type="hidden" id="pk_group_code" name="pk_group_code" />
							<input type="hidden" id="type" name="type" />
							<input type="hidden" id="sPageType" name="sPageType" value=""/>
							<select id="searchTel" name="searchTel" class="listmenu_st" onchange="searchCal('t','')" style="width:100px;">
								<option value="">전체</option>
								<c:forEach var="meml" items="${memList}" varStatus="stat">
									<option value="${meml.pk_tel}" <c:if test="${meml.pk_tel==param.searchTel }">selected='selected'</c:if>>${meml.pk_tel}</option>
								</c:forEach>
							</select>
						</form>
					</div>

					<div style="float:left; margin-left:10px;">
						<div style="float:left;"><a href="/smsManager/smsManagerMainList.do"><img src="/resources/images/shopmaster/view_list_white.gif" border="0"/></a></div>
						<div style="float:left;"><a href="/smsManager/smsManagerMain.do"><img src="/resources/images/shopmaster/view_calendar_gray.gif" border="0"/></a></div>
					</div>

					<div style="float:right;"><a href="javascript:goSmsRegister();"><img src="/resources/images/shopmaster/btn_sms_register.gif" border="0"/></a></div>
				</div>

				<div class="tb_top"></div>
				<div id="calendar_area">
<!-- 달력 -->
					<table width="930" border="0" cellspacing="1" cellpadding="0" bgcolor="#cccccc">
						<tr>
							<td width="131" class="tb_tit_c">일</td>
						    <td width="132" class="tb_tit_c">월</td>
						    <td width="132" class="tb_tit_c">화</td>
						    <td width="132" class="tb_tit_c">수</td>
						    <td width="132" class="tb_tit_c">목</td>
						    <td width="132" class="tb_tit_c">금</td>
						    <td width="131" class="tb_tit_c">토</td>
						</tr>

<c:set var="calNum" value=""></c:set><%-- 일(숫자) --%>
<c:set var="calTxt" value=""></c:set><%-- 일(팝업) --%>
<c:forEach var="calList" items="${calList}" varStatus="stat">
<c:set var="calDayNum" value="${calList.dayNum-(chkDay-1)}"></c:set><%-- 테이블 번호 --%>

	<%-- 달력 --%>
		<c:if test="${calList.dayNum%7==0}">
			<c:set var="calNum" value="<tr>"></c:set>
			<c:set var="calTxt" value="<tr>"></c:set>
		</c:if>
	<%-- 타이틀 TXT --%>
			<c:set var="calNum" value="${calNum}<td class=\"message_calendar_num${calList.classTxt}\" title=\"${calList.txtArray[calDayNum].dayList[0].fd_title}\">${calList.dayTxt}"></c:set>
			<c:if test="${calList.txtArray[calDayNum].dayList.size() > 0}">
				<c:set var="calNum" value="${calNum} <font class=\"ca_txt11_ff0000\" style=\"float:right;display:block;overflow:hidden;width:100px;padding:1px;white-space:nowrap;text-overflow:ellipsis;\">${calList.txtArray[calDayNum].dayList[0].fd_title}</font>"></c:set>
			</c:if>
			<c:set var="calNum" value="${calNum}</td>"></c:set>

	<%-- 본문 TXT --%>
			<c:set var="calTxt" value="${calTxt} <td class=\"message_calendar_txt${calList.classTxt}\">"></c:set>
			<c:if test="${calList.smsGroupList[calDayNum].dayList.size()>0}">
				<c:set var="calTxt" value="${calTxt} <a href=\"javascript:openPop('${calDayNum}')\" id=\"cdNum_${calDayNum}\" class=\"ca_num\"><div class=\"ca_number\">${calList.smsGroupList[calDayNum].dayList.size()}</div></a>"></c:set>

				<c:set var="calTxtIn" value=""></c:set>
				<c:forEach var="itemIn" items="${calList.smsGroupList[calDayNum].dayList}" varStatus="statusIn" >
					<c:set var="calTxtIn" value="${calTxtIn} <a href=\"javascript:searchCal('v','${itemIn.pk_group_code}','${itemIn.reserve_type}','${calDayNum}')\">"></c:set>
					<c:set var="calTxtIn" value="${calTxtIn} ${itemIn.reserve_type}, "></c:set>
					<c:set var="calTxtIn" value="${calTxtIn} ${itemIn.reserve_time}, "></c:set>
					<c:set var="calTxtIn" value="${calTxtIn} ${itemIn.req_count}건"></c:set>
					<c:set var="calTxtIn" value="${calTxtIn} </a><br />"></c:set>
				</c:forEach>

				<c:set var="calTxt" value="${calTxt} <div id=\"smsList_${calDayNum}\" class=\"sub_memo\"  style=\"line-height:18px;display:none;\">${calTxtIn}</div>"></c:set>

			</c:if>
			<c:set var="calTxt" value="${calTxt}</td>"></c:set>

		<c:if test="${calList.dayNum%7==6}">
			<c:set var="calNum" value="${calNum}</tr>"></c:set>
			<c:set var="calTxt" value="${calTxt}</tr>"></c:set>
			${calNum}
			${calTxt}
		</c:if>
		
	<%-- 달력 --%>
</c:forEach>
					</table>
<!-- 달력 -->
                </div>
                <div id="list_area">
                
                </div>
			</div>
        </div>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>
	</jsp:body>
</layout:common>