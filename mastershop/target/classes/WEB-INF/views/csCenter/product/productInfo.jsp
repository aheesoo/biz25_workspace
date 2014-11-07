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
        	<div class="sub_tit">
            	<div style="float:left;"><img src="/resources/images/shopmaster/tit_cs_center.gif" border="0"/></div>
            	<div class="sub_now">홈 &gt; 고객센터 > <font class="sub_now_t">상품안내</font></div>
            </div>
            <div class="sub_tit_dot"></div>
            <div class="sub_contents">                       	  
                <div style="height:24px;">
                    <div style="float:left; margin-top:2px;"><font style="font-size:14px; color:#222222; font-weight:bold">상품구성</font></div>
                    <div style="float:right; padding-top:5px;">부가세 별도</div>
                </div>
                <div class="tb_top"></div>
                <div>
                	<table width="930" border="0" cellspacing="0" cellpadding="0">
                    	<tr>
	                        <td width="150" class="tb_tit_c">구분</td>
	                        <td width="150" class="tb_tit_c">kt 통화OPEN API 이용료</td>
	                        <td width="150" class="tb_tit_c">마스터 샵 이용료</td>
	                        <td width="150" class="tb_tit_c">합계</td>
	                        <td class="tb_tit_c">비고</td>
                      	</tr>
                      	<tr>
	                        <td class="tb_nor_c">월정액</td>
	                        <td class="tb_nor_c">4,000 원</td>
	                        <td class="tb_nor_c">6,000 원</td>
	                        <td class="tb_nor_c">10,000 원</td>
	                        <td class="tb_nor_c">기본 400 Coin 제공 / 이월없음</td>
                      	</tr>
                    </table>
        		</div>
              	<div style="height:24px; margin-top:40px;"><font style="font-size:14px; color:#222222; font-weight:bold">선불충전</font></div>
              	<div class="tb_top"></div>
                	<div>
                		<table width="930" border="0" cellspacing="0" cellpadding="0">
                      		<tr>
		                        <td width="130" class="tb_tit_c">구분</td>
		                        <td width="140" class="tb_tit_c">충전금액</td>
		                        <td width="140" class="tb_tit_c">충전 Coin</td>
		                        <td width="150" class="tb_tit_c">보너스 Coin</td>
		                        <td width="150" class="tb_tit_c">합계</td>
                      		</tr>
		                    <tr>
		                        <td class="tb_nor_c">A형</td>
		                        <td class="tb_nor_c">10,000 원</td>
		                        <td class="tb_nor_c">1,000 Coin</td>
		                        <td class="tb_nor_c">0 Coin</td>
		                        <td class="tb_nor_c">1,000 Coin</td>
		                        
		                  	</tr>
		                	<tr>
		                        <td class="tb_nor_c">B형</td>
		                        <td class="tb_nor_c">30,000 원</td>
		                        <td class="tb_nor_c">3,000 Coin</td>
		                        <td class="tb_nor_c">30 Coin</td>
		                        <td class="tb_nor_c">3,030 Coin</td>
		                        
                      		</tr>
                      		<tr>
		                        <td class="tb_nor_c">C형</td>
		                        <td class="tb_nor_c">50,000 원</td>
		                        <td class="tb_nor_c">5,000 Coin</td>
		                        <td class="tb_nor_c">100 Coin</td>
		                        <td class="tb_nor_c">5,100 Coin</td>
		                        
                      		</tr>
                      		<tr>
		                        <td class="tb_nor_c">D형</td>
		                        <td class="tb_nor_c">100,000 원</td>
		                        <td class="tb_nor_c">10,000 Coin</td>
		                        <td class="tb_nor_c">500 Coin</td>
		                        <td class="tb_nor_c">10,500 Coin</td>
		                        
                      		</tr>
                    	</table>
                	</div>                       	  
	                <div style="height:24px; margin-top:40px;">
	                    <div style="float:left; margin-top:2px;"><font style="font-size:14px; color:#222222; font-weight:bold">문자발송 요금</font></div>
	                    <div style="float:right; padding-top:5px;">부가세 별도</div>
	                </div>

                	<div class="tb_top"></div>
               		<div>
               			<table width="930" border="0" cellspacing="0" cellpadding="0">
                     			<tr>
		                        <td width="150" class="tb_tit_c">구분</td>
		                        <td width="170" class="tb_tit_c">SMS</td>
		                        <td width="170" class="tb_tit_c">LMS</td>
		                        <td width="170" class="tb_tit_c">MMS</td>
		                        <td class="tb_tit_c">비고</td>
                     			</tr>
                     			<tr>
		                        <td class="tb_nor_c">Coin</td>
		                        <td class="tb_nor_c">2 Coin</td>
		                        <td class="tb_nor_c">5 Coin</td>
		                        <td class="tb_nor_c">20 Coin</td>
		                        <td rowspan="2" class="tb_nor_c">1 Coin = 10 원</td>
                     			</tr>
                    			<tr>
		                        <td class="tb_nor_c">금액</td>
		                        <td class="tb_nor_c">20 원</td>
		                        <td class="tb_nor_c">50 원</td>
		                        <td class="tb_nor_c">200 원</td>
                     			</tr>
                   		</table>
               		</div>
           		</div>
       		</div>
       		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		</div>
	    
	</jsp:body>
</layout:common>