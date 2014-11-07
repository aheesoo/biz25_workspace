<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>

<layout:common> 

	<jsp:attribute name="stylesheet">		
	</jsp:attribute>
	
	<jsp:attribute name="javascript">
	</jsp:attribute>
	
	<jsp:body>
	
		<div id="main_r">
			<div class="contents_area">
				<!-- 네비게이터 시작 -->
	        	<div class="sub_tit">
	            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_my_info.gif" border="0"/></div>
	            	<div class="sub_now">홈 &gt; 내정보 &gt; <font class="sub_now_t">코인 충전내역</font></div>
	            </div>
	        	<!-- 네비게이터 끝 -->    
	        	
	        	<div class="sub_tit_dot"></div>
	        	
	        	<!-- 컨텐츠 시작 --> 
	        	<div class="sub_contents">	        	
                <div class="tb_top"></div>
                <div>
                	<table width="930px" border="0" cellspacing="0" cellpadding="0">
	                  <!-- tr영역에 링크를 걸었는데 마우스오버시 tr 컬러변경이 되지않아 그부분은 삭제했어요. 컬러가 '#e0e6ec'로 수정부탁드립니다 -->
                      <tr>                        
                        <td width="150px" class="tb_tit_c"><font color="#000000"><a href="#" style="text-decoration:none;">날짜</a></font></td>
                        <td width="100px" class="tb_tit_c"><a href="#" class="tbt_a" style="text-decoration:none;">결제 방식</a></td>
                        <td width="100px" class="tb_tit_c">충전 코인</td>
                        <td width="100px" class="tb_tit_c">보너스 코인</td>
                        <td width="100px" class="tb_tit_c">합계 코인</td>
                        <td width="150px" class="tb_tit_c">결제 금액</td>                        
                      </tr>
                      <c:if test="${!empty coinChargeLogList}">
                      	<c:forEach var="item" items="${coinChargeLogList}" varStatus="status" >
                      		<tr>                        
		                        <td class="tb_nor_c">		                        	
		                        	<fmt:formatDate value="${item.fd_reg_date}" type="date" />
		                        </td>		                        
		                        <td class="tb_nor_c">${item.payType} </td>
		                        <td class="tb_nor_c"><fmt:formatNumber value="${item.fd_recharge_coin}" type="number"  groupingUsed="true"/>  코인</td>
		                        <td class="tb_nor_c"><fmt:formatNumber value="${item.fd_bonus_coin}" type="number"  groupingUsed="true"/>   코인</td>		                        
		                        <td class="tb_nor_c"><fmt:formatNumber value="${item.totalCoin}" type="number"  groupingUsed="true"/>  코인</td>
		                        <td class="tb_nor_c"><fmt:formatNumber value="${item.fd_pay_mount}" type="number"  groupingUsed="true"/>  원</td>                        
		                      </tr>	
                      	</c:forEach>
                      </c:if>
                       <c:if test="${empty coinChargeLogList}">
                       	<tr>
                       		<td colspan="6" align="center" class="tb_nor_c">충전 내역이 없습니다.</td>  
                       	</tr>
                       </c:if>
                      
                    </table>                    
				</div>
				
				<!-- 안내분구 시작 -->
				 <div style="width:930px; height:24px; margin-top:15px;">                	
                	<div style="float:left;width:930px; text-align:left;">
                		* 최근 6개월 이내의 코인 충전내역이 표시됩니다.                		
                	</div>
                	
                </div>
                <!-- 안내분구 끝 -->
                
				<!-- 패이징 부분 시작 -->
				<!-- 
                <div style="width:930px; height:24px; margin-top:15px;">                	
                	<div style="float:left;width:930px; text-align:center;">
                		<a href="#"><img src="/resources/images/shopmaster/list_first.gif" border="0" align="absmiddle" /></a>&nbsp;
                		<a href="#"><img src="/resources/images/shopmaster/list_priv.gif" border="0" align="absmiddle" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<font class="list_num_now">1</font>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="#" class="list_num">2</a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="#" class="list_num">3</a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="#" class="list_num">4</a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="#" class="list_num">5</a>&nbsp;&nbsp;&nbsp;&nbsp;
                		<a href="#"><img src="/resources/images/shopmaster/list_next.gif" border="0" align="absmiddle" /></a>&nbsp;
                		<a href="#"><img src="/resources/images/shopmaster/list_end.gif" border="0" align="absmiddle" /></a>
                	</div>                	
                </div>
                 -->
                <!-- 패이징 부분 끝 -->
            </div>
	        <!-- 컨텐츠 끝 -->           
	        </div>
	        
	        
	         <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	    </div>
	    
	</jsp:body>
</layout:common>