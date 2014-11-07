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

			//ID 값으로 검색하여 창 숨김처리
			function openPopDiv(dName){
				$('#'+dName).show();
			}
			
			//ID 값으로 검색하여 창 숨김처리
			function closePopDiv(dName){
				$('#'+dName).hide();
			}

			
			//수정화면 이동
			function changeInfo(){
				
				if($("#fd_mobile").val() ==''){
					alert("휴대전화번호는 필수 입력입니다. ");
					$("#fd_mobile").focus();
					return;
				}else if($("#fd_post_num1").val() ==''){
					alert("주소는 필수 입력 사항입니다. ");					
					return;
				}else if($("#fd_post_num2").val() ==''){
					alert("주소는 필수 입력 사항입니다. ");
					return;
				}else if($("#fd_addr").val() ==''){
					alert("주소는 필수 입력 사항입니다. ");
					$("#fd_addr").focus();
					return;
				}else if($("#fd_addr_detail").val() ==''){
					alert("주소는 필수 입력 사항입니다. ");
					$("#fd_addr_detail").focus();
					return;
				}else{
					$("#mainForm").submit();
				}
				
				
			}

			
			//비밀번호 변경
			function changePwd(){

				//$("#searchForm").attr("target","ifrm");
				//$("#proc").val("regist_search");
				//$("#modifyForm").attr("action","/member/memberModify.do");
				if($("#oldPwd").val() ==''){
					alert("기존 비밀 번호를 입력해 주세요.");
					$("#oldPwd").focus();
					return;
				}else if($("#newPwd").val()  ==''){
					alert("수정하실 비밀 번호를 입력해주세요.");
					$("#newPwd").focus();
					return;
				}else if($("#newPwd").val().length<6){
					alert("비밀번호는 6자리 이상 입력해주세요.");
					$("#newPwd").focus();
					return;
				}else if($("#newPwdRepeat").val()  ==''){
					alert("비밀번호를 입력해주세요.");
					$("#newPwdRepeat").focus();
					return;
				}else if($("#newPwdRepeat").val().length<6){
					alert("비밀번호는 6자리 이상 입력해주세요.");
					$("#newPwdRepeat").focus();
					return;
				}else if( $("#newPwd").val() == $("#newPwdRepeat").val() ){
					
					closePopDiv('popPwdChange');
					
					$("#pwdForm").submit();
					
				}else{
					alert("수정될 비밀번호가 일치하지 않습니다.\n비밀번호를 확인하여 주세요.");
				}
			}
			
			function setPostNum(arrr, postnum){
				
				var postnum1 = "";
				var postnum2 = "";
				
				if(postnum.length == 6){
					postnum1 = postnum.substr(0,3);
					postnum2 = postnum.substr(3,6);
					
					$("#fd_post_num1").val(postnum1);
					$("#fd_post_num2").val(postnum2);
					$("#fd_addr").val(arrr);
				}
				
				closePopDiv('popZipcode');
				 
			}
		</script>

	</jsp:attribute>
	
	<jsp:body>

		
		<!-- 우편번호 변경 레이어 -->
		<div id="popZipcode" style="display:none;position:absolute;top:170px;left:20px;width:100%;height:0px;z-index:10;">
			<iframe id="postIfrm" name="postIfrm" src="/myInfo/ifrmZipcode.do" frameborder="0" style="display:block;position:absolute;top:0px;left:420px;width:520px;height:510px;"></iframe>
		</div>
		


		<!-- 비밀번호 변경 레이어 -->
		<form name="pwdForm" id="pwdForm" action="/myInfo/changePwd.do" method="post">
		<div id="popPwdChange" style="display:none;position:absolute;top:170px;left:20px;width:100%;height:1px;">
			<div id="popup" style="height:240px;">
				<div class="pop_top">
					<div class="pop_x"><a href="javascript:closePopDiv('popPwdChange')"><img src="/resources/images/shopmaster/btn_popx.gif" border="0"/></a></div>
			    	<div class="pop_tit">비밀번호 변경</div>
			    </div>
			    <div class="pop_txt">
			        <table width="470" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td colspan="2" height="2" bgcolor="#222222"></td>
			          </tr>
			          <tr>
			            <td width="140" class="pop_tb_tit_l">기존 비밀번호 <font color="#ff9933">*</font></td>
			            <td width="320" class="pop_tb_nor_l"><input type="password" id="oldPwd" name="oldPwd" class="fild_st" style="width:150px;"></td>
			          </tr>
			          <tr>
			            <td width="140" class="pop_tb_tit_l">수정 비밀번호 <font color="#ff9933">*</font></td>
			            <td width="320" class="pop_tb_nor_l"><input type="password" id="newPwd" name="newPwd" class="fild_st" style="width:150px;"></td>
			          </tr>
			          <tr>
			            <td width="140" class="pop_tb_tit_l">수정 비밀번호 확인 <font color="#ff9933">*</font></td>
			            <td width="320" class="pop_tb_nor_l"><input type="password" id="newPwdRepeat" name="newPwdRepeat"  class="fild_st" style="width:150px;"></td>
			          </tr>
			        </table>
			    </div>
			    <div class="pop_btn"><a href="#" onclick="javascript:changePwd();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>&nbsp;<a href="javascript:closePopDiv('popPwdChange');"><img src="/resources/images/shopmaster/btn_cancel.gif" border="0"/></a></div>
			</div>
		</div>
		</form>
		
	
		<div id="main_r">
			<form name="mainForm" id="mainForm" action="/myInfo/joinInfoModify.do" method="post">
			<input type="hidden" name="proc" value="modify">
			
			<div class="contents_area">
	        	<div class="sub_tit">
	            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_my_info.gif" border="0"/></div>
	            	<div class="sub_now">홈 > 내정보 > <font class="sub_now_t">가입정보</font></div>
	            </div>
	            <div class="sub_tit_dot"></div>
	            <div class="sub_contents">
	            	<div style="height:22px;"><font style="font-size:14px; color:#222222; font-weight:bold">고객정보 변경</font></div>
	                <div class="tb_top"></div>
	                <div>
	                	<table width="930" border="0" cellspacing="0" cellpadding="0">
	                	  <c:if test="${member.fd_view_name==0}">
	                      <tr>
	                        <td width="120" class="tb2_tit_l">상호 (성명)</td>
	                        <td width="345" colspan="3"  class="tb2_nor_l">${member.fd_user_name}</td>
	                      </tr>
	                      </c:if>
	                      <tr>
	                        <td width="120" class="tb2_tit_l">업종</td>
	                        <td width="345" colspan="3" class="tb2_nor_l">${member.fd_business_type}</td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">일반전화번호 <font color="#ff9933">*</font></td>
	                        <td class="tb2_nor_l">
	                    		<c:if test="${!empty memberSub}">
									<c:forEach var="item" items="${memberSub}" varStatus="status" >	
										&nbsp;${item.pk_tel}<br />
									</c:forEach>
								</c:if>
							</td>
	                        <td class="tb2_tit_l">휴대전화번호 <font color="#ff9933">*</font></td>
	                        <td class="tb2_nor_l">
	                        	<input type="text" name="fd_mobile" id="fd_mobile" class="fild_st" style="width:120px;" value="${member.fd_mobile}"> <!-- 520 x 240 --><br />
	                        	<font style="font-size:11px; color:#999999;">* 예약 설정된 발송내역은 수정하기 이전 번호로 적용됩니다</font>
	                        </td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">주소 <font color="#ff9933">*</font></td>
	                        <td colspan="3" class="tb2_nor_l">
	                       		<div>
	                       	  		<input type="text" class="fild_st" id="fd_post_num1" name="fd_post_num1" value="${arrPostNum[0]}" readonly  style="width:60px;"> - 
	                       	  		<input type="text" id="fd_post_num2" name="fd_post_num2" class="fild_st" value="${arrPostNum[1]}" readonly  style="width:60px;"> <!-- 520 x 500 -->
	                       	  		<a href="javascript:openPopDiv('popZipcode')">
	                       	  			<img src="/resources/images/shopmaster/btn_postnum.gif"  border="0" align="absmiddle"/>
	                       	  		</a>
	                       		</div>
	                        	<div style="padding-top:4px;"><input type="text" id="fd_addr" name="fd_addr" class="fild_st" value="${member.fd_addr}" readonly style="width:40%;"> 
	                        	<input type="text" id="fd_addr_detail" name="fd_addr_detail" value="${member.fd_addr_detail}" class="fild_st" style="width:40%;"></div></td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">아이디</td>
	                        <td class="tb2_nor_l">${member.pk_member_id}</td>
	                        <td class="tb2_tit_l">비밀번호</td>
	                        <td class="tb2_nor_l">*********  <a href="javascript:openPopDiv('popPwdChange')"><img src="/resources/images/shopmaster/btn_pwedit.gif"  border="0" align="absmiddle"/></a></td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">이름 설정</td>
	                        <td colspan="3" class="tb2_nor_l">
	                        	보기<input type="radio" name="fd_view_name" value="0" <c:if test="${member.fd_view_name==0}">checked</c:if> > &nbsp;&nbsp;&nbsp;&nbsp;
	                        	숨김<input type="radio" value="1" name="fd_view_name" <c:if test="${member.fd_view_name==1}">checked</c:if>></td>
	                      </tr>
	                    </table>
	                </div>
	                <div style="width:930px; text-align:right; padding-top:15px;"><a href="javascript:changeInfo();"><img src="/resources/images/shopmaster/btn_ok.gif" border="0"/></a>&nbsp;<a href="/myInfo/joinInfo.do"><img src="/resources/images/shopmaster/btn_cancel.gif" border="0"/></a></div>
	            </div>
	        </div>
	        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    </div>
	    
	</jsp:body>
</layout:common>