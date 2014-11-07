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
			
			//수정화면 이동
			function callModify(){
				
				//$("#searchForm").attr("target","ifrm");
				//$("#proc").val("regist_search");
				//$("#modifyForm").attr("action","/member/memberModify.do");
				//$("#modifyForm").submit();
				
			}
		</script>
		
	</jsp:attribute>
	
	<jsp:body>



		<div id="main_r">
			<div class="contents_area">
	        	<div class="sub_tit">
	            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_my_info.gif" border="0"/></div>
	            	<div class="sub_now">홈 > 내정보 > <font class="sub_now_t">가입정보</font></div>
	            </div>
	            <div class="sub_tit_dot"></div>
	            <div class="sub_contents">
	            	<div style="height:22px;"><font style="font-size:14px; color:#222222; font-weight:bold">고객정보</font></div>
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
	                        <td class="tb2_tit_l">일반전화번호</td>
	                        <td class="tb2_nor_l">
	                        
	                    		<c:if test="${!empty memberSub}">
									<c:forEach var="item" items="${memberSub}" varStatus="status" >	
										&nbsp;${item.pk_tel}<br />
									</c:forEach>
								</c:if>
	                        
	                        </td>
	                        <td width="120" class="tb2_tit_l">휴대전화번호</td>
	                        <td width="345" class="tb2_nor_l">${member.fd_mobile}</td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">주소</td>
	                        <td colspan="3" class="tb2_nor_l">${member.fd_post_num}<br />${member.fd_addr}&nbsp;${member.fd_addr_detail}</td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">아이디</td>
	                        <td class="tb2_nor_l">${member.pk_member_id}</td>
	                        <td class="tb2_tit_l">비밀번호</td>
	                        <td class="tb2_nor_l">*********</td>
	                      </tr>
	                      <tr>
	                        <td class="tb2_tit_l">이름 설정</td>
							<td colspan="3" class="tb2_nor_l">
	                        	보기<input type="radio" name="fd_view_name" value="0" <c:if test="${member.fd_view_name==0}">checked</c:if> > &nbsp;&nbsp;&nbsp;&nbsp;
	                        	숨김<input type="radio" value="1" name="fd_view_name" <c:if test="${member.fd_view_name==1}">checked</c:if>></td>
	                      </tr>
	                    </table>
	                </div>
	                <div style="width:930px; text-align:right; padding-top:15px;"><a href="/myInfo/joinInfoModify.do" ><img src="/resources/images/shopmaster/btn_user_modify.gif" border="0"/></a></div>
	            </div>
	        </div>
	        
	        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	        
	    </div>
	    
	</jsp:body>
</layout:common>