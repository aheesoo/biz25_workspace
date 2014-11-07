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
			${script}

			//수정화면 이동
			function goPwdCheck(){

				closePwdPop();
				
				var frm = document.cancelForm;
				frm.target = "ifrmProc";
				frm.proc.value = "pwdCkeck";
				frm.submit();

			}

			
			//해지처리
			function goCancel(){

				var frm = document.cancelForm;
				frm.target = "ifrmProc";
				frm.proc.value = "cancel";
				frm.submit();

				//$("#searchForm").attr("target","ifrm");
				//$("#proc").val("regist_search");
				//$("#modifyForm").attr("action","/member/memberModify.do");
				//$("#modifyForm").submit();
				
			}
			
			
			function openPwdPop(){
				
				var check = $('input[name="checkboxCancelAgree"]').is(":checked");
				if(check){
					$('#background_gray').show();
					$('#alertInArea').show();
				}else{
					alert("해지 사항에 대하여 확인하였다는 체크하여 주시기 바랍니다.")
				}
			}
			
			function closePwdPop(){
				$('#background_gray').hide();
				$('#alertInArea').hide();
			}
			
			function ProcOk(rtnCode){
				
				//alert("응답코드[개발중] "+rtnCode);
				
				alert("해지 처리 완료 되었습니다.\n\n이용해주셔서 감사합니다.");
				location.href = "/manager/logout.do";

			}
		</script>
		
	</jsp:attribute>
	
	<jsp:body>

	<form name="cancelForm" id="cancelForm" action="serviceCancel.do">
		<input type="hidden" name="proc">

		<div id="background_gray" style="display:none;position:fixed; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50); z-index:999;"></div>
	
		<div id="alertInArea" style="display:none;position:absolute; top:0; left:0; width:100%; height:100%;z-index:9999;">
			<div id="popup" style="margin:0 auto;margin-top:300px;width:300px;">
				<div class="pop_top">
					<div class="pop_x" style="margin-left:270px"><a href="javascript:closePwdPop();">
						<img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a>
					</div>
					<div class="pop_tit">웹 페이지 메시지</div>
				</div>
				<div id="alertTxt" style=" margin:0 auto;margin-top:20px;width:260px;height:50px; text-align:left; ">
					비밀번호를 입력해 주세요. &nbsp;<input type="password" name="pwd" id="pwd" style="width:90px;">
				</div>
				<div style="margin:0 auto;width:50px;height:30px">
					<a href="javascript:goPwdCheck();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0" /></a>
				</div>
			</div>
		</div>
	</form>
	
	
	<div id="main_r">
		<div class="contents_area">
        	<div class="sub_tit">
            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_my_info.gif" border="0"/></div>
            	<div class="sub_now">홈 > 내정보 > <font class="sub_now_t">서비스 해지</font></div>
            </div>
            <div class="sub_tit_dot"></div>
            <div class="sub_contents">
            	<div style="width:900px; border:1px solid #ddd; background-color:#efefef; padding:15px; font-size:14px; font-weight:bold;">
                	<div>· MasterShop 서비스 해지 시 kt 통화 Open API는 해지 되지 않으며, kt 고객 센터 100번에 별도 해지 처리 해야 합니다.</div>
                    <div style="margin-top:10px;">&nbsp; 미 처리 시 비용이 청구됩니다.</div>
                    <div style="margin-top:10px;">· MasterShop 해지 처리 시 충전된 모든 코인은 모두 소멸 됩니다.</div>
                    <div style="margin-top:10px;">· MasterShop 와 kt 통화 Open API에 월정액은 해지신청 전일 기준으로 일할 계산되어 익월에 청구 됩니다.</div>
                    <div style="margin-top:10px;">· 아래의 예약 발송 문자는 모두 취소 처리 됩니다.</div>                
                </div>
                <div class="tb_top" style="margin-top:30px;"></div>
                <div>
                	<table width="930" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="70" class="tb_tit_c">발송일</td>
                        <td width="150" class="tb_tit_c">요청일</td>
                        <td width="120" class="tb_tit_c">타입</td>
                        <td width="120" class="tb_tit_c">발송 건수</td>
                        <td class="tb_tit_c">발송 대상</td>
                        <td width="120" class="tb_tit_c">상태</td>
                      </tr>
                      
					<c:forEach var="item" items="${smsGroupList}" varStatus="stat">
						<c:set var="clickEv" value="" />
						<tr>
							<td class="tb_nor_c" ${clickEv}>${item.reserveDayTxt}일</td>
							<td class="tb_nor_c" ${clickEv}>${item.reg_dateTxt}</td>
							<td class="tb_nor_c" ${clickEv}>${item.msg_type}</td>
							<td class="tb_nor_c" ${clickEv}>${item.search_req_cnt}</td>
							<td class="tb_nor_l" ${clickEv}><font color="#ff0000">최근 ${item.search_month}개월 이내의 ${item.search_customerTxt} 고객</font></td>
							<td class="tb_nor_c" ${clickEv}>${item.resever_statusTxt}</td>
						</tr>
					</c:forEach>
							
<!--                       <tr>
                        <td class="tb_nor_c">18일</td>
                        <td class="tb_nor_c">2014.06.01</td>
                        <td class="tb_nor_c">SMS</td>
                        <td class="tb_nor_c">145</td>
                        <td class="tb_nor_l"><font color="#ff0000">최근 6개월 이내의 전체 고객</font></td>
                        <td class="tb_nor_c">발송예약</td>
                      </tr>
 -->
                    </table>
                </div>              	
                <div style="width:930px; border-bottom: 1px solid #555555;"></div>
              <div style="width:930px; margin-top:20px;">
                	<div style="float:left;"><input type="checkbox" name="checkboxCancelAgree" id="checkboxCancelAgree" /></div>
                    <div style="float:left; margin-top:3px;"> 예, 동의합니다.</div>
                    <div style="float:right;"><a href="javascript:openPwdPop();"><img src="/resources/images/shopmaster/btn_exit.gif" border="0" /></a></div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </div>

	<!-- 통화오픈API 로그인 완료후 업데이트처리 -->
	<iframe name="ifrmProc" id="ifrmProc" width="0px" height="0px" frameborder="0" src=""></iframe>

	</jsp:body>
</layout:common>