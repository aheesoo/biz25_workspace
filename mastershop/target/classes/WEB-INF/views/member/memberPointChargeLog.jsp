<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
		<script src="/resources/js/jquery-1.11.0.js"></script>
		<script langauge="javascript">
		 $(document).ready(function(){
	
		 }); 

		</script>
	</head>
	<body>
		<table width="100%" style="BORDER: none;">
			<tr>
				<td style="text-align:left">&nbsp;포인트 충전내역</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="BORDER: #EDCE9B  1px solid; padding:0 0 0 0">
						<tr height="0">
							<td width="10%"></td>
							<td width="18%"></td>				
							<td width="18%"></td>
							<td width="18%"></td>
							<td width="18%"></td>
							<td width="18%"></td>
						</tr>	
						<tr height="25">
							<td class="item_title_border">&nbsp;날 짜</td>
							<td class="item_title_border">&nbsp;결제 방식</td>
							<td class="item_title_border">&nbsp;충전 포인트</td>
							<td class="item_title_border">&nbsp;보너스 포인트</td>
							<td class="item_title_border">&nbsp;합계 포인트</td>
							<td class="item_title">&nbsp;결제 금액</td>
						</tr>
						<c:if test="${!empty contents_list}">
							<c:forEach var="contents" items="${contents_list}" varStatus="status" >
								<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>				
								<tr height="25">
									<td class="item_input_border" align="center">&nbsp;
										<fmt:parseDate value="${contents.fd_reg_date}" var="dateFmt" pattern="yyyyMMdd"/>
										<fmt:formatDate value="${dateFmt}"  pattern="yyyy년 MM월 dd일"/>
									</td>
									<td class="item_input_border" align="center">&nbsp;
										<c:choose>
											<c:when test="${contents.fd_pay_type == '1'}">휴대폰</c:when>
											<c:when test="${contents.fd_pay_type == '2'}">신용카드</c:when>
											<c:when test="${contents.fd_pay_type == '3'}">계좌이체</c:when>
											<c:when test="${contents.fd_pay_type == '4'}">무통장입금</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</td>
									<td class="item_input_border" align="center">&nbsp;
										<fmt:formatNumber value="${contents.fd_recharge_coin}" type="number"/>&nbsp;Coin
									</td>
									<td class="item_input_border" align="center">&nbsp;
										<fmt:formatNumber value="${contents.fd_bonus_coin}" type="number"/>&nbsp;Coin
									</td>
									<td class="item_input_border" align="center">&nbsp;
										<fmt:formatNumber value="${contents.sum_coin}" type="number"/>&nbsp;Coin
									</td>
									<td class="item_input" align="center">&nbsp;
										<fmt:formatNumber value="${contents.fd_pay_mount}" type="number"/>&nbsp;원
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty contents_list}">
							<tr height="1"><td colspan="6" bgcolor="#F2DAB4"></td></tr>
							<tr height="60" bgcolor="white"> 	
								<td class="item_input" align="center" colspan="8">&nbsp;검색된 데이터가 없습니다</td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>
			<tr><td colspan="3" style="height:10px"></td></tr>
			<tr>
				<td style="text-align:left">&nbsp;* 최근 6개월 이내의 포인트 충전내역이 표시됩니다.</td>
			</tr>
		</table>
	</body>
</html>