<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="layout" 	tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c"  		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" 		uri="http://java.sun.com/jsp/jstl/functions"%>
<layout:noFrame>
	<jsp:attribute name="stylesheet">
		<style>
			body, tr, td {font-size:10pt; font-family:굴림,verdana; color:#433F37; line-height:19px;}
			table, img {border:none}
			
			/* Padding ******/ 
			.pl_01 {padding:1 10 0 10; line-height:19px;}
			.pl_03 {font-size:20pt; font-family:굴림,verdana; color:#FFFFFF; line-height:29px;}
			
			/* Link ******/ 
			.a:link  {font-size:9pt; color:#333333; text-decoration:none}
			.a:visited { font-size:9pt; color:#333333; text-decoration:none}
			.a:hover  {font-size:9pt; color:#0174CD; text-decoration:underline}
			
			.txt_03a:link  {font-size: 8pt;line-height:18px;color:#333333; text-decoration:none}
			.txt_03a:visited {font-size: 8pt;line-height:18px;color:#333333; text-decoration:none}
			.txt_03a:hover  {font-size: 8pt;line-height:18px;color:#EC5900; text-decoration:underline}
		</style>
	</jsp:attribute>
	<jsp:attribute name="javascript">
		<script type="text/javascript">
				

				$(document).ready(function(){
					var openwin=window.open("childwin.html","childwin","width=299,height=149");
					openwin.close();

					if('${ResultCode}' == "00"){

						var total_coin = commaNum('${result_total_coin}');
						var recharge_coin = commaNum('${result_recharge_coin}');
						var base_coin = commaNum('${result_base_coin}');


						$(opener.document).find('#charge_total_coin').html(total_coin);
						$(opener.document).find('#charge_recharge_coin').html(recharge_coin);
						$(opener.document).find('#charge_base_coin').html(base_coin);
						/* 
						if('${call_page}' =='coin'){

							$(opener.document).find('#charge_total_coin').html(total_coin);
							$(opener.document).find('#charge_recharge_coin').html(recharge_coin);
							$(opener.document).find('#charge_base_coin').html(base_coin);
							
						
						//	opener.document.location.href = '/myInfo/myPointCharge.do';
						}else{
							

							opener.document.coinFrm.total_coin.value = total_coin;
							opener.document.coinFrm.recharge_coin.value = recharge_coin;
							opener.document.coinFrm.base_coin.value = base_coin;
								
    					   	opener.popupMethdProc1();
    					   	opener.parent.closePopDiv('popCoin');
						} */
					}
						
				});


				$(window).load(function() {
					var strWidth;
					var strHeight;
					 
					//innerWidth / innerHeight / outerWidth / outerHeight 지원 브라우저
					if ( window.innerWidth && window.innerHeight && window.outerWidth && window.outerHeight ) {
						strWidth = $('#popup').outerWidth() + (window.outerWidth - window.innerWidth);
					    strHeight = $('#popup').outerHeight() + (window.outerHeight - window.innerHeight);
					}else {
					    var strDocumentWidth = $(document).outerWidth();
					    var strDocumentHeight = $(document).outerHeight();
					    
					    window.resizeTo ( strDocumentWidth, strDocumentHeight );
					    var strMenuWidth = strDocumentWidth - $(window).width();
					    var strMenuHeight = strDocumentHeight - $(window).height();
					    strWidth = $('#popup').outerWidth() + strMenuWidth;
					    strHeight = $('#popup').outerHeight() + strMenuHeight;
					}
					
					//resize
					window.resizeTo( strWidth, strHeight );
				}); 
				
				function show_receipt(tid) // 영수증 출력
				{
					if('${ResultCode}' == "00"){
						var receiptUrl = "https://iniweb.inicis.com/DefaultWebApp/mall/cr/cm/mCmReceipt_head.jsp?noTid=${tid}&amp;noMethod=1";						
						window.open(receiptUrl,"receipt","width=430,height=700");
					}else{
						alert("해당하는 결제내역이 없습니다");
					}
				}
					
				function errhelp(){ // 상세 에러내역 출력
				
					var errhelpUrl = "http://www.inicis.com/ErrCode/Error.jsp?result_err_code=${ResultErrorCode}&amp;mid=${mid}&amp;tid=${tid}&amp;goodname=${goodname}&amp;price=${price}&amp;paymethod=${PayMethod}&amp;buyername=${buyerName}&amp;buyertel=${buyertel}&amp;buyeremail=${buyeremail}&amp;codegw=${HPP_GWCode}";
					window.open(errhelpUrl,"errhelp","width=520,height=150, scrollbars=yes,resizable=yes");
				}

				/* 숫자 콤마 처리*/
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
			
				
			<script language="JavaScript" type="text/JavaScript">
			<!--
			function MM_reloadPage(init) {  //reloads the window if Nav4 resized
			  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
			    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
			  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
			}
			MM_reloadPage(true);
			//-->
		</script>
	</jsp:attribute>
	<jsp:body>
	<div id="popup">
		<table width="520" border="0" cellspacing="0" cellpadding="0">
  			<tr>
  			 	<td height="85" background="${ background_img}" style="padding:0 0 0 64">
  			 	<!-------------------------------------------------------------------------------------------------------
				 *
				 *  아래 부분은 모든 결제수단의 공통적인 결과메세지 출력 부분입니다.
				 *
				 *  1. inipay.GetResult("ResultCode")  (결 과 코 드)
				 *  2. inipay.GetResult("ResultMsg")   (결과 메세지)
				 *  3. inipay.GetResult("PayMethod")   (결 제 수 단)
				 *  4. inipay.GetResult("TID")         (거 래 번 호)
				 *  5. inipay.GetResult("MOID")        (주 문 번 호)
				 -------------------------------------------------------------------------------------------------------->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="3%" valign="top"><img src="/resources/images/shopmaster/pg/title_01.gif" width="8" height="27" vspace="5"></td>
							<td width="97%" height="40" class="pl_03"><font color="#FFFFFF"><b>결제결과</b></font></td>
						</tr>
					</table>
				</td>
			</tr>
			 <tr> 
    			<td align="center" bgcolor="6095BC">
      				<table width="500" border="0" cellspacing="0" cellpadding="0">
      					<tr>
      						<td bgcolor="#FFFFFF" style="padding:0 0 0 56">
      							<table width="490" border="0" cellspacing="0" cellpadding="0">
      								<tr>
      									<td width="7"><img src="/resources/images/shopmaster/pg/life.gif" width="7" height="30"></td>
      									<td background="/resources/images/shopmaster/pg/center.gif"><img src="/resources/images/shopmaster/pg/icon03.gif" width="12" height="10">
							                <!-------------------------------------------------------------------------------------------------------
							                 * 1. inipay.GetResult("ResultCode") 										*	
							                 *       가. 결 과 코 드: "00" 인 경우 결제 성공[무통장입금인 경우 - 고객님의 무통장입금 요청이 완료]	*
							                 *       나. 결 과 코 드: "00"외의 값인 경우 결제 실패  						*
							                 --------------------------------------------------------------------------------------------------------> 
						                	<b>
						                  	<c:choose>
						                  		<c:when test="${ResultCode== '00' && PayMethod== 'VBank'} ">
						                  			고객님의 무통장입금 요청이 완료되었습니다.		
						                  		</c:when>
						                  		<c:when test="${ResultCode == '00'}">
						                  			고객님의 결제요청이 성공되었습니다.
						                  		</c:when>
						                  		<c:otherwise>
						                  			고객님의 결제요청이 실패되었습니다.
						                  		</c:otherwise>
						                  	</c:choose>
                  							</b>
                  						</td>
                  						<td width="8"><img src="/resources/images/shopmaster/pg/right.gif" width="8" height="30"></td>
                  					</tr>
                  				</table>
                  			</td>
                  		</tr>
                  	</table>
				</td>
			</tr>
		</table>
		<br/>
		<table width="490" border="0" cellspacing="0" cellpadding="0">
           		<tr>
              		<td width="390"  style="padding:0 0 0 9"><img src="/resources/images/shopmaster/pg/icon.gif" width="10" height="11">
              		<strong><font color="433F37">결제내역</font></strong></td>
              		<td width="100">&nbsp;</td>
              	</tr>
              	<tr>
              		<td colspan="2"  style="padding:0 0 0 23">
              			<table width="490" border="0" cellspacing="0" cellpadding="0">
              				<tr>
              					<!-------------------------------------------------------------------------------------------------------
				                 * 2. inipay.GetResult("PayMethod")
				                 *       가. 결제 방법에 대한 값
				                 *       	1. 신용카드 	- 	Card
				                 *       	2. ISP		-	VCard
				                 *       	3. 은행계좌	-	DirectBank
				                 *       	4. 무통장입금	-	VBank
				                 *       	5. 핸드폰	- 	HPP
				                 *       	6. 전화결제 (ars전화 결제)	-	Ars1588Bill
				                 *       	7. 전화결제 (받는전화결제)	-	PhoneBill
				                 *       	8. OK CASH BAG POINT		-	OCBPoint
				                 *       	9. 문화상품권			-	Culture
				                 *       	10. 틴캐시 결제 		- 	TEEN
				                 *       	11. 게임문화 상품권 		-	DGCL
				                 *       	12. 도서문화 상품권 		-	BCSH
				                 *-------------------------------------------------------------------------------------------------------->
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">결 제 방 법</td>
									<td width="343">${PayMethod}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="26">결 과 코 드</td>
									<td width="343">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>${ResultCode}</td>
												<td width='142' align='right'>
									                <!-------------------------------------------------------------------------------------------------------
									                 * 3. inipay.GetResult("ResultCode") 값에 따라 "영수증 보기" 또는 "실패 내역 자세히 보기" 버튼 출력		*
									                 *       가. 결제 코드의 값이 "00"인 경우에는 "영수증 보기" 버튼 출력					*
									                 *       나. 결제 코드의 값이 "00" 외의 값인 경우에는 "실패 내역 자세히 보기" 버튼 출력			*
									                 -------------------------------------------------------------------------------------------------------->
													<!-- 실패결과 상세 내역 버튼 출력 -->
													<c:if test="${ResultCode=='00'}">
														<a href='javascript:show_receipt();'><img src='/resources/images/shopmaster/pg/button_02.gif' width='94' height='24' border='0'></a>
													</c:if>
													<c:if test="${ResultCode!='00'}">
														<a href='javascript:errhelp();'><img src='/resources/images/shopmaster/pg/button_01.gif' width='142' height='24' border='0'></a>
													</c:if>
												</td>
											</tr>
										</table>
									</td>
								</tr>
				                
				                <!-------------------------------------------------------------------------------------------------------
				                 * 4. inipay.GetResult("ResultMsg") 										*
				                 *    - 결과 내용을 보여 준다 실패시에는 "[에러코드] 실패 메세지" 형태로 보여 준다.                     *
				                 *		예> [9121]서명확인오류									*
				                 -------------------------------------------------------------------------------------------------------->
				                 <tr>
				                 	<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
				                 </tr>
				                 <tr>
				                 	<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
				                 	<td width="109" height="25">결 과 내 용</td>
				                 	<td width="343">
				                 		<c:if test="${ResultCode=='00'}">
				                 			정상처리 되었습니다.
				                 		</c:if>
				                 		<c:if test="${ResultCode!='00'}">
				                 			<c:if test="${fn:indexOf(ResultMsg, 'TX9229') > -1}">
												결제시 이메일 주소는 필수 입니다. 이메일 주소를 입력해주세요.
											</c:if>
				                 			<c:if test="${fn:indexOf(ResultMsg, 'TX9229') <= -1}">
												${ResultMsg }
											</c:if>
				                 		</c:if>
				                 	</td>
				                 </tr>
				                 <tr>
				                 	<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
				                 </tr>
				                <!-------------------------------------------------------------------------------------------------------
				                 * 5. inipay.GetResult("tid")											*
				                 *    - 이니시스가 부여한 거래 번호 -모든 거래를 구분할 수 있는 키가 되는 값			        *
				                 -------------------------------------------------------------------------------------------------------->
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">거 래 번 호</td>
									<td width="343">${tid} </td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
			                <!-------------------------------------------------------------------------------------------------------
			                 * 6. inipay.GetResult("MOID")											*
			                 *    - 상점에서 할당한 주문번호 									*
			                 -------------------------------------------------------------------------------------------------------->
								<tr> 
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
			                      	<td width="109" height="25">주 문 번 호</td>
			                      	<td width="343">${MOID}</td>
			                    </tr>
			                    <tr> 
			                      	<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
			                    </tr>
			                <!-------------------------------------------------------------------------------------------------------
			                 * 7. inipay.GetResult("TotPrice")										*
			                 *    - 결제완료 금액                  									*
				 			 *																					*
				 			 * 결제 되는 금액 =>원상품가격과  결제결과금액과 비교하여 금액이 동일하지 않다면  *
				 			 * 결제 금액의 위변조가 의심됨으로 정상적인 처리가 되지않도록 처리 바랍니다. (해당 거래 취소 처리) *
				 			 *																									*
			                 -------------------------------------------------------------------------------------------------------->
			                 	<tr>
			                 		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
			                 		<td width="109" height="25">결제완료금액</td>
			                 		<td width="343">${TotPrice} 원</td>
			                 	</tr>
			                 	<tr>
			                 		<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
			                 	</tr>
                    			<!-- 결제 타입 카드  -->
                    			<c:if test="${(PayMethod == 'Card') ||  (PayMethod == 'VCard') }">
                    			<tr> 
                    		  		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    		  		<td width="109" height="25">신용카드번호</td>
                    		  		<td width="343">${CARD_Num}</td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    		  		<td width="109" height="25">승 인 날 짜</td>
                    		  		<td width="343">${ApplDate}</td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    		  		<td width="109" height="25">승 인 시 각</td>
                    		  		<td width="343">${ApplTime}</td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    		  		<td width="109" height="25">승 인 번 호</td>
                    		  		<td width="343">${ApplNum}</td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    		  	</tr>
                    		  	<tr>
                    		  		<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    		  		<td width="109" height="25">할 부 기 간</td>
                    		  		<td width="343">${CARD_Quota} 개월&nbsp;
	             		  				<b>
	             		  				<font color=red>${CARD_Interest} <br/>
	             		  				${CARD_Interest_Msg}</font>
	             		  	   		  	</b>
									</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
                   			 	</c:if>                     
								
								<!--   2.  은행계좌결제 결과 출력 -->					 
			                    <c:if test="${PayMethod == 'DirectBank' }">
			                    <tr>
			                    	<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
			                    	<td width="109" height="25">승 인 날 짜</td>
			                    	<td width="343">${ApplDate}</td>
			                    </tr>
			                    <tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">승 인 시 각</td>
									<td width="343">${ApplTime}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">은 행 코 드</td>
									<td width="343">${ACCT_BankCode}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">현금영수증<br>발급결과코드</td>
									<td width="343">${CSHR_ResultCode}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">현금영수증<br>발급구분코드</td>
									<td width="343">${CSHR_Type}
										<font color=red><b>(0 - 소득공제용, 1 - 지출증빙용)</b></font>
									</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
                    			</c:if>
                    
                    			<!--   3.  무통장입금 입금 예정 결과 출력 (결제 성공이 아닌 입금 예정 성공 유무)	 -->					 
                    			<c:if test="${PayMethod == 'VBank' }">
                    			<tr>
                    				<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    				<td width="109" height="25">입금계좌번호</td>
                    				<td width="343">${VACT_Num}</td>
                    			</tr>
                    			<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">입금 은행코드</td>
									<td width="343">${VACT_BankCode}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">예금주 명</td>
									<td width="343">${VACT_Name}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">송금자 명</td>
									<td width="343">${VACT_InputName}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">상품 주문번호</td>
									<td width="343">${MOID}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">송금 일자</td>
									<td width="343">${VACT_Date}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
								<tr>
									<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
									<td width="109" height="25">송금 시간</td>
									<td width="343">${VACT_Time}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
								</tr>
                    			</c:if>
                    			<!--    4.  핸드폰 결제 	 -->					 
                    			<c:if test="${PayMethod == 'HPP' }">
                    			<tr>
                    				<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    				<td width="109" height="25">휴대폰번호</td>
                    				<td width="343">${HPP_Num}</td>
                    			</tr>
                    			<tr>
                    				<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    			</tr>
                    			<tr>
                    				<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    				<td width="109" height="25">승 인 날 짜</td>
                    				<td width="343">${ApplDate}</td>
                    			</tr>
                    			<tr>
                    				<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    			</tr>
                    			<tr>
                    				<td width="18" align="center"><img src="/resources/images/shopmaster/pg/icon02.gif" width="7" height="7"></td>
                    				<td width="109" height="25">승 인 시 각</td>
                    				<td width="343">${ApplTime}</td>
								</tr>
								<tr>
									<td height="1" colspan="3" align="center"  background="/resources/images/shopmaster/pg/line.gif"></td>
                    			</tr>
                    			</c:if>
                    		</table>
                    	</td>
                    </tr>
				</table>
			</div>
	</jsp:body>
</layout:noFrame>